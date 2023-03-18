package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.troll.start.Start;

public class TntRainModule extends Module {

	public TntRainModule() {
		super("Tntrain", "Leotendo", "Beim Spieler regnet es TNT");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #tntrain <player>");
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
        for (double x = target.getLocation().getX() - 5.0D; x <= target.getLocation().getX() + 5.0D; ++x) {
            for (double z = target.getLocation().getZ() - 5.0D; z <= target.getLocation().getZ() + 5.0D; ++z) {
                target.getWorld().spawnEntity(new Location(target.getWorld(), x, target.getLocation().getY() + 8.0D, z), EntityType.PRIMED_TNT);
            }
        }
        
        p.sendMessage(Start.getInstance().getPrefix() + "§aBeim Spieler regnet es nun TNT");
		
		return true;
	}
}
