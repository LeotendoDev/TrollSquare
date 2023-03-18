package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import com.troll.start.Start;

import net.minecraft.server.v1_8_R3.PacketPlayOutGameStateChange;

public class VirusModule extends Module implements Runnable {
	private int percent = 0;
	private BukkitTask task = null;
	private Player target;
	
	public VirusModule() {
		super("Virus", "Leotendo", "Gebe dem Spieler einen Virus-Screen");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #virus <player>");
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
		this.target = target;
		
        ((CraftPlayer)target).getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(7, 5.0F));
		task = Bukkit.getScheduler().runTaskTimer(Start.getInstance(), this, 5L, 5L);
        ((CraftPlayer)target).getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(7, 0.0F));
        
		return true;
	}

	@Override
	public void run() {
		if (percent < 100)
			percent++;
		else
			task.cancel();
		
		this.target.sendTitle("§c§lENTPACKE VIRUS...", "§l§6" + percent + "%");
	}
}
