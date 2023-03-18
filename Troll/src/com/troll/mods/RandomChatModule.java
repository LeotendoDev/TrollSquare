package com.troll.mods;

import java.util.Collections;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.google.common.primitives.Chars;
import com.troll.start.Start;

public class RandomChatModule extends Module implements Listener {
	private boolean isActive = false;

	public RandomChatModule() {
		super("RandomChat", "Leotendo", "Verschlüssel den Chat");
	}
	
	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 1) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #randomchat");
			return false;
		}
		
		isActive = !isActive;
		if (isActive) {
			p.sendMessage(Start.getInstance().getPrefix() + "§aDer Chat ist nun verschlüsselt");
		} else {
			p.sendMessage(Start.getInstance().getPrefix() + "§aDer Chat ist nun wieder entschlüsselt");
		}

		return true;
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if (isActive && !e.getMessage().startsWith(Start.getInstance().getCommandPrefix())) {
			List<Character> msg = Chars.asList(e.getMessage().toCharArray());
			Collections.shuffle(msg);
			
			String cypher = "";
			for (Character c : msg) {
				cypher += c.charValue();
			}
			
			e.setMessage(cypher);
		}
	}
}
