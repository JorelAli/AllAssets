/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Misc;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.Utils.ItemUtils;

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
		for (int i = 1; i < 5; i++) {
			final ItemStack is = new ItemStack(Material.DIAMOND_CHESTPLATE);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, i);
			map.put(i - 1, is);
		}

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

		final ItemStack nextPage = new ItemStack(Material.ARROW, 1);
		ItemUtils.setDisplayName(nextPage, AllAssets.houseStyleColor + "Next Page");
		ItemUtils.addGlow(nextPage);

		final ItemStack previousPage = new ItemStack(Material.ARROW, 1);
		ItemUtils.setDisplayName(previousPage, AllAssets.houseStyleColor + "Previous Page");
		ItemUtils.addGlow(previousPage);

		map.put(52, previousPage);
		map.put(53, nextPage);

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
			is.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS , i - 8);
			map.put(i, is);
		}

		final ItemStack silkTouch = new ItemStack(Material.IRON_PICKAXE);
		silkTouch.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1);
		map.put(18, silkTouch);

		final ItemStack nextPage = new ItemStack(Material.ARROW, 1);
		ItemUtils.setDisplayName(nextPage, AllAssets.houseStyleColor + "Next Page");
		ItemUtils.addGlow(nextPage);

		final ItemStack previousPage = new ItemStack(Material.ARROW, 1);
		ItemUtils.setDisplayName(previousPage, AllAssets.houseStyleColor + "Previous Page");
		ItemUtils.addGlow(previousPage);

		map.put(25, previousPage);
		map.put(26, nextPage);

		final Inventory inv1 = Bukkit.createInventory(null, 27, "Enchant - Tools");

		for (final Entry<Integer, ItemStack> entry : map.entrySet())
			inv1.setItem(entry.getKey(), entry.getValue());
		return inv1;
	}
}