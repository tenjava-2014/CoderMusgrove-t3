package com.tenjava.entries.CoderMusgrove.t3.handler.randomevent;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Random Event that spawns lightning around a 3-block radius with a 1/9 chance
 * of actually hitting the player!
 * 
 * @author CoderMusgrove
 * 
 */
public class RandomEventLightning implements RandomEvent {

	private Random random = new Random();

	@Override
	public void runRandomEvent(Player p) {
		Location loc = p.getLocation();

		int rx = random.nextInt(6) - 3 + loc.getBlockX();
		int rz = random.nextInt(6) - 3 + loc.getBlockZ();

		Location target = new Location(loc.getWorld(), rx, loc.getWorld().getHighestBlockYAt(loc), rz);
		target.getWorld().strikeLightning(target);
	}
}
