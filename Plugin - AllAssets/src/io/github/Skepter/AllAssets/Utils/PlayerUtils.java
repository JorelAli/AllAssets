/*******************************************************************************
 * Skepter's licence
 * Copyright 2014 
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
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. I've just made life easier for you :)
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
package io.github.Skepter.AllAssets.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.bukkit.Bukkit.*;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlayerUtils {

	public static Player getOnlinePlayerFromString(final String string) throws Exception {
		for (final Player p : getOnlinePlayers())
			if (p.getName().equals(string))
				return getPlayer(p.getUniqueId());
		return null;
	}

	//cache data from the world data files and install them into the UUID map
	//ensure that duplicates are NOT added! (use a set)

	public static List<String> getAllOfflinePlayerNames() {
		final List<String> playerNames = new ArrayList<String>();
		for (final OfflinePlayer p : getOfflinePlayers())
			playerNames.add(p.getName());
		return playerNames;
	}

	public static Player getPlayerFromString(final String string) {
		for (final OfflinePlayer p : getOfflinePlayers())
			if (p.getName().equals(string))
				return getPlayer(p.getUniqueId());
		return null;
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
