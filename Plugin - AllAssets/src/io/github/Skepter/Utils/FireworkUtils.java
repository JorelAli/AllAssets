package io.github.Skepter.Utils;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

public class FireworkUtils {

	public static void spawnRandomFirework(final Location loc) {
		final Firework firework = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
		final FireworkMeta fireworkMeta = firework.getFireworkMeta();
		final Random random = new Random();
		final FireworkEffect effect = FireworkEffect.builder().flicker(random.nextBoolean()).withColor(getColor(random.nextInt(17) + 1)).withFade(getColor(random.nextInt(17) + 1)).with(Type.values()[random.nextInt(Type.values().length)]).trail(random.nextBoolean()).build();
		fireworkMeta.addEffect(effect);
		fireworkMeta.setPower(random.nextInt(2) + 1);
		firework.setFireworkMeta(fireworkMeta);
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
	
	class CustomFireworkBuilder {
		
		private ItemStack firework;
		private FireworkMeta meta;
		
		public CustomFireworkBuilder(int amount) {
			this.firework = new ItemStack(Material.FIREWORK, amount);
			this.meta = (FireworkMeta) firework.getItemMeta();
		}
		
		public void setPower(int power) {
			meta.setPower(power);
		}
		
		public void addTrail(boolean trail) {
			meta.addEffect(FireworkEffect.builder().trail(trail).build());
		}
		
		public void setType(Type type) {
			meta.addEffect(FireworkEffect.builder().with(type).build());
		}
		
		public void addFade(Color color) {
			meta.addEffect(FireworkEffect.builder().withFade(color).build());
		}
		
		public void addFlicker(boolean flicker) {
			meta.addEffect(FireworkEffect.builder().flicker(flicker).build());
		}
		
		public void addColor(Color color) {
			meta.addEffect(FireworkEffect.builder().withColor(color).build());
		}
		
		public ItemStack getFirework() {
			firework.setItemMeta(meta);
			return firework;
		}
	}
}
