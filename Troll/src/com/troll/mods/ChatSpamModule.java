package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import com.troll.start.Start;

public class ChatSpamModule extends Module implements Runnable {
	private boolean isActive = false;
	private BukkitTask task = null;
	private String message = "";
	
	public ChatSpamModule() {
		super("Chatspam", "Leotendo", "Spamme den Chat mit einer Nachricht voll");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length < 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #chatspam <message>");
			return false;
		}
		
		for (int i = 1; i < args.length; i++) {
			this.message += args[i] + " ";
		}
		
		isActive = !isActive;
		if (isActive) {
			p.sendMessage(Start.getInstance().getPrefix() + "§aDie Nachricht wird nun in den Chat gespammt");
			task = Bukkit.getScheduler().runTaskTimer(Start.getInstance(), this, 5L, 5L);
			return true;
		}
		
		task.cancel();
		p.sendMessage(Start.getInstance().getPrefix() + "§aDer Chat ist nun wieder normal");
		
		return true;
	}

	@Override
	public void run() {
		Bukkit.getOnlinePlayers().forEach(user -> user.sendMessage(this.message));
	}
}
