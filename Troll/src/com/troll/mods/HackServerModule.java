package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import com.troll.start.Start;

public class HackServerModule extends Module implements Runnable {
	private int percent = 0;
	private BukkitTask task;
	
	public HackServerModule() {
		super("Hackserver", "Leotendo", "Hacke den Server!");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		p.sendMessage(Start.getInstance().getPrefix() + "§aDer Server wird jetzt gehackt!");
		
		task = Bukkit.getScheduler().runTaskTimer(Start.getInstance(), this, 3L, 3L);
		
		return true;
	}

	@Override
	public void run() {
		if (percent < 100) {
			percent++;
			Bukkit.getOnlinePlayers().forEach(user -> user.sendMessage(ChatColor.LIGHT_PURPLE + "[Server] Downloading VIRUS_29181.exe: " + percent + "%"));
			return;
		}		
		
		task.cancel();
	}
}
