package com.tenjava.entries.CoderMusgrove.t3.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.tenjava.entries.CoderMusgrove.t3.TenJava;
import com.tenjava.entries.CoderMusgrove.t3.handler.randomevent.RandomEvent;
import com.tenjava.entries.CoderMusgrove.t3.handler.randomevent.RandomEventAngryWolf;
import com.tenjava.entries.CoderMusgrove.t3.handler.randomevent.RandomEventBonusChest;
import com.tenjava.entries.CoderMusgrove.t3.handler.randomevent.RandomEventCreeperSpawn;
import com.tenjava.entries.CoderMusgrove.t3.handler.randomevent.RandomEventLightning;
import com.tenjava.entries.CoderMusgrove.t3.handler.randomevent.RandomEventPoop;
import com.tenjava.entries.CoderMusgrove.t3.handler.randomevent.RandomEventRainDogs;
import com.tenjava.entries.CoderMusgrove.t3.handler.randomevent.RandomEventSinkhole;
import com.tenjava.entries.CoderMusgrove.t3.handler.randomevent.RandomEventSpawnLava;
import com.tenjava.entries.CoderMusgrove.t3.handler.randomevent.RandomEventTakeEntityVelocity;
import com.tenjava.entries.CoderMusgrove.t3.handler.randomevent.RandomEventWaterWave;
import com.tenjava.entries.CoderMusgrove.t3.handler.randomevent.RandomEventWind;

/**
 * A handler for all of the random events that are going to occur.
 * 
 * @author CoderMusgrove
 */
public class RandomEventHandler {

	private List<BukkitRunnable> runnable = new ArrayList<>();
	private Random random = new Random();
	private List<RandomEvent> randomEvents = new ArrayList<>();

	/**
	 * Anything needed to initialize the Random Events
	 */
	private RandomEventHandler() {
		TenJava plugin = TenJava.getInstance();
		if (plugin.getRandomLightning()) randomEvents.add(new RandomEventLightning());
		if (plugin.getRandomCreepers()) randomEvents.add(new RandomEventCreeperSpawn());
		if (plugin.getRandomSpawnLava()) randomEvents.add(new RandomEventSpawnLava());
		if (plugin.getRandomRainDogs()) randomEvents.add(new RandomEventRainDogs());
		if (plugin.getRandomPoop()) randomEvents.add(new RandomEventPoop());
		if (plugin.getRandomWaterWave()) randomEvents.add(new RandomEventWaterWave());
		if (plugin.getRandomSinkhole()) randomEvents.add(new RandomEventSinkhole());
		if (plugin.getRandomTakeEntityVelocity()) {
			RandomEventTakeEntityVelocity e = new RandomEventTakeEntityVelocity();
			plugin.getServer().getPluginManager().registerEvents(e, plugin);
			randomEvents.add(e);
		}
		if (plugin.getRandomBonusChests()) randomEvents.add(new RandomEventBonusChest());
		if (plugin.getRandomWind()) randomEvents.add(new RandomEventWind());
		if (plugin.getRandomAngryWolf()) randomEvents.add(new RandomEventAngryWolf());
		initRunnable();
	}

	/**
	 * Initializes the RandomEvent Handler
	 */
	public static RandomEventHandler initialize() {
		return new RandomEventHandler();
	}

	/**
	 * Returns the list of runnables used.
	 * 
	 * @return
	 */
	public List<BukkitRunnable> getRunnables() {
		return runnable;
	}

	/**
	 * Initializes the runnable so that it can run the random events randomly
	 */
	private void initRunnable() {
		for (int i = 0; i < TenJava.getInstance().getThreads(); i++) {
			runnable.add(new BukkitRunnable() {
				@Override
				public void run() {
					World w = Bukkit.getWorlds().get(random.nextInt(Bukkit.getWorlds().size()));
					List<Player> players = w.getPlayers();
					if (players.isEmpty()) return;
					Player p = players.get(random.nextInt(players.size()));
					int r = random.nextInt(randomEvents.size() + 1);
					if (r == randomEvents.size()) return;
					RandomEvent event = randomEvents.get(r);
					event.runRandomEvent(p);
				}
			});
		}
		for (int i = 0; i < runnable.size(); i++)
			runnable.get(i).runTaskTimer(TenJava.getInstance(), 20, TenJava.getInstance().getDelay());
	}
}
