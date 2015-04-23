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

import io.github.skepter.allassets.api.utils.PlayerMap;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomInventory implements Listener {

	private static PlayerMap<CustomInventory> inventoryMap;
	private final Inventory inv;

	private final Map<Integer, CustomItemStack> itemMap;

	/** Rows = number of rows to have. 1 row = 9 slots. */
	public CustomInventory(final JavaPlugin plugin, String title, final int rows) {
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
		itemMap = new HashMap<Integer, CustomItemStack>();
		if (inventoryMap == null)
			inventoryMap = new PlayerMap<CustomInventory>(plugin);
		if (title.length() >= 32)
			title = title.substring(0, 32);
		inv = Bukkit.createInventory(null, rows * 9, title);
	}

	public void addCustomItemStack(final CustomItemStack is) {
		if (!(inv.firstEmpty() == -1)) {
			is.setInventory(this);
			itemMap.put(inv.firstEmpty(), is);
			inv.addItem(is.getItemStack());
		}
		//couldn't add itemStack, no room ):
	}

	public void addCustomItemStack(final CustomItemStack is, final int location) {
		is.setInventory(this);
		inv.setItem(location, is.getItemStack());
		itemMap.put(location, is);
	}

	public Inventory getInventory() {
		return inv;
	}

	public PlayerMap<CustomInventory> getInventoryMap() {
		return inventoryMap;
	}

	public Map<Integer, CustomItemStack> getItemMap() {
		return itemMap;
	}

	@EventHandler
	public void onClick(final InventoryClickEvent event) {
		if (event.getInventory().equals(inv))
			if (!event.getInventory().getItem(event.getSlot()).equals(Material.AIR))
				itemMap.get(event.getSlot()).clickAction(Bukkit.getPlayer(event.getWhoClicked().getUniqueId()));
	}

	public void open(final Player... players) {
		for (final Player player : players)
			if (inventoryMap.containsPlayer(player.getUniqueId()))
				player.openInventory(inventoryMap.get(player).inv);
			else
				openNew(player);
	}

	public void openNew(final Player... players) {
		for (final Player player : players) {
			player.openInventory(inv);
			inventoryMap.put(player, this);
		}
	}

	public void update(final Player player) {
		inventoryMap.put(player, this);
	}
}
