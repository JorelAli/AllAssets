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
