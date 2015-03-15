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
