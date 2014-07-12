package com.tenjava.entries.CoderMusgrove.t3.handler.randomevent;

import java.util.Random;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * Sometimes it gets windy, so why can't it get so windy, that you get lifted
 * off of the ground momentarily?
 * 
 * @author CoderMusgrove
 */
public class RandomEventWind implements RandomEvent {

	private Random random = new Random();

	@Override
	public void runRandomEvent(Player p) {
		if (p.getLocation().getY() < 63) return;
		double x = random.nextDouble() * 2 - 1;
		double y = random.nextDouble() / 2;
		double z = random.nextDouble() * 2 - 1;

		p.setVelocity(new Vector(x, y, z));
		p.playSound(p.getLocation(), Sound.BAT_TAKEOFF, 1f, 1f);
	}
}
