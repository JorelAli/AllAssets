package io.github.Skepter.Utils;

import io.github.Skepter.AllAssets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CommandCooldown {

	public static List<UUID> cooldownPlayers = new ArrayList<UUID>();
	public static Map<UUID, Long> cooldownTimeMap = new HashMap<UUID, Long>();

	public CommandCooldown(final Player player, final long time) {
		cooldownPlayers.add(player.getUniqueId());
		cooldownTimeMap.put(player.getUniqueId(), (System.currentTimeMillis() / 1000) + time);
		new BukkitRunnable() {
			@Override
			public void run() {
				cooldownPlayers.remove(player.getUniqueId());
			}
		}.runTaskLater(AllAssets.instance(), time * 20);
	}

	public static boolean isOnCooldown(final Player player) {
		return cooldownPlayers.contains(player.getUniqueId()) ? true : false;
	}

}
