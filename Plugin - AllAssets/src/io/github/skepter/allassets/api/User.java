/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
 *
 * AllAssets was created by Skepter and Tundra (http://skepter.github.io/).
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

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.api.LogEvent.LogType;
import io.github.skepter.allassets.commands.administration.CommandLog;
import io.github.skepter.allassets.config.PlayerData;
import io.github.skepter.allassets.reflection.MinecraftReflectionUtils;
import io.github.skepter.allassets.serializers.InventorySerializer;
import io.github.skepter.allassets.serializers.LocationSerializer;
import io.github.skepter.allassets.tasks.PingTask;
import io.github.skepter.allassets.utils.utilclasses.PlayerUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class User {

	Player player;
	PlayerData playerData;
	public static int ping;

	public User(final Player p) {
		player = p;
		
		try {
			playerData = new PlayerData(player);
		} catch (Exception e) {
			CommandLog.addLog("Error retrieving " + p.getName() + "'s data file", LogType.ERROR);
		}
	}

	@Deprecated
	public User(final String s) {
		try {
			player = PlayerUtils.getOnlinePlayerFromString(s);
		} catch (final Exception e) {
		}
		playerData = new PlayerData(player);
	}

	public User(final UUID u) {
		try {
			for (final OfflinePlayer p : Bukkit.getOfflinePlayers())
				if (u.equals(p.getUniqueId())) {
					player = (Player) p;
					break;
				}
		} catch (final Exception e) {
			AllAssets.instance().getLogger().warning("Error trying to get uuid!");
		}
		playerData = new PlayerData(player);
	}

	public void refreshPing() {
		Bukkit.getScheduler().scheduleSyncDelayedTask(AllAssets.instance(), new Runnable() {
			@Override
			public void run() {
				getPing();
			}
		}, 20L);
	}
	
	public int getPing() {
		/* Delay it by 1 tick for accurate results */
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(AllAssets.instance(), new PingTask(player), 1L);
		return ping;
	}

	/** Returns the language that is selected in the player's settings (thus
	 * giving their language) */
	public String getLanguage(final Player p) {
		try {
			switch (new MinecraftReflectionUtils(p).locale.toLowerCase()) {
			case "de_de":
				return "de";
			case "sv_se":
				return "sv";
			case "nl_nl":
				return "nl";
			case "fr_fr":
				return "fr";
			}
		} catch (final Exception e) {
			return "en";
		}
		return "en";
	}

	/** Gets a list of every user's file and loads them as OfflineUsers */
	public static List<User> onlineUsers() {
		final List<User> userList = new ArrayList<User>();
		for(Player player : Bukkit.getOnlinePlayers()) {
			userList.add(new User(player));
		}
		return userList;
	}

	public Location getLastLoc() {
		return LocationSerializer.LocFromString(playerData.getDataFile().getString("lastLoc"));
	}

	public void setLastLoc() {
		playerData.getDataFile().set("lastLoc", LocationSerializer.LocToString(player.getLocation()));
		playerData.saveDataFile();
	}

	public void setLastLoc(final Location loc) {
		playerData.getDataFile().set("lastLoc", LocationSerializer.LocToString(loc));
		playerData.saveDataFile();
	}

	public Location getHome(final String name) {
		final String s = playerData.getDataFile().getString("home." + name);
		return LocationSerializer.LocFromString(s);
	}

	public Player getPlayer() {
		return player;
	}

	public Long getTimeSinceLastPlay() {
		return playerData.getDataFile().getLong("lastTimePlayed");
	}

	public Long getTotalTimePlayed() {
		return playerData.getDataFile().getLong("totalTimePlayed");
	}

	public void setHome(final Location loc, final String name) {
		playerData.getDataFile().set("home." + name, LocationSerializer.LocToString(loc));
		playerData.saveDataFile();
	}

	public Location getWaypoint(final Location loc) {
		final String s = playerData.getDataFile().getString("waypoint");
		return LocationSerializer.LocFromString(s);
	}

	public void setWaypoint(final Location loc) {
		playerData.getDataFile().set("waypoint", LocationSerializer.LocToString(loc));
		playerData.saveDataFile();
		player.setCompassTarget(loc);
	}

	public void setCanTP(final boolean tp) {
		playerData.getDataFile().set("canTP", tp);
		playerData.saveDataFile();
	}

	public void setTotalTimePlayed(final long time) {
		playerData.getDataFile().set("totalTimePlayed", time);
		playerData.saveDataFile();
	}

	public void setTimeSinceLastPlay(final long time) {
		playerData.getDataFile().set("lastTimePlayed", time);
		playerData.saveDataFile();
	}

	public int getAttackStrength() {
		return playerData.getDataFile().getInt("attackStrength");
	}

	public void setAttackStrength(final int strength) {
		playerData.getDataFile().set("attackStrength", strength);
		playerData.saveDataFile();
	}

	public int getJumpPower() {
		return playerData.getDataFile().getInt("jumpPower");
	}

	public void setJumpPower(final int power) {
		playerData.getDataFile().set("jumpPower", power);
		playerData.saveDataFile();
	}

	public Inventory getLastInventory() {
		final String inv = playerData.getDataFile().getString("lastInv");
		return InventorySerializer.fromString(inv);
	}

	public void setLastInventory(final String s) {
		playerData.getDataFile().set("lastInv", s);
		playerData.saveDataFile();
	}

	public int getDeathCount() {
		return playerData.getDataFile().getInt("deathCount");
	}

	public void setDeathCount(final int i) {
		playerData.getDataFile().set("deathCount", i);
		playerData.saveDataFile();
	}

	public int getJoinCount() {
		return playerData.getDataFile().getInt("joinCount");
	}

	public void setJoinCount(final int i) {
		playerData.getDataFile().set("joinCount", i);
		playerData.saveDataFile();
	}

	public List<String> IPs() {
		return playerData.getDataFile().getStringList("ips");
	}

	public void setIPs(final List<String> arr) {
		playerData.getDataFile().set("ips", arr);
		playerData.saveDataFile();
	}

	public void setAFK(final boolean afk) {
		playerData.getDataFile().set("afk", afk);
		playerData.saveDataFile();
		//add tag TODO
	}

	public boolean isAFK() {
		return playerData.getDataFile().getBoolean("afk");
	}

	public List<UUID> getFriendList() {
		List<String> str = playerData.getDataFile().getStringList("friends");
		List<UUID> list = new ArrayList<UUID>();
		for(String s : str) {
			list.add(UUID.fromString(s));
		}
		return list;
	}

	public void setFriendList(List<UUID> friends) {
		playerData.getDataFile().set("friends", friends);
		playerData.saveDataFile();
	}

	public boolean canTp() {
		return playerData.getDataFile().getBoolean("canTP");
	}

	/*
	 * last play = maths with ticks lived? add feature where you can set
	 * waypoints: /waypoint <set/reset> - sets the compass pointing location
	 * 
	 * feature where you can add holograms??
	 */
}
