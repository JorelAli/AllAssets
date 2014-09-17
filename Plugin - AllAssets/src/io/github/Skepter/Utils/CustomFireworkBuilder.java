package io.github.Skepter.Utils;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

public class CustomFireworkBuilder {
		
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