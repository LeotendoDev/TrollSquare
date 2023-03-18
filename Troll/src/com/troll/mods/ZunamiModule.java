package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

import com.troll.start.Start;

public class ZunamiModule extends Module implements Listener, Runnable {
	private boolean isActive = false;
	private Player executor;

	public ZunamiModule() {
		super("Zunami", "Leotendo", "Spawnt einen Zunami");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 1) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #zunami");
			return false;
		}
		
		this.executor = p;
		
		isActive = !isActive;
		if (isActive) {
			p.sendMessage(Start.getInstance().getPrefix() + "§aNun ist ein Zunami ausgebrochen");
			Bukkit.getScheduler().runTask(Start.getInstance(), this);
		} else {
			p.sendMessage(Start.getInstance().getPrefix() + "§aDer Zunami wurde gestoppt");
		}
			
		return true;
	}
	
	@Override
	public void run() {
        for (double x = this.executor.getLocation().getX() - 1.0D; x <= this.executor.getLocation().getX() + 1.0D; ++x) {
            (new Location(this.executor.getWorld(), x, this.executor.getLocation().getY(), this.executor.getLocation().getZ())).getBlock().setType(Material.WATER);
            (new Location(this.executor.getWorld(), x, this.executor.getLocation().getY(), this.executor.getLocation().getZ())).getBlock().setData((byte) 8);
        }
	}
	
	@EventHandler
	public void onBlockFromTo(BlockFromToEvent e) {
        if (this.isActive) {
        	if (e.getBlock().getType().getId() == 8 || e.getBlock().getType().getId() == 9 && e.getBlock().getData() == 8) {
        		for (double x = e.getBlock().getLocation().getX() - 1.0D; x <= e.getBlock().getLocation().getX() + 1.0D; ++x) {
        			for (double z = e.getBlock().getLocation().getZ() - 1.0D; z <= e.getBlock().getLocation().getZ() + 1.0D; ++z) {
        				(new Location(e.getBlock().getWorld(), x, e.getBlock().getLocation().getY(), z)).getBlock().setType(Material.WATER);
                        (new Location(e.getBlock().getWorld(), x, e.getBlock().getLocation().getY(), z)).getBlock().setData((byte) 8);
                    }
                }
            }
        }
	}
}
