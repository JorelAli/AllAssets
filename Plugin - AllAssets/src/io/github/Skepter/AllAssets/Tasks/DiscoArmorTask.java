package io.github.Skepter.AllAssets.Tasks;

import io.github.Skepter.AllAssets.Utils.FireworkUtils;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class DiscoArmorTask implements Runnable {

	private final Player player;

	public DiscoArmorTask(final Player player) {
		this.player = player;
	}

	@Override
	public void run() {
		ItemStack leatherHelmet = new ItemStack(Material.LEATHER_HELMET, 1);
		LeatherArmorMeta leatherHelmetMeta = (LeatherArmorMeta) leatherHelmet.getItemMeta();
		leatherHelmetMeta.setColor(FireworkUtils.getColor(new Random().nextInt(17)));
		leatherHelmet.setItemMeta(leatherHelmetMeta);
		
		ItemStack leatherChestPlate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		LeatherArmorMeta leatherChestPlateMeta = (LeatherArmorMeta) leatherChestPlate.getItemMeta();
		leatherChestPlateMeta.setColor(FireworkUtils.getColor(new Random().nextInt(17)));
		leatherChestPlate.setItemMeta(leatherChestPlateMeta);
		
		ItemStack leatherLeggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		LeatherArmorMeta leatherLeggingsMeta = (LeatherArmorMeta) leatherLeggings.getItemMeta();
		leatherLeggingsMeta.setColor(FireworkUtils.getColor(new Random().nextInt(17)));
		leatherLeggings.setItemMeta(leatherLeggingsMeta);
		
		ItemStack leatherBoots = new ItemStack(Material.LEATHER_BOOTS, 1);
		LeatherArmorMeta leatherBootsMeta = (LeatherArmorMeta) leatherBoots.getItemMeta();
		leatherBootsMeta.setColor(FireworkUtils.getColor(new Random().nextInt(17)));
		leatherBoots.setItemMeta(leatherBootsMeta);

		player.getInventory().setHelmet(leatherHelmet);
		player.getInventory().setChestplate(leatherChestPlate);
		player.getInventory().setLeggings(leatherLeggings);
		player.getInventory().setBoots(leatherBoots);
	}

}
