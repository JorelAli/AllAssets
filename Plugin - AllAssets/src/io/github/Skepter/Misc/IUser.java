package io.github.Skepter.Misc;

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

}
