package io.github.skepter.allassets.api.users;

import io.github.skepter.allassets.config.PlayerData;
import io.github.skepter.allassets.reflection.MinecraftReflectionUtils;
import io.github.skepter.allassets.serializers.InventorySerializer;
import io.github.skepter.allassets.serializers.LocationSerializer;
import io.github.skepter.allassets.utils.utilclasses.PlayerUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class User implements IUser {

	private Player player;
	private OfflinePlayer oPlayer;
	private PlayerData playerData;
	private boolean isOnline;

	public User(Player player) {
		this.player = player;
		oPlayer = Bukkit.getOfflinePlayer(player.getUniqueId());
		playerData = new PlayerData(player);
		isOnline = true;
	}

	public User(UUID uuid) {
		oPlayer = Bukkit.getOfflinePlayer(uuid);
		player = null;
		if (oPlayer.isOnline()) {
			isOnline = true;
			player = Bukkit.getPlayer(uuid);
		}
		playerData = new PlayerData(player);
	}

	public User(OfflinePlayer player) {
		player = null;
		oPlayer = player;
		playerData = new PlayerData(oPlayer);
		isOnline = false;
	}

	public User(String playerName) {
		OfflinePlayer p = PlayerUtils.getOfflinePlayerFromString(playerName);
		if(p != null) {
			player = null;
			oPlayer = p;
			playerData = new PlayerData(oPlayer);
			isOnline = false;
		}
	}

	@Override
	public int getPing() {
		if (!isOnline)
			return 0;
		try {
			return new MinecraftReflectionUtils(player).ping;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public String getLanguage() {
		if (!isOnline)
			return "en";
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
		if (!isOnline)
			return;
		playerData.set("lastLoc", LocationSerializer.locToString(player.getLocation()));
	}

	@Override
	public void setLastLoc(Location location) {
		playerData.set("lastLoc", LocationSerializer.locToString(location));
	}

	@Override
	public Location getHome(String homeName) {
		return LocationSerializer.locFromString(playerData.getDataFile().getString("home." + homeName));
	}

	@Override
	public void setHome(Location location, String homeName) {
		playerData.set("home." + homeName, LocationSerializer.locToString(location));
	}

	@Override
	public Location getWaypoint() {
		return LocationSerializer.locFromString(playerData.getDataFile().getString("waypoint"));
	}

	@Override
	public void setWaypoint(Location location) {
		playerData.set("waypoint", LocationSerializer.locToString(location));
		if (!isOnline)
			return;
		player.setCompassTarget(location);
	}

	@Override
	public void setTpStatus(boolean canTp) {
		playerData.set("canTP", canTp);
	}

	@Override
	public boolean canTp() {
		return playerData.getDataFile().getBoolean("canTP");
	}

	@Override
	public int getAttackStrength() {
		return playerData.getDataFile().getInt("attackStrength");
	}

	@Override
	public void setAttackStrength(int attackStrength) {
		playerData.set("attackStrength", attackStrength);
	}

	@Override
	public int getJumpPower() {
		return playerData.getDataFile().getInt("jumpPower");
	}

	@Override
	public void setJumpPower(int jumpPower) {
		playerData.set("jumpPower", jumpPower);
	}

	@Override
	public Inventory getLastInventory() {
		return InventorySerializer.fromString(playerData.getDataFile().getString("lastInv"));
	}

	@Override
	public void setLastInventory(Inventory inventory) {
		playerData.set("lastInv", InventorySerializer.toString(inventory));
	}

	@Override
	public int getDeathCount() {
		return playerData.getDataFile().getInt("deathCount");
	}

	@Override
	public void setDeathCount(int deathCount) {
		playerData.set("deathCount", deathCount);
	}

	@Override
	public int getJoinCount() {
		return playerData.getDataFile().getInt("joinCount");
	}

	@Override
	public void setJoinCount(int joinCount) {
		playerData.set("joinCount", joinCount);
	}

	@Override
	public List<String> getStoredIps() {
		return playerData.getDataFile().getStringList("ips");
	}

	@Override
	public void setIps(List<String> ips) {
		playerData.set("ips", ips);
	}

	@Override
	public void setAFKStatus(boolean afk) {
		playerData.set("afk", afk);
	}

	@Override
	public boolean isAFK() {
		return playerData.getDataFile().getBoolean("afk");
	}

	@Override
	public Set<UUID> getFriendList() {
		final List<String> str = playerData.getDataFile().getStringList("friends");
		final Set<UUID> list = new HashSet<UUID>();
		for (final String s : str)
			list.add(UUID.fromString(s));
		return list;
	}

	@Override
	public void setFriendList(Set<UUID> friendList) {
		playerData.set("friends", friendList);
	}

	@Override
	public Player getPlayer() {
		if (!isOnline)
			return null;
		return player;
	}

	@Override
	public long getTimeSinceLastPlay() {
		return playerData.getDataFile().getLong("lastTimePlayed");
	}

	@Override
	public long getTotalTimePlayed() {
		return playerData.getDataFile().getLong("totalTimePlayed");
	}

	@Override
	public void setTimeSinceLastPlayed(long time) {
		playerData.set("lastTimePlayed", time);
	}

	@Override
	public void setTotalTimePlayed(long time) {
		playerData.set("totalTimePlayed", time);
	}

	@Override
	public void setPrefix(String prefix) {
		playerData.set("prefix", prefix);
	}

	@Override
	public String getPrefix() {
		return playerData.getDataFile().getString("prefix");
	}

	@Override
	public void setSuffix(String suffix) {
		playerData.set("suffix", suffix);
	}

	@Override
	public String getSuffix() {
		return playerData.getDataFile().getString("suffix");
	}

	@Override
	public double getBalance() {
		return playerData.getDataFile().getDouble("balance");
	}

	@Override
	public void setBalance(double balance) {
		playerData.set("balance", balance);
	}
}
