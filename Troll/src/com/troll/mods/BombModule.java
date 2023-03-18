package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.util.Vector;

import com.troll.start.Start;

public class BombModule extends Module implements Listener {
	private Player player;
	
	public BombModule() {
		super("Bomb", "Leotendo", "Lege eine Bombe");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #bomb <player>");
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}
		
		this.player = target;
		
		FallingBlock block = player.getWorld().spawnFallingBlock(player.getLocation(), Material.SPONGE, (byte)123);
		block.setCustomName("GRANATE");
		
		return true;
	}
	

    @EventHandler
    public void onBlockChangeByEntity(EntityChangeBlockEvent e) {
        try {
            if (e.getEntityType().equals(EntityType.FALLING_BLOCK)) {
                if (e.getEntity().getCustomName().equalsIgnoreCase("SPLITTER2")) {
                    e.setCancelled(true);
                    e.getBlock().getWorld().createExplosion(e.getBlock().getLocation(), 20.0F);
                }

                int i;
                FallingBlock fb;
                FallingBlock fb2;
                FallingBlock fb3;
                FallingBlock fb4;
                if (e.getEntity().getCustomName().equalsIgnoreCase("SPLITTER")) {
                    e.setCancelled(true);

                    for (i = 0; i < 4; ++i) {
                        switch (i) {
                            case 0:
                                fb = e.getBlock().getWorld().spawnFallingBlock(e.getBlock().getLocation(), Material.COAL_BLOCK, (byte) 77);
                                fb.setCustomName("SPLITTER2");
                                fb.setVelocity(new Vector(0.5D, 0.05D, 0.0D));
                                break;
                            case 1:
                                fb2 = e.getBlock().getWorld().spawnFallingBlock(e.getBlock().getLocation(), Material.COAL_BLOCK, (byte) 77);
                                fb2.setCustomName("SPLITTER2");
                                fb2.setVelocity(new Vector(-0.5D, 0.05D, 0.0D));
                                break;
                            case 2:
                                fb3 = e.getBlock().getWorld().spawnFallingBlock(e.getBlock().getLocation(), Material.COAL_BLOCK, (byte) 77);
                                fb3.setCustomName("SPLITTER2");
                                fb3.setVelocity(new Vector(0.0D, 0.05D, 0.5D));
                                break;
                            case 3:
                                fb4 = e.getBlock().getWorld().spawnFallingBlock(e.getBlock().getLocation(), Material.COAL_BLOCK, (byte) 77);
                                fb4.setCustomName("SPLITTER2");
                                fb4.setVelocity(new Vector(0.0D, 0.05D, -0.5D));
                        }

                        e.getBlock().getWorld().createExplosion(e.getBlock().getLocation(), 20.0F);
                    }
                } else if (e.getEntity().getCustomName().equalsIgnoreCase("GRANATE")) {
                    e.setCancelled(true);

                    for (i = 0; i < 4; ++i) {
                        switch (i) {
                            case 0:
                                fb = e.getBlock().getWorld().spawnFallingBlock(e.getBlock().getLocation(), Material.COAL_BLOCK, (byte) 77);
                                fb.setCustomName("SPLITTER");
                                fb.setVelocity(new Vector(0.5D, 0.05D, 0.0D));
                                break;
                            case 1:
                                fb2 = e.getBlock().getWorld().spawnFallingBlock(e.getBlock().getLocation(), Material.COAL_BLOCK, (byte) 77);
                                fb2.setCustomName("SPLITTER");
                                fb2.setVelocity(new Vector(-0.5D, 0.05D, 0.0D));
                                break;
                            case 2:
                                fb3 = e.getBlock().getWorld().spawnFallingBlock(e.getBlock().getLocation(), Material.COAL_BLOCK, (byte) 77);
                                fb3.setCustomName("SPLITTER");
                                fb3.setVelocity(new Vector(0.0D, 0.05D, 0.5D));
                                break;
                            case 3:
                                fb4 = e.getBlock().getWorld().spawnFallingBlock(e.getBlock().getLocation(), Material.COAL_BLOCK, (byte) 77);
                                fb4.setCustomName("SPLITTER");
                                fb4.setVelocity(new Vector(0.0D, 0.05D, -0.5D));
                        }

                        e.getBlock().getWorld().createExplosion(e.getBlock().getLocation(), 20.0F);
                    }
                }
            }
        } catch (Exception ignored) {
        }
    }

}
