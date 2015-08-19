/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and MCSpartans
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and MCSpartans
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
package io.github.skepter.allassets.utils;

import io.github.skepter.allassets.items.BlockInfo;
import io.github.skepter.allassets.items.Item;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public class InputParser {

	private final String inputString;

	public InputParser(final String inputString) {
		this.inputString = inputString;
	}

	@SuppressWarnings("deprecation")
	public BlockInfo parseBlockInfo() {
		Item i = Item.match(inputString);
		if (i != null)
			return Item.match(inputString).getInfo();
		else {
			if (inputString.matches("\\d+"))
				return new BlockInfo(Material.getMaterial(Integer.parseInt(inputString)), (byte) 0);
			else if (inputString.matches("\\d+:\\d+"))
				return new BlockInfo(Material.getMaterial(Integer.parseInt(inputString.split(":")[0])), Byte.parseByte(inputString.split(":")[1]));
		}
		return null;
	}

	public EntityType parseMob() {
		if (inputString == null)
			return EntityType.UNKNOWN;
		try {
			if (EntityType.valueOf(inputString.toUpperCase()) != null)
				return EntityType.valueOf(inputString.toUpperCase());
		} catch (final Exception e) {
		}
		switch (inputString.toLowerCase()) {
			case "zombiepigman":
			case "pigzombie":
				return EntityType.PIG_ZOMBIE;
			case "enderdragon":
			case "dragon":
				return EntityType.ENDER_DRAGON;
			case "mooshroom":
			case "mushroomcow":
				return EntityType.MUSHROOM_COW;
			case "golem":
			case "irongolem":
				return EntityType.IRON_GOLEM;
		}
		return EntityType.UNKNOWN;
	}
}
