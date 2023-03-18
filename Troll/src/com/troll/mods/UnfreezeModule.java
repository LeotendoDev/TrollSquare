package com.troll.mods;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.troll.start.Start;

public class UnfreezeModule extends Module {

	public UnfreezeModule() {
		super("Unfreeze", "Leotendo", "Befreie den Spieler");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #freeze <player>");
			return false;
		}
		
		Player victim = Bukkit.getPlayer(args[1]);
		if (victim == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler §e" + args[1] + "§c ist nicht online!");
			return false;
		}
		
		if (Start.getInstance().getFreezer().getFrozenPlayers().contains(victim)) {
			Start.getInstance().getFreezer().getFrozenPlayers().remove(victim);
		} else {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler §e" + victim.getName() + " §cwar noch nicht eingefroren");
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #freeze " + victim.getName());
			return false;
		}
		
		p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler §e" + victim.getName() + " §akann sich nun wieder bewegen");
		
		
		
		return true;
	}
}
