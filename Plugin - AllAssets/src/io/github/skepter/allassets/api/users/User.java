package io.github.skepter.allassets.api.users;

import io.github.skepter.allassets.config.PlayerData;
import io.github.skepter.allassets.reflection.MinecraftReflectionUtils;
import io.github.skepter.allassets.serializers.LocationSerializer;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class User implements IUser{

	Player player;
	PlayerData playerData;
	
	public User(Player player) {
		this.player = player;
		playerData = new PlayerData(player);
	}
	
	public User(UUID uuid) {
		player = Bukkit.getPlayer(uuid);
		playerData = new PlayerData(player);
	}
	
	@Override
	public int getPing() {
		try {
			return new MinecraftReflectionUtils(player).ping;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
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

	@Override
	public Location getLastLoc() {
		return LocationSerializer.locFromString(playerData.getDataFile().getString("lastLoc"));
	}

	@Override
	public void setLastLoc() {
		playerData.set("lastLoc", LocationSerializer.locToString(player.getLocation()));
	}

	@Override
	public void setLastLoc(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Location getHome(String homeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setHome(Location location, String homeName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Location getWaypoint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setWaypoint(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTpStatus(boolean canTp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canTp() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getAttackStrength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAttackStrength(int attackStrength) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getJumpPower() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setJumpPower(int jumpPower) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Inventory getLastInventory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLastInventory(Inventory inventory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDeathCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setDeathCount(int deathCount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getJoinCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setJoinCount(int joinCount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getStoredIps() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIps(List<String> ips) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAFKStatus(boolean afk) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAFK() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<UUID> getFriendList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFriendList(Set<UUID> friendList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Player getPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getTimeSinceLastPlay() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getTotalTimePlayed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPrefix(String prefix) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPrefix() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSuffix(String suffix) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSuffix() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getBalance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setBalance(double balance) {
		// TODO Auto-generated method stub
		
	}

}
