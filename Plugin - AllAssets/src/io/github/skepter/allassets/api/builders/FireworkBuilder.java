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
package io.github.skepter.allassets.api.builders;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

/** Made this to allow multiple colors / fades etc. */
public class FireworkBuilder {

	private final ItemStack firework;
	private final FireworkMeta meta;
	private final Builder builder;
	private final FireworkBuilder b;

	private int power;

	public FireworkBuilder(final int amount) {
		this.firework = new ItemStack(Material.FIREWORK, amount);
		this.meta = (FireworkMeta) firework.getItemMeta();
		this.builder = FireworkEffect.builder();
		b = this;
	}

	public FireworkBuilder setPower(final int power) {
		this.power = power;
		return b;
	}

	public FireworkBuilder addTrail() {
		builder.trail(true);
		return b;
	}

	public FireworkBuilder setType(final Type type) {
		builder.with(type);
		return b;
	}

	public FireworkBuilder addFade(final Color color) {
		builder.withFade(color);
		return b;
	}

	public FireworkBuilder addFlicker() {
		builder.flicker(true);
		return b;
	}

	public FireworkBuilder addColor(final Color color) {
		builder.withColor(color);
		return b;
	}

	public ItemStack build() {
		meta.setPower(power);
		meta.addEffect(builder.build());
		firework.setItemMeta(meta);
		return firework;
	}
}
