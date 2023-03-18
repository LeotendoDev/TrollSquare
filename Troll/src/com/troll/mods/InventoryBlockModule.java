package com.troll.mods;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.troll.start.Start;

public class InventoryBlockModule extends Module implements Listener {
	private HashMap<UUID, ItemStack[]> blockedPlayers;
	
	public InventoryBlockModule() {
		super("Blockinventory", "Leotendo", "Blockiert das Inventar des Spielers");
		
		this.blockedPlayers = new HashMap<>();
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #blockinventory <player>");
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
		UUID uuid = target.getUniqueId();
		
		if (blockedPlayers.containsKey(uuid)) {
			p.sendMessage(Start.getInstance().getPrefix() + "§aDas Inventar des Spielers ist nun wieder freigegeben");
			
			target.getInventory().clear();
			target.getInventory().setContents(blockedPlayers.get(uuid));
			
			blockedPlayers.remove(uuid);
			
			return true;
		}
		
		blockedPlayers.put(uuid, target.getInventory().getContents());
		p.sendMessage(Start.getInstance().getPrefix() + "§aDas Inventar des Spielers ist nun gesperrt");
		
		// Fill inventory with barriers
		target.getInventory().clear();
		
		ItemStack barrier = new ItemStack(Material.BARRIER);
		ItemMeta meta = barrier.getItemMeta();
		meta.setDisplayName("§c§lBLOCKED");
		barrier.setItemMeta(meta);
		
		for (int i = 0; i < 36*64; i++) {
			target.getInventory().setItem(i, barrier);
		}
		
		return true;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player p = (Player)e.getWhoClicked();
		
		if (blockedPlayers.containsKey(p.getUniqueId()) && e.getClickedInventory() == p.getInventory())
			e.setCancelled(true);
	}
	
	@EventHandler
	public void onInventoryDrag(InventoryDragEvent e) {
		Player p = (Player)e.getWhoClicked();
		
		if (blockedPlayers.containsKey(p.getUniqueId()) && e.getInventory() == p.getInventory())
			e.setCancelled(true);
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		
		if (blockedPlayers.containsKey(p.getUniqueId()))
			e.setCancelled(true);
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		
		if (blockedPlayers.containsKey(p.getUniqueId()))
			e.setCancelled(true);
	}
}
