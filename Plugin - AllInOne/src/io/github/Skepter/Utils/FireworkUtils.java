package io.github.Skepter.Utils;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

public class FireworkUtils {

	public static void spawnRandomFirework(final Location loc) {
		final Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
		final FireworkMeta fwm = fw.getFireworkMeta();
		Type type = Type.BALL;
		final int pick = new Random().nextInt(Type.values().length);
		type = Type.values()[pick];
		final int r1i = new Random().nextInt(17) + 1;
		final int r2i = new Random().nextInt(17) + 1;
		final Color c1 = getColor(r1i);
		final Color c2 = getColor(r2i);
		final FireworkEffect effect = FireworkEffect.builder().flicker(new Random().nextBoolean()).withColor(c1).withFade(c2).with(type).trail(new Random().nextBoolean()).build();
		fwm.addEffect(effect);
		final int rp = new Random().nextInt(2) + 1;
		fwm.setPower(rp);
		fw.setFireworkMeta(fwm);
	}

	private static Color getColor(final int i) {
		switch (i) {
		case 1:
			return Color.AQUA;
		case 2:
			return Color.BLACK;
		case 3:
			return Color.BLUE;
		case 4:
			return Color.FUCHSIA;
		case 5:
			return Color.GRAY;
		case 6:
			return Color.GREEN;
		case 7:
			return Color.LIME;
		case 8:
			return Color.MAROON;
		case 9:
			return Color.NAVY;
		case 10:
			return Color.OLIVE;
		case 11:
			return Color.ORANGE;
		case 12:
			return Color.PURPLE;
		case 13:
			return Color.RED;
		case 14:
			return Color.SILVER;
		case 15:
			return Color.TEAL;
		case 16:
			return Color.WHITE;
		case 17:
			return Color.YELLOW;
		}
		return null;
	}
}
