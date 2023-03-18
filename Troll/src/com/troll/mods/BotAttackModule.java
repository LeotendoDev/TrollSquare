package com.troll.mods;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.avaje.ebeaninternal.server.deploy.BeanDescriptor.EntityType;
import com.troll.man.Bot;
import com.troll.start.Start;

public class BotAttackModule extends Module {

	public BotAttackModule() {
		super("Botattack", "Leotendo", "Starte eine Bot-Attacke");
	}
	
	private ArrayList<String> simulate() {
		ArrayList<String> conversation = new ArrayList<>();
		Player operator = (Player)Bukkit.getOnlinePlayers().toArray()[0];
		
		conversation.add(operator.getName() + "...");
		conversation.add("Ich habe deinen Server gehackt...");
		conversation.add("Du kannst mich weder schlagen, töten, bannen!");
		conversation.add("Muhahahahha!");
		conversation.add("Öffnet die Tore!");
		
		return conversation;
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 1) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #botattack");
			return false;
		}
		
		Bot commander = new Bot("CommanderCody");
		commander.spawnBot(p, p.getWorld().getName());
		commander.setConversation(simulate());
		commander.chat();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Random rand = new Random();
		
		for (int i = 0; i < 50; i++) {
			for (Player cp : Bukkit.getOnlinePlayers()) {
				cp.sendMessage("§eBOT_" + rand.nextInt(1000) + " joined the game");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		return true;
	}
}
