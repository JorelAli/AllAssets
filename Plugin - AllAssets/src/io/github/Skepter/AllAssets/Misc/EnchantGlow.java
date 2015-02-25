/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
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
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. The only exception is the BukkitDev staff who I have allowed permission to decompile (to search for malicious code)
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
package io.github.Skepter.AllAssets.Misc;

import java.lang.reflect.Field;
import java.util.HashMap;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EnchantGlow extends EnchantmentWrapper {

	private static Enchantment glow;
	private static int id;
	private static String name;

	public EnchantGlow(final int id) {
		super(id);
		EnchantGlow.id = id;
	}

	@Override
	public boolean canEnchantItem(final ItemStack item) {
		return true;
	}

	@Override
	public boolean conflictsWith(final Enchantment other) {
		return false;
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return null;
	}

	@Override
	public int getMaxLevel() {
		return 10;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getStartLevel() {
		return 1;
	}

	public static Enchantment getGlow(final String cname) {
		if (glow != null)
			return glow;

		try {
			final Field f = Enchantment.class.getDeclaredField("acceptingNew");
			f.setAccessible(true);
			f.set(null, true);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		name = cname;
		glow = new EnchantGlow(70);
		Enchantment.registerEnchantment(glow);
		return glow;
	}

	@SuppressWarnings("unchecked")
	public static void unLoad() {
		try {
			final Field f = Enchantment.class.getDeclaredField("byId");
			f.setAccessible(true);
			HashMap<Integer, Enchantment> byIDMap = (HashMap<Integer, Enchantment>) f.get(null);
			if (byIDMap.containsKey(id))
				byIDMap.remove(id);

			final Field f1 = Enchantment.class.getDeclaredField("byName");
			f1.setAccessible(true);
			HashMap<String, Enchantment> byNameMap = (HashMap<String, Enchantment>) f1.get(null);
			if (byNameMap.containsKey(name))
				byNameMap.remove(name);

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public static void addGlow(final ItemStack item, final String name) {
		item.addEnchantment(getGlow(name), 1);
	}

	public static void removeGlow(final ItemStack item, final String name) {
		item.removeEnchantment(getGlow(name));
	}

	public static boolean hasGlow(final ItemStack item) {
		if (item.containsEnchantment(getGlow(name)))
			return true;
		return false;
	}

	public static void addGlow(ItemMeta meta, String name) {
		meta.addEnchant(getGlow(name), 1, true);
	}

}
