/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter 
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter 
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
		for (final FireworkEffect effect : meta.getEffects())
			fireworkMeta.addEffect(effect);
		fireworkMeta.setPower(meta.getPower());
		firework.setFireworkMeta(fireworkMeta);
	}
}
