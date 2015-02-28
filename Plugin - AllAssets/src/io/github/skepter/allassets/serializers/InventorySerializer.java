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