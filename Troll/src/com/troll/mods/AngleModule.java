package com.troll.mods;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import com.troll.start.Start;

public class AngleModule extends Module implements Listener {
	private ArrayList<Player> players;
	
	public AngleModule() {
		super("Angle", "Leotendo", "Lass den Spieler schnell wie Licht werden");
		
		this.players = new ArrayList<Player>();
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #angle <player>");
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}

		if (this.players.contains(target)) {
			this.players.remove(target);
			p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler ist nun wieder ein Spieler");
			
			target.setVelocity(new Vector(0, 0, 0));
			
			return true;
		}
		
		this.players.add(target);
		p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler ist nun ein Engel");
		
		return true;
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (this.players.contains(e.getPlayer())) e.getPlayer().setVelocity(new Vector(0, 1, 1));
	}
}
