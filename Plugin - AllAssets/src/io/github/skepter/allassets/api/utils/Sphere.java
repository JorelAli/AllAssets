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
package io.github.skepter.allassets.api.utils;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class Sphere {
	private final ArrayList<Block> sphere = new ArrayList<Block>();
	private final Location center;
	private final int radius;

	/** Creates a sphere */
	public Sphere(final Location center, final int radius) {
		this.center = center;
		this.radius = radius;
		for (int Y = -radius; Y < radius; Y++)
			for (int X = -radius; X < radius; X++)
				for (int Z = -radius; Z < radius; Z++)
					if (Math.sqrt((X * X) + (Y * Y) + (Z * Z)) <= radius) {
						final Block block = center.getWorld().getBlockAt(X + center.getBlockX(), Y + center.getBlockY(), Z + center.getBlockZ());
						sphere.add(block);
					}
	}

	/** Creates a sphere where the blocks inside the sphere are ONLY made up of
	 * the material specified. */
	public Sphere(final Location center, final int radius, final Material... type) {
		this.center = center;
		this.radius = radius;
		for (int Y = -radius; Y < radius; Y++)
			for (int X = -radius; X < radius; X++)
				for (int Z = -radius; Z < radius; Z++)
					if (Math.sqrt((X * X) + (Y * Y) + (Z * Z)) <= radius) {
						final Block block = center.getWorld().getBlockAt(X + center.getBlockX(), Y + center.getBlockY(), Z + center.getBlockZ());
						for (final Material m : type)
							if (block.getType().equals(m))
								sphere.add(block);
					}
	}

	/** Gets the center location of the sphere */
	public Location getCenter() {
		return center;
	}

	/** Gets the radius of the sphere (Distance from the center to the rim) */
	public int getRadius() {
		return radius;
	}

	/** Gets all of the blocks inside this sphere. If you have used the
	 * Sphere(center, radius, type) construction, it will only return the blocks
	 * inside this sphere which are of that type */
	public ArrayList<Block> getBlocks() {
		return sphere;
	}

	/** Gets all of the blocks at the outer rim of this sphere */
	public ArrayList<Block> getOuterLayerBlocks() {
		final Sphere sphere = new Sphere(center, radius - 1);
		final ArrayList<Block> blocks = getBlocks();
		for (final Block c : sphere.getBlocks())
			blocks.remove(c);
		return blocks;
	}

	/** Returns true if one of the blocks from another sphere is inside this
	 * sphere */
	public boolean overlaps(final Sphere other) {
		for (final Block block : other.getBlocks())
			if (contains(block.getLocation()))
				return true;
		return false;
	}

	/** Checks if the sphere contains this location EXACTLY */
	public boolean containsPrecise(final Location loc) {
		final double x = loc.getX(), y = loc.getY(), z = loc.getZ();
		if (Math.sqrt((x * x) + (y * y) + (z * z)) <= radius)
			return false;
		else
			return true;
	}

	/** Checks if the sphere contains this location. May be off by some decimal
	 * places as it rounds to the closest block. */
	public boolean contains(final Location loc) {
		final int x = loc.getBlockX(), y = loc.getBlockY(), z = loc.getBlockZ();
		if (Math.sqrt((x * x) + (y * y) + (z * z)) <= radius)
			return false;
		else
			return true;
	}
}
