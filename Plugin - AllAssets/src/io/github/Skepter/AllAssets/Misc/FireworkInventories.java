/*******************************************************************************
 * Skepter's licence
 * Copyright 2014 
 *
 * AllAssets was created by Skepter (http://skepter.github.io/).
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
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. I've just made life easier for you :)
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
package io.github.Skepter.AllAssets.Misc;

import io.github.Skepter.AllAssets.Utils.ItemUtils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class FireworkInventories {

	/* 1 */
	public static Inventory chooseType() {
		final Inventory inv = Bukkit.createInventory(null, 9, "Firework - Type");
		inv.setItem(0, ItemUtils.setDisplayName(new ItemStack(Material.SKULL_ITEM, 1, (short) 4), "Creeper"));
		inv.setItem(1, ItemUtils.setDisplayName(new ItemStack(Material.FIREWORK_CHARGE), "Ball"));
		inv.setItem(2, ItemUtils.setDisplayName(new ItemStack(Material.FIREBALL), "Large Ball"));
		inv.setItem(3, ItemUtils.setDisplayName(new ItemStack(Material.FEATHER), "Burst"));
		inv.setItem(4, ItemUtils.setDisplayName(new ItemStack(Material.NETHER_STAR), "Star"));
		return inv;
	}

	/* 2 & 3 - fade boolean to set title to fade */
	public static Inventory chooseColor(final boolean fade) {
		Inventory inv = null;
		if (fade)
			inv = Bukkit.createInventory(null, 18, "Firework - Fade");
		else
			inv = Bukkit.createInventory(null, 18, "Firework - Color");

		inv.setItem(0, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK), "Black"));
		inv.setItem(1, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 8), "Gray"));
		inv.setItem(2, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 7), "Silver"));
		inv.setItem(3, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 3), "Brown"));
		inv.setItem(4, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 12), "Light Blue"));
		inv.setItem(5, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 4), "Blue"));
		inv.setItem(6, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 6), "Cyan"));
		inv.setItem(7, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 13), "Magenta"));
		inv.setItem(8, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 10), "Lime"));
		inv.setItem(9, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 2), "Green"));
		inv.setItem(10, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 5), "Purple"));
		inv.setItem(11, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 9), "Pink"));
		inv.setItem(12, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 1), "Red"));
		inv.setItem(13, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 14), "Orange"));
		inv.setItem(14, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 11), "Yellow"));
		inv.setItem(15, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 15), "White"));
		//put a way to have no fade!
		return inv;
	}

	/* 4 & 5*/
	public static Inventory chooseFlicker(final boolean trail) {
		Inventory inv = null;
		if (trail)
			inv = Bukkit.createInventory(null, 9, "Do you want a trail?");
		else
			inv = Bukkit.createInventory(null, 9, "Do you want flickering?");
		inv.setItem(0, ItemUtils.setDisplayName(new ItemStack(Material.MAGMA_CREAM), "Yes"));
		inv.setItem(1, ItemUtils.setDisplayName(new ItemStack(Material.SLIME_BALL), "No"));
		return inv;
	}

	/* 6 */
	public static Inventory choosePower() {
		final Inventory inv = Bukkit.createInventory(null, 9, "Choose a power size");
		inv.setItem(0, ItemUtils.setDisplayName(new ItemStack(Material.FIREWORK_CHARGE), "Power: 0"));
		inv.setItem(1, ItemUtils.setDisplayName(new ItemStack(Material.FIREWORK_CHARGE), "Power: 1"));
		inv.setItem(2, ItemUtils.setDisplayName(new ItemStack(Material.FIREWORK_CHARGE), "Power: 2"));
		inv.setItem(3, ItemUtils.setDisplayName(new ItemStack(Material.FIREWORK_CHARGE), "Power: 3"));
		return inv;
	}

	public static Inventory anotherColor() {
		final Inventory inv = Bukkit.createInventory(null, 9, "Do you want another color?");
		inv.setItem(0, ItemUtils.setDisplayName(new ItemStack(Material.MAGMA_CREAM), "Yes"));
		inv.setItem(1, ItemUtils.setDisplayName(new ItemStack(Material.SLIME_BALL), "No"));
		return inv;
	}
}