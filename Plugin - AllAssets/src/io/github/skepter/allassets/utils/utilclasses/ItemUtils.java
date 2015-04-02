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
package io.github.skepter.allassets.utils.utilclasses;

import io.github.skepter.allassets.misc.EnchantGlow;

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

	public static boolean isArmor(final ItemStack item) {
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

	/** Deprecated - Use ItemBuilder */
	@Deprecated
	public static ItemStack setDisplayName(final ItemStack itemStack, final String name) {
		final ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(name);
		itemStack.setItemMeta(meta);
		return itemStack;
	}

	/** Deprecated - Use ItemBuilder */
	@Deprecated
	public static String getDisplayName(final ItemStack itemStack) {
		final ItemMeta meta = itemStack.getItemMeta();
		return meta.getDisplayName();
	}

	/** Deprecated - Use ItemBuilder */
	@Deprecated
	public static ItemStack setLore(final ItemStack itemStack, final String... lore) {
		final ItemMeta meta = itemStack.getItemMeta();
		meta.setLore(Arrays.asList(lore));
		itemStack.setItemMeta(meta);
		return itemStack;
	}

	/** Deprecated - Use ItemBuilder */
	@Deprecated
	public static ItemStack addGlow(final ItemStack itemStack) {
		EnchantGlow.addGlow(itemStack, "");
		return itemStack;
	}

	/** Deprecated - Use ItemBuilder */
	@Deprecated
	public static ItemStack removeGlow(final ItemStack itemStack) {
		EnchantGlow.removeGlow(itemStack, "");
		return itemStack;
	}

	/** Deprecated - Use ItemBuilder */
	@Deprecated
	public static boolean hasGlow(final ItemStack itemStack) {
		return EnchantGlow.hasGlow(itemStack);
	}
}
