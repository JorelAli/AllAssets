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
	@Deprecated
	public static Player getPlayerFromServer(final String playerName) {
		final UUIDFetch fetch = new UUIDFetch(playerName, false);
		return fetch.getPlayer();
	}
}
