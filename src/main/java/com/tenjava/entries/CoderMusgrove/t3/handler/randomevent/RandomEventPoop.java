package com.tenjava.entries.CoderMusgrove.t3.handler.randomevent;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * While the player is wandering, he may randomly poop..
 * 
 * @author CoderMusgrove
 */
public class RandomEventPoop implements RandomEvent {

	@Override
	@SuppressWarnings("deprecation")
	public void runRandomEvent(Player p) {
		Location loc = p.getLocation();
		Location locMinusOne = loc;
		locMinusOne.setY(locMinusOne.getY() - 1);
		World w = loc.getWorld();
		Block b = locMinusOne.getBlock();
		if (b.getType() == Material.GRASS) {
			b.setType(Material.DIRT);
			b.setData((byte) 2);
			for (int i = 0; i < 25; i++) w.playEffect(loc, Effect.SMOKE, 0);
			w.playSound(loc, Sound.BURP, 1f, 10f);
			w.playSound(loc, Sound.BURP, 1f, 5f);
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 2, 1));
		}
	}
}
