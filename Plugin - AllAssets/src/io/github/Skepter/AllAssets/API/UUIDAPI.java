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
package io.github.skepter.allassets.api;

import io.github.skepter.allassets.config.UUIDData;
import io.github.skepter.allassets.utils.UUIDFetch;

import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@SuppressWarnings("deprecation")
public class UUIDAPI {

	/** Gets the player object from their name using the UUIDMap
	 * 
	 * @param playerName - The player's name
	 * @return The player object from their name
	 * @throws NullPointerException if they are not in the UUIDMap */
	public static Player getPlayer(final String playerName) throws NullPointerException {
		for (final Entry<String, UUID> entry : UUIDData.getUUIDMap().entrySet())
			if (playerName.toLowerCase().equals(entry.getKey().toLowerCase()))
				return Bukkit.getPlayer(entry.getValue());
		throw new NullPointerException();
	}

	/** Gets the player object from their name using the Mojang Servers. Not
	 * recommended in mass use.
	 * 
	 * @param playerName - The player's name
	 * @return the player object from their name */
	public static Player getPlayerFromServer(final String playerName) {
		final UUIDFetch fetch = new UUIDFetch(playerName, false);
		return fetch.getPlayer();
	}
}
