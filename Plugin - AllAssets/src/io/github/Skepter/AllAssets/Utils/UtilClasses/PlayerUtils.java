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
package io.github.Skepter.AllAssets.Utils.UtilClasses;

import static org.bukkit.Bukkit.getOfflinePlayers;
import static org.bukkit.Bukkit.getOnlinePlayers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlayerUtils {

	public static Player getOnlinePlayerFromString(final String string) {
		for (final Player p : getOnlinePlayers())
			if (p.getName().equalsIgnoreCase(string) || p.getName().toLowerCase().startsWith(string.toLowerCase()))
				return p;
		return null;
	}

	//cache data from the world data files and install them into the UUID map
	//ensure that duplicates are NOT added! (use a set)

	/** Retrieves the list of offline player names using Bukkit's getOfflinePlayers() */
	public static List<String> getAllOfflinePlayerNames() {
		final List<String> playerNames = new ArrayList<String>();
		for (final OfflinePlayer p : getOfflinePlayers())
			playerNames.add(p.getName());
		return playerNames;
	}

	@SuppressWarnings("deprecation")
	public static OfflinePlayer getOfflinePlayerFromString(final String string) {
		for (final OfflinePlayer p : getOfflinePlayers())
			if (p.getName().equalsIgnoreCase(string))
				return p;
		return Bukkit.getOfflinePlayer(string);
	}

	public static String getPlayernameFromUUID(final UUID uuid) {
		for (final OfflinePlayer p : getOfflinePlayers())
			if (p.getUniqueId().equals(uuid))
				return p.getName();
		return null;
	}

	public static boolean isOnline(final String player) {
		for (final Player p : getOnlinePlayers())
			if (p.getName().equals(player))
				return true;
		return false;
	}
}
