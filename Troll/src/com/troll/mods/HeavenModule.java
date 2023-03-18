package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.troll.start.Start;

public class HeavenModule extends Module {

	public HeavenModule() {
		super("Heaven", "Leotendo", "Schicke den Spieler in den Himmel");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #heaven <player>");
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}

		target.getWorld().spawnEntity(target.getLocation(), EntityType.FIREWORK);
		target.setVelocity(new Vector(0, 5, 0));
		
		return true;
	}
}
