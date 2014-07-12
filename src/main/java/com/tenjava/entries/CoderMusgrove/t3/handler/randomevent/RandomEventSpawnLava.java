package com.tenjava.entries.CoderMusgrove.t3.handler.randomevent;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 * A spawner to spawn lava randomly inside of a radius of stone/cobblestone around 10x2x10
 * 
 * @author CoderMusgrove
 */
public class RandomEventSpawnLava implements RandomEvent {

	private Random random = new Random();
	private Material[] acceptable = { Material.STONE, Material.COBBLESTONE };

	@Override
	public void runRandomEvent(Player p) {
		Location loc = p.getLocation();

		int rx = random.nextInt(20) - 10;
		int ry = random.nextInt(4) - 4;
		int rz = random.nextInt(20) - 10;

		Location target = loc.add(rx, ry, rz);
		Block targetBlock = target.getBlock();
		if (Arrays.asList(acceptable).contains(targetBlock.getType())) target.getBlock().setType(Material.LAVA);
	}
}
