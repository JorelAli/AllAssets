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
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;

public class BlockPoweredListener implements Listener {

	@EventHandler
	public void onBlockRedstoneChange(final BlockPhysicsEvent event) {
		BlockFace[] adjFaces = { BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.UP, BlockFace.DOWN };

		for (BlockFace adjFace : adjFaces) {
			Block block = event.getBlock().getRelative(adjFace);
			if (block.isBlockPowered()) {
				switch (block.getType()) {
					case PUMPKIN:
						block.setType(Material.JACK_O_LANTERN);
						break;
					case OBSIDIAN:
						block.setType(Material.GLOWSTONE);
						break;
					case NETHERRACK:
						block.getRelative(BlockFace.UP).setType(Material.FIRE);
						break;
					default:
						break;
				}
			} else {
				boolean b = false;
				for (BlockFace bf : adjFaces)
					if (event.getBlock().getRelative(bf).getType().equals(Material.REDSTONE_WIRE))
						b = true;
				if (b)
					switch (block.getType()) {
						case JACK_O_LANTERN:
							block.setType(Material.PUMPKIN);
							break;
						case GLOWSTONE:
							block.setType(Material.OBSIDIAN);
							break;
						case NETHERRACK:
							block.getRelative(BlockFace.UP).setType(Material.AIR);
							break;
						default:
							break;
					}
			}
		}
	}
}
