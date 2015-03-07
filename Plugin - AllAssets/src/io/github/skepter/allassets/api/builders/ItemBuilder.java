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
package io.github.skepter.allassets.api.builders;

import io.github.skepter.allassets.misc.EnchantGlow;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {

	private ItemStack itemStack;
	private ItemMeta meta;
	private ItemBuilder builder;

	/** Creates a new ItemBuilder with a material. The amount is defaulted to 1 */
	public ItemBuilder(Material material) {
		this.itemStack = new ItemStack(material);
		this.meta = itemStack.getItemMeta();
		builder = this;
	}

	/** Creates a new ItemBuilder with a material and an amount */
	public ItemBuilder(Material material, int amount) {
		this.itemStack = new ItemStack(material, amount);
		this.meta = itemStack.getItemMeta();
		builder = this;
	}

	/** Creates a new ItemBuilder with a material and a data value. The amount is
	 * defaulted to 1 */
	public ItemBuilder(Material material, short data) {
		this.itemStack = new ItemStack(material, 1, data);
		this.meta = itemStack.getItemMeta();
		builder = this;
	}

	/** Creates a new ItemBuilder with a material, an amount and a data value */
	public ItemBuilder(Material material, int amount, short data) {
		this.itemStack = new ItemStack(material, amount, data);
		this.meta = itemStack.getItemMeta();
		builder = this;
	}

	/** Creates a new ItemBuilder with an ItemStack */
	public ItemBuilder(ItemStack itemStack) {
		this.itemStack = itemStack;
		this.meta = itemStack.getItemMeta();
		builder = this;
	}

	/** Gets the generated ItemStack */
	public ItemStack build() {
		itemStack.setItemMeta(meta);
		return itemStack;
	}

	/** Sets the display name of the item */
	public ItemBuilder setDisplayName(final String name) {
		meta.setDisplayName(name);
		return builder;
	}

	/** Sets the lore of the item */
	public ItemBuilder setLore(final String... lore) {
		meta.setLore(Arrays.asList(lore));
		return builder;
	}

	/** Sets the lore of the item */
	public ItemBuilder setLore(final List<String> lore) {
		meta.setLore(lore);
		return builder;
	}

	/** Adds a new lore to the existing item */
	public ItemBuilder addLore(final String... lore) {
		List<String> currentLore = getLore();
		for(String str : lore)
			currentLore.add(str);
		setLore(currentLore);
		return builder;
	}

	/** Adds an enchantment to the item */
	public ItemBuilder addEnchantment(final Enchantment enchantment, int power) {
		meta.addEnchant(enchantment, power, true);
		return builder;
	}

	/** Removes an enchantment from the item */
	public ItemBuilder removeEnchantment(final Enchantment enchantment) {
		meta.removeEnchant(enchantment);
		return builder;
	}

	/** Gets display name of the item */
	public String getDisplayName() {
		return meta.getDisplayName();
	}

	/** Gets lore of the item */
	public List<String> getLore() {
		return meta.getLore();
	}

	/** Adds a glow to the item */
	public ItemBuilder addGlow() {
		EnchantGlow.addGlow(meta, "");
		return builder;
	}

	/** Removes a glow from the item */
	public ItemBuilder removeGlow() {
		if (hasGlow())
			EnchantGlow.removeGlow(itemStack, "");
		return builder;
	}

	/** Checks if the item has a glow present */
	public boolean hasGlow() {
		return EnchantGlow.hasGlow(itemStack);
	}
}
