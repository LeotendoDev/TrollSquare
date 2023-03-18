package com.troll.utils;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

import org.bukkit.Bukkit;

public class ConsoleFilter {

	public void create() {
		Bukkit.getServer().getLogger().addHandler(new Handler() {
			
			@Override
			public void publish(LogRecord record) {
			}
			
			@Override
			public void flush() {
				
			}
			
			@Override
			public void close() throws SecurityException {
			}
		});
	}
}
