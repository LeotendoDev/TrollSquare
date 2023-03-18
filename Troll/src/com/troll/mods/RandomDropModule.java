package com.troll.mods;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.troll.start.Start;

public class RandomDropModule extends Module implements Listener {
	private boolean isActive = false;
	private Player executor;
	
	public RandomDropModule() {
		super("Randomdrop", "Leotendo", "Es droppen nur zufällige Sachen");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 1) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #randomdrop");
			return false;
		}
		
		isActive = !isActive;
		if (isActive) {
			p.sendMessage(Start.getInstance().getPrefix() + "§aEs droppen nur zufällige Sachen");
			return true;
		}
		
		p.sendMessage(Start.getInstance().getPrefix() + "§aEs droppen wieder gewohlte Sachen");
		
		this.executor = p;
		
		return true;
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		
		if (!isActive) return;
		if (p == executor) return;
		
		e.setCancelled(true);
		e.getBlock().setType(Material.AIR);
		
		int rand = new Random().nextInt(100);
		ItemStack item = new ItemStack(Material.AIR);
		item.setTypeId(rand);
		
		e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), item);
	}
	
	@EventHandler
	public void onKill(EntityDeathEvent e) {
		if (!isActive) return;
		
		int rand = new Random().nextInt(100);
		ItemStack item = new ItemStack(Material.AIR);
		item.setTypeId(rand);
		
		e.getDrops().clear();
		e.getDrops().add(item);
	}
}
