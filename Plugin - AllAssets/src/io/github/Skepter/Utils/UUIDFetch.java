package io.github.Skepter.Utils;

import io.github.Skepter.AllAssets;
import io.github.Skepter.API.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.libs.com.google.gson.JsonObject;
import org.bukkit.craftbukkit.libs.com.google.gson.JsonParser;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Custom UUID Fetcher (written by me :D)
 * @author Skepter
 */
public class UUIDFetch {

	private String playerName;
	private UUID uuid;
	private static Map<UUID, String> map = new HashMap<UUID, String>();
	
	public UUIDFetch(final String name, final boolean cache) {
		new BukkitRunnable() {
			@Override
			public void run() {
				try {
					URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);

					BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
					String str;
					while ((str = in.readLine()) != null) {
						JsonParser parser = new JsonParser();
						Object o = parser.parse(str);
						JsonObject jObject = (JsonObject) o;

						final String uuid = jObject.get("id").getAsString();
						final String playerName = jObject.get("name").getAsString();
						new BukkitRunnable() {

							@Override
							public void run() {
								if(cache) {
									cacheSetup(uuid, playerName);
								} else {
									setup(uuid, playerName);
								}
							}

						}.runTask(AllAssets.instance());
					}
					in.close();

				} catch (MalformedURLException e) {
				} catch (IOException e) {
				}
			}
		}.runTaskAsynchronously(AllAssets.instance());
	}

	private void setup(String uuid, String playerName) {
		this.uuid = UUID.fromString(uuid);
		this.playerName = playerName;
	}
	
	private void cacheSetup(String uuid, String playerName) {
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
		for(OfflinePlayer player : Bukkit.getOfflinePlayers()) {
			new UUIDFetch(player.getName(), true);
		}
	}
	
	public static Map<UUID, String> getCacheData() {
		return map;
	}
}
