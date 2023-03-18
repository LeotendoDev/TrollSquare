package com.troll.mods;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.troll.start.Start;

public class JailModule extends Module {
	private ArrayList<Player> players;
	
	public JailModule() {
		super("Jail", "Leotendo", "Sperre den Spieler im Gefängnis ein");
		
		this.players = new ArrayList<>();
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #jail <player>");
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}

		if (!players.contains(target)) {
			p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler ist jetzt gefangen!");
			players.add(target);
			
			Bukkit.getScheduler().runTask(Start.getInstance(), new Runnable() {
				
				@Override
				public void run() {
					Location loc = target.getLocation();
					
					// Ground Layer
					target.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()-1, loc.getBlockZ()).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX()+1, loc.getBlockY()-1, loc.getBlockZ()).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX()-1, loc.getBlockY()-1, loc.getBlockZ()).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()-1, loc.getBlockZ()+1).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()-1, loc.getBlockZ()-1).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX()+1, loc.getBlockY()-1, loc.getBlockZ()+1).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX()-1, loc.getBlockY()-1, loc.getBlockZ()+1).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX()+1, loc.getBlockY()-1, loc.getBlockZ()-1).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX()-1, loc.getBlockY()-1, loc.getBlockZ()-1).setType(Material.BEDROCK);
					
					// Walls 1
					target.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()+1).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()-1).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX()+1, loc.getBlockY(), loc.getBlockZ()).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX()-1, loc.getBlockY(), loc.getBlockZ()).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX()+1, loc.getBlockY(), loc.getBlockZ()+1).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX()-1, loc.getBlockY(), loc.getBlockZ()+1).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX()+1, loc.getBlockY(), loc.getBlockZ()-1).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX()-1, loc.getBlockY(), loc.getBlockZ()-1).setType(Material.BEDROCK);
					
					// Walls 2
					target.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()+1, loc.getBlockZ()+1).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()+1, loc.getBlockZ()-1).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX()+1, loc.getBlockY()+1, loc.getBlockZ()).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX()-1, loc.getBlockY()+1, loc.getBlockZ()).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX()+1, loc.getBlockY()+1, loc.getBlockZ()+1).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX()-1, loc.getBlockY()+1, loc.getBlockZ()+1).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX()+1, loc.getBlockY()+1, loc.getBlockZ()-1).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX()-1, loc.getBlockY()+1, loc.getBlockZ()-1).setType(Material.BEDROCK);
					
					// Walls 3
					target.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()+2, loc.getBlockZ()+1).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()+2, loc.getBlockZ()-1).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX()+1, loc.getBlockY()+2, loc.getBlockZ()).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX()-1, loc.getBlockY()+2, loc.getBlockZ()).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX()+1, loc.getBlockY()+2, loc.getBlockZ()+1).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX()-1, loc.getBlockY()+2, loc.getBlockZ()+1).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX()+1, loc.getBlockY()+2, loc.getBlockZ()-1).setType(Material.BEDROCK);
					target.getWorld().getBlockAt(loc.getBlockX()-1, loc.getBlockY()+2, loc.getBlockZ()-1).setType(Material.BEDROCK);
				}
			});
			
		} else {
			p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler ist jetzt wieder befreit!");
			players.remove(target);
		}
		
		return false;
	}
}
