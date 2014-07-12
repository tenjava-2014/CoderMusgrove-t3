package com.tenjava.entries.CoderMusgrove.t3.handler.randomevent;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.metadata.FixedMetadataValue;

import com.tenjava.entries.CoderMusgrove.t3.TenJava;

/**
 * Cats and dogs will randomly rain down upon you!
 * 
 * @author CoderMusgrove
 * 
 */
public class RandomEventRainDogs implements RandomEvent {

	private Random random = new Random();

	@Override
	public void runRandomEvent(Player p) {
		Location loc = p.getLocation();
		if (loc.getWorld().getEnvironment() == Environment.NETHER) return;
		loc.setY(loc.getWorld().getHighestBlockYAt(loc) + 50);

		Location point1 = new Location(loc.getWorld(), loc.getBlockX() - 10, loc.getY(), loc.getBlockZ() - 10);
		Location point2 = new Location(loc.getWorld(), loc.getBlockX() + 10, loc.getY(), loc.getBlockZ() + 10);

		for (int x = point1.getBlockX(); x < point2.getBlockX(); x++) {
			for (int z = point1.getBlockZ(); z < point2.getBlockZ(); z++) {
				int y = loc.getBlockY() - random.nextInt(20);
				Location target = new Location(loc.getWorld(), x, y, z);
				if (target.getBlock().getType() != Material.AIR) continue;
				int selectType = random.nextInt(20);
				EntityType type = null;
				if (selectType != 0) continue;
				type = EntityType.WOLF;
				Wolf wolf = (Wolf) target.getWorld().spawnEntity(target, type);
				wolf.setMetadata("raining", new FixedMetadataValue(TenJava.getInstance(), ""));
			}
		}
	}
}
