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

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils {

	public static boolean isPick(final ItemStack item) {
		if ((item.getType() == Material.DIAMOND_PICKAXE) || (item.getType() == Material.IRON_PICKAXE) || (item.getType() == Material.GOLD_PICKAXE) || (item.getType() == Material.STONE_PICKAXE) || (item.getType() == Material.WOOD_PICKAXE))
			return true;
		else
			return false;
	}

	public static boolean isAxe(final ItemStack item) {
		if ((item.getType() == Material.DIAMOND_AXE) || (item.getType() == Material.IRON_AXE) || (item.getType() == Material.GOLD_AXE) || (item.getType() == Material.STONE_AXE) || (item.getType() == Material.WOOD_AXE))
			return true;
		else
			return false;
	}

	public static boolean isSpade(final ItemStack item) {
		if ((item.getType() == Material.DIAMOND_SPADE) || (item.getType() == Material.IRON_SPADE) || (item.getType() == Material.GOLD_SPADE) || (item.getType() == Material.STONE_SPADE) || (item.getType() == Material.WOOD_SPADE))
			return true;
		else
			return false;
	}

	public static boolean isHoe(final ItemStack item) {
		if ((item.getType() == Material.DIAMOND_HOE) || (item.getType() == Material.IRON_HOE) || (item.getType() == Material.GOLD_HOE) || (item.getType() == Material.STONE_HOE) || (item.getType() == Material.WOOD_HOE))
			return true;
		else
			return false;
	}

	public static boolean isSword(final ItemStack item) {
		if ((item.getType() == Material.DIAMOND_SWORD) || (item.getType() == Material.IRON_SWORD) || (item.getType() == Material.GOLD_SWORD) || (item.getType() == Material.STONE_SWORD) || (item.getType() == Material.WOOD_SWORD))
			return true;
		else
			return false;
	}

	public static boolean isArmour(final ItemStack item) {
		if ((item.getType() == Material.DIAMOND_CHESTPLATE) || (item.getType() == Material.IRON_CHESTPLATE) || (item.getType() == Material.GOLD_CHESTPLATE) || (item.getType() == Material.LEATHER_CHESTPLATE) || (item.getType() == Material.DIAMOND_BOOTS) || (item.getType() == Material.IRON_BOOTS) || (item.getType() == Material.GOLD_BOOTS) || (item.getType() == Material.LEATHER_BOOTS) || (item.getType() == Material.DIAMOND_LEGGINGS) || (item.getType() == Material.IRON_LEGGINGS) || (item.getType() == Material.GOLD_LEGGINGS) || (item.getType() == Material.LEATHER_LEGGINGS) || (item.getType() == Material.DIAMOND_BOOTS) || (item.getType() == Material.IRON_BOOTS) || (item.getType() == Material.GOLD_BOOTS) || (item.getType() == Material.LEATHER_BOOTS) || (item.getType() == Material.DIAMOND_HELMET) || (item.getType() == Material.IRON_HELMET) || (item.getType() == Material.GOLD_HELMET) || (item.getType() == Material.LEATHER_HELMET))
			return true;
		else
			return false;
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

	public static ItemStack setLore(final ItemStack itemStack, final String[] lore) {
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
