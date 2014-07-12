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
	private RandomEventHandler randomEventHandler;
	private int delay = 5;
	private boolean lightning = true;
	private boolean creeper, chargedcreeper = true;
	private boolean rainDogs = true;
	private boolean lava = true;
	private boolean poop = true;
	private boolean waterWave = true;
	private boolean sinkhole = true;

	@Override
	public void onEnable() {
		saveDefaultConfig();
		instance = this;
		loadValues();
		randomEventHandler = RandomEventHandler.initialize();
	}

	@Override
	public void onDisable() {
		randomEventHandler.getRunnable().cancel();
	}

	/**
	 * Returns an instance of the TenJava plugin
	 * 
	 * @return
	 */
	public static TenJava getInstance() {
		return instance;
	}

	/**
	 * Gets the delay for each loop of the random event
	 */
	public int getDelay() {
		return delay;
	}

	/**
	 * Returns if random lightning will be used.
	 * 
	 * @return
	 */
	public boolean getRandomLightning() {
		return lightning;
	}

	/**
	 * Returns if random creepers will spawn in daylight
	 * 
	 * @return
	 */
	public boolean getRandomCreepers() {
		return creeper;
	}

	/**
	 * Returns if random creepers will have the potential to become charged.
	 * 
	 * This will not work if random creepers aren't even enabled.
	 * 
	 * @return
	 */
	public boolean getRandomCreeperCharged() {
		return chargedcreeper;
	}

	/**
	 * Returns if it will rain dogs
	 * 
	 * @return
	 */
	public boolean getRandomRainDogs() {
		return rainDogs;
	}

	/**
	 * Returns if lava will randomly spawn
	 * 
	 * @return
	 */
	public boolean getRandomSpawnLava() {
		return lava;
	}

	/**
	 * Returns if the player will poop
	 * 
	 * @return
	 */
	public boolean getRandomPoop() {
		return poop;
	}

	/**
	 * Returns if a random water wave will occur
	 * 
	 * @return
	 */
	public boolean getRandomWaterWave() {
		return waterWave;
	}

	/**
	 * Returns if random sinkholes may occur
	 * 
	 * @return
	 */
	public boolean getRandomSinkhole() {
		return sinkhole;
	}

	/**
	 * Loads the values from the config.
	 * 
	 * Each value is handled with a try and catch just in case something goes
	 * wrong
	 */
	private void loadValues() {
		try {
			delay = getConfig().getInt("delay");
		} catch (Exception e) {
			System.out.println("Error loading 'delay' from config.yml! Using the default value (true)");
		}

		try {
			lightning = getConfig().getBoolean("lightning");
		} catch (Exception e) {
			System.out.println("Error loading 'lighting' from config.yml! Using the default value (true)");
		}

		try {
			creeper = getConfig().getBoolean("creeper");
		} catch (Exception e) {
			System.out.println("Error loading 'creeper' from config.yml! Using the default value (true)");
		}

		try {
			chargedcreeper = getConfig().getBoolean("charged-creeper");
		} catch (Exception e) {
			System.out.println("Error loading 'charged-creeper' from config.yml! Using the default value (true)");
		}

		try {
			rainDogs = getConfig().getBoolean("rain-dogs");
		} catch (Exception e) {
			System.out.println("Error loading 'rain-dogs' from config.yml! Using the default value (true)");
		}

		try {
			lava = getConfig().getBoolean("lava");
		} catch (Exception e) {
			System.out.println("Error loading 'lava' from config.yml! Using the default value (true)");
		}

		try {
			poop = getConfig().getBoolean("poop");
		} catch (Exception e) {
			System.out.println("Error loading 'poop' from config.yml! Using the default value (true)");
		}

		try {
			waterWave = getConfig().getBoolean("waterwave");
		} catch (Exception e) {
			System.out.println("Error loading 'waterwave' from config.yml! Using the default value (true)");
		}

		try {
			sinkhole = getConfig().getBoolean("sinkholes");
		} catch (Exception e) {
			System.out.println("Error loading 'sinkholes' from config.yml! Using the default value (true)");
		}
	}
}
