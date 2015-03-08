/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
 *
 * AllAssets was created by Skepter and Tundra (http://skepter.github.io/).
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
package io.github.skepter.allassets.utils.utilclasses;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

public class LocationUtils {

	/* Used to calculate locations
	 * E.g. exact spawn location for a mob
	 * Location to teleport a player using asc/desc
	 * Checking if the target location is safe or not */

	public static boolean isSafeStrict(final Location location) {
		final List<Block> nearbyBlocks = getCube(location, 3);
		for (final Block block : nearbyBlocks)
			if (block.getType().equals(Material.LAVA)) {

			}
		//nearbyEntities zombies etc.
		return false;
	}

	public static List<Block> getCube(final Location origin, final int size) {
		final int minX = origin.getBlockX() - (size / 2);
		final int minY = origin.getBlockY() - (size / 2);
		final int minZ = origin.getBlockZ() - (size / 2);
		final List<Block> blocks = new ArrayList<Block>();
		for (int x = minX; x < (minX + size); x++)
			for (int y = minY; y < (minY + size); y++)
				for (int z = minZ; z < (minZ + size); z++)
					blocks.add(origin.getWorld().getBlockAt(x, y, z));
		return blocks;
	}

	public static List<Entity> getNearbyEntities(final Location location, final int radius) {
		final List<Entity> entityList = new ArrayList<Entity>();
		for (final Entity entity : location.getWorld().getEntities())
			if (entity.getLocation().distance(location) <= radius)
				entityList.add(entity);
		return entityList;
	}

	//temporary Nav
	public static Entity[] getNearbyEntitiesTemp(final Location l, final int radius) {
		final int chunkRadius = radius < 16 ? 1 : (radius - (radius % 16)) / 16;
		final HashSet<Entity> radiusEntities = new HashSet<Entity>();
		for (int chX = 0 - chunkRadius; chX <= chunkRadius; chX++)
			for (int chZ = 0 - chunkRadius; chZ <= chunkRadius; chZ++) {
				final int x = (int) l.getX(), y = (int) l.getY(), z = (int) l.getZ();
				for (final Entity e : new Location(l.getWorld(), x + (chX * 16), y, z + (chZ * 16)).getChunk().getEntities())
					if ((e.getLocation().distance(l) <= radius) && (e.getLocation().getBlock() != l.getBlock()))
						radiusEntities.add(e);
			}
		return radiusEntities.toArray(new Entity[radiusEntities.size()]);
	}

	public static Location getCenter(final Location loc) {
		return new Location(loc.getWorld(), getCenter(loc.getBlockX()), getCenter(loc.getBlockY()), getCenter(loc.getBlockZ()));
	}

	private static double getCenter(final int location) {
		final double newLocation = location;
		return newLocation + 0.5D;
	}

}
