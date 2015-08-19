/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and MCSpartans
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and MCSpartans
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
package io.github.skepter.allassets.api.users;

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

/**
 * @Deprecated Use the User class
 */
@Deprecated
public class OldOfflineUser {

	OfflinePlayer player;
	PlayerData playerData;

	public OldOfflineUser(final OfflinePlayer p) {
		player = p;
		playerData = new PlayerData(p);
	}

	public OldOfflineUser(final String s) {
		try {
			player = PlayerUtils.getOfflinePlayerFromStringExact(s);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		playerData = new PlayerData(player);
	}

	public OldOfflineUser(final UUID u) {
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
	public static List<OldOfflineUser> offlineUsers() {
		final List<OldOfflineUser> userList = new ArrayList<OldOfflineUser>();
		for (final String s : PlayerUtils.getAllOfflinePlayerNames())
			userList.add(new OldOfflineUser(s));
		return userList;
	}

	public Location getLastLoc() {
		return LocationSerializer.locFromString(playerData.getDataFile().getString("lastLoc"));
	}
	
	public void setLastLoc(final Location loc) {
		playerData.getDataFile().set("lastLoc", LocationSerializer.locToString(loc));
		playerData.saveDataFile();
	}

	public Location getHome(final String name) {
		final String s = playerData.getDataFile().getString("home." + name);
		return LocationSerializer.locFromString(s);
	}

	public Long getTimeSinceLastPlay() {
		return playerData.getDataFile().getLong("lastTimePlayed");
	}

	public Long getTotalTimePlayed() {
		return playerData.getDataFile().getLong("totalTimePlayed");
	}

	public void setHome(final Location loc, final String name) {
		playerData.getDataFile().set("home." + name, LocationSerializer.locToString(loc));
		playerData.saveDataFile();
	}

	public Location getWaypoint(final Location loc) {
		final String s = playerData.getDataFile().getString("waypoint");
		return LocationSerializer.locFromString(s);
	}

	public void setWaypoint(final Location loc) {
		playerData.getDataFile().set("waypoint", LocationSerializer.locToString(loc));
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
		final List<String> str = playerData.getDataFile().getStringList("friends");
		final Set<UUID> list = new HashSet<UUID>();
		for (final String s : str)
			list.add(UUID.fromString(s));
		return list;
	}

	public void setFriendList(final Set<UUID> friends) {
		playerData.getDataFile().set("friends", friends);
	}

	public boolean canTp() {
		return playerData.getDataFile().getBoolean("canTP");
	}
	
	public void setPrefix(final String prefix) {
		playerData.getDataFile().set("prefix", prefix);
		playerData.saveDataFile();
	}
	
	public String getPrefix() {
		return playerData.getDataFile().getString("prefix");
	}
	
	public void setSuffix(final String suffix) {
		playerData.getDataFile().set("suffix", suffix);
		playerData.saveDataFile();
	}
	
	public String getSuffix() {
		return playerData.getDataFile().getString("suffix");
	}
	
	public void setBalance(double balance) {
		playerData.getDataFile().set("balance", balance);
		playerData.saveDataFile();
	}
	
	public double getBalance() {
		return playerData.getDataFile().getDouble("balance");
	}
}
