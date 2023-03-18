package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.troll.start.Start;

public class FakeStopModule extends Module {

	public FakeStopModule() {
		super("FakeStop", "Leotendo", "Täuscht einen Stop des Servers vor");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #fakestop <player>");
			return false;
		}
		
		Player victim = Bukkit.getPlayer(args[1]);
		if (victim == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
		Bukkit.getScheduler().runTask(Start.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler denkt nun der Server wurde gestoppt");
				victim.sendMessage("§7§o[Server: Stopped]");
				victim.kickPlayer("Server stopped");
			}
		});
		
		return true;
	}
}
