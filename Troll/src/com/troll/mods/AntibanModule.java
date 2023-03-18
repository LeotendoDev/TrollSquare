package com.troll.mods;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.troll.start.Start;

public class AntibanModule extends Module implements Listener {
	public boolean isActive = false;
	
	public AntibanModule() {
		super("Antiban", "Leotendo", "Es können keine Spieler mehr gebannt werden");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		isActive = !isActive;
		if (isActive)
			p.sendMessage(Start.getInstance().getPrefix() + "§aNun kann keiner mehr gebannt werden");
		else
			p.sendMessage(Start.getInstance().getPrefix() + "§aNun können Spieler wieder gebannt werden");
		return true;
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		
	}
}
