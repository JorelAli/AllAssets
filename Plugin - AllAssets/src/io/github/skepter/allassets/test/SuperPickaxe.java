/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 * 
 * AllAssets, created by Skepter and MCSpartans
 * 
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 * 
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and MCSpartans
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 * 
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
package io.github.skepter.allassets.test;

import io.github.skepter.allassets.api.CustomItem;
import io.github.skepter.allassets.api.utils.Sphere;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class SuperPickaxe extends CustomItem {

	public SuperPickaxe(JavaPlugin plugin, ItemStack itemStack, String itemName) {
		super(plugin, itemStack, itemName);
	}

	@Override
	public boolean leftClickBlock(Player player) {
		Sphere sphere = new Sphere(getInteractedBlock(player).getLocation(), 7);
		for(Block b : sphere.getBlocks()) {
			b.breakNaturally();
		}
		return true;
	}
}
