package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import com.troll.start.Start;

public class TimeSpamModule extends Module implements Runnable {
	private final int DAY = 14000, NIGHT = 0;
	private BukkitTask task = null;
	private boolean isActive = false;
	private int counter = 0;
	private Player player;
	
	public TimeSpamModule() {
		super("Timespam", "Leotendo", "Spame Tag und Nacht!");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		this.player = p;
		
		isActive = !isActive;
		if (isActive) {
			p.sendMessage(Start.getInstance().getPrefix() + "§aBeim Spieler wird nun konstant Tag und Nacht gesetzt");
			
			task = Bukkit.getScheduler().runTaskTimer(Start.getInstance(), this, 5L, 5L);
			
			return true;
		}
		
		p.sendMessage(Start.getInstance().getPrefix() + "§aBeim Spieler ist nun wieder nur eine Zeit aktiv");
		task.cancel();
		
		return true;
	}

	@Override
	public void run() {
		counter++;
		if (counter % 2 == 0)
			this.player.getWorld().setTime(DAY);
		else
			this.player.getWorld().setTime(NIGHT);
	}
}
