package com.tenjava.entries.CoderMusgrove.t3.handler;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.tenjava.entries.CoderMusgrove.t3.TenJava;

public class EventHandler {

	private BukkitRunnable runnable;
	private Random random;
	
	private EventHandler() {
		initRunnable();
	}
	
	public static void initialize() {
		new EventHandler();
	}
	
	public BukkitRunnable getRunnable() {
		return runnable;
	}
	
	private void initRunnable() {
		runnable = new BukkitRunnable() {
			@Override
			public void run() {
				World w = Bukkit.getWorlds().get(random.nextInt(Bukkit.getWorlds().size()));
				List<Player> players = w.getPlayers();
				Player p = players.get(random.nextInt(players.size()));
			}
		};
		runnable.runTaskTimer(TenJava.getInstance(), 20, 20);
	}
}
