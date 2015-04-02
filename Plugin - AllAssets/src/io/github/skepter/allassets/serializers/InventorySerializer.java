/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and Tundra
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and Tundra
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.serializers;

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
