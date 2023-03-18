package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.troll.start.Start;

public class TimeoutModule extends Module {
	private final String KICK_REASON = "java.io.IOException: Connection reset by peer";
	
	public TimeoutModule() {
		super("Timeout", "Leotendo", "Lass einen Spieler austimen");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #timeout <player>");
			return false;
		}
		
		Player victim = Bukkit.getPlayer(args[1]);
		if (victim == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler §e" + args[1] + "§c ist nicht online!");
			return false;
		}
		
		p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler §e" + victim.getName() + " §adenkt nun sein Internet wäre schlecht");
		victim.kickPlayer(KICK_REASON);
		
		return true;
	}
}
