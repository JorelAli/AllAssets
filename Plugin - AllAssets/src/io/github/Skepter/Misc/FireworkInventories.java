package io.github.Skepter.Misc;

import io.github.Skepter.Utils.ItemUtils;

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
	public static Inventory chooseColor(boolean fade) {
		Inventory inv = null;
		if (fade)
			inv = Bukkit.createInventory(null, 18, "Firework - Fade");
		else
			inv = Bukkit.createInventory(null, 18, "Firework - Color");
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
		//put a way to have no fade!
		return inv;
	}

	/* 4 & 5*/
	public static Inventory chooseFlicker(boolean trail) {
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
		Inventory inv = Bukkit.createInventory(null, 9, "Do you want another color?");
		inv.setItem(0, ItemUtils.setDisplayName(new ItemStack(Material.MAGMA_CREAM), "Yes"));
		inv.setItem(1, ItemUtils.setDisplayName(new ItemStack(Material.SLIME_BALL), "No"));
		return inv;
	}
}