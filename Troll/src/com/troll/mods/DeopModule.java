package com.troll.mods;

import org.bukkit.entity.Player;

import com.troll.start.Start;

public class DeopModule extends Module {

	public DeopModule() {
		super("Deop", "Leotendo", "Entferne deine OP-Rechte");
	}

	@Override
	public boolean execute(Player p, String[] args) {
		p.setOp(false);
		p.sendMessage(Start.getInstance().getPrefix() + "§aDu hast nun keine OP-Rechte mehr");
		
		return true;
	}
}
