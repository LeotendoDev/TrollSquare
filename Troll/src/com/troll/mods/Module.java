package com.troll.mods;

import org.bukkit.entity.Player;

import com.troll.start.*;

public abstract class Module {
	public String name;
	public String author;
	public String description;
	
	public Module(String name, String author, String description) {
		this.name = name;
		this.author = author;
		this.description = description;
	}
	
	public abstract boolean execute(Player p, String[] args);
	
	public static Module getModuleByName(String name) {
		for (Module mod : Start.getInstance().getModuleManager().getModules()) {
			if (mod.name.equalsIgnoreCase(name))
				return mod;
		}
		
		return null;
	}
}