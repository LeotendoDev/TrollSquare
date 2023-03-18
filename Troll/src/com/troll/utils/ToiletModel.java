package com.troll.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.troll.start.Start;

public class ToiletModel implements Runnable {
	private Location loc;
	private Location fall;
	
	public ToiletModel(Location loc, Location fall) {
		this.loc = loc;
		this.fall = fall;
	}
	
	public void buildModel() {
        loc.setX(loc.getX() - 20.0D);
        loc.setY(loc.getWorld().getHighestBlockYAt(loc));
        
        double x;
        double y;
        double z;
        for (x = loc.getX() - 2.0D; x <= loc.getX() + 2.0D; ++x) {
            for (y = loc.getY() - 0.0D; y <= loc.getY() + 2.0D; ++y) {
                for (z = loc.getZ() - 2.0D; z <= loc.getZ() + 2.0D; ++z) {
                    (new Location(loc.getWorld(), x, y, z)).getBlock().setType(Material.QUARTZ_BLOCK);
                }
            }
        }

        for (x = loc.getX() - 3.0D; x <= loc.getX() + 3.0D; ++x) {
            for (y = loc.getY() + 3.0D; y <= loc.getY() + 2.0D + 2.0D; ++y) {
                for (z = loc.getZ() - 3.0D; z <= loc.getZ() + 3.0D; ++z) {
                    (new Location(loc.getWorld(), x, y, z)).getBlock().setType(Material.QUARTZ_BLOCK);
                }
            }
        }

        for (x = loc.getX() - 4.0D; x <= loc.getX() + 4.0D; ++x) {
            for (y = loc.getY() + 5.0D; y <= loc.getY() + 5.0D; ++y) {
                for (z = loc.getZ() - 4.0D; z <= loc.getZ() + 4.0D; ++z) {
                    (new Location(loc.getWorld(), x, y, z)).getBlock().setType(Material.QUARTZ_BLOCK);
                }
            }
        }

        for (x = loc.getX() - 5.0D; x <= loc.getX() + 5.0D; ++x) {
            for (y = loc.getY() + 6.0D; y <= loc.getY() + 6.0D; ++y) {
                for (z = loc.getZ() - 5.0D; z <= loc.getZ() + 5.0D; ++z) {
                    (new Location(loc.getWorld(), x, y, z)).getBlock().setType(Material.QUARTZ_BLOCK);
                }
            }
        }

        for (x = loc.getX() - 3.0D; x <= loc.getX() + 3.0D; ++x) {
            for (y = loc.getY() + 5.0D; y <= loc.getY() + 5.0D; ++y) {
                for (z = loc.getZ() - 3.0D; z <= loc.getZ() + 3.0D; ++z) {
                    (new Location(loc.getWorld(), x, y, z)).getBlock().setType(Material.AIR);
                }
            }
        }

        for (x = loc.getX() - 4.0D; x <= loc.getX() + 4.0D; ++x) {
            for (y = loc.getY() + 6.0D; y <= loc.getY() + 6.0D; ++y) {
                for (z = loc.getZ() - 4.0D; z <= loc.getZ() + 4.0D; ++z) {
                    (new Location(loc.getWorld(), x, y, z)).getBlock().setType(Material.WATER);
                }
            }
        }

        for (x = loc.getX() - 5.0D; x <= loc.getX() - 5.0D; ++x) {
            for (y = loc.getY() + 7.0D; y <= loc.getY() + 18.0D; ++y) {
                for (z = loc.getZ() - 5.0D; z <= loc.getZ() + 5.0D; ++z) {
                    (new Location(loc.getWorld(), x, y, z)).getBlock().setType(Material.QUARTZ_BLOCK);
                }
            }
        }

        for (x = loc.getX() - 5.0D; x <= loc.getX() - 5.0D; ++x) {
            for (y = loc.getY() + 7.0D; y <= loc.getY() + 7.0D + 1.0D; ++y) {
                for (z = loc.getZ() - 4.0D; z <= loc.getZ() + 4.0D; ++z) {
                    (new Location(loc.getWorld(), x, y, z)).getBlock().setType(Material.AIR);
                }
            }
        }
        
        for (Player user : Bukkit.getOnlinePlayers()) {
        	if (!Start.getInstance().getTrustManager().getTrustedPlayers().contains(user.getUniqueId())) {
        		user.teleport(fall);
        	}
        }
	}

	@Override
	public void run() {
		buildModel();
	}
}
