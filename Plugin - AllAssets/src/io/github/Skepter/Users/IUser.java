package io.github.Skepter.Users;

import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract interface IUser {

	public abstract Location getLastLoc();

	public abstract void setLastLoc();

	public abstract void setLastLoc(Location loc);

	public abstract Location getHome(String name);

	public abstract void setHome(Location loc, String name);

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

	public abstract int getJoinCount();

	public abstract void setJoinCount(int i);

	public abstract List<String> IPs();

	public abstract void setIPs(List<String> arr);

	public abstract UUID getUUID();

	public abstract void setUUID(UUID uuid);

	public abstract void setAFK(boolean afk);

	public abstract boolean isAFK();

}
