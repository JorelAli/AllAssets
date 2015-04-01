/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 * 
 * AllAssets, created by Skepter and Tundra
 * 
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 * 
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and Tundra
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 * 
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
package io.github.skepter.allassets.api;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.config.PlayerData;
import io.github.skepter.allassets.serializers.InventorySerializer;
import io.github.skepter.allassets.serializers.LocationSerializer;
import io.github.skepter.allassets.utils.utilclasses.PlayerUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.Inventory;

public class OfflineUser {

	OfflinePlayer player;
	PlayerData playerData;

	public OfflineUser(final OfflinePlayer p) {
		player = p;
		playerData = new PlayerData(p);
	}

	public OfflineUser(final String s) {
		try {
			player = PlayerUtils.getOfflinePlayerFromString(s);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		playerData = new PlayerData(player);
	}

	public OfflineUser(final UUID u) {
		try {
			for (final OfflinePlayer p : Bukkit.getOfflinePlayers())
				if (u.equals(p.getUniqueId())) {
					player = p;
					break;
				}
		} catch (final Exception e) {
			AllAssets.instance().getLogger().warning("Error trying to get uuid!");
		}
		playerData = new PlayerData(player);
	}

	public OfflinePlayer getPlayer() {
		return player;
	}

	/** Gets a list of every user's file and loads then as OfflineUsers */
	public static List<OfflineUser> offlineUsers() {
		final List<OfflineUser> userList = new ArrayList<OfflineUser>();
		for (final String s : PlayerUtils.getAllOfflinePlayerNames())
			userList.add(new OfflineUser(s));
		return userList;
	}

	public Location getLastLoc() {
		return LocationSerializer.LocFromString(playerData.getDataFile().getString("lastLoc"));
	}

	public void setLastLoc(final Location loc) {
		playerData.getDataFile().set("lastLoc", LocationSerializer.LocToString(loc));
		playerData.saveDataFile();
	}

	public Location getHome(final String name) {
		final String s = playerData.getDataFile().getString("home." + name);
		return LocationSerializer.LocFromString(s);
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

	public Set<UUID> getFriendList() {
		List<String> str = playerData.getDataFile().getStringList("friends");
		Set<UUID> list = new HashSet<UUID>();
		for (String s : str) {
			list.add(UUID.fromString(s));
		}
		return list;
	}

	public void setFriendList(Set<UUID> friends) {
		playerData.getDataFile().set("friends", friends);
	}

	public boolean canTp() {
		return playerData.getDataFile().getBoolean("canTP");
	}
}
