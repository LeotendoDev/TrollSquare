package com.troll.utils;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.troll.start.Start;

public class ScoreboardUtil {
	private Start plugin;
	
	public ScoreboardUtil(Start plugin) {
		this.plugin = plugin;
	}
	
	public Scoreboard create() {
		ScoreboardManager mgr = Bukkit.getScoreboardManager();
		Scoreboard board = mgr.getNewScoreboard();
		Objective obj = board.registerNewObjective("board", "board");
		
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName("§6§lTroll§bSquare§c§l1§5");
		
		obj.getScore(" ").setScore(10);
		obj.getScore("§7>> §dTrusted Players").setScore(9);
		
		for (int i = 0; i < plugin.getTrustManager().getTrustedPlayers().size(); i++) {
			obj.getScore("§7 -§e " + Bukkit.getPlayer(plugin.getTrustManager().getTrustedPlayers().get(i)).getName()).setScore(i + 8);
		}
		
		obj.getScore("").setScore(0);
		obj.getScore("§aPlugin Coder §cLeotendo").setScore(-1);;
		
		return board;
	}
}
