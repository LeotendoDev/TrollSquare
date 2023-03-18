package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.troll.start.Start;

public class FillInventoryModule extends Module {
	private ItemStack[] oldInv;

	public FillInventoryModule() {
		super("FillInventory", "Leotendo", "Fühlt das Inventar des Spielers");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 3) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #fillinventory <player> <item>");
			return false;
		}
		
		Player victim = Bukkit.getPlayer(args[1]);
		if (victim == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
				
		if (args[2].equalsIgnoreCase("reset")) {
			if (oldInv == null) {
				p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler hat bereits ein leeres Inventar");
				return false;
			}
			victim.getInventory().clear();
			victim.getInventory().setContents(oldInv);
			return true;
		}
		
		if (!(p.getInventory().getContents().length == 0))
			oldInv = victim.getInventory().getContents();
		Material mat;
		try {
			mat = Material.valueOf(args[2].toUpperCase());
		} catch (IllegalArgumentException e) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDas Material §e" + args[2] + " §cgibt es nicht!");
			return false;
		}
		
		ItemStack item = new ItemStack(mat);
		item.setAmount(64);
		
		Inventory inv = victim.getInventory();
		inv.clear();
		
		for (int i = 0; i < inv.getSize(); i++) {
			inv.addItem(item);
		}
		
		p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler hat nun ein Inventar voller §e" + args[2]);
		
		return true;
	}

}
