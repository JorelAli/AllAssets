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
package io.github.skepter.allassets.utils.utilclasses;

import io.github.skepter.allassets.reflection.MinecraftReflectionUtils;
import io.github.skepter.allassets.reflection.ReflectionUtils;

import org.bukkit.inventory.ItemStack;

@SuppressWarnings("deprecation")
public class ItemUtils {

	private ItemStack is;

	public ItemUtils(final ItemStack is) {
		this.is = is;
	}

	@Deprecated
	public ItemStack addStringNBT(String key, String data) {
		try {
			Object nmsItem = new MinecraftReflectionUtils().craftItemStackClass.getDeclaredMethod("asNMSCopy", ItemStack.class).invoke(null, is);
			Object tag = ReflectionUtils.getPrivateFieldValue(nmsItem, "tag");
			if (tag == null)
				tag = new MinecraftReflectionUtils().getNMSClass("NBTTagCompund").newInstance();
			tag.getClass().getDeclaredMethod("setString", String.class, String.class).invoke(tag, key, data);
			return (ItemStack) new MinecraftReflectionUtils().craftItemStackClass.getDeclaredMethod("asCraftMirror", nmsItem.getClass()).invoke(null, nmsItem);
		} catch (Exception e) {
		}
		return is;
	}

	@Deprecated
	public String getStringNBT(String key) {
		try {
			Object nmsItem = new MinecraftReflectionUtils().craftItemStackClass.getDeclaredMethod("asNMSCopy", ItemStack.class).invoke(null, is);
			Object tag = ReflectionUtils.getPrivateFieldValue(nmsItem, "tag");
			return (String) tag.getClass().getDeclaredMethod("getString", String.class).invoke(tag, key);
		} catch (Exception e) {
		}
		return null;
	}

	public boolean isPick() {
		switch (is.getType()) {
			case DIAMOND_PICKAXE:
			case IRON_PICKAXE:
			case GOLD_PICKAXE:
			case STONE_PICKAXE:
			case WOOD_PICKAXE:
				return true;
			default:
				return false;
		}
	}

	public boolean isAxe() {
		switch (is.getType()) {
			case DIAMOND_AXE:
			case IRON_AXE:
			case GOLD_AXE:
			case STONE_AXE:
			case WOOD_AXE:
				return true;
			default:
				return false;
		}
	}

	public boolean isSpade() {
		switch (is.getType()) {
			case DIAMOND_SPADE:
			case IRON_SPADE:
			case GOLD_SPADE:
			case STONE_SPADE:
			case WOOD_SPADE:
				return true;
			default:
				return false;
		}
	}

	public boolean isHoe() {
		switch (is.getType()) {
			case DIAMOND_HOE:
			case IRON_HOE:
			case GOLD_HOE:
			case STONE_HOE:
			case WOOD_HOE:
				return true;
			default:
				return false;
		}
	}

	public boolean isSword() {
		switch (is.getType()) {
			case DIAMOND_SWORD:
			case IRON_SWORD:
			case GOLD_SWORD:
			case STONE_SWORD:
			case WOOD_SWORD:
				return true;
			default:
				return false;
		}
	}

	public boolean isArmor() {
		switch (is.getType()) {
			case DIAMOND_HELMET:
			case IRON_HELMET:
			case GOLD_HELMET:
			case LEATHER_HELMET:
			case CHAINMAIL_HELMET:
			case DIAMOND_CHESTPLATE:
			case IRON_CHESTPLATE:
			case GOLD_CHESTPLATE:
			case LEATHER_CHESTPLATE:
			case CHAINMAIL_CHESTPLATE:
			case DIAMOND_LEGGINGS:
			case IRON_LEGGINGS:
			case GOLD_LEGGINGS:
			case LEATHER_LEGGINGS:
			case CHAINMAIL_LEGGINGS:
			case DIAMOND_BOOTS:
			case IRON_BOOTS:
			case GOLD_BOOTS:
			case LEATHER_BOOTS:
			case CHAINMAIL_BOOTS:
				return true;
			default:
				return false;
		}
	}

	public boolean isRepairable() {
		switch (is.getType()) {
			case DIAMOND_PICKAXE:
			case DIAMOND_SWORD:
			case DIAMOND_SPADE:
			case DIAMOND_AXE:
			case DIAMOND_HOE:
			case DIAMOND_HELMET:
			case DIAMOND_CHESTPLATE:
			case DIAMOND_LEGGINGS:
			case DIAMOND_BOOTS:
			case IRON_PICKAXE:
			case IRON_SWORD:
			case IRON_SPADE:
			case IRON_AXE:
			case IRON_HOE:
			case IRON_HELMET:
			case IRON_CHESTPLATE:
			case IRON_LEGGINGS:
			case IRON_BOOTS:
			case GOLD_PICKAXE:
			case GOLD_SWORD:
			case GOLD_SPADE:
			case GOLD_AXE:
			case GOLD_HOE:
			case GOLD_HELMET:
			case GOLD_CHESTPLATE:
			case GOLD_LEGGINGS:
			case GOLD_BOOTS:
			case STONE_PICKAXE:
			case STONE_SWORD:
			case STONE_SPADE:
			case STONE_AXE:
			case STONE_HOE:
			case CHAINMAIL_HELMET:
			case CHAINMAIL_CHESTPLATE:
			case CHAINMAIL_LEGGINGS:
			case CHAINMAIL_BOOTS:
			case WOOD_PICKAXE:
			case WOOD_SWORD:
			case WOOD_SPADE:
			case WOOD_AXE:
			case WOOD_HOE:
			case LEATHER_HELMET:
			case LEATHER_CHESTPLATE:
			case LEATHER_LEGGINGS:
			case LEATHER_BOOTS:
			case FLINT_AND_STEEL:
			case SHEARS:
			case BOW:
			case FISHING_ROD:
			case ANVIL:
				return true;
			default:
				return false;
		}
	}
}
