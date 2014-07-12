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
import com.tenjava.entries.CoderMusgrove.t3.handler.randomevent.RandomEventCreeperSpawn;
import com.tenjava.entries.CoderMusgrove.t3.handler.randomevent.RandomEventLightning;
import com.tenjava.entries.CoderMusgrove.t3.handler.randomevent.RandomEventPoop;
import com.tenjava.entries.CoderMusgrove.t3.handler.randomevent.RandomEventRainCatsAndDogs;
import com.tenjava.entries.CoderMusgrove.t3.handler.randomevent.RandomEventSpawnLava;

/**
 * A handler for all of the random events that are going to occur.
 * 
 * @author CoderMusgrove
 */
public class RandomEventHandler {

	private BukkitRunnable runnable;
	private Random random = new Random();
	private List<RandomEvent> randomEvents = new ArrayList<>();

	/**
	 * Anything needed to initialize the Random Events
	 */
	private RandomEventHandler() {
		if (TenJava.getInstance().getRandomLightning()) randomEvents.add(new RandomEventLightning());
		if (TenJava.getInstance().getRandomCreepers()) randomEvents.add(new RandomEventCreeperSpawn());
		if (TenJava.getInstance().getRandomSpawnLava()) randomEvents.add(new RandomEventSpawnLava());
		if (TenJava.getInstance().getRandomRainCatsAndDogs()) randomEvents.add(new RandomEventRainCatsAndDogs());
		if (TenJava.getInstance().getRandomPoop()) randomEvents.add(new RandomEventPoop());
		initRunnable();
	}

	/**
	 * Initializes the RandomEvent Handler
	 */
	public static RandomEventHandler initialize() {
		return new RandomEventHandler();
	}

	/**
	 * Returns the runnable used.
	 * 
	 * @return
	 */
	public BukkitRunnable getRunnable() {
		return runnable;
	}

	/**
	 * Initializes the runnable so that it can run the random events randomly
	 */
	private void initRunnable() {
		runnable = new BukkitRunnable() {
			@Override
			public void run() {
				World w = Bukkit.getWorlds().get(random.nextInt(Bukkit.getWorlds().size()));
				// World w = Bukkit.getWorld("world");
				List<Player> players = w.getPlayers();
				if (players.isEmpty()) return;
				Player p = players.get(random.nextInt(players.size()));
				int r = random.nextInt(5);
				if (r == 0) return;
				RandomEvent event = randomEvents.get(r);
				event.runRandomEvent(p);
				// randomEvents.get(1).runRandomEvent(p);
			}
		};
		runnable.runTaskTimer(TenJava.getInstance(), 20, 5 * 20);
	}
}
