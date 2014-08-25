package io.github.Skepter.API;

import io.github.Skepter.Config.UUIDData;
import io.github.Skepter.Utils.UUIDFetch;

import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class UUIDAPI {

	/**
	 * Gets the player object from their name using the UUIDMap
	 * @param playerName - The player's name
	 * @return The player object from their name
	 * @throws NullPointerException if they are not in the UUIDMap
	 */
	public static Player getPlayer(String playerName) throws NullPointerException {
		UUIDData data = new UUIDData();
		for (Entry<String, UUID> entry : data.getUUIDMap().entrySet())
			if (playerName.toLowerCase().equals(entry.getKey().toLowerCase()))
				return Bukkit.getPlayer(entry.getValue());
		throw new NullPointerException();
	}
	
	/**
	 * Gets the player object from their name using the Mojang Servers.
	 * Not recommended in mass use.
	 * @param playerName - The player's name
	 * @return the player object from their name
	 */
	public static Player getPlayerFromServer(String playerName) {
		UUIDFetch fetch = new UUIDFetch(playerName, false);
		return fetch.getPlayer();
	}
}
