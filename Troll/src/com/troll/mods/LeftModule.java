package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.troll.start.Start;

public class LeftModule extends Module {
	private boolean isActive = false;
	private Player executor;
	
	public LeftModule() {
		super("Left", "Leotendo", "Simuliere einen Spielverlass");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 1) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #left");
			return false;
		}
		
		this.executor = p;
		
		isActive = !isActive;
		
		if (!isActive) {
			p.sendMessage(Start.getInstance().getPrefix() + "§aDie Spieler denken nun du wärest wieder online");
			
			Location spawn = p.getWorld().getSpawnLocation();
			
			Bukkit.getLogger().info("UUUID of player " + p.getName() + " is" + p.getUniqueId().toString());
			Bukkit.getLogger().info(p.getName() + "[" + p.getAddress().getAddress().toString() + "] logged in with entity id 1688 at ([" + p.getWorld().getName() +
					"]" + spawn.getX() + ", " + spawn.getY() + ", " + spawn.getZ() + ")");
			for (Player cp : Bukkit.getOnlinePlayers()) {
				if (cp.getUniqueId() != p.getUniqueId()) {
					cp.showPlayer(p);
					cp.sendMessage("§e" + p.getName() + " joined the game");
				}
			}	
		} else {
			executor.sendMessage(Start.getInstance().getPrefix() + "§aDie Spieler denken nun wärest du offline");
			
			for (Player cp : Bukkit.getOnlinePlayers()) {
				if (cp.getUniqueId() != executor.getUniqueId()) {
					cp.hidePlayer(executor);
					cp.sendMessage("§e" + executor.getName() + " left the game");
				}
			}
			
			Bukkit.getLogger().info(executor.getName() + " lost connection: Disconnected");
			Bukkit.getLogger().info(executor.getName() + " left the game");		}
		
		return true;
	}

	public Player getExecutor() {
		return this.executor;
	}
	
	public void setExecutor(Player executor) {
		this.executor = executor;
	}
}
