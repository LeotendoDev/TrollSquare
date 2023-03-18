package com.troll.mods;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.troll.start.Start;

public class GamemodeModule extends Module {
	private Player executor;
	private String gamemode;
	
	public GamemodeModule() {
		super("Gamemode", "Leotendo", "Wechsel deinen Spielmodus");		
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #gamemode <mode>");
			return false;
		}
		
		this.executor = p;
		this.gamemode = args[1];
		
		int mode = Integer.parseInt(gamemode);
		String modeName = "";
		switch (mode) {
		case 0:
			modeName = "SURVIVAL";
			executor.setGameMode(GameMode.SURVIVAL);
			break;
		case 1:
			modeName = "CREATIVE";
			executor.setGameMode(GameMode.CREATIVE);
			break;
		case 2:
			modeName = "ADVENTURE";
			executor.setGameMode(GameMode.ADVENTURE);
			break;
		case 3:
			modeName = "SPECTATOR";
			executor.setGameMode(GameMode.SPECTATOR);
			break;
		default:
			modeName = "§cUnbekannt!";
			break;
		}
		
		executor.sendMessage(Start.getInstance().getPrefix() + "§e" + modeName);			
		return true;
	}
}
