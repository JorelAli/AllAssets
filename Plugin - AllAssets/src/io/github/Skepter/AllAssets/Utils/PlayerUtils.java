/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlayerUtils {

	public static Player getOnlinePlayerFromString(final String string) throws Exception {
		for (final Player p : Bukkit.getOnlinePlayers())
			if (p.getName().equals(string))
				return Bukkit.getPlayer(p.getUniqueId());
		return null;
	}

	//cache data from the world data files and install them into the UUID map
	//ensure that duplicates are NOT added! (use a set)

	public static List<String> getAllOfflinePlayerNames() {
		final List<String> playerNames = new ArrayList<String>();
		for (final OfflinePlayer p : Bukkit.getOfflinePlayers())
			playerNames.add(p.getName());
		return playerNames;
	}

	public static Player getPlayerFromString(final String string) {
		for (final OfflinePlayer p : Bukkit.getOfflinePlayers())
			if (p.getName().equals(string))
				return Bukkit.getPlayer(p.getUniqueId());
		return null;
	}

	public static String getPlayernameFromUUID(final UUID uuid) {
		for (final OfflinePlayer p : Bukkit.getOfflinePlayers())
			if (p.getUniqueId().equals(uuid))
				return p.getName();
		return null;
	}

	public static boolean isOnline(final String player) {
		for (final Player p : Bukkit.getOnlinePlayers())
			if (p.getName().equals(player))
				return true;
		return false;
	}
}
