package com.troll.mods;

import java.util.Random;

import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import com.troll.start.Start;

public class RandomPlaceModule extends Module implements Listener {
	private boolean isActive = false;
	private Player executor;
	
	public RandomPlaceModule() {
		super("Randomplace", "Leotendo", "Die Spieler setzen zufällige Blöcke");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 1) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #randomplace");
			return false;
		}
		
		isActive = !isActive;
		if (isActive) {
			p.sendMessage(Start.getInstance().getPrefix() + "§aDie Spieler setzt nun zufällige Blöcke");
			return true;
		}
		
		p.sendMessage(Start.getInstance().getPrefix() + "§aDie Spieler setzt nun wieder gewollte Blöcke");
		
		this.executor = p;
		
		return true;
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		
		if (!isActive) return;
		if (p == executor) return;
		
		int rand = new Random().nextInt(100);
		
		e.getBlock().setTypeId(rand);
	}
}
