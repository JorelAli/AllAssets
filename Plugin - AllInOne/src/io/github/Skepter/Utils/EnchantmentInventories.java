package io.github.Skepter.Utils;

import io.github.Skepter.AllInOne;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EnchantmentInventories {

	public static Inventory page1() {

		Map<Integer, ItemStack> map = new HashMap<Integer, ItemStack>();

		for (int i = 1; i < 4; i++) {
			ItemStack is = new ItemStack(Material.DIAMOND_CHESTPLATE);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, i);
			map.put(i - 1, is);
		}
		
		for (int i = 5; i < 8; i++) {
			ItemStack is = new ItemStack(Material.IRON_BOOTS);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, i + 1);
			map.put(i, is);
		}
		
		for (int i = 9; i < 12; i++) {
			ItemStack is = new ItemStack(Material.IRON_CHESTPLATE);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, i + 1);
			map.put(i, is);
		}

		for (int i = 18; i < 21; i++) {
			ItemStack is = new ItemStack(Material.DIAMOND_CHESTPLATE);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, i + 1);
			map.put(i, is);
		}

		for (int i = 27; i < 30; i++) {
			ItemStack is = new ItemStack(Material.IRON_CHESTPLATE);
			is.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, i + 1);
			map.put(i, is);
		}

		for (int i = 36; i < 38; i++) {
			ItemStack is = new ItemStack(Material.DIAMOND_HELMET);
			is.addUnsafeEnchantment(Enchantment.OXYGEN, i + 1);
			map.put(i, is);
		}
		
		for (int i = 45; i < 47; i++) {
			ItemStack is = new ItemStack(Material.IRON_CHESTPLATE);
			is.addUnsafeEnchantment(Enchantment.THORNS, i + 1);
			map.put(i, is);
		}

		final ItemStack aquaaffinity = new ItemStack(Material.DIAMOND_HELMET);
		aquaaffinity.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
		map.put(14, aquaaffinity);

		final ItemStack nextPage = new ItemStack(Material.ARROW, 1);
		ItemUtils.setDisplayName(nextPage, AllInOne.instance().houseStyleColor + "Next Page");


		final ItemStack previousPage = new ItemStack(Material.ARROW, 1);
		ItemUtils.setDisplayName(previousPage, AllInOne.instance().houseStyleColor + "Previous Page");
		
		map.put(52, previousPage);
		map.put(53, nextPage);

		final Inventory inv1 = Bukkit.createInventory(null, 54, "Enchant - Armor");

		for (Entry<Integer, ItemStack> entry : map.entrySet()) {
			inv1.setItem(entry.getKey(), entry.getValue());
		}
		return inv1;
	}
}