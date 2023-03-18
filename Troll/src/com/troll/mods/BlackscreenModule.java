package com.troll.mods;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.troll.start.Start;

import net.minecraft.server.v1_8_R3.PacketPlayOutGameStateChange;

public class BlackscreenModule extends Module {
	private ArrayList<Player> victims;

	public BlackscreenModule() {		
		super("Blackscreen", "Leotendo", "Gibt dem Spieler dem Blackscreen");
	
		victims = new ArrayList<Player>();
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #blackscreen <player>");
			return false;
		}
		
		Player victim = Bukkit.getPlayer(args[1]);
		if (victim == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
		if (victims.contains(victim)) {
			p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler hat jetzt keine Farben mehr");
			((CraftPlayer)victim).getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(7, 0.0f));
			
			victims.remove(victim);
			
		} else {
			p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler hat nun wieder Farben");
			((CraftPlayer)victim).getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(7, 5.0f));
			
			victims.add(victim);
		}
        
		return true;
	}
}
