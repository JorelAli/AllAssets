package io.github.skepter.allassets.api.users;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;


public interface IUser {
	
	int getPing();
	
	String getLanguage();
		
	Location getLastLoc();
	
	void setLastLoc();
	
	void setLastLoc(Location location);
	
	Location getHome(String homeName);
	
	void setHome(Location location, String homeName);
	
	Location getWaypoint();
	
	void setWaypoint(Location location);
	
	void setTpStatus(boolean canTp);
	
	boolean canTp();
	
	int getAttackStrength();
	
	void setAttackStrength(int attackStrength);
	
	@Deprecated
	int getJumpPower();
	
	@Deprecated
	void setJumpPower(int jumpPower);
	
	Inventory getLastInventory();
	
	/** Serializes and then stores inventory*/
	void setLastInventory(Inventory inventory);
	
	int getDeathCount();
	
	void setDeathCount(int deathCount);
	
	int getJoinCount();
	
	void setJoinCount(int joinCount);
	
	List<String> getStoredIps();
	
	void setIps(List<String> ips);
	
	void setAFKStatus(boolean afk);
	
	boolean isAFK();
	
	Set<UUID> getFriendList();
	
	void setFriendList(Set<UUID> friendList);
	
	Player getPlayer();
	
	long getTimeSinceLastPlay();
	
	long getTotalTimePlayed();
	
	//Chat
	
	void setPrefix(String prefix);
	
	String getPrefix();
	
	void setSuffix(String suffix);
	
	String getSuffix();
	
	//Economy
	
	double getBalance();
	
	void setBalance(double balance);
	
}
