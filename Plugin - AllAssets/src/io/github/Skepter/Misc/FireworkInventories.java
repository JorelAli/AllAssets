package io.github.Skepter.Misc;

import io.github.Skepter.Utils.ItemUtils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class FireworkInventories {

	/* 1 */
	public static Inventory chooseType() {
		final Inventory inv = Bukkit.createInventory(null, 9, "FireworkBuilder - Choose a firework type");
		inv.setItem(0, ItemUtils.setDisplayName(new ItemStack(Material.SKULL_ITEM, 1, (short) 4), "Creeper"));
		inv.setItem(1, ItemUtils.setDisplayName(new ItemStack(Material.FIREWORK_CHARGE), "Ball"));
		inv.setItem(2, ItemUtils.setDisplayName(new ItemStack(Material.FIREBALL), "Large Ball"));
		inv.setItem(3, ItemUtils.setDisplayName(new ItemStack(Material.FEATHER), "Burst"));
		inv.setItem(4, ItemUtils.setDisplayName(new ItemStack(Material.NETHER_STAR), "Star"));
		return inv;
	}

	/* 2 */
	public static Inventory chooseColor() {
		final Inventory inv = Bukkit.createInventory(null, 18, "FireworkBuilder - Choose a color");
		inv.setItem(0, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK), "Black"));
		inv.setItem(1, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 8), "Gray"));
		inv.setItem(2, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 7), "Silver"));
		inv.setItem(3, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 3), "Maroon"));
		inv.setItem(4, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 4), "Navy"));
		inv.setItem(5, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 12), "Blue"));
		inv.setItem(6, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 6), "Teal"));
		inv.setItem(6, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 12), "Aqua"));
		inv.setItem(7, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 2), "Olive"));
		inv.setItem(8, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 10), "Lime"));
		inv.setItem(9, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 2), "Green"));
		inv.setItem(10, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 5), "Purple"));
		inv.setItem(11, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 13), "Fuchsia"));
		inv.setItem(12, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 1), "Red"));
		inv.setItem(13, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 14), "Orange"));
		inv.setItem(14, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 11), "Yellow"));
		inv.setItem(15, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 15), "White"));		
		return inv;
	}

	/* 3 */
	public static Inventory chooseFade() {
		final Inventory inv = Bukkit.createInventory(null, 18, "FireworkBuilder - Choose a fade color");
		return null;
	}

	/* 4 */
	public static Inventory chooseFlicker() {
		final Inventory inv = Bukkit.createInventory(null, 9, "FireworkBuilder - Do you want flickering?");

		return null;
	}

	/* 5 */
	public static Inventory chooseTrail() {
		final Inventory inv = Bukkit.createInventory(null, 9, "FireworkBuilder - Do you want a trail?");

		return null;
	}

	/* 6 */
	public static Inventory choosePower() {
		final Inventory inv = Bukkit.createInventory(null, 9, "FireworkBuilder - Choose a power size");
		//return itemStack upon clicking whatever :D

		return null;
	}
}