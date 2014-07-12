package com.tenjava.entries.CoderMusgrove.t3.handler.randomevent;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * A random event in which a bonus chest will spawn, being a poor chest, a
 * feasible chest, or a rich chest. Each one contains its own goodies
 * 
 * @author CoderMusgrove
 */
public class RandomEventBonusChest implements RandomEvent {

	private Random random = new Random();

	private Material[] poor = { Material.APPLE, Material.BREAD, Material.WOOD_AXE, Material.WOOD_PICKAXE, Material.WOOD, Material.BONE,
			Material.BOOK, Material.FLINT, Material.FLINT_AND_STEEL, Material.COAL, Material.COBBLESTONE, Material.STONE, Material.WOOD_SWORD,
			Material.MUSHROOM_SOUP, Material.RED_MUSHROOM, Material.BROWN_MUSHROOM, Material.SEEDS, Material.LAVA_BUCKET, Material.WATER_BUCKET,
			Material.GOLD_NUGGET, Material.ROTTEN_FLESH, Material.SPIDER_EYE };

	private Material[] feasible = { Material.RAW_BEEF, Material.RAW_CHICKEN, Material.RAW_FISH, Material.COOKED_BEEF, Material.COOKED_CHICKEN,
			Material.COOKED_FISH, Material.PORK, Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.IRON_INGOT, Material.STONE_AXE,
			Material.IRON_AXE, Material.STONE_HOE, Material.STONE_SWORD, Material.GOLD_INGOT, Material.POTATO_ITEM, Material.CARROT_ITEM,
			Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.GOLD_HELMET, Material.GOLD_BOOTS,
			Material.BOW, Material.ARROW, Material.COOKIE, Material.COCOA, Material.CAKE, Material.GRILLED_PORK };

	private Material[] rich = { Material.DIAMOND, Material.RECORD_10, Material.RECORD_11, Material.RECORD_12, Material.RECORD_3, Material.RECORD_4,
			Material.RECORD_5, Material.RECORD_6, Material.RECORD_7, Material.RECORD_8, Material.RECORD_9, Material.GOLD_CHESTPLATE,
			Material.GOLD_LEGGINGS, Material.EMERALD, Material.EMERALD_ORE, Material.DIAMOND_ORE, Material.BOOK_AND_QUILL, Material.BAKED_POTATO,
			Material.WATCH, Material.COMPASS, Material.ANVIL, Material.CAKE, Material.DIAMOND_HELMET, Material.DIAMOND_BOOTS, Material.TNT,
			Material.REDSTONE_WIRE, Material.REDSTONE_TORCH_ON, Material.SPECKLED_MELON, Material.MELON_SEEDS, Material.PUMPKIN_PIE, Material.MELON,
			Material.MELON_BLOCK, Material.SPONGE, Material.NETHER_WARTS, Material.LEASH, Material.JACK_O_LANTERN };

	@Override
	public void runRandomEvent(Player p) {
		Location loc = p.getLocation();

		int rx = random.nextInt(40) - 20 + loc.getBlockX();
		int rz = random.nextInt(40) - 20 + loc.getBlockZ();

		Location target = new Location(loc.getWorld(), rx, loc.getWorld().getHighestBlockYAt(rx, rz), rz);

		Block b = target.getBlock();
		b.setType(Material.CHEST);

		int level = random.nextInt(11);
		
		if (level == 10) setRichChest(b);
		else if (level > 6) setFeasibleChest(b);
		else setPoorChest(b);
	}

	/**
	 * Sets the items inside of the chest according to the "poor" list of items.
	 * The lowest class.
	 * 
	 * @param block
	 *            - The chest that will be filled with the items
	 */
	private void setPoorChest(Block block) {
		if (!(block.getState() instanceof Chest)) {
			return;
		}
		Chest chest = (Chest) block.getState();
		Inventory chestInv = chest.getBlockInventory();
		ItemStack[] contents = chestInv.getContents();

		int size = random.nextInt(3) + 3;

		for (int i = 0; i < size; i++) {
			int pr = random.nextInt(contents.length);
			int ir = random.nextInt(poor.length);
			int is = random.nextInt(3) + 1;
			contents[pr] = new ItemStack(poor[ir], is);
		}
		chestInv.setContents(contents);
		chest.update();
	}

	/**
	 * Sets the items inside of the chest according to the "feasible" list of
	 * items. The middle class.
	 * 
	 * @param block
	 *            - The chest that will be filled with the items
	 */
	private void setFeasibleChest(Block block) {
		if (block.getState() instanceof Chest) return;
		Chest chest = (Chest) block.getState();
		Inventory chestInv = chest.getBlockInventory();
		ItemStack[] contents = chestInv.getContents();

		int size = random.nextInt(5) + 5;

		for (int i = 0; i < size; i++) {
			int pr = random.nextInt(contents.length);
			int ir = random.nextInt(feasible.length);
			int is = random.nextInt(5) + 1;
			contents[pr] = new ItemStack(feasible[ir], is);
		}
		chestInv.setContents(contents);
		chest.update();
	}

	/**
	 * Sets the items inside of the chest according to the "rich" list of items.
	 * The highest class.
	 * 
	 * @param block
	 *            - The chest that will be filled with the items
	 */
	private void setRichChest(Block block) {
		if (block.getState() instanceof Chest) return;
		Chest chest = (Chest) block.getState();
		Inventory chestInv = chest.getBlockInventory();
		ItemStack[] contents = chestInv.getContents();

		int size = random.nextInt(8) + 8;

		for (int i = 0; i < size; i++) {
			int pr = random.nextInt(contents.length);
			int ir = random.nextInt(rich.length);
			int is = random.nextInt(8) + 1;
			contents[pr] = new ItemStack(rich[ir], is);
		}
		chestInv.setContents(contents);
		chest.update();
	}
}
