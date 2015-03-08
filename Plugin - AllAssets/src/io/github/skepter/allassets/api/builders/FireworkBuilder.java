/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
 *
 * AllAssets was created by Skepter and Tundra (http://skepter.github.io/).
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