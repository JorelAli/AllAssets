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
package io.github.Skepter.AllAssets.Utils;

import io.github.Skepter.AllAssets.Misc.EnchantGlow;

import java.util.Arrays;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils {

	public static boolean isPick(final ItemStack item) {
		switch (item.getType()) {
		case DIAMOND_PICKAXE:
		case IRON_PICKAXE:
		case GOLD_PICKAXE:
		case STONE_PICKAXE:
		case WOOD_PICKAXE:
			return true;
		default:
			return false;
		}
	}

	public static boolean isAxe(final ItemStack item) {
		switch (item.getType()) {
		case DIAMOND_AXE:
		case IRON_AXE:
		case GOLD_AXE:
		case STONE_AXE:
		case WOOD_AXE:
			return true;
		default:
			return false;
		}
	}

	public static boolean isSpade(final ItemStack item) {
		switch (item.getType()) {
		case DIAMOND_SPADE:
		case IRON_SPADE:
		case GOLD_SPADE:
		case STONE_SPADE:
		case WOOD_SPADE:
			return true;
		default:
			return false;
		}
	}

	public static boolean isHoe(final ItemStack item) {
		switch (item.getType()) {
		case DIAMOND_HOE:
		case IRON_HOE:
		case GOLD_HOE:
		case STONE_HOE:
		case WOOD_HOE:
			return true;
		default:
			return false;
		}
	}

	public static boolean isSword(final ItemStack item) {
		switch (item.getType()) {
		case DIAMOND_SWORD:
		case IRON_SWORD:
		case GOLD_SWORD:
		case STONE_SWORD:
		case WOOD_SWORD:
			return true;
		default:
			return false;
		}
	}

	public static boolean isArmour(final ItemStack item) {
		switch (item.getType()) {
		case DIAMOND_HELMET:
		case IRON_HELMET:
		case GOLD_HELMET:
		case LEATHER_HELMET:
		case CHAINMAIL_HELMET:
		case DIAMOND_CHESTPLATE:
		case IRON_CHESTPLATE:
		case GOLD_CHESTPLATE:
		case LEATHER_CHESTPLATE:
		case CHAINMAIL_CHESTPLATE:
		case DIAMOND_LEGGINGS:
		case IRON_LEGGINGS:
		case GOLD_LEGGINGS:
		case LEATHER_LEGGINGS:
		case CHAINMAIL_LEGGINGS:
		case DIAMOND_BOOTS:
		case IRON_BOOTS:
		case GOLD_BOOTS:
		case LEATHER_BOOTS:
		case CHAINMAIL_BOOTS:
			return true;
		default:
			return false;
		}
	}

	public static ItemStack setDisplayName(final ItemStack itemStack, final String name) {
		final ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(name);
		itemStack.setItemMeta(meta);
		return itemStack;
	}

	public static String getDisplayName(final ItemStack itemStack) {
		final ItemMeta meta = itemStack.getItemMeta();
		return meta.getDisplayName();
	}

	public static ItemStack setLore(final ItemStack itemStack, final String... lore) {
		final ItemMeta meta = itemStack.getItemMeta();
		meta.setLore(Arrays.asList(lore));
		itemStack.setItemMeta(meta);
		return itemStack;
	}

	public static ItemStack addGlow(final ItemStack itemStack) {
		EnchantGlow.addGlow(itemStack, "");
		return itemStack;
	}

	public static ItemStack removeGlow(final ItemStack itemStack) {
		EnchantGlow.removeGlow(itemStack, "");
		return itemStack;
	}

	public static boolean hasGlow(final ItemStack itemStack) {
		return EnchantGlow.hasGlow(itemStack);
	}
}
