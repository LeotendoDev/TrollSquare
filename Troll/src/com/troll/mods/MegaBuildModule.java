package com.troll.mods;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.troll.start.Start;

public class MegaBuildModule extends Module implements Listener {
	private boolean isActive = false;
	private Player executor;
	
	public MegaBuildModule() {
		super("Megabuild", "Leotendo", "Lässt die Spieler im 3x3 Format bauen/abbauen");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 1) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #megabuild");
			return false;
		}
		
		isActive = !isActive;
		if (isActive) {
			p.sendMessage(Start.getInstance().getPrefix() + "§aDie Spieler bauen nun im 3x3 Format");
			return true;
		}
		
		p.sendMessage(Start.getInstance().getPrefix() + "§aDie Spieler bauen nun wieder normal");
		
		this.executor = p;
		
		return true;
	}
	
	private void placeMegaBlock(Player p, Location loc, Material mat) {
		// Layer 1
		p.getWorld().getBlockAt(loc.getBlockX()-1, loc.getBlockY(), loc.getBlockZ()).setType(mat);;
		p.getWorld().getBlockAt(loc.getBlockX()+1, loc.getBlockY(), loc.getBlockZ()).setType(mat);
		p.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()+1).setType(mat);	
		p.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()-1).setType(mat);
		p.getWorld().getBlockAt(loc.getBlockX()+1, loc.getBlockY(), loc.getBlockZ()+1).setType(mat);
		p.getWorld().getBlockAt(loc.getBlockX()-1, loc.getBlockY(), loc.getBlockZ()+1).setType(mat);
		p.getWorld().getBlockAt(loc.getBlockX()+1, loc.getBlockY(), loc.getBlockZ()-1).setType(mat);
		p.getWorld().getBlockAt(loc.getBlockX()-1, loc.getBlockY(), loc.getBlockZ()-1).setType(mat);
		
		// Layer 2
		p.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()+1, loc.getBlockZ()).setType(mat);
		p.getWorld().getBlockAt(loc.getBlockX()+1, loc.getBlockY()+1, loc.getBlockZ()).setType(mat);
		p.getWorld().getBlockAt(loc.getBlockX()-1, loc.getBlockY()+1, loc.getBlockZ()).setType(mat);
		p.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()+1, loc.getBlockZ()+1).setType(mat);	
		p.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()+1, loc.getBlockZ()-1).setType(mat);
		p.getWorld().getBlockAt(loc.getBlockX()+1, loc.getBlockY()+1, loc.getBlockZ()+1).setType(mat);
		p.getWorld().getBlockAt(loc.getBlockX()-1, loc.getBlockY()+1, loc.getBlockZ()+1).setType(mat);
		p.getWorld().getBlockAt(loc.getBlockX()+1, loc.getBlockY()+1, loc.getBlockZ()-1).setType(mat);
		p.getWorld().getBlockAt(loc.getBlockX()-1, loc.getBlockY()+1, loc.getBlockZ()-1).setType(mat);

		// Layer 3
		p.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()+2, loc.getBlockZ()).setType(mat);
		p.getWorld().getBlockAt(loc.getBlockX()+1, loc.getBlockY()+2, loc.getBlockZ()).setType(mat);
		p.getWorld().getBlockAt(loc.getBlockX()-1, loc.getBlockY()+2, loc.getBlockZ()).setType(mat);
		p.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()+2, loc.getBlockZ()+1).setType(mat);	
		p.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()+2, loc.getBlockZ()-1).setType(mat);
		p.getWorld().getBlockAt(loc.getBlockX()+1, loc.getBlockY()+2, loc.getBlockZ()+1).setType(mat);
		p.getWorld().getBlockAt(loc.getBlockX()-1, loc.getBlockY()+2, loc.getBlockZ()+1).setType(mat);
		p.getWorld().getBlockAt(loc.getBlockX()+1, loc.getBlockY()+2, loc.getBlockZ()-1).setType(mat);
		p.getWorld().getBlockAt(loc.getBlockX()-1, loc.getBlockY()+2, loc.getBlockZ()-1).setType(mat);
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();

		if (!(isActive && (p != executor)))
			return;
		
		this.placeMegaBlock(p, e.getBlock().getLocation(), e.getBlock().getType());
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		
		if (!(isActive && (p != executor)))
			return;
		
		this.placeMegaBlock(p, e.getBlock().getLocation(), Material.AIR);
	}
}
