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
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.misc;

import io.github.skepter.allassets.api.builders.ItemBuilder;
import io.github.skepter.allassets.utils.Strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EnchantmentInventories {

	//PUT UNBREAKING!
	public static Inventory page1() {
		final Map<Integer, ItemStack> map = new HashMap<Integer, ItemStack>();
		for (int i = 1; i < 5; i++)
			map.put(i - 1, new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build());

		for (int i = 5; i < 9; i++) {
			final ItemStack is = new ItemStack(Material.IRON_BOOTS);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, (i - 4));
			map.put(i, is);
		}

		for (int i = 9; i < 13; i++) {
			final ItemStack is = new ItemStack(Material.IRON_CHESTPLATE);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, (i - 8));
			map.put(i, is);
		}

		for (int i = 18; i < 22; i++) {
			final ItemStack is = new ItemStack(Material.DIAMOND_CHESTPLATE);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, (i - 17));
			map.put(i, is);
		}

		for (int i = 27; i < 31; i++) {
			final ItemStack is = new ItemStack(Material.IRON_CHESTPLATE);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, (i - 26));
			map.put(i, is);
		}

		for (int i = 36; i < 39; i++) {
			final ItemStack is = new ItemStack(Material.DIAMOND_HELMET);
			is.addUnsafeEnchantment(Enchantment.OXYGEN, (i - 35));
			map.put(i, is);
		}

		for (int i = 45; i < 48; i++) {
			final ItemStack is = new ItemStack(Material.IRON_CHESTPLATE);
			is.addUnsafeEnchantment(Enchantment.THORNS, (i - 44));
			map.put(i, is);
		}

		final ItemStack aquaaffinity = new ItemStack(Material.DIAMOND_HELMET);
		aquaaffinity.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
		map.put(14, aquaaffinity);

		map.put(52, new ItemBuilder(Material.ARROW).setDisplayName(Strings.HOUSE_STYLE_COLOR + "Previous Page").addGlow().build());
		map.put(53, new ItemBuilder(Material.ARROW).setDisplayName(Strings.HOUSE_STYLE_COLOR + "Next Page").addGlow().build());

		final Inventory inv1 = Bukkit.createInventory(null, 54, "Enchant - Armor");

		for (final Entry<Integer, ItemStack> entry : map.entrySet())
			inv1.setItem(entry.getKey(), entry.getValue());
		return inv1;
	}

	public static Inventory page2() {
		final Map<Integer, ItemStack> map = new HashMap<Integer, ItemStack>();
		for (int i = 1; i < 6; i++) {
			final ItemStack is = new ItemStack(Material.DIAMOND_PICKAXE);
			is.addUnsafeEnchantment(Enchantment.DIG_SPEED, i);
			map.put(i - 1, is);
		}

		for (int i = 9; i < 14; i++) {
			final ItemStack is = new ItemStack(Material.DIAMOND_PICKAXE);
			is.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, i - 8);
			map.put(i, is);
		}

		final ItemStack silkTouch = new ItemStack(Material.IRON_PICKAXE);
		silkTouch.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1);
		map.put(18, silkTouch);

		map.put(25, new ItemBuilder(Material.ARROW).setDisplayName(Strings.HOUSE_STYLE_COLOR + "Previous Page").addGlow().build());
		map.put(26, new ItemBuilder(Material.ARROW).setDisplayName(Strings.HOUSE_STYLE_COLOR + "Next Page").addGlow().build());

		final Inventory inv1 = Bukkit.createInventory(null, 27, "Enchant - Tools");

		for (final Entry<Integer, ItemStack> entry : map.entrySet())
			inv1.setItem(entry.getKey(), entry.getValue());
		return inv1;
	}
}
