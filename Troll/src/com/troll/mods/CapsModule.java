package com.troll.mods;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.troll.start.Start;

public class CapsModule extends Module implements Listener {
	private boolean isActive = false;

	public CapsModule() {
		super ("Caps", "Leotendo", "Der ganze Chat ist in CAPS!");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 1) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #caps");
			return false;
		}
		
		isActive = !isActive;
		if (isActive) {
			p.sendMessage(Start.getInstance().getPrefix() + "§aDer Chat ist nun sehr agressiv :)");
		} else {
			p.sendMessage(Start.getInstance().getPrefix() + "§aDer Chat ist nun wieder normal");
		}

		return true;
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if (isActive && !e.getMessage().startsWith(Start.getInstance().getCommandPrefix())) {
			e.setMessage(e.getMessage().toUpperCase() + "!");
		}
	}
}
