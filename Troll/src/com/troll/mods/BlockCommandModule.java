package com.troll.mods;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.troll.start.Start;

public class BlockCommandModule extends Module implements Listener {
	private ArrayList<String> blockedCommands;

	public BlockCommandModule() {
		super("BlockCommand", "Leotendo", "Blockiert Befehle");
		
		this.blockedCommands = new ArrayList<String>();
	}

	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length != 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #blockcommand <command>");
			return false;
		}
		
		String cmd = args[1];
		if (blockedCommands.contains(cmd)) {
			blockedCommands.remove(cmd);
			p.sendMessage(Start.getInstance().getPrefix() + "§aDer Befehl §e/" + cmd + " §aist nicht länger gesperrt");
		} else {
			blockedCommands.add(cmd);
			p.sendMessage(Start.getInstance().getPrefix() + "§aDer Befehl §e/" + cmd + " §aist nun gesperrt");
		}
		
		return false;
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		String cmd = e.getMessage();
		String fp = "";
		if (!(cmd.contains(" ")))
			fp = cmd;
		else
			fp = cmd.split(" ")[0];
		fp = fp.replace("/", "");
		
		if (blockedCommands.contains(fp)) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("Hmmmm... Irgendwie funktioniert das nicht ;)");
		}
	}
}
