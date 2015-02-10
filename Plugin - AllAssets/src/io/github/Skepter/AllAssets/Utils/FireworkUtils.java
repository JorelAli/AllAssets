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
package io.github.Skepter.AllAssets.Utils;

import static org.bukkit.Color.AQUA;
import static org.bukkit.Color.BLACK;
import static org.bukkit.Color.BLUE;
import static org.bukkit.Color.FUCHSIA;
import static org.bukkit.Color.GRAY;
import static org.bukkit.Color.GREEN;
import static org.bukkit.Color.LIME;
import static org.bukkit.Color.MAROON;
import static org.bukkit.Color.NAVY;
import static org.bukkit.Color.OLIVE;
import static org.bukkit.Color.ORANGE;
import static org.bukkit.Color.PURPLE;
import static org.bukkit.Color.RED;
import static org.bukkit.Color.SILVER;
import static org.bukkit.Color.TEAL;
import static org.bukkit.Color.WHITE;
import static org.bukkit.Color.YELLOW;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

public class FireworkUtils {

	public static void spawnRandomFirework(final Location loc) {
		final Firework firework = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
		final FireworkMeta fireworkMeta = firework.getFireworkMeta();
		final Random random = new Random();
		final FireworkEffect effect = FireworkEffect.builder().flicker(random.nextBoolean()).withColor(getRandomColor()).withFade(getRandomColor()).with(Type.values()[random.nextInt(Type.values().length)]).trail(random.nextBoolean()).build();
		fireworkMeta.addEffect(effect);
		fireworkMeta.setPower(random.nextInt(2) + 1);
		firework.setFireworkMeta(fireworkMeta);
	}

	public static Color getColor(final int i) {
		final Color[] colors = { AQUA, BLACK, BLUE, FUCHSIA, GRAY, GREEN, LIME, MAROON, NAVY, OLIVE, ORANGE, PURPLE, RED, SILVER, TEAL, WHITE, YELLOW };
		return colors[i];
	}

	public static Color getRandomColor() {
		return getColor(new Random().nextInt(17));
	}
	
	public static void spawnFireworkFromItemStack(final Location loc, final ItemStack itemStack) {
		final Firework firework = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
		final FireworkMeta fireworkMeta = firework.getFireworkMeta();
		final FireworkMeta meta = (FireworkMeta) itemStack.getItemMeta();
		for(final FireworkEffect effect : meta.getEffects())
			fireworkMeta.addEffect(effect);
		fireworkMeta.setPower(meta.getPower());
		firework.setFireworkMeta(fireworkMeta);
	}
}
