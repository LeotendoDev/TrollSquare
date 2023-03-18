package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.troll.start.Start;

public class ParticleSpamModule extends Module {

	public ParticleSpamModule() {
		super("Particlespam", "Leotendo", "Spamme den Spieler mit Partikeln voll");
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #particlespam");
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
		Location loc = target.getLocation();
        for (int i = 0; i < 100; ++i) {
            target.playEffect(loc, Effect.ENDER_SIGNAL, 5);
            target.playEffect(loc, Effect.LARGE_SMOKE, 5);
            target.playEffect(loc, Effect.EXPLOSION_LARGE, 5);
            target.playEffect(loc, Effect.MOBSPAWNER_FLAMES, 5);
            target.playEffect(loc, Effect.STEP_SOUND, 5);
            target.playEffect(loc, Effect.CLOUD, 5);
            target.playEffect(loc, Effect.PORTAL, 1);
            target.playSound(loc, Sound.ANVIL_LAND, 1.0F, 1.0F);
        }
		
		return true;
	}
}
