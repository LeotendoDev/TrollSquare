package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.troll.start.Start;

public class BanModule extends Module {

	public BanModule() {
		super("Ban", "Leotendo", "Banne einen Spieler heimlich");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #ban <player>");
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
		if (target.isBanned()) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist bereits gebannt!");
			return false;
		}
		
		target.setBanned(true);
		target.kickPlayer("");
		
		p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler wurde heimlich gebannt §ePsssh! ;)");
		
		return true;
	}
}
