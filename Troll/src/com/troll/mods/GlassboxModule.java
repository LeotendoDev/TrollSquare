package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.troll.start.Start;

public class GlassboxModule extends Module {
	private boolean isActive = false;
	
	public GlassboxModule() {
		super("GlassBox", "Leotendo", "Fange den Spieler in einer Glasbox");
	}
	
	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #glassbox <player>");
			return false;
		}
		
		Player victim = Bukkit.getPlayer(args[1]);
		if (victim == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
		isActive = !isActive;
		if (isActive) {
			p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler ist nun in einer Glas-Arena");
		} else {
			p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler ist nun wieder befreit");
			return true;
		}
		
        double x;
        double y;
        double z;
        for (x = victim.getLocation().getX() - 5.0D; x <= victim.getLocation().getX() + 5.0D; ++x) {
            for (y = victim.getLocation().getY() - 5.0D; y <= victim.getLocation().getY() + 5.0D; ++y) {
                for (z = victim.getLocation().getZ() - 5.0D; z <= victim.getLocation().getZ() + 5.0D; ++z) {
                    victim.sendBlockChange(new Location(victim.getWorld(), x, y, z), 20, (byte) 0);
                }
            }
        }

        for (x = victim.getLocation().getX() - 4.0D; x <= victim.getLocation().getX() + 4.0D; ++x) {
            for (y = victim.getLocation().getY() - 4.0D; y <= victim.getLocation().getY() + 4.0D; ++y) {
                for (z = victim.getLocation().getZ() - 4.0D; z <= victim.getLocation().getZ() + 4.0D; ++z) {
                    victim.sendBlockChange(new Location(victim.getWorld(), x, y, z), 0, (byte) 0);
                }
            }
        }
		
		return true;
	}
}
