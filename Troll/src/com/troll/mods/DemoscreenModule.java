package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.troll.start.Start;

import net.minecraft.server.v1_8_R3.PacketPlayOutGameStateChange;

public class DemoscreenModule extends Module {

	public DemoscreenModule() {		
		super("Demoscreen", "Leotendo", "Gibt dem Spieler dem den Demo-Screen");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #demoscreen <player>");
			return false;
		}
		
		Player victim = Bukkit.getPlayer(args[1]);
		if (victim == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
		((CraftPlayer)victim).getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(5, 0.0f));
		p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler denkt nun er hätte die Demo-Version :)");
		
		return true;
	}
}
