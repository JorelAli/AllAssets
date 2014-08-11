package io.github.Skepter.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EnchantmentInventories {

	public static Inventory page1() {
		final ItemStack protection1 = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		protection1.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		final ItemStack protection2 = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		protection2.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		final ItemStack protection3 = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		protection3.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		final ItemStack protection4 = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		protection4.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);

		final ItemStack fireprotection1 = new ItemStack(Material.IRON_CHESTPLATE, 1);
		fireprotection1.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 1);
		final ItemStack fireprotection2 = new ItemStack(Material.IRON_CHESTPLATE, 1);
		fireprotection2.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 2);
		final ItemStack fireprotection3 = new ItemStack(Material.IRON_CHESTPLATE, 1);
		fireprotection3.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 3);
		final ItemStack fireprotection4 = new ItemStack(Material.IRON_CHESTPLATE, 1);
		fireprotection4.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 4);

		final ItemStack thorns1 = new ItemStack(Material.IRON_CHESTPLATE, 1);
		thorns1.addUnsafeEnchantment(Enchantment.THORNS, 1);
		final ItemStack thorns2 = new ItemStack(Material.IRON_CHESTPLATE, 1);
		thorns2.addUnsafeEnchantment(Enchantment.THORNS, 2);
		final ItemStack thorns3 = new ItemStack(Material.IRON_CHESTPLATE, 1);
		thorns3.addUnsafeEnchantment(Enchantment.THORNS, 3);

		final ItemStack blastprotection1 = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		blastprotection1.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 1);
		final ItemStack blastprotection2 = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		blastprotection2.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 2);
		final ItemStack blastprotection3 = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		blastprotection3.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 3);
		final ItemStack blastprotection4 = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		blastprotection4.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);

		final ItemStack projectileprotection1 = new ItemStack(Material.IRON_CHESTPLATE, 1);
		projectileprotection1.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
		final ItemStack projectileprotection2 = new ItemStack(Material.IRON_CHESTPLATE, 1);
		projectileprotection2.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
		final ItemStack projectileprotection3 = new ItemStack(Material.IRON_CHESTPLATE, 1);
		projectileprotection3.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);
		final ItemStack projectileprotection4 = new ItemStack(Material.IRON_CHESTPLATE, 1);
		projectileprotection4.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);

		final ItemStack respiration1 = new ItemStack(Material.DIAMOND_HELMET, 1);
		respiration1.addUnsafeEnchantment(Enchantment.OXYGEN, 1);
		final ItemStack respiration2 = new ItemStack(Material.DIAMOND_HELMET, 1);
		respiration2.addUnsafeEnchantment(Enchantment.OXYGEN, 2);
		final ItemStack respiration3 = new ItemStack(Material.DIAMOND_HELMET, 1);
		respiration3.addUnsafeEnchantment(Enchantment.OXYGEN, 3);

		final ItemStack aquaaffinity = new ItemStack(Material.DIAMOND_HELMET, 1);
		aquaaffinity.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);

		final ItemStack featherfalling1 = new ItemStack(Material.IRON_BOOTS, 1);
		featherfalling1.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 1);
		final ItemStack featherfalling2 = new ItemStack(Material.IRON_BOOTS, 1);
		featherfalling2.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 2);
		final ItemStack featherfalling3 = new ItemStack(Material.IRON_BOOTS, 1);
		featherfalling3.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 3);
		final ItemStack featherfalling4 = new ItemStack(Material.IRON_BOOTS, 1);
		featherfalling4.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 4);

		final ItemStack nextPage = new ItemStack(Material.ARROW, 1);
		final ItemStack previousPage = new ItemStack(Material.ARROW, 1);

		final ItemMeta npm = nextPage.getItemMeta();
		npm.setDisplayName(ChatColor.AQUA + "Next Page");
		nextPage.setItemMeta(npm);

		final ItemMeta ppm = previousPage.getItemMeta();
		ppm.setDisplayName(ChatColor.AQUA + "Previous Page");
		previousPage.setItemMeta(ppm);

		final Inventory inv1 = Bukkit.createInventory(null, 54, "Enchant - Armor");

		inv1.setItem(0, protection1);
		inv1.setItem(1, protection2);
		inv1.setItem(2, protection3);
		inv1.setItem(3, protection4);

		inv1.setItem(5, featherfalling1);
		inv1.setItem(6, featherfalling2);
		inv1.setItem(7, featherfalling3);
		inv1.setItem(8, featherfalling4);

		inv1.setItem(9, fireprotection1);
		inv1.setItem(10, fireprotection2);
		inv1.setItem(11, fireprotection3);
		inv1.setItem(12, fireprotection4);

		inv1.setItem(18, blastprotection1);
		inv1.setItem(19, blastprotection2);
		inv1.setItem(20, blastprotection3);
		inv1.setItem(21, blastprotection4);

		inv1.setItem(27, projectileprotection1);
		inv1.setItem(28, projectileprotection2);
		inv1.setItem(29, projectileprotection3);
		inv1.setItem(30, projectileprotection4);

		inv1.setItem(36, respiration1);
		inv1.setItem(37, respiration2);
		inv1.setItem(38, respiration3);

		inv1.setItem(45, thorns1);
		inv1.setItem(46, thorns2);
		inv1.setItem(47, thorns3);

		inv1.setItem(14, aquaaffinity);

		inv1.setItem(52, previousPage);
		inv1.setItem(53, nextPage);

		return inv1;
	}
}
