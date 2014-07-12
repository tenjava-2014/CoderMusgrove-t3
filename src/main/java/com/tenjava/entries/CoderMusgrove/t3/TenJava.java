package com.tenjava.entries.CoderMusgrove.t3;

import org.bukkit.plugin.java.JavaPlugin;

import com.tenjava.entries.CoderMusgrove.t3.handler.RandomEventHandler;

/**
 * The main class for my TenJava plugin.
 * 
 * THEME: What random events can occur in Minecraft?
 * 
 * @author CoderMusgrove
 */
public class TenJava extends JavaPlugin {

	private static TenJava instance;

	@Override
	public void onEnable() {
		instance = this;
		RandomEventHandler.initialize();
	}

	/**
	 * Returns an instance of the TenJava plugin
	 * 
	 * @return
	 */
	public static TenJava getInstance() {
		return instance;
	}
}
