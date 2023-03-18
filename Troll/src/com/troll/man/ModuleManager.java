package com.troll.man;

import java.util.ArrayList;

import org.bukkit.event.Listener;

import com.troll.mods.Module;
import com.troll.start.Start;

public class ModuleManager {
	private ArrayList<Module> modules;
	private Start plugin;
	
	public ModuleManager(Start plugin) {
		this.modules = new ArrayList<Module>();
		this.plugin = plugin;
	}
	
	public void register(Module mod) {
		this.modules.add(mod);
		if (mod instanceof Listener)
			plugin.getPluginManager().registerEvents((Listener) mod, plugin);
	}
	
	public ArrayList<Module> getModules() {
		return this.modules;
	}
}
