package com.troll.mods;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.troll.start.Start;

public class InvisibleBlockModule extends Module implements Listener {
	private ArrayList<UUID> targets;
	
	public InvisibleBlockModule() {
		super("Invisibleblock", "Leotendo", "Lasse die Blöcke des Spielers verschwinden");
	
		this.targets = new ArrayList<>();
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #invisibleblock <player>");
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
		UUID uuid = target.getUniqueId();
		
		if (this.targets.contains(uuid)) {
			this.targets.remove(uuid);
			p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler setzt nun wieder sichtbare Blöcke");
			return true;
		}
		
		this.targets.add(uuid);
		p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler setzt nun unsichtbare Blöcke");
		
		return true;
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (!this.targets.contains(e.getPlayer().getUniqueId()))
			return;
		
		e.getBlock().setType(Material.BARRIER);
	}
}
