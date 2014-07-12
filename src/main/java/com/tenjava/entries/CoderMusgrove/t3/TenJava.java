package com.tenjava.entries.CoderMusgrove.t3;

import org.bukkit.plugin.java.JavaPlugin;

public class TenJava extends JavaPlugin {

	private static TenJava instance;

	@Override
	public void onEnable() {
		instance = this;
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
