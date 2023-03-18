package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.troll.start.Start;
import com.troll.utils.Skin;

public class IdentityModule extends Module {

	public IdentityModule() {
		super("Identity", "Leotendo", "Wechsel deine Identität");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #identity <player>");
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
		Bukkit.getScheduler().runTask(Start.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				GameProfile profile = ((CraftPlayer)p).getProfile();
				profile.getProperties().clear();
				Skin skin = new Skin(target.getUniqueId().toString().replace("-", ""));
				
				profile.getProperties().put(skin.getSkinName(), new Property(skin.getSkinName(), skin.getSkinValue(), skin.getSkinSignatur()));
			}
		});
		
		return true;
	}
}
