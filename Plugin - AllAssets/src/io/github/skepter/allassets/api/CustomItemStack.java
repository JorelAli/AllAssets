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

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class CustomItemStack {

	private ItemStack is;
	private CustomInventory inv;

	public ItemStack getItemStack() {
		return is;
	}
	
	public void setItemStack(ItemStack is) {
		this.is = is;
	}

	public abstract void clickAction(Player player);

	public CustomInventory getInventory() {
		return inv;
	}
	
	public void setInventory(CustomInventory inv) {
		this.inv = inv;
	}
}
