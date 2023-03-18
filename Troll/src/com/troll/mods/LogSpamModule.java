package com.troll.mods;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import com.troll.start.Start;

public class LogSpamModule extends Module implements Runnable {
	private boolean isActive;
	private BukkitTask task = null;
	private String msg = "";
	
	public LogSpamModule() {
		super("Logspam", "Leotendo", "Spamme eine Nachricht in die Konsole!");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length < 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #logspam <message>");
			return true;
		}
		
		isActive = !isActive;
		if (isActive) {
			p.sendMessage(Start.getInstance().getPrefix() + "§aDie Nachricht wird nun in die Konsole gespammt");

			for (int i = 1; i < args.length; i++) {
				msg += args[i] + " ";
			}
			
			task = Bukkit.getScheduler().runTaskTimer(Start.getInstance(), this, 5L, 5L);
			
			return true;
		} else {
			p.sendMessage(Start.getInstance().getPrefix() + "§aDie Konsole ist nun wieder clean");
			task.cancel();

			try {
				((CraftServer)Bukkit.getServer()).getReader().clearScreen();
			} catch (IOException e) {
			}
		}
		
		return true;
	}

	@Override
	public void run() {
		Bukkit.getLogger().info(msg);
	}
}
