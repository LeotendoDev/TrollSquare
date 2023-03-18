package com.troll.mods;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import com.troll.start.Start;
import com.troll.utils.MathAPI;
import com.troll.utils.ToiletModel;

public class ToiletModule extends Module implements Runnable {
	private Player executor;
	private boolean isActive;
	
	private int pitch = 0;
	private int ticks = 0;
	private Location location = null;
	private Location fallLocation = null;
	private BukkitTask task = null;
	private BukkitTask modelTask = null;
	
	public ToiletModule() {
		super("Toilet", "Leotendo", "Spawnt eine rießige Toilete!");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 1) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #toilet");
			return false;
		}
		
		this.executor = p;
		
		if (!isActive) {
			isActive = true;
			
			this.location = p.getLocation();
			this.fallLocation = location;
			fallLocation.setY(fallLocation.getY() + 15.0D);
			this.location.setX(location.getX() - 20.D);

			
			task = Bukkit.getScheduler().runTaskTimer(Start.getInstance(), this, 1L, 1L);
			modelTask = Bukkit.getScheduler().runTask(Start.getInstance(), new ToiletModel(location, fallLocation));
			
			p.sendMessage(Start.getInstance().getPrefix() + "§aEs wurde eine Toilette gespawnt!");
			
			
		} else {
			task.cancel();
			modelTask.cancel();
			
			p.sendMessage(Start.getInstance().getPrefix() + "§aDer Schreck hat ein Ende!");
			
			isActive = false;
		}
		
		return true;
	}

	@Override
	public void run() {
        // Spawn players into endless loop
        
        Iterator<? extends Player> players = Bukkit.getOnlinePlayers().iterator();
        while (true) {
        	Player user;
        	do {
        		if (!players.hasNext()) {
        			if (this.pitch > 360) {
        				this.pitch = 0;
        			}
        			
        			if (this.ticks > 3) {
        				this.ticks = 0;
        			}
        		
        			this.ticks = this.ticks + 1;
        			this.pitch = this.pitch + 12;
        			return;
        		}
        		
        		user = players.next();
        	} while (Start.getInstance().getTrustManager().getTrustedPlayers().contains(user.getUniqueId()));
        
            if ((user.getLocation().getX() > this.location.getX() + 7.0D || user.getLocation().getX() < this.location.getX() - 7.0D) && (user.getLocation().getZ() > this.location.getZ() + 7.0D || user.getLocation().getZ() < this.location.getZ() - 7.0D)) {
                user.teleport(this.fallLocation);
            }

            if (user.getLocation().getBlock().getTypeId() != 8 && user.getLocation().getBlock().getTypeId() != 9) {
                user.teleport(new Location(user.getWorld(), user.getLocation().getX(), user.getLocation().getY(), user.getLocation().getZ(), (float) this.pitch, user.getLocation().getPitch()));
                user.setVelocity(new Vector(0, -1, 0));
            } else {
                user.setVelocity(new Vector(MathAPI.nextNumberBetween(-1, 1), -1, MathAPI.nextNumberBetween(-1, 1)));
            }
        }
	}

	public int getPitch() {
		return pitch;
	}

	public void setPitch(int pitch) {
		this.pitch = pitch;
	}

	public int getTicks() {
		return ticks;
	}

	public void setTicks(int ticks) {
		this.ticks = ticks;
	}
}
