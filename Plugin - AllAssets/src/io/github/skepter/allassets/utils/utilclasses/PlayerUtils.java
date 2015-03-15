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
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.utils.utilclasses;

import static org.bukkit.Bukkit.getOfflinePlayers;
import static org.bukkit.Bukkit.getOnlinePlayers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

public class PlayerUtils {

	/** Gets the target block. No longer messing around with hashsets and who
	 * else knows what nonsense. */
	public static Block getTargetBlock(final Player player, int range) {
		BlockIterator itr = new BlockIterator(player, range);
		Block target = itr.next();
		while (itr.hasNext()) {
			target = itr.next();
			if (target.getType().equals(Material.AIR))
				continue;
			break;
		}
		return target;
	}

	/** Gets the target block. Range defaults to 120 */
	public static Block getTargetBlock(final Player player) {
		return getTargetBlock(player, 120);
	}
	
	/**
	 * get(0) = closest to player
	 * get(1) = farthest from player
	 */
	public static List<Block> getLastTwoTargetBlocks(final Player player) {
		return getLastTwoTargetBlocks(player, 120);
	}
	
	/**
	 * get(0) = closest to player
	 * get(1) = farthest from player
	 * 
	 * If the range is -1, it will have pretty much no limit (15,000)
	 */
	public static List<Block> getLastTwoTargetBlocks(final Player player, int range) {
		if(range == -1)
			range = 15000;
		BlockIterator itr = new BlockIterator(player, range);
		Block target = itr.next();
		Block previous = null;
		while (itr.hasNext()) {
			previous = target;
			target = itr.next();
			if (target.getType().equals(Material.AIR))
				continue;
			break;
		}
		List<Block> blocks = new ArrayList<Block>();
		blocks.add(previous);
		blocks.add(target);
		return blocks;
	}

	/** Gets the player from the name. Returns null if player not found */
	public static Player getOnlinePlayerFromString(final String string) {
		for (final Player p : getOnlinePlayers())
			if (p.getName().equalsIgnoreCase(string) || p.getName().toLowerCase().startsWith(string.toLowerCase()))
				return p;
		return null;
	}

	//cache data from the world data files and install them into the UUID map
	//ensure that duplicates are NOT added! (use a set)

	/** Retrieves the list of offline player names using Bukkit's
	 * getOfflinePlayers() */
	public static List<String> getAllOfflinePlayerNames() {
		final List<String> playerNames = new ArrayList<String>();
		for (final OfflinePlayer p : getOfflinePlayers())
			playerNames.add(p.getName());
		return playerNames;
	}

	@SuppressWarnings("deprecation")
	public static OfflinePlayer getOfflinePlayerFromString(final String string) {
		for (final OfflinePlayer p : getOfflinePlayers())
			if (p.getName().equalsIgnoreCase(string))
				return p;
		return Bukkit.getOfflinePlayer(string);
	}

	public static String getPlayernameFromUUID(final UUID uuid) {
		for (final OfflinePlayer p : getOfflinePlayers())
			if (p.getUniqueId().equals(uuid))
				return p.getName();
		return null;
	}

	public static boolean isOnline(final String player) {
		for (final Player p : getOnlinePlayers())
			if (p.getName().equals(player))
				return true;
		return false;
	}
}
