package io.github.Skepter.AllAssets.Utils;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.API.User;
import io.github.Skepter.AllAssets.Tasks.UUIDFetchTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

/** Custom UUID Fetcher (written by me :D)
 * 
 * @author Skepter */
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
