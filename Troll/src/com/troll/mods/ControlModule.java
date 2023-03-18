package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitTask;

import com.troll.start.Start;

public class ControlModule extends Module implements Listener {
	private Player executor;
	private Player target;
	private boolean isActive = false;
	private BukkitTask task = null;
	
	public ControlModule() {
		super("Control", "Leotendo", "Kontrolliere andere Spieler");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #control <player>");
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
		isActive = !isActive;
		if (isActive) {
			p.sendMessage(Start.getInstance().getPrefix() + "§aDu kontrollierst nun §e" + target.getName());
			
			this.executor = p;
			this.target = target;
			
			p.hidePlayer(target);
			target.hidePlayer(p);
			
			p.getInventory().setArmorContents(target.getInventory().getArmorContents());
			p.getInventory().setContents(target.getInventory().getContents());
			p.setHealth(target.getHealth());
			p.setFoodLevel(target.getFoodLevel());
			p.setExp(target.getExp());
			p.setGameMode(target.getGameMode());
			p.teleport(target);	
			
			task = Bukkit.getScheduler().runTaskTimer(Start.getInstance(), new Runnable() {
				
				@Override
				public void run() {
					target.teleport(executor);
					target.getInventory().setArmorContents(executor.getInventory().getArmorContents());
					target.getInventory().setContents(executor.getInventory().getContents());
				}
			}, 2L, 2L);
			
		} else {
			p.sendMessage(Start.getInstance().getPrefix() + "§aDu kontrollierst nun keinen mehr!");
			p.showPlayer(target);
			target.showPlayer(p);
			
			task.cancel();
		}
		
		return true;
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		
		if (!isActive) return;
		if (e.getPlayer() == target) e.setCancelled(true);
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if (!isActive) return;
		if (e.getPlayer() == executor) {
			e.setCancelled(true);
			target.chat(e.getMessage());
		}
	}
}
