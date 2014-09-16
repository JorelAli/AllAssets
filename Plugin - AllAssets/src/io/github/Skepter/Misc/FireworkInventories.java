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
		inv.setItem(0, ItemUtils.setDisplayName(new ItemStack(Material.SKULL_ITEM, 1, (short) 1), "Creeper"));
		inv.setItem(1, ItemUtils.setDisplayName(new ItemStack(Material.FIREWORK_CHARGE), "Ball"));
		inv.setItem(2, ItemUtils.setDisplayName(new ItemStack(Material.FIREBALL), "Large Ball"));
		inv.setItem(3, ItemUtils.setDisplayName(new ItemStack(Material.FEATHER), "Burst"));
		inv.setItem(4, ItemUtils.setDisplayName(new ItemStack(Material.NETHER_STAR), "Star"));
		return inv;
	}
	
	/* 2 */
	public static Inventory chooseColor() {
		final Inventory inv = Bukkit.createInventory(null, 18, "FireworkBuilder - Choose a color");

		//use all 18 colors
		return null;
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

		return null;
	}
}