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
package io.github.skepter.allassets.misc;

import static org.bukkit.Bukkit.createInventory;
import static org.bukkit.Material.FEATHER;
import static org.bukkit.Material.FIREBALL;
import static org.bukkit.Material.FIREWORK_CHARGE;
import static org.bukkit.Material.INK_SACK;
import static org.bukkit.Material.MAGMA_CREAM;
import static org.bukkit.Material.NETHER_STAR;
import static org.bukkit.Material.SKULL_ITEM;
import static org.bukkit.Material.SLIME_BALL;
import io.github.skepter.allassets.api.builders.ItemBuilder;

import org.bukkit.inventory.Inventory;

public class FireworkInventories {

	/* 1 */
	public static Inventory chooseType() {
		final Inventory inv = createInventory(null, 9, "Firework - Type");
		inv.setItem(0, new ItemBuilder(SKULL_ITEM, (short) 4).setDisplayName("Creeper").build());
		inv.setItem(1, new ItemBuilder(FIREWORK_CHARGE).setDisplayName("Ball").build());
		inv.setItem(2, new ItemBuilder(FIREBALL).setDisplayName("Large Ball").build());
		inv.setItem(3, new ItemBuilder(FEATHER).setDisplayName("Burst").build());
		inv.setItem(4, new ItemBuilder(NETHER_STAR).setDisplayName("Star").build());
		return inv;
	}

	/* 2 & 3 - fade boolean to set title to fade */
	public static Inventory chooseColor(final boolean fade) {
		Inventory inv = null;
		if (fade)
			inv = createInventory(null, 18, "Firework - Fade");
		else
			inv = createInventory(null, 18, "Firework - Color");

		inv.setItem(0, new ItemBuilder(INK_SACK).setDisplayName("Black").build());
		inv.setItem(1, new ItemBuilder(INK_SACK, 1, (short) 8).setDisplayName("Gray").build());
		inv.setItem(2, new ItemBuilder(INK_SACK, 1, (short) 7).setDisplayName("Silver").build());
		inv.setItem(3, new ItemBuilder(INK_SACK, 1, (short) 3).setDisplayName("Brown").build());
		inv.setItem(4, new ItemBuilder(INK_SACK, 1, (short) 12).setDisplayName("Light Blue").build());
		inv.setItem(5, new ItemBuilder(INK_SACK, 1, (short) 4).setDisplayName("Blue").build());
		inv.setItem(6, new ItemBuilder(INK_SACK, 1, (short) 6).setDisplayName("Cyan").build());
		inv.setItem(7, new ItemBuilder(INK_SACK, 1, (short) 13).setDisplayName("Magenta").build());
		inv.setItem(8, new ItemBuilder(INK_SACK, 1, (short) 10).setDisplayName("Lime").build());
		inv.setItem(9, new ItemBuilder(INK_SACK, 1, (short) 2).setDisplayName("Green").build());
		inv.setItem(10, new ItemBuilder(INK_SACK, 1, (short) 5).setDisplayName("Purple").build());
		inv.setItem(11, new ItemBuilder(INK_SACK, 1, (short) 9).setDisplayName("Pink").build());
		inv.setItem(12, new ItemBuilder(INK_SACK, 1, (short) 1).setDisplayName("Red").build());
		inv.setItem(13, new ItemBuilder(INK_SACK, 1, (short) 14).setDisplayName("Orange").build());
		inv.setItem(14, new ItemBuilder(INK_SACK, 1, (short) 11).setDisplayName("Yellow").build());
		inv.setItem(15, new ItemBuilder(INK_SACK, 1, (short) 15).setDisplayName("White").build());
		//put a way to have no fade!
		return inv;
	}

	/* 4 & 5*/
	public static Inventory chooseFlicker(final boolean trail) {
		Inventory inv = null;
		if (trail)
			inv = createInventory(null, 9, "Do you want a trail?");
		else
			inv = createInventory(null, 9, "Do you want flickering?");
		inv.setItem(0, new ItemBuilder(MAGMA_CREAM).setDisplayName("Yes").build());
		inv.setItem(1, new ItemBuilder(SLIME_BALL).setDisplayName("No").build());
		return inv;
	}

	/* 6 */
	public static Inventory choosePower() {
		final Inventory inv = createInventory(null, 9, "Choose a power size");
		for (int i = 0; i <= 3; i++) {
			inv.setItem(i, new ItemBuilder(FIREWORK_CHARGE).setDisplayName("Power: " + i).build());
		}
		return inv;
	}

	public static Inventory anotherColor() {
		final Inventory inv = createInventory(null, 9, "Do you want another color?");
		inv.setItem(0, new ItemBuilder(MAGMA_CREAM).setDisplayName("Yes").build());
		inv.setItem(1, new ItemBuilder(SLIME_BALL).setDisplayName("No").build());
		return inv;
	}
}
