/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter 
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter 
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.misc;

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
		
		try {
			final Field f = Enchantment.class.getDeclaredField("byId");
			f.setAccessible(true);
			@SuppressWarnings("unchecked")
			//Static access therefore, f.get(null)
			final HashMap<Integer, Enchantment> byIDMap = (HashMap<Integer, Enchantment>) f.get(null);
			if (!byIDMap.containsKey(id))
				Enchantment.registerEnchantment(glow);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		
		//Enchantment.registerEnchantment(glow);
		return glow;
	}

	@SuppressWarnings("unchecked")
	public static void unLoad() {
		try {
			final Field f = Enchantment.class.getDeclaredField("byId");
			f.setAccessible(true);
			final HashMap<Integer, Enchantment> byIDMap = (HashMap<Integer, Enchantment>) f.get(null);
			if (byIDMap.containsKey(id))
				byIDMap.remove(id);

			final Field f1 = Enchantment.class.getDeclaredField("byName");
			f1.setAccessible(true);
			final HashMap<String, Enchantment> byNameMap = (HashMap<String, Enchantment>) f1.get(null);
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

	public static void addGlow(final ItemMeta meta, final String name) {
		meta.addEnchant(getGlow(name), 1, true);
	}

}
