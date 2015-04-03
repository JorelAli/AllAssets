package io.github.skepter.allassets.test;

import io.github.skepter.allassets.api.CustomItem;
import io.github.skepter.allassets.api.utils.Sphere;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class SuperPickaxe extends CustomItem {

	public SuperPickaxe(JavaPlugin plugin, ItemStack itemStack, String itemName) {
		super(plugin, itemStack, itemName);
	}

	@Override
	public boolean leftClickBlock(Player player) {
		Sphere sphere = new Sphere(getInteractedBlock(player).getLocation(), 7);
		for(Block b : sphere.getBlocks()) {
			b.breakNaturally();
		}
		return true;
	}

	@Override
	public boolean rightClickBlock(Player player) {
		return false;
	}

	@Override
	public boolean leftClickAir(Player player) {
		return false;
	}

	@Override
	public boolean rightClickAir(Player player) {
		return false;
	}

}
