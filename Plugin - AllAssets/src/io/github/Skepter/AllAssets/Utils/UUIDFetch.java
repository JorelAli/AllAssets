/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
 *
 * AllAssets was created by Skepter (http://skepter.github.io/).
 *
 * You are able to:
 * * View AllAsset's source code on github
 * * Experiment with the code to your wish
 * * Download it to use on your server
 *
 * You are NOT able to:
 * * Sell AllAssets - it is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter
 * * Distribute it on any other website. The ONLY places where it can be downloaded from is github and the Bukkit repository
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. The only exception is the BukkitDev staff who I have allowed permission to decompile (to search for malicious code)
 *
 * You can not:
 * * Hold me liable for your actions:
 *     - If there is a bug in the program, you are allowed to notify me.
 *     - If other players abuse AllAssets, you are NOT allowed to hold me liable for that
 *     - If it was setup incorrectly or you used a non-official build, you can NOT hold me liable for that.
 * * Yell at me:
 *     - If something is wrong, please approach the situation calmly.
 * * Modify the code:
 *     - You are NOT allowed to 'steal' the whole code from Github, modify it, compile it yourself and distribute that.
 *
 * If you are to break from these implications, future use of this plugin will be forbidden.
 *******************************************************************************/
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
