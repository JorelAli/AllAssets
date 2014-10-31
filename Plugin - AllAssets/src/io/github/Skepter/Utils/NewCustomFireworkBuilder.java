package io.github.Skepter.Utils;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

/** Made this to allow multiple colors / fades etc. */
public class NewCustomFireworkBuilder {

	private ItemStack firework;
	private FireworkMeta meta;
	private Builder builder;

	private int power;

	public NewCustomFireworkBuilder(int amount) {
		this.firework = new ItemStack(Material.FIREWORK, amount);
		this.meta = (FireworkMeta) firework.getItemMeta();
		this.builder = FireworkEffect.builder();
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void addTrail(boolean trail) {
		builder.trail(trail);
	}

	public void setType(Type type) {
		builder.with(type);
	}

	public void addFade(Color color) {
		builder.withFade(color);
	}

	public void addFlicker(boolean flicker) {
		builder.flicker(flicker);
	}

	public void addColor(Color color) {
		builder.withColor(color);
	}

	public ItemStack getFirework() {
		meta.setPower(power);
		meta.addEffect(builder.build());
		firework.setItemMeta(meta);
		return firework;
	}
}