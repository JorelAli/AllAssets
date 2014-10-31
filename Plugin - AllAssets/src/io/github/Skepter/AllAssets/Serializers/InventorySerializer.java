package io.github.Skepter.AllAssets.Serializers;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

public final class InventorySerializer {

	public static String toString(final Inventory i) {
		final YamlConfiguration configuration = new YamlConfiguration();
		configuration.set("Title", i.getTitle());
		configuration.set("Size", i.getSize());
		for (int a = 0; a < i.getSize(); a++) {
			final ItemStack s = i.getItem(a);
			if (s != null)
				configuration.set("Contents." + a, s);
		}
		return Base64Coder.encodeString(configuration.saveToString());
	}

	public static Inventory fromString(final String s) {
		final YamlConfiguration configuration = new YamlConfiguration();
		try {
			configuration.loadFromString(Base64Coder.decodeString(s));
			final Inventory i = Bukkit.createInventory(null, configuration.getInt("Size"), configuration.getString("Title"));
			final ConfigurationSection contents = configuration.getConfigurationSection("Contents");
			for (final String index : contents.getKeys(false))
				i.setItem(Integer.parseInt(index), contents.getItemStack(index));
			return i;
		} catch (final InvalidConfigurationException e) {
			return null;
		}
	}
}