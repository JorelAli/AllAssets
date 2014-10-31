package io.github.Skepter.AllAssets.Misc;

import java.lang.reflect.Field;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.inventory.ItemStack;

public class EnchantGlow extends EnchantmentWrapper {

	private static Enchantment glow;

	static String name;

	public EnchantGlow(final int id) {
		super(id);
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
		glow = new EnchantGlow(255);
		Enchantment.registerEnchantment(glow);
		return glow;
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

}
