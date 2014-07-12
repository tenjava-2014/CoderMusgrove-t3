package com.tenjava.entries.CoderMusgrove.t3.handler.randomevent;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

/**
 * Sometimes man's best friend isn't your best friend, so they'll randomly want
 * to protect their territory and attack you.
 * 
 * @author CoderMusgrove
 */
public class RandomEventAngryWolf implements RandomEvent {

	private Random random = new Random();

	@Override
	public void runRandomEvent(Player p) {
		Location loc = p.getLocation();
		World w = loc.getWorld();

		int rx = random.nextInt(20) - 10 + loc.getBlockX();
		int rz = random.nextInt(20) - 10 + loc.getBlockZ();
		
		Location target = new Location(w, rx, w.getHighestBlockYAt(rx, rz), rz);
		
		Wolf wolf = (Wolf) w.spawnEntity(target, EntityType.WOLF);
		wolf.setAngry(true);
		wolf.setAdult();
		wolf.setTarget(p);
	}
}
