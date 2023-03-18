package com.troll.mods;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.troll.start.Start;
  
public class CommandspyModule extends Module implements Listener {
	private boolean isActive = false;
	private Player executor;
	private String prefix = Start.getInstance().getPrefix();

	public CommandspyModule() {
		super("Commandspy", "Leotendo", "Du kannst alle Befehle mit lesen");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 1) {
			p.sendMessage(prefix + "§eBenutze: #commandspy");
			return false;
		}
		
		isActive = !isActive;
		if (isActive)
			p.sendMessage(prefix + "§aNun kannst du alle Befehle mitlesen");
		else
			p.sendMessage(prefix + "§aNun kannst du keine Befehle mehr mitplesen");
		
		this.executor = p;
		
		return true;
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		if (isActive) {
			this.executor.sendMessage(prefix + "§e" + e.getPlayer().getName() + "§7 -> §6" + e.getMessage());
			System.out.println(e.isCancelled());
		}
	}
}
