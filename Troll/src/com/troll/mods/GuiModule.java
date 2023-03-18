package com.troll.mods;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.troll.start.Start;

public class GuiModule extends Module {
	private Inventory inv;
	private final int INVENTORY_SIZE = 27;
	
	public GuiModule() {
		super("Gui", "Leotendo", "Öffne das GUI");
		
		this.inv = Bukkit.createInventory(null, InventoryType.PLAYER, Start.getInstance().getPrefix());
	}
	
	private void configureInventory() {
		Random rand = new Random();
		
		for (int i = 0; i < 36; i++) {
			Module mod = Start.getInstance().getModuleManager().getModules().get(i);
			
			ItemStack item = new ItemStack(Material.WOOD);
			ItemMeta meta = item.getItemMeta();
			item.setTypeId(rand.nextInt(100));
			
			String colorCode = "§" + rand.nextInt(9);
			List<String> lore = new ArrayList<String>();
			lore.add("§b" + mod.description);
			
			meta.setDisplayName(colorCode + mod.name);
			meta.setLore(lore);
			item.setItemMeta(meta);
			
			inv.setItem(i, item);
		}
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #gui <player>");
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
		configureInventory();

		p.openInventory(inv);
		
		return true;
	}
}
