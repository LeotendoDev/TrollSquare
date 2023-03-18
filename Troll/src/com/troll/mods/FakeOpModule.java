package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.troll.start.Start;

public class FakeOpModule extends Module {

	public FakeOpModule() {
		super("FakeOp", "Leotendo", "Täuscht dem Spieler OP-Rechte vor");
	}
	
	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #fakeop <player>");
			return false;
		}
		
		Player victim = Bukkit.getPlayer(args[1]);
		if (victim == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
		p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler denkt nun er hätte OP-Rechte");
		victim.sendMessage("§7§o[Server: Opped " + victim.getName() + "]");
		
		Bukkit.getLogger().info("Opped " + victim.getName());
		
		return true;
	}
}
