package com.troll.mods;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.troll.start.Start;

import net.minecraft.server.v1_8_R3.PacketPlayOutGameStateChange;

public class HackModule extends Module {

	public HackModule() {
		super("Hack", "Leotendo", "Lass den Spieler denken er wurde gehackt");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #hack <player>");
			return false;
		}
		
		Player victim = Bukkit.getPlayer(args[1]);
		if (victim == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
		Random rand = new Random();	
		
		for (int state = 0; state < 15; state++) {
			((CraftPlayer)victim).getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(7, (float) state));

			for (int i = 0; i < 40; ++i) {
				victim.sendMessage("");
			}

			victim.playSound(victim.getLocation(), Sound.ANVIL_BREAK, 100.0F, 100.0F);
			victim.playSound(victim.getLocation(), Sound.BAT_DEATH, 100.0F, 100.0F);
			victim.playSound(victim.getLocation(), Sound.CAT_MEOW, 100.0F, 100.0F);
			victim.sendMessage(ChatColor.LIGHT_PURPLE + "         |||||||         ");
			victim.sendMessage(ChatColor.LIGHT_PURPLE + "      ==||O|||O||==      " + ChatColor.GREEN + rand.nextInt(100) * 6);
        	victim.sendMessage(ChatColor.LIGHT_PURPLE + "      ==|||___|||==      " + ChatColor.GREEN + rand.nextInt(100) * 6);
        	victim.sendMessage(ChatColor.LIGHT_PURPLE + "        |||||||||        ");
        	victim.sendMessage("");
        	victim.sendMessage("§cDein Mojang Passwort wird geändert, Bitte warten .§a");

        	victim.closeInventory();
        	victim.openInventory(Bukkit.createInventory(null, 27));
        	((CraftPlayer) victim).getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(5, 0.0F));
		}
        	
		return true;
	}
}
