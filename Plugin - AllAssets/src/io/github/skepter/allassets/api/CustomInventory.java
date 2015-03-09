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

import java.util.HashMap;
import java.util.Map;

import net.minecraft.server.v1_8_R1.Material;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomInventory implements Listener {

	private Inventory inv;
	private Map<Integer, CustomItemStack> itemMap;

	/** Rows = number of rows to have. 1 row = 9 slots. */
	public CustomInventory(JavaPlugin plugin, String title, int rows) {
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
		itemMap = new HashMap<Integer, CustomItemStack>();
		inv = Bukkit.createInventory(null, rows * 9, title);
	}

	public void addCustomItemStack(CustomItemStack is, int location) {
		is.setInventory(inv);
		inv.setItem(location, is.getItemStack());
		itemMap.put(location, is);
	}

	public void addCustomItemStack(CustomItemStack is) {
		if (!(inv.firstEmpty() == -1)) {
			is.setInventory(inv);
			itemMap.put(inv.firstEmpty(), is);
			inv.addItem(is.getItemStack());
		}
		//couldn't add itemStack, no room ):
	}

	public void open(Player... players) {
		for (Player player : players)
			player.openInventory(inv);
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (event.getInventory().equals(inv)) {
			if (!event.getInventory().getItem(event.getSlot()).equals(Material.AIR)) {
				Bukkit.broadcastMessage("inv clicked");
				itemMap.get(event.getSlot()).clickAction(Bukkit.getPlayer(event.getWhoClicked().getUniqueId()));
			}
		}
	}
}
