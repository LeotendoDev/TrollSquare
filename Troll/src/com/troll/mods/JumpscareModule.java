package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.troll.start.Start;

import net.minecraft.server.v1_8_R3.PacketPlayOutGameStateChange;

public class JumpscareModule extends Module {
	private final float DURATION = 2.14748365E9f;
	private final float TIME = 0.2f;
	
	public JumpscareModule() {
		super("Jumpscare", "Leotendo", "Erschrecke den Spieler");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #jumpscare <player>");
			return false;
		}
		
		Player victim = Bukkit.getPlayer(args[1]);
		if (victim == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
		((CraftPlayer)victim).getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(10, 0.0f));
		for (int i = 0; i < 10; i++) {
			victim.playSound(victim.getLocation(), Sound.BAT_DEATH, DURATION, TIME);
			victim.playSound(victim.getLocation(), Sound.ANVIL_LAND, DURATION, TIME);
			victim.playSound(victim.getLocation(), Sound.ARROW_HIT, DURATION, TIME);
			victim.playSound(victim.getLocation(), Sound.AMBIENCE_THUNDER, 1.0f, TIME);
		}

		p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler wird nun erschrocken");
		
		return true;
	}
}
