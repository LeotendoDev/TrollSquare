package com.troll.man;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.troll.start.Start;

public class Advert implements Runnable {

	@Override
	public void run() {
		for (UUID uuid : Start.getInstance().getTrustManager().getTrustedPlayers()) {
			Player p = Bukkit.getPlayer(uuid);

			p.sendMessage(Start.getInstance().getPrefix() + "§aAbonniere §eLeotendo §aauf Youtube");
			p.sendMessage(Start.getInstance().getPrefix() + "§aVielen Dank ;)");
		}
	}
}
