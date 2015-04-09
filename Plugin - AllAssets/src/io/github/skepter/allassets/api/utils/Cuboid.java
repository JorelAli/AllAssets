package io.github.skepter.allassets.api.utils;

import io.github.skepter.allassets.commands.worldmodifier.WorldModifierData;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class Cuboid {

	private Location loc1;
	private Location loc2;
	
	public Cuboid(WorldModifierData data) {
		loc1 = data.getPos1();
		loc2 = data.getPos2();
		
	}
	
	public List<Block> blocksFromTwoPoints() {
		final List<Block> blocks = new ArrayList<Block>();

		final int topBlockX = (loc1.getBlockX() < loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());
		final int bottomBlockX = (loc1.getBlockX() > loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());

		final int topBlockY = (loc1.getBlockY() < loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY());
		final int bottomBlockY = (loc1.getBlockY() > loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY());

		final int topBlockZ = (loc1.getBlockZ() < loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());
		final int bottomBlockZ = (loc1.getBlockZ() > loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());

		for (int x = bottomBlockX; x <= topBlockX; x++)
			for (int z = bottomBlockZ; z <= topBlockZ; z++)
				for (int y = bottomBlockY; y <= topBlockY; y++)
					blocks.add(loc1.getWorld().getBlockAt(x, y, z));

		return blocks;
	}

	public List<Block> blocksFromTwoPointsEx(final Material... excludedBlocks) {
		final List<Block> blocks = new ArrayList<Block>();

		final int topBlockX = (loc1.getBlockX() < loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());
		final int bottomBlockX = (loc1.getBlockX() > loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());

		final int topBlockY = (loc1.getBlockY() < loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY());
		final int bottomBlockY = (loc1.getBlockY() > loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY());

		final int topBlockZ = (loc1.getBlockZ() < loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());
		final int bottomBlockZ = (loc1.getBlockZ() > loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());

		for (int x = bottomBlockX; x <= topBlockX; x++)
			for (int z = bottomBlockZ; z <= topBlockZ; z++)
				for (int y = bottomBlockY; y <= topBlockY; y++)
					for (final Material m : excludedBlocks)
						if (!(loc1.getWorld().getBlockAt(x, y, z).getType() == m))
							blocks.add(loc1.getWorld().getBlockAt(x, y, z));
		return blocks;
	}

	public List<Block> blocksFromTwoPointsInc(final Material... includedBlocks) {
		final List<Block> blocks = new ArrayList<Block>();

		final int topBlockX = (loc1.getBlockX() < loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());
		final int bottomBlockX = (loc1.getBlockX() > loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());

		final int topBlockY = (loc1.getBlockY() < loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY());
		final int bottomBlockY = (loc1.getBlockY() > loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY());

		final int topBlockZ = (loc1.getBlockZ() < loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());
		final int bottomBlockZ = (loc1.getBlockZ() > loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());

		for (int x = bottomBlockX; x <= topBlockX; x++)
			for (int z = bottomBlockZ; z <= topBlockZ; z++)
				for (int y = bottomBlockY; y <= topBlockY; y++)
					for (final Material m : includedBlocks)
						if (loc1.getWorld().getBlockAt(x, y, z).getType() == m)
							blocks.add(loc1.getWorld().getBlockAt(x, y, z));
		return blocks;
	}

	/** Gets information for WM regen */
	public List<Block> getChunkData() {
		final List<Block> blocks = new ArrayList<Block>();

		final int topBlockX = (loc1.getBlockX() < loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());
		final int bottomBlockX = (loc1.getBlockX() > loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());

		final int topBlockY = (loc1.getBlockY() < loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY());
		final int bottomBlockY = (loc1.getBlockY() > loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY());
		final int y = topBlockY - bottomBlockY;

		final int topBlockZ = (loc1.getBlockZ() < loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());
		final int bottomBlockZ = (loc1.getBlockZ() > loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());

		for (int x = bottomBlockX; x <= topBlockX; x++)
			for (int z = bottomBlockZ; z <= topBlockZ; z++)
				blocks.add(loc1.getWorld().getBlockAt(x, y, z));
		return blocks;
	}
}
