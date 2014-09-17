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

	private boolean trail;
	private Type type;
	private Color color;
	private Color fade;
	private int power;
	private boolean flicker;

	public CustomFireworkBuilder(int amount) {
		this.firework = new ItemStack(Material.FIREWORK, amount);
		this.meta = (FireworkMeta) firework.getItemMeta();
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void addTrail(boolean trail) {
		this.trail = trail;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void addFade(Color color) {
		this.fade = color;
	}

	public void addFlicker(boolean flicker) {
		this.flicker = flicker;
	}

	public void addColor(Color color) {
		this.color = color;
	}

	public ItemStack getFirework() {
		meta.setPower(power);
		meta.addEffect(FireworkEffect.builder().withColor(color).withFade(fade).flicker(flicker).trail(trail).with(type).build());
		firework.setItemMeta(meta);
		return firework;
	}
}