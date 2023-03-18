package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.troll.start.Start;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

public class ExplosionModule extends Module {
	
	public ExplosionModule() {
		super("Explosion", "Leotendo", "Starte eine Explosion");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 3) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #explosion <player> <intensity (1-100>>");
			return false;
		}
		
		int intensity = Integer.parseInt(args[2]);
		if (intensity > 100)
			intensity = 100;
		
		Player victim = Bukkit.getPlayer(args[1]);
		if (victim == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
		Location victimLoc = victim.getLocation();
		
		for (int i = 0; i < intensity; i++) {
			((CraftPlayer)victim).getHandle().playerConnection.sendPacket(new PacketPlayOutWorldParticles(EnumParticle.EXPLOSION_HUGE, true,
				(float)victimLoc.getX(), (float)victimLoc.getY(), (float)victimLoc.getZ(), 0, 0, 5, 0, 0));
		
			victim.playSound(victimLoc, Sound.EXPLODE, 100.0f, 100.0f);
		}
		
		p.sendMessage("§aBammmm!");
			
		return true;
	}
}
