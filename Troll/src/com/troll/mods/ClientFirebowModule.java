package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.troll.start.Start;

public class ClientFirebowModule extends Module implements Listener {
	private Player player;
	private int intensity;
	
	public ClientFirebowModule() {
		super("ClientFirebow", "Leotendo", "Erschaffe einen Explosions-Bogen");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 3) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #clientfirebow <player> <intensity>");
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
			p.sendMessage(Start.getInstance().getPrefix() + "§cUngültiger Wert!");
			return false;
		}
		
		this.player = p;
		this.intensity = intensity;
		
		ItemStack item = new ItemStack(Material.BOW);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName("§5FIRE BOW §7(§aRechtsklick§7)");
		item.setItemMeta(meta);
		
		p.getInventory().addItem(item);
		
		return true;
	}
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e) {
		if (this.player == null) return;
		if (!(e.getEntity() instanceof Arrow)) return;
		
		Arrow arrow = (Arrow)e.getEntity();
		if (!(arrow.getShooter() instanceof Player)) return;

		Player p = (Player)arrow.getShooter();
		
		if (p.getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§5FIRE BOW §7(§aRechtsklick§7)")) {
			this.player.getWorld().createExplosion(arrow.getLocation(), this.intensity);
		}
	}
}
