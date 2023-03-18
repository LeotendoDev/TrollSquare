package com.troll.mods;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import com.troll.start.Start;

public class RocketModule extends Module {
	private ArrayList<Player> players;
	private BukkitTask task = null;
	
	public RocketModule() {
		super("Rocket", "Leotendo", "Schieße den Spieler auf eine Reise :)");
		
		this.players = new ArrayList<>();
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #rocket <player>");
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
		if (this.players.contains(target)) {
			this.players.remove(target);
			task.cancel();
			
			p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler ist nun wieder auf Planet-Erde");
			return true;
		}
		
		this.players.add(target);
		p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler ist nun eine Rakete!");
		
		task = Bukkit.getScheduler().runTaskTimer(Start.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				target.setVelocity(new Vector(0, 1, 1));
				target.getWorld().getBlockAt(target.getLocation()).setType(Material.LAVA);
			}
		}, 30L, 30L);
		
		return true;
	}
}
