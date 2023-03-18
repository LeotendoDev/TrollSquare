package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.troll.start.Start;

public class ChatModule extends Module implements Listener {
	
	public ChatModule() {
		super("Chat", "Leotendo", "Schreibe Nachrichten als andere Spieler");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length < 3) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #chat <player> <msg>");
			return false;
		}
		
		Player victim = Bukkit.getPlayer(args[1]);
		if (victim == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
		String msg = "";
		for (int i = 2; i < args.length; i++) {
			msg += args[i] + " ";
		}
		
		victim.chat(msg);
		
		return true;
	}
}
