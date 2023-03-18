package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.troll.start.Start;

public class ShortcutModule extends Module implements Listener {
	private ItemStack item;
	private Player player;
	private String command = "";
	
	public ShortcutModule() {
		super("Shortcut", "Leotendo", "Setzte einen bestimmen Command auf ein Item");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length < 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #shortcut <command>");
			return false;
		}
		
		this.command = "";
		for (int i = 1; i < args.length; i++) {
			this.command += args[i] + " ";
		}

		try {
			this.item = p.getInventory().getItemInHand();
		} catch (NullPointerException e) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDu hälst kein Item in der Hand!");
			return false;
		}
		
		this.player = p;
		
		return false;
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction() != Action.RIGHT_CLICK_AIR) return;
		if (e.getPlayer() != this.player) return;
		
		e.setCancelled(true);

		e.getPlayer().chat(this.command);
	}
}
