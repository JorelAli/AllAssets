package io.github.Skepter.Utils;

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

	public static boolean isSafeStrict(Location location) {
		List<Block> nearbyBlocks = getCube(location, 3);
		for (Block block : nearbyBlocks) {
			if (block.getType().equals(Material.LAVA)) {

			}
		}
		return false;
	}

	public static List<Block> getCube(Location origin, int size) {
		int minX = origin.getBlockX() - size / 2;
		int minY = origin.getBlockY() - size / 2;
		int minZ = origin.getBlockZ() - size / 2;
		List<Block> blocks = new ArrayList<Block>();
		for (int x = minX; x < minX + size; x++)
			for (int y = minY; y < minY + size; y++)
				for (int z = minZ; z < minZ + size; z++)
					blocks.add(origin.getWorld().getBlockAt(x, y, z));
		return blocks;
	}

	public static List<Entity> getNearbyEntities(Location location, int radius) {
		List<Entity> entityList = new ArrayList<Entity>();
		for (Entity entity : location.getWorld().getEntities())
			if (entity.getLocation().distance(location) <= radius)
				entityList.add(entity);
		return entityList;
	}

	//temporary Nav
	public static Entity[] getNearbyEntitiesTemp(Location l, int radius) {
		int chunkRadius = radius < 16 ? 1 : (radius - (radius % 16)) / 16;
		HashSet<Entity> radiusEntities = new HashSet<Entity>();
		for (int chX = 0 - chunkRadius; chX <= chunkRadius; chX++) {
			for (int chZ = 0 - chunkRadius; chZ <= chunkRadius; chZ++) {
				int x = (int) l.getX(), y = (int) l.getY(), z = (int) l.getZ();
				for (Entity e : new Location(l.getWorld(), x + (chX * 16), y, z + (chZ * 16)).getChunk().getEntities())
					if (e.getLocation().distance(l) <= radius && e.getLocation().getBlock() != l.getBlock())
						radiusEntities.add(e);
			}
		}
		return radiusEntities.toArray(new Entity[radiusEntities.size()]);
	}

}
