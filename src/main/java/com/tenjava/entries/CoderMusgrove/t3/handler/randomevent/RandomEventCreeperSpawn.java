package com.tenjava.entries.CoderMusgrove.t3.handler.randomevent;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.tenjava.entries.CoderMusgrove.t3.TenJava;

/**
 * A random event in which a creeper will randomly spawn in a 20 block radius of you.
 * 
 * But don't fear! It will spawn at the uppermost solid block, so it won't get
 * into your house. ;)
 * 
 * @author CoderMusgrove
 * 
 */
public class RandomEventCreeperSpawn implements RandomEvent {

	private Random random = new Random();

	@Override
	public void runRandomEvent(Player p) {
		Location loc = p.getLocation();

		int rx = random.nextInt(40) - 20 + loc.getBlockX();
		int rz = random.nextInt(40) - 20 + loc.getBlockZ();
		int y = loc.getWorld().getHighestBlockYAt(rx, rz);
		

		Location target = new Location(loc.getWorld(), rx, y, rz);
		Creeper creeper = (Creeper) target.getWorld().spawnEntity(target, EntityType.CREEPER);
		p.playSound(creeper.getLocation(), Sound.AMBIENCE_CAVE, 2f, 1f);
		if (TenJava.getInstance().getRandomCreeperCharged()) {
			boolean charged = random.nextBoolean();
			if (charged) creeper.setPowered(true);
		}
	}
}
