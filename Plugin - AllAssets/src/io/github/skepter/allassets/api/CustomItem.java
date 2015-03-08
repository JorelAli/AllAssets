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
package io.github.skepter.allassets.api;

import io.github.skepter.allassets.commands.administration.CommandSpawnItem;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class CustomItem implements Listener {

	private ItemStack itemStack;
	private String permission;
	private Block block;
	private String itemName;

	public CustomItem(JavaPlugin plugin, ItemStack itemStack, String itemName) {
		this.itemStack = itemStack;
		this.itemName = itemName;
		registerItem();
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	public CustomItem(JavaPlugin plugin, ItemStack itemStack, String itemName, String permission) {
		this.itemStack = itemStack;
		this.itemName = itemName;
		this.permission = permission;
		registerItem();
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	private void registerItem() {
		CommandSpawnItem.items.put(itemName, itemStack);
	}

	/** Return true to cancel the event (Return false if you want nothing to
	 * happen) */
	public abstract boolean leftClickBlock(Player player);

	/** Return true to cancel the event (Return false if you want nothing to
	 * happen) */
	public abstract boolean rightClickBlock(Player player);

	/** Return true to cancel the event (Return false if you want nothing to
	 * happen) */
	public abstract boolean leftClickAir(Player player);

	/** Return true to cancel the event (Return false if you want nothing to
	 * happen) */
	public abstract boolean rightClickAir(Player player);

	public Block getInteractedBlock(Player player) {
		return block;
	}

	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		this.block = event.getClickedBlock();
		if (permission == null || event.getPlayer().hasPermission(permission))
			if (event.getPlayer().getItemInHand().equals(itemStack)) {
				switch (event.getAction()) {
				case LEFT_CLICK_AIR:
					if (leftClickAir(event.getPlayer()))
						event.setCancelled(true);
					break;
				case LEFT_CLICK_BLOCK:
					if (leftClickBlock(event.getPlayer()))
						event.setCancelled(true);
					break;
				case RIGHT_CLICK_AIR:
					if (rightClickAir(event.getPlayer()))
						event.setCancelled(true);
					break;
				case RIGHT_CLICK_BLOCK:
					if (rightClickBlock(event.getPlayer()))
						event.setCancelled(true);
					break;
				case PHYSICAL:
					break;

				}
			}
	}
}
