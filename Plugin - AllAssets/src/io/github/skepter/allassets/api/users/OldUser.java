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
package io.github.skepter.allassets.api.users;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.api.events.LogEvent.LogType;
import io.github.skepter.allassets.commands.administration.CommandLog;
import io.github.skepter.allassets.config.PlayerData;
import io.github.skepter.allassets.reflection.MinecraftReflectionUtils;
import io.github.skepter.allassets.serializers.InventorySerializer;
import io.github.skepter.allassets.serializers.LocationSerializer;
import io.github.skepter.allassets.tasks.PingTask;
import io.github.skepter.allassets.utils.utilclasses.PlayerUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class OldUser {

	Player player;
	PlayerData playerData;
	public static int ping;

	public OldUser(final Player p) {
		player = p;

		try {
			playerData = new PlayerData(player);
		} catch (final Exception e) {
			CommandLog.addLog("Error retrieving " + p.getName() + "'s data file", LogType.ERROR);
		}
	}

	@Deprecated
	public OldUser(final String s) {
		try {
			player = PlayerUtils.getOnlinePlayerFromString(s);
		} catch (final Exception e) {
		}
		playerData = new PlayerData(player);
	}

	public OldUser(final UUID u) {
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
	public String getLanguage() {
		try {
			switch (new MinecraftReflectionUtils(player).locale.toLowerCase()) {
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
	public static List<OldUser> onlineUsers() {
		final List<OldUser> userList = new ArrayList<OldUser>();
		for (final Player player : Bukkit.getOnlinePlayers())
			userList.add(new OldUser(player));
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

	//Compass
	public Location getWaypoint() {
		final String s = playerData.getDataFile().getString("waypoint");
		return LocationSerializer.LocFromString(s);
	}

	//Compass
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

	public Set<UUID> getFriendList() {
		final List<String> str = playerData.getDataFile().getStringList("friends");
		final Set<UUID> list = new HashSet<UUID>();
		for (final String s : str)
			list.add(UUID.fromString(s));
		return list;
	}

	public void setFriendList(final Set<UUID> friends) {
		playerData.getDataFile().set("friends", friends);
		playerData.saveDataFile();
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

	/*
	 * last play = maths with ticks lived? add feature where you can set
	 * waypoints: /waypoint <set/reset> - sets the compass pointing location
	 *
	 * feature where you can add holograms??
	 */
}
