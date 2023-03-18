package com.troll.mods;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;

import com.troll.start.Start;

public class UnknownServerModule extends Module implements Listener {
	private ArrayList<UUID> players;
	
	public UnknownServerModule() {
		super("Unknownserver", "Leotendo", "Lass den Spieler denken, dass der Server nicht mehr exestiert!");
		
		this.players = new ArrayList<>();
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #unknownserver <player>");
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
		UUID uuid = p.getUniqueId();
		if (!this.players.contains(uuid)) {
			p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler denkt nun der Server existiert nicht mehr");
			this.players.add(uuid);
			return true;
		}
		
		this.players.remove(uuid);
		p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler denkt nun der Server existiert wieder");
		
		return true;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onLogin(PlayerPreLoginEvent e) {
		if (this.players.contains(e.getUniqueId())) {
			e.disallow(null, "Unknown host");
		}
	}
}
