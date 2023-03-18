package com.troll.mods;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.troll.start.Start;

public class HideCommandModule extends Module implements Listener {
	private String command = "";
	private String output = "";
	
	public HideCommandModule() {
		super("Hidecommand", "Leotendo", "Verstecke das Plugin hinter einem Command");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length < 3) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #hidecommand <command> <output>");
			return false;
		}
		
		this.output = "";
		
		this.command = args[1];
		for (int i = 2; i < args.length; i++) {
			this.output += args[i] + " ";
		}
		
		return true;
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		if (this.command == "") return;
		if (this.output == "") return;
		
		if (e.getMessage().equalsIgnoreCase("/" + command)) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(this.output.replace("&", "§"));
		}
	}
}
