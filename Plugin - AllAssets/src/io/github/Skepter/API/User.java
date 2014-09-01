package io.github.Skepter.API;

import io.github.Skepter.AllAssets;
import io.github.Skepter.Config.PlayerData;
import io.github.Skepter.Misc.IUser;
import io.github.Skepter.Serializer.InventorySerializer;
import io.github.Skepter.Serializer.LocationSerializer;
import io.github.Skepter.Tasks.PingTask;
import io.github.Skepter.Utils.PlayerUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class User implements IUser {

	Player player;
	PlayerData playerData;
	public static int ping;

	public User(final Player p) {
		player = p;
		playerData = new PlayerData(player);
	}

	/** Remember that when using this method the following are impossible:
	 * Getting the ping Setting the last Location Setting the last waypoint etc.
	 * 
	 * @param p */
	public User(final OfflinePlayer p) {
		playerData = new PlayerData(p);
	}

	/** Risky.... very risky. If the player isn't online, then it's risky...
	 * 
	 * @param s */
	public User(final String s) {
		try {
			player = PlayerUtils.getPlayerFromString(s);
		} catch (final Exception e) {
		}
		playerData = new PlayerData(player);
	}

	public User(final UUID u) {
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

	public int getPing() {
		/* Delay it by 1 tick for accurate results */
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(AllAssets.instance(), new PingTask(player), 1L);
		return ping;
	}

	public String getLanguage(final Player p) {
		try {
			final Object nmsPlayer = p.getClass().getMethod("getHandle").invoke(p);
			final Field field = nmsPlayer.getClass().getDeclaredField("locale");
			field.setAccessible(true);
			final String language = (String) field.get(nmsPlayer);
			final String lang = language.toLowerCase();
			switch (lang) {
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

	public static List<User> userList() {
		final List<User> userList = new ArrayList<User>();
		for (final String s : PlayerUtils.getAllOfflinePlayerNames())
			userList.add(new User(s));
		return userList;
	}

	@Override
	public Location getLastLoc() {
		return LocationSerializer.LocFromString(playerData.getPlayerData().getString("lastLoc"));
	}

	@Override
	public void setLastLoc() {
		playerData.getPlayerData().set("lastLoc", LocationSerializer.LocToString(player.getLocation()));
		playerData.savePlayerData();
	}

	@Override
	public void setLastLoc(final Location loc) {
		playerData.getPlayerData().set("lastLoc", LocationSerializer.LocToString(loc));
		playerData.savePlayerData();
	}

	@Override
	public Location getHome(final String name) {
		final String s = playerData.getPlayerData().getString("home." + name);
		return LocationSerializer.LocFromString(s);
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public Long getTimeSinceLastPlay() {
		return playerData.getPlayerData().getLong("lastTimePlayed");
	}

	@Override
	public Long getTotalTimePlayed() {
		return playerData.getPlayerData().getLong("totalTimePlayed");
	}

	@Override
	public void setHome(final Location loc, final String name) {
		playerData.getPlayerData().set("home." + name, LocationSerializer.LocToString(loc));
		playerData.savePlayerData();
	}

	@Override
	public Location getWaypoint(final Location loc) {
		final String s = playerData.getPlayerData().getString("waypoint");
		return LocationSerializer.LocFromString(s);
	}

	@Override
	public void setWaypoint(final Location loc) {
		playerData.getPlayerData().set("waypoint", LocationSerializer.LocToString(loc));
		playerData.savePlayerData();
		player.setCompassTarget(loc);
	}

	@Override
	public boolean canTp() { // change to something like 'has tp enabled or
							 // isAllowedToTpTo' or something because canTp
							 // sounds like its from another player to another
							 // player...
		// TODO canTP in User file
		return false;
	}

	@Override
	public void setTotalTimePlayed(final long time) {
		playerData.getPlayerData().set("totalTimePlayed", time);
		playerData.savePlayerData();
	}

	@Override
	public void setTimeSinceLastPlay(final long time) {
		playerData.getPlayerData().set("lastTimePlayed", time);
		playerData.savePlayerData();
	}

	@Override
	public int getAttackStrength() {
		return playerData.getPlayerData().getInt("attackStrength");
	}

	@Override
	public void setAttackStrength(final int strength) {
		playerData.getPlayerData().set("attackStrength", strength);
		playerData.savePlayerData();
	}

	@Override
	public int getJumpPower() {
		return playerData.getPlayerData().getInt("jumpPower");
	}

	@Override
	public void setJumpPower(final int power) {
		playerData.getPlayerData().set("jumpPower", power);
		playerData.savePlayerData();
	}

	@Override
	public Inventory getLastInventory() {
		final String inv = playerData.getPlayerData().getString("lastInv");
		return InventorySerializer.fromString(inv);
	}

	@Override
	public void setLastInventory(final String s) {
		playerData.getPlayerData().set("lastInv", s);
		playerData.savePlayerData();
	}

	@Override
	public int getDeathCount() {
		return playerData.getPlayerData().getInt("deathCount");
	}

	@Override
	public void setDeathCount(final int i) {
		playerData.getPlayerData().set("deathCount", i);
		playerData.savePlayerData();
	}

	@Override
	public int getJoinCount() {
		return playerData.getPlayerData().getInt("joinCount");
	}

	@Override
	public void setJoinCount(final int i) {
		playerData.getPlayerData().set("joinCount", i);
		playerData.savePlayerData();
	}

	@Override
	public List<String> IPs() {
		return playerData.getPlayerData().getStringList("ips");
	}

	@Override
	public void setIPs(final List<String> arr) {
		playerData.getPlayerData().set("ips", arr);
		playerData.savePlayerData();
	}

	@Override
	public void setAFK(final boolean afk) {
		playerData.getPlayerData().set("afk", afk);
		playerData.savePlayerData();
	}

	@Override
	public boolean isAFK() {
		return playerData.getPlayerData().getBoolean("afk");
	}

	/*
	 * last play = maths with ticks lived? add feature where you can set
	 * waypoints: /waypoint <set/reset> - sets the compass pointing location
	 * 
	 * feature where you can add holograms??
	 */
}
