package com.troll.mods;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.troll.start.Start;
import com.troll.utils.ScoreboardUtil;

public class ScoreboardModule extends Module {
	private ArrayList<Player> players;
	
	public ScoreboardModule() {
		super("Scoreboard", "Leotendo", "Zeige das TrollSquare Scoreboard an");
		
		this.players = new ArrayList<>();
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (this.players.contains(p)) {
			this.players.remove(p);
			p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
			
			p.sendMessage(Start.getInstance().getPrefix() + "§aDu hast das Scoreboard deaktiviert");
			
			return true;
		}
		
		p.setScoreboard(new ScoreboardUtil(Start.getInstance()).create());
		p.sendMessage(Start.getInstance().getPrefix() + "§aDu hast das Scoreboard aktiviert");
		
		this.players.add(p);
		
		return false;
	}
}
