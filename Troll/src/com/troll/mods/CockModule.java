package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.troll.start.Start;

public class CockModule extends Module {

	public CockModule() {
		super("Cock", "Leotendo", "Spawnt einen P****s beim Spieler");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #cock <player>");
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
        double x;
        double y;
        double z;
        for (x = target.getLocation().getX() - 4.0D; x <= target.getLocation().getX() + 4.0D; ++x) {
            for (y = target.getLocation().getY() + 4.0D; y <= target.getLocation().getY() + 6.0D; ++y) {
                for (z = target.getLocation().getZ(); z <= target.getLocation().getZ() + 2.0D; ++z) {
                    target.sendBlockChange(new Location(target.getWorld(), x, y, z), Material.WOOL, (byte) 6);
                }
            }
        }

        for (x = target.getLocation().getX() - 1.0D; x <= target.getLocation().getX() + 1.0D; ++x) {
            for (y = target.getLocation().getY() + 6.0D; y <= target.getLocation().getY() + 12.0D; ++y) {
                for (z = target.getLocation().getZ(); z <= target.getLocation().getZ() + 2.0D; ++z) {
                    target.sendBlockChange(new Location(target.getWorld(), x, y, z), Material.WOOL, (byte) 6);
                }
            }
        }

        for (x = target.getLocation().getX() - 1.0D; x <= target.getLocation().getX() + 1.0D; ++x) {
            for (y = target.getLocation().getY() + 12.0D	; y <= target.getLocation().getY() + 14.0D; ++y) {
                for (z = target.getLocation().getZ(); z <= target.getLocation().getZ() + 2.0D; ++z) {
                    target.sendBlockChange(new Location(target.getWorld(), x, y, z), Material.WOOL, (byte) 2);
                }
            }
        }
        
        p.sendMessage(Start.getInstance().getPrefix() + "§aBeim Spieler erscheint nun ein Pimmel :)");
		
		return true;
	}
}
