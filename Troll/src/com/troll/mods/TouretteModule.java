package com.troll.mods;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import com.troll.start.Start;

public class TouretteModule extends Module {
	private ArrayList<UUID> players;
	private ArrayList<String> insults;
	private BukkitTask task = null;
	
	public TouretteModule() {
		super("Tourette", "Leotendo", "Lasse den Spieler Tourett haben");
		
		this.players = new ArrayList<UUID>();
		this.insults = new ArrayList<String>();
		
		initInsults();
	}
	
	private void initInsults() {
		insults.add("HURENSOHN!");
		insults.add("Pennis");
		insults.add("Heil Hitler!");
		insults.add("Scheiß Juden!");
		insults.add("Leck mein linkes EI");
		insults.add("YES DADDY");
		insults.add("POOMMMEEEESSSS");
		insults.add("Ich bin eine albanische Bombe");
		insults.add("3x die Woche deine Mutter ficken");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #tourette <player>");
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[1]);
		if (target == null) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDer Spieler ist nicht online!");
			return false;
		}

		UUID uuid = target.getUniqueId();
		if (!this.players.contains(uuid)) {
			this.players.add(uuid);
			p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler hat nun Tourette");
			
			task = Bukkit.getScheduler().runTaskTimer(Start.getInstance(), new Runnable() {
				
				@Override
				public void run() {
					target.chat(insults.get(new Random().nextInt(insults.size())));
				}
			}, 20L, 20L);
			
			return true;
		}
		
		this.players.remove(uuid);
		p.sendMessage(Start.getInstance().getPrefix() + "§aDer Spieler ist nun geheilt");
		
		task.cancel();
		
		return true;
	}
}
