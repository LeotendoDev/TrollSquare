package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.troll.start.Start;

public class InfoModule extends Module {

	public InfoModule() {
		super("Info", "Leotendo", "Informiere dich über einen Spieler");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		p.sendMessage(Start.getInstance().getPrefix() + "§6======== INFO ========");
		p.sendMessage(Start.getInstance().getPrefix() + "§aServer-IP: §5" + Bukkit.getServerName());
		p.sendMessage(Start.getInstance().getPrefix() + "§aBukkit-Version: §5" + Bukkit.getBukkitVersion());
		p.sendMessage(Start.getInstance().getPrefix() + "§6======================");
		
		return true;
	}
}
