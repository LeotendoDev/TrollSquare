package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import com.troll.start.Start;

public class BookMatrixModule extends Module {

	public BookMatrixModule() {
		super("Bookmatrix", "Leotendo", "Lass beim Spieler ein Buch mit Matrix erscheinen");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #bookmatrix <player>");
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta meta = (BookMeta)book.getItemMeta();
		
		meta.setTitle("Title");
		meta.setAuthor("Leo");
		book.setItemMeta(meta);
		
		((CraftPlayer)p).getHandle().openBook(CraftItemStack.asNMSCopy(book));
		
		return true;
	}
}
