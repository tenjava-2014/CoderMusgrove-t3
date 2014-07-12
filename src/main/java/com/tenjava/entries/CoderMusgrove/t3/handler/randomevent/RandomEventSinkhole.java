package com.tenjava.entries.CoderMusgrove.t3.handler.randomevent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 * Random sinkholes will occur! Be VERY careful!
 * 
 * @author CoderMusgrove
 */
public class RandomEventSinkhole implements RandomEvent {

	private Random random = new Random();
	private Material[] noFall = { Material.BEDROCK, Material.BEACON, Material.BOOKSHELF, Material.OBSIDIAN, Material.ENCHANTMENT_TABLE, Material.CHEST };
	private List<Location> fall = new ArrayList<>();

	@Override
	@SuppressWarnings("deprecation")
	public void runRandomEvent(Player p) {
		Location loc = p.getLocation();
		World w = loc.getWorld();
		Location underneath = new Location(w, loc.getX(), loc.getY() - 1, loc.getZ());
		if (underneath.getBlock().getType() == Material.AIR) return;

		int size = random.nextInt(11);
		if (size == 10) size = 5;
		else if (size > 6) size = 2;
		else if (size > 0) size = 1;
		else size = 0;
		if (size == 0) return;

		Location point1 = new Location(w, loc.getX() - size, loc.getY() - size, loc.getZ() - size);
		Location point2 = new Location(w, loc.getX() + size, loc.getY(), loc.getZ() + size);
		
		for (int x = point1.getBlockX(); x < point2.getBlockX(); x++) {
			for (int y = point1.getBlockY(); y < point2.getBlockY(); y++) {
				for (int z = point1.getBlockZ(); z < point2.getBlockZ(); z++) {
					Location currentLoc = new Location(w, x, y, z);
					Block currentBlock = currentLoc.getBlock();
					if (Arrays.asList(noFall).contains(currentBlock.getType())) continue;
					int vanish = random.nextInt(3);
					if (vanish > 0) currentBlock.setType(Material.AIR);
					else fall.add(currentLoc);
				}
			}
		}

		for (Location fallLoc : fall) {
			Block b = fallLoc.getBlock();
			Material mat = b.getType();
			byte data = b.getData();
			b.setType(Material.AIR);
			w.spawnFallingBlock(b.getLocation(), mat, data);
			w.playSound(fallLoc, Sound.ZOMBIE_WOODBREAK, 3f, 1f);
		}
		fall.clear();
	}
}
