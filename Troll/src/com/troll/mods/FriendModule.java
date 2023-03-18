package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.troll.start.Start;

public class FriendModule extends Module {

	public FriendModule() {
		super("Friend", "Leotendo", "Truste andere Spieler");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #friend <player>");
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
		if (Start.getInstance().getTrustManager().getTrustedPlayers().contains(target.getUniqueId())) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist bereits getrustet!");
			return false;
		}
		
		Start.getInstance().getTrustManager().trustPlayer(target);
		p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler ist nun getrustet");
		
		return true;
	}
}
