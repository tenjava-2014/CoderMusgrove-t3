package com.tenjava.entries.CoderMusgrove.t3.handler.randomevent;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Random Event that if a player were to hit an entity, they will have their
 * velocity sent backwards of their current velocity, with a multiplier
 * 
 * @author CoderMusgrove
 */
public class RandomEventTakeEntityVelocity implements RandomEvent, Listener {

	private boolean takeVelocity = false;

	@EventHandler
	public void onEntityDamageEntity(EntityDamageByEntityEvent e) {
		if (!takeVelocity) return;
		if (!(e.getDamager() instanceof Player)) return;
		takeVelocity = false;

		Player p = (Player) e.getDamager();
		Location loc = p.getLocation();
		Location eyeLoc = p.getEyeLocation();
		World w = loc.getWorld();
		p.setVelocity(loc.getDirection().multiply(-Math.PI).setY(0.75));
		for (int i = 0; i < 10; i++)
			w.playEffect(eyeLoc, Effect.SMOKE, 0);
		w.playSound(eyeLoc, Sound.ANVIL_LAND, 1f, 3f);
	}

	@Override
	public void runRandomEvent(Player p) {
		takeVelocity = true;
	}
}
