/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter 
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter 
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.utils;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.api.utils.PlayerMap;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CommandCooldown {

	private static PlayerMap<String> cooldownPlayer = new PlayerMap<String>(AllAssets.instance());

	private static Set<UUID> cooldownPlayers = new HashSet<UUID>();
	private static PlayerMap<Long> cooldownTimeMap = new PlayerMap<Long>(AllAssets.instance());

	public CommandCooldown(final Player player, final long time, final String command) {
		cooldownPlayer.put(player, command);
		cooldownPlayers.add(player.getUniqueId());
		cooldownTimeMap.put(player, (System.currentTimeMillis() / 1000) + time);
		new BukkitRunnable() {
			@Override
			public void run() {
				cooldownPlayers.remove(player);
				cooldownPlayer.remove(player);
			}
		}.runTaskLater(AllAssets.instance(), time * 20);
	}

	public static boolean isOnCooldown(final Player player, final String command) {
		if (cooldownPlayer.containsPlayer(player))
			if (command.equals(cooldownPlayer.get(player)))
				return true;
		return false;
	}

	@Deprecated
	public static boolean isOnCooldown(final Player player) {
		return cooldownPlayers.contains(player) ? true : false;
	}

	public static long getTimeLeft(final Player player) {
		return cooldownTimeMap.get(player) - (System.currentTimeMillis() / 1000);
	}

}
