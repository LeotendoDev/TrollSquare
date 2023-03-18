package com.troll.man;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import com.troll.mods.Module;
import com.troll.start.Start;

public class HelpManager {

	public void sendHelp(Player p, int page) {
		if (page == 0)
			page = 1;
		
		ArrayList<Module> modules = Start.getInstance().getModuleManager().getModules();
		
		int max = modules.size() / 5 + 1;
		if (page > max) {
			p.sendMessage(Start.getInstance().getPrefix() + "§cZu hohe Seiten anzahl!");
			return;
		}
		
		int i;
		for (i = 0; i < 40; i++) {
			p.sendMessage("");
		}
		
		p.sendMessage("§6====== §7(§a" + page + "§7/§a" + (modules.size() / 5 + 1) + "§7) §6======");
		
		for (i = (page - 1) * 5; i < (page - 1) * 5 + 5; i++) {
			p.sendMessage(Start.getInstance().getPrefix() + "§5#" + modules.get(i).name.toLowerCase() + "§7 - §a" + modules.get(i).description);
		}
	}
	
	public void describeModule(Module mod, Player p) {
		p.sendMessage(Start.getInstance().getPrefix() + "§eBeschreibung§7: §a" + mod.description);
	}
}
