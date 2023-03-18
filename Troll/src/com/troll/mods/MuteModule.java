package com.troll.mods;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.troll.start.Start;

public class MuteModule extends Module implements Listener {
	private ArrayList<UUID> mutedPlayers;

	public MuteModule() {		
		super("Mute", "Leotendo", "Mutet den Spieler");
		
		this.mutedPlayers = new ArrayList<UUID>();
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #mute <player>");
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
		UUID uuid = target.getUniqueId();
		
		if (mutedPlayers.contains(uuid)) {
			p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler ist jetzt nicht mehr gemutet");
			mutedPlayers.remove(uuid);
			return true;
		}
		
		p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler ist jetzt gemutet");
		mutedPlayers.add(uuid);
		
		return true;
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if (mutedPlayers.contains(e.getPlayer().getUniqueId()))
			e.setCancelled(true);
	}
}
