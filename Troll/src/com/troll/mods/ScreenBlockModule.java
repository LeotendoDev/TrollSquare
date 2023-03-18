package com.troll.mods;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import com.troll.start.Start;

public class ScreenBlockModule extends Module {
	private ArrayList<Player> players;
	private BukkitTask task = null;
	
	public ScreenBlockModule() {
		super("Screenblock", "Leotendo", "Blockiere den Bildschirm des Spielers!");
		
		this.players = new ArrayList<>();
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #screenblock <player>");
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
		if (this.players.contains(target)) {
			this.players.remove(target);
			p.sendMessage(Start.getInstance().getPrefix() + "§aDer Bildschrim des Spieler ist nun wieder freigegeben");
			
			task.cancel();
			
			return true;
		}

		task = Bukkit.getScheduler().runTaskTimer(Start.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				target.openInventory(p.getInventory());
				target.closeInventory();
			}
		}, 1L, 1L);
		
		this.players.add(target);
		p.sendMessage(Start.getInstance().getPrefix() + "§aDer Bildschrim des Spieler ist nun blockiert");
		
		return true;
	}
}
