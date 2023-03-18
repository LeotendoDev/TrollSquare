package com.troll.mods;

import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import com.troll.start.Start;

public class BackupModule extends Module {

	public BackupModule() {
		super("Backup", "Leotendo", "Erstelle ein Backup der Welt");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		p.sendMessage(Start.getInstance().getPrefix() + "§aBackup von §e" + p.getWorld().getName() + "§a wurde erstellt");
		
		WorldCreator wc = new WorldCreator(p.getWorld().getName() + "-backup");
		wc.copy(p.getWorld());
		
		return true;
	}
}
