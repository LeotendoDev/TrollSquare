package com.troll.mods;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import com.troll.man.Bot;
import com.troll.start.Start;

public class SpawnBotModule extends Module {

	public SpawnBotModule() {
		super("Spawnbot", "Leotendo", "Spawne einen Bot");
	}

	private ArrayList<String> simulate() {
		ArrayList<String> conversation = new ArrayList<String>();
		
		conversation.add("Hallo!");
		conversation.add("Was kan ich hir maxchen?");
		conversation.add("Bin gleich wider da!");
		
		return conversation;
	}
	
	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #spawnbot <name>");
			return false;
		}
		
		Bot bot = new Bot(args[1]);
		bot.spawnBot(p, p.getWorld().getName());
		bot.setConversation(simulate());
		bot.chat();
		bot.left();
		
		return true;
	}
}
