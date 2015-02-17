package io.github.Skepter.AllAssets.Utils;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class Sphere {
	private final ArrayList<Block> sphere = new ArrayList<Block>();
	private final Location center;
	private final int radius;

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

	public Location getCenter() {
		return center;
	}

	public int getRadius() {
		return radius;
	}

	public ArrayList<Block> getBlocks() {
		return sphere;
	}

	public ArrayList<Block> getOuterLayerBlocks() {
		final Sphere sphere = new Sphere(center, radius - 1);
		final ArrayList<Block> blocks = getBlocks();
		for (final Block c : sphere.getBlocks())
			if (blocks.contains(c))
				blocks.remove(c);
		return blocks;
	}

	private boolean isInside(final int X, final int Y, final int Z) {
		if (Math.sqrt((X * X) + (Y * Y) + (Z * Z)) <= radius)
			return false;
		else
			return true;
	}

	public boolean overlaps(final Sphere other) {
		for (final Block block : other.getBlocks())
			if (contains(block.getLocation()))
				return true;
		return false;
	}

	public boolean contains(final Location loc) {
		return isInside(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
	}
}