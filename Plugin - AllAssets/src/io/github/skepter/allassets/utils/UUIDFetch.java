/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 * 
 * AllAssets, created by Skepter and Tundra
 * 
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 * 
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and Tundra
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
import io.github.skepter.allassets.api.User;
import io.github.skepter.allassets.tasks.UUIDFetchTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

/** Custom UUID Fetcher (written by me :D)
 * 
 * @author Skepter */
@Deprecated
public class UUIDFetch {

	private static String playerName;
	private static UUID uuid;
	private static Map<UUID, String> map = new HashMap<UUID, String>();

	public UUIDFetch(final String name, final boolean cache) {
		Bukkit.getScheduler().runTaskAsynchronously(AllAssets.instance(), new UUIDFetchTask(name, cache));
	}

	public static void setup(final String uuid, final String playerName) {
		UUIDFetch.uuid = UUID.fromString(uuid);
		UUIDFetch.playerName = playerName;
	}

	public static void cacheSetup(final String uuid, final String playerName) {
		map.put(UUID.fromString(uuid), playerName);
	}

	public String getPlayerName() {
		return playerName;
	}

	public UUID getUUID() {
		return uuid;
	}

	public Player getPlayer() {
		return Bukkit.getPlayer(uuid);
	}

	public User getUser() {
		return new User(uuid);
	}

	public static void cacheData() {
		for (final OfflinePlayer player : Bukkit.getOfflinePlayers())
			new UUIDFetch(player.getName(), true);
	}

	public static Map<UUID, String> getCacheData() {
		return map;
	}
}
