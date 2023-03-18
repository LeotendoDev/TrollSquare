package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.troll.start.Start;

public class SpongeWalkModule extends Module implements Listener {
	private Player victim;
	private boolean isActive = false;
	
	public SpongeWalkModule() {
		super("SpongeWalk", "Leotendo", "Spawnt Sponge-Linie hinter dem Spieler");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #spongewalk <player>");
			return false;
		}
		
		Player victim = Bukkit.getPlayer(args[1]);
		if (victim == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler §e" + args[1] + "§c ist nicht online!");
			return false;
		}
		
		isActive = !isActive;
		if (isActive)
			p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler §e" + victim.getName() + " §aspawnt nun eine Schwäme");
		else
			p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler §e" + victim.getName() + " §aspawnt nun keine Schwäme mehr");
		
		this.victim = victim;
		
		p.setAllowFlight(true);
		
		return true;
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (p == victim && isActive) {
			new Location(victim.getWorld(), victim.getLocation().getBlockX()+1, victim.getLocation().getY()-1, victim.getLocation().getBlockZ()+1).getBlock().setType(Material.SPONGE);
		}
	}
}
