package io.github.Skepter.Utils;

import io.github.Skepter.Misc.EnchantGlow;

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
		EnchantGlow.removeGlow(itemStack);
		return itemStack;
	}
}
