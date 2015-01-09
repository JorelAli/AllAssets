/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
 *
 * AllAssets was created by Skepter (http://skepter.github.io/).
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
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Misc;

import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public interface IUser {

	/** Gets the last location of a player */
	public Location getLastLoc();

	/** Sets the last location for a player at their current location */
	public void setLastLoc();

	/** Sets the last location for a player at a specific location */
	public void setLastLoc(Location loc);

	/** Gets a home location from the home name */
	public Location getHome(String name);

	/** Sets a home as the specified location and name */
	public void setHome(Location loc, String name);

	/** Gets a waypoint from a specific location */
	public Location getWaypoint(Location loc);

	public void setWaypoint(Location loc);

	public Player getPlayer();

	public Long getTimeSinceLastPlay();

	public Long getTotalTimePlayed();

	public boolean canTp();

	public void setTotalTimePlayed(long time);

	public void setTimeSinceLastPlay(long time);

	public int getAttackStrength();

	public void setAttackStrength(int strength);

	public int getJumpPower();

	public void setJumpPower(int power);

	public Inventory getLastInventory();

	public void setLastInventory(String s);

	public int getDeathCount();

	public void setDeathCount(int i);

	/** Gets the amount of times a player has joined */
	public int getJoinCount();

	/** Sets the amount of times a player has joined */
	public void setJoinCount(int joinCount);

	public List<String> IPs();

	public void setIPs(List<String> arr);

	public void setAFK(boolean afk);

	public boolean isAFK();

	public List<UUID> getFriendList();

	public void setFriendList();

	public String getLanguage(Player p);

	public boolean canTP();

	public void setCanTP(boolean tp);

}
