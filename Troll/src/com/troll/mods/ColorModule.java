package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.troll.start.Start;

import net.minecraft.server.v1_8_R3.PacketPlayOutGameStateChange;

public class ColorModule extends Module {

	public ColorModule() {
		super("Color", "Leotendo", "Wechsel den Kontrast eines Spielers");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 3) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #color <player> <color>");
			return false;
		}
		
		Player victim = Bukkit.getPlayer(args[1]);
		if (victim == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
		int color;
		try {
			color = Integer.parseInt(args[2]);
		} catch (NumberFormatException e) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cUngültiger Wert angegeben!");
			return false;
		}
		
		switch (color) {
		case 0:
			((CraftPlayer)victim).getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(7, 0.0f));
			break;
		case 1:
			((CraftPlayer)victim).getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(7, 12.0f));
			break;
		case 2:
			((CraftPlayer)victim).getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(7, 30.0f));
			break;
		case 3:
			((CraftPlayer)victim).getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(7, 40000.0f));
			break;
		case 4:
			((CraftPlayer)victim).getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(7, 20000.0f));
			break;
		case 5:
			((CraftPlayer)victim).getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(7, 19500.0f));
			break;
		default:
			p.sendMessage(Start.getInstance().getPrefix() + "§e" + color + " §cis no valid color code");
			break;
		}

		p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler ist nun Farben-Blind");
		
		return true;
	}
}
