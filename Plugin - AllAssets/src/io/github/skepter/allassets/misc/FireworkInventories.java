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