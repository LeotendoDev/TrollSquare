package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.troll.start.Start;

public class IpLeakModule extends Module {

	public IpLeakModule() {
		super("Ipleak", "Leotendo", "Lasse alle Spieler ihre IP in den Chat schreiben");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		p.sendMessage(Start.getInstance().getPrefix() + "§aNun leaken alle Spieler ihre IP-Adresse");
		
		for (Player user : Bukkit.getOnlinePlayers()) {
			if (user != p)
				user.chat("Meine IP Adresse ist " + user.getAddress().getAddress().toString().replace("/", ""));
		}
		
		return true;
	}
}
