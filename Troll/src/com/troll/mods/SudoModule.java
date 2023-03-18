package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.troll.start.Start;

public class SudoModule extends Module {

	public SudoModule() {
		super("Sudo", "Leotendo", "Führe Befehle als ein anderer Spieler aus");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length < 3) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #sudo <victim> <command>");
			return false;
		}
		
		Player victim = Bukkit.getPlayer(args[1]);
		if (victim == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler §e" + args[1] + "§c ist nicht online!");
			return false;
		}
		
		String cmd = "";
		for (int i = 2; i < args.length; i++) {
			cmd += args[i] + " ";
		}

		Bukkit.dispatchCommand(victim, cmd);
		
		p.sendMessage(Start.getInstance().getPrefix() + "§aStarte: §e/" + cmd + " §aals §e" + victim.getName());
		
		return true;
	}
}
