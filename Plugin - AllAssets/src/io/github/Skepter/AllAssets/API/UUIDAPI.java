/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.API;

import io.github.Skepter.AllAssets.Config.UUIDData;
import io.github.Skepter.AllAssets.Utils.UUIDFetch;

import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
