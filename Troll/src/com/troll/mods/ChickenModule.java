package com.troll.mods;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import com.troll.man.Advert;
import com.troll.start.Start;

public class ChickenModule extends Module {
	private ArrayList<Player> players;
	private BukkitTask task = null;
	
	public ChickenModule() {
		super("Chicken", "Leotendo", "Spawnt eine Hühner-Arme");
		
		this.players = new ArrayList<>();
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #chicken <player>");
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
		if (!this.players.contains(target)) {
			this.players.add(target);
			
			p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler hat nun alles voller Hühner!");
			task = Bukkit.getScheduler().runTaskTimer(Start.getInstance(), new Runnable() {
				
				@Override
				public void run() {
					target.getWorld().spawnEntity(new Location(target.getWorld(), target.getLocation().getX(), target.getLocation().getY() + 15,
							target.getLocation().getZ()), EntityType.CHICKEN);
					
					target.playSound(target.getLocation(), Sound.ANVIL_LAND, 5, 5);
				}
			}, 5L, 5L);
			
			return true;
		}
		
		this.players.remove(target);
		p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler ist nun wieder alleine");

		task.cancel();
		
		return true;
	}
}
