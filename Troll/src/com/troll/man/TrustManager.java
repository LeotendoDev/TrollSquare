package com.troll.man;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.troll.start.Start;
import com.troll.utils.ScoreboardUtil;

public class TrustManager {
	private ArrayList<UUID> trustedPlayers;
	private String trustCommand;
	
	public TrustManager(String command) {
		this.trustedPlayers = new ArrayList<UUID>();
		this.trustCommand = command;
	}
	
	public void trustPlayer(Player p) {
		if (trustedPlayers.contains(p.getUniqueId())) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cDu bist bereits getrustet");
			return;
		}
		
		p.sendMessage(Start.getInstance().getPrefix() + "§aHura! Du kannst nun §c# §aBefehle ausführen!");
		trustedPlayers.add(p.getUniqueId());
	}
	
	public ArrayList<UUID> getTrustedPlayers() {
		return this.trustedPlayers;
	}
	
	public String getTrustCommand() {
		return this.trustCommand;
	}
}
