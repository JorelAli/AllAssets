package io.github.Skepter.Utils;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlayerUtils {

	public static Player getOnlinePlayerFromString(final String string) throws Exception {
		for (final Player p : Bukkit.getOnlinePlayers()) {
			if (p.getName().equals(string)) {
				return Bukkit.getPlayer(p.getUniqueId());
			}
		}
		return null;
	}

	//cache data from the world data files and install them into the UUID map
	//ensure that duplicates are NOT added!
	
	public static Player getPlayerFromString(final String string) {
		for (final OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			if (p.getName().equals(string)) {
				return Bukkit.getPlayer(p.getUniqueId());
			}
		}
		return null;
	}

	public static String getPlayernameFromUUID(final UUID uuid) {
		for (final OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			if (p.getUniqueId().equals(uuid)) {
				return p.getName();
			}
		}
		return null;
	}
	
	public static boolean isOnline(final String player) {
		for (final Player p : Bukkit.getOnlinePlayers()) {
			if (p.getName().equals(player)) {
				return true;
			}
		}
		return false;
	}
}
