/*******************************************************************************
 * Skepter's licence
 * Copyright 2014 
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
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. I've just made life easier for you :)
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

public abstract interface IUser {

	/** Gets the last location of a player */
	public abstract Location getLastLoc();

	/** Sets the last location for a player at their current location */
	public abstract void setLastLoc();

	/** Sets the last location for a player at a specific location */
	public abstract void setLastLoc(Location loc);

	/** Gets a home location from the home name */
	public abstract Location getHome(String name);

	/** Sets a home as the specified location and name */
	public abstract void setHome(Location loc, String name);

	/** Gets a waypoint from a specific location */
	public abstract Location getWaypoint(Location loc);

	public abstract void setWaypoint(Location loc);

	public abstract Player getPlayer();

	public abstract Long getTimeSinceLastPlay();

	public abstract Long getTotalTimePlayed();

	public abstract boolean canTp();

	public abstract void setTotalTimePlayed(long time);

	public abstract void setTimeSinceLastPlay(long time);

	public abstract int getAttackStrength();

	public abstract void setAttackStrength(int strength);

	public abstract int getJumpPower();

	public abstract void setJumpPower(int power);

	public abstract Inventory getLastInventory();

	public abstract void setLastInventory(String s);

	public abstract int getDeathCount();

	public abstract void setDeathCount(int i);

	/** Gets the amount of times a player has joined */
	public abstract int getJoinCount();

	/** Sets the amount of times a player has joined */
	public abstract void setJoinCount(int joinCount);

	public abstract List<String> IPs();

	public abstract void setIPs(List<String> arr);

	public abstract void setAFK(boolean afk);

	public abstract boolean isAFK();
	
	public abstract List<UUID> getFriendList();
	
	public abstract void setFriendList();
	
	public abstract String getLanguage(Player p);

	public abstract boolean canTP();

	public abstract void setCanTP(boolean tp);

}
