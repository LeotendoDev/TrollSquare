package com.troll.mods;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.troll.start.Start;

public class CockBuildModule extends Module implements Listener {
	private boolean isActive = false;
	private Player executor;
	
	public CockBuildModule() {
		super("Cockbuild", "Leotendo", "Lässt die Spieler im Cock Format bauen");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 1) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #megabuild");
			return false;
		}
		
		isActive = !isActive;
		if (isActive) {
			p.sendMessage(Start.getInstance().getPrefix() + "§aDie Spieler bauen nun im Cock Format");
			return true;
		}
		
		p.sendMessage(Start.getInstance().getPrefix() + "§aDie Spieler bauen nun wieder normal");
		
		this.executor = p;
		
		return true;
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();

		if (!isActive) return;
		if (p == executor) return;
		
		Location loc = e.getBlock().getLocation();
		
		// Bottom
		p.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()).setType(Material.WOOL);
		p.getWorld().getBlockAt(loc.getBlockX()+1, loc.getBlockY(), loc.getBlockZ()).setType(Material.WOOL);
		p.getWorld().getBlockAt(loc.getBlockX()-1, loc.getBlockY(), loc.getBlockZ()).setType(Material.WOOL);
		
		// Top
		p.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()+1, loc.getBlockZ()).setType(Material.WOOL);
		p.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()+2, loc.getBlockZ()).setType(Material.WOOL);
		p.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()+3, loc.getBlockZ()).setType(Material.WOOL);
		p.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()+4, loc.getBlockZ()).setType(Material.REDSTONE_BLOCK);
	}
}
