package com.troll.mods;

import org.bukkit.entity.Player;

import com.troll.man.HelpManager;
import com.troll.start.Start;

public class HelpModule extends Module {

	public HelpModule() {
		super("Help", "Leotendo", "Zeige die Hilfe an");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		HelpManager helpMgr = new HelpManager();
		
		if (args.length > 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #help <page>");
			return false;
		}
		
		try {
			int page = Integer.parseInt(args[1]);
			helpMgr.sendHelp(p, page);
			return true;
		} catch (NumberFormatException e) {
			if (Module.getModuleByName(args[1]) != null) {
				helpMgr.describeModule(Module.getModuleByName(args[1]), p);
				return true;
			}
			
			p.sendMessage(Start.getInstance().getPrefix() + "§cDas Modul §e" + args[1] + " §cexistiert nicht :/");
		}
		
		return true;
	}
}
