package com.troll.mods;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.troll.start.Start;

public class FreezeModule extends Module implements Listener {
	private ArrayList<Player> frozenPlayers;
	
	public FreezeModule() {
		super("Freeze", "Leotendo", "Friere den Spieler ein");
	
		this.frozenPlayers = new ArrayList<Player>();
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #freeze <player>");
			return false;
		}
		
		Player victim = Bukkit.getPlayer(args[1]);
		if (victim == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
			
		frozenPlayers.add(victim);
		p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler kann sich nicht mehr bewegen");
		
		return true;
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (frozenPlayers.contains(p)) {
			e.setCancelled(true);
			p.teleport(e.getFrom());
		}
	}
	
	public ArrayList<Player> getFrozenPlayers() {
		return this.frozenPlayers;
	}
}
