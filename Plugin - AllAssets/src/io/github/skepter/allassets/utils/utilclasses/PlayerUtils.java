/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter 
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter 
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockIterator;

public class PlayerUtils {

	/** Gets the target block. No longer messing around with hashsets and who
	 * else knows what nonsense. */
	public static Block getTargetBlock(final Player player, final int range) {
		final BlockIterator itr = new BlockIterator(player, range);
		Block target = itr.next();
		while (itr.hasNext()) {
			target = itr.next();
			if (target.getType().equals(Material.AIR))
				continue;
			break;
		}
		return target;
	}

	/** Sets a player's item in their hand. If the item is not empty, it will
	 * shift it into the next available space and set the item in their hand.
	 * Otherwise, it will not add the item and return false. */
	public static boolean setItemInHand(final Player player, final ItemStack is) {
		if (player.getInventory().firstEmpty() == -1)
			return false;
		else {
			if (player.getItemInHand().equals(null) || player.getItemInHand().equals(Material.AIR))
				player.getInventory().setItem(player.getInventory().firstEmpty(), player.getItemInHand());
			player.setItemInHand(is);
			return true;
		}
	}

	/** Gets the target block. Range defaults to 120 */
	public static Block getTargetBlock(final Player player) {
		return getTargetBlock(player, 120);
	}

	/** get(0) = closest to player get(1) = farthest from player */
	public static List<Block> getLastTwoTargetBlocks(final Player player) {
		return getLastTwoTargetBlocks(player, 120);
	}

	/** get(0) = closest to player get(1) = farthest from player
	 *
	 * If the range is -1, it will have pretty much no limit (15,000) */
	public static List<Block> getLastTwoTargetBlocks(final Player player, int range) {
		if (range == -1)
			range = 15000;
		final BlockIterator itr = new BlockIterator(player, range);
		Block target = itr.next();
		Block previous = null;
		while (itr.hasNext()) {
			previous = target;
			target = itr.next();
			if (target.getType().equals(Material.AIR))
				continue;
			break;
		}
		final List<Block> blocks = new ArrayList<Block>();
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
	public static OfflinePlayer getOfflinePlayerFromStringExact(final String string) {
		for (final OfflinePlayer p : getOfflinePlayers())
			if (p.getName().equalsIgnoreCase(string))
				return p;
		return Bukkit.getOfflinePlayer(string);
	}
	
	@SuppressWarnings("deprecation")
	public static OfflinePlayer getOfflinePlayerFromString(final String string) {
		for (final OfflinePlayer p : getOfflinePlayers())
			if (p.getName().equalsIgnoreCase(string) || p.getName().toLowerCase().startsWith(string.toLowerCase()))
				return p;
		return Bukkit.getOfflinePlayer(string);
	}

	public static String getPlayernameFromUUID(final UUID uuid) {
		for (final OfflinePlayer p : getOfflinePlayers())
			if (p.getUniqueId().equals(uuid))
				return p.getName();
		return null;
	}

	public static boolean isOnlineExact(final String player) {
		for (final Player p : getOnlinePlayers())
			if (p.getName().equals(player))
				return true;
		return false;
	}
	
	public static boolean isOnline(final String string) {
		for (final Player p : getOnlinePlayers())
			if (p.getName().equalsIgnoreCase(string) || p.getName().toLowerCase().startsWith(string.toLowerCase()))
				return true;
		return false;
	}
}
