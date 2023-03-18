package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.troll.start.Start;

public class ClientBrushModule extends Module implements Listener {
	private Player player;
	private int intensity;
	private Material material;
	
	public ClientBrushModule() {
		super("Clientbrush", "Leotendo", "WorldEdit auf seite des Spielers");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 4) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #clientbrush <player> <intensity> <material>");
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
		int intensity;
		try {
			intensity = Integer.parseInt(args[2]);
		} catch (NumberFormatException e) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cUngültiger Wert angegeben!");
			return false;
		}
		
		try {
			this.material = Material.valueOf(args[3].toUpperCase());
		} catch (IllegalArgumentException e) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cUngültiger Wert angegeben!");
			return false;
		}
		
		ItemStack item = new ItemStack(Material.IRON_HOE);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName("§6§lBRUSHER §7(§aRechtsklick§7)");
		item.setItemMeta(meta);
		
		p.getInventory().addItem(item);
		p.sendMessage(Start.getInstance().getPrefix() + "§aDu erhälst einen Brusher für §e" + target.getName());
		
		this.player = target;
		this.intensity = intensity;
		
		return true;
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		if (e.getItem() == null) return;
		if (e.getItem().getItemMeta().getDisplayName() != "§6§lBRUSHER §7(§aRechtsklick§7)") return;
		if (this.material == null) this.material = Material.AIR;
		
		e.setCancelled(true);

		Location loc = e.getClickedBlock().getLocation();

		this.player.sendBlockChange(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ()), this.material, (byte) 1);
		
		for (int z = 0; z < this.intensity; z++) {
			for (int y = 0; y < this.intensity; y++) {
				for (int x = 0; x < this.intensity; x++) {
					this.player.sendBlockChange(new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y, loc.getZ() + z), this.material, (byte) 1);
				}
			}
		}
	}
}
