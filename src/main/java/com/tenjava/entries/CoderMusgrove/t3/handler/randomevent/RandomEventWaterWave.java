package com.tenjava.entries.CoderMusgrove.t3.handler.randomevent;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.tenjava.entries.CoderMusgrove.t3.TenJava;

/**
 * Creates a random water wave while swimming, just for a nice little effect
 * 
 * @author CoderMusgrove
 * 
 */
public class RandomEventWaterWave implements RandomEvent {

	private List<Location> blockLocs = new ArrayList<>();

	@Override
	public void runRandomEvent(Player p) {
		Location loc = p.getLocation();
		if (loc.getBlock().getType() != Material.STATIONARY_WATER) return;
		World w = loc.getWorld();

		Location point1 = new Location(w, loc.getX() - 1, loc.getY() + 1, loc.getZ() - 1);
		Location point2 = new Location(w, loc.getX() + 1, loc.getY() + 1, loc.getZ() + 1);

		for (int x = point1.getBlockX(); x < point2.getBlockX(); x++) {
			for (int z = point1.getBlockZ(); z < point2.getBlockZ(); z++) {
				Location currentLoc = new Location(w, x, loc.getY() + 1, z);
				Block currentBlock = currentLoc.getBlock();
				if (currentBlock.getType() != Material.AIR) continue;
				blockLocs.add(currentLoc);
				currentBlock.setType(Material.WATER);
			}
		}

		new BukkitRunnable() {
			@Override
			public void run() {
				for (Location loc : blockLocs) {
					Block b = loc.getBlock();
					if (b.getType() == Material.STATIONARY_WATER) b.setType(Material.AIR);
				}
				blockLocs.clear();
			}
		}.runTaskLater(TenJava.getInstance(), 20 * 1);
	}
}
