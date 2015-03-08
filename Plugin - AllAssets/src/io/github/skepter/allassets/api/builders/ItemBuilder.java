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
package io.github.skepter.allassets.api.builders;

import io.github.skepter.allassets.misc.EnchantGlow;

import java.util.ArrayList;
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
		if(currentLore == null || currentLore.isEmpty())
			currentLore = new ArrayList<String>();
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
