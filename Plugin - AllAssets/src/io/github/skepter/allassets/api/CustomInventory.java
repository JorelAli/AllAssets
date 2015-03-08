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

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class CustomInventory {

	// Stuff goes here. Yeah. Something along the lines of a custom Inventory.
	//Other stuff goes here. Perhaps a CustomItemStack item which does a certain
	//action when it's inside this certain inventory

	private Inventory inv;

	/** Rows = number of rows to have. 1 row = 9 slots. */
	public CustomInventory(String title, int rows) {
		inv = Bukkit.createInventory(null, rows * 9, title);
	}
	
	public void addCustomItemStack(CustomItemStack is) {
		
	}

	public void open(Player... players) {
		for (Player player : players)
			player.openInventory(inv);
	}
}
