/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 * 
 * AllAssets, created by Skepter and Tundra
 * 
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 * 
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and Tundra
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 * 
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.tasks;

import io.github.skepter.allassets.utils.utilclasses.FireworkUtils;

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
		final ItemStack leatherHelmet = new ItemStack(Material.LEATHER_HELMET, 1);
		final LeatherArmorMeta leatherHelmetMeta = (LeatherArmorMeta) leatherHelmet.getItemMeta();
		leatherHelmetMeta.setColor(FireworkUtils.getColor(new Random().nextInt(17)));
		leatherHelmet.setItemMeta(leatherHelmetMeta);

		final ItemStack leatherChestPlate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		final LeatherArmorMeta leatherChestPlateMeta = (LeatherArmorMeta) leatherChestPlate.getItemMeta();
		leatherChestPlateMeta.setColor(FireworkUtils.getColor(new Random().nextInt(17)));
		leatherChestPlate.setItemMeta(leatherChestPlateMeta);

		final ItemStack leatherLeggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		final LeatherArmorMeta leatherLeggingsMeta = (LeatherArmorMeta) leatherLeggings.getItemMeta();
		leatherLeggingsMeta.setColor(FireworkUtils.getColor(new Random().nextInt(17)));
		leatherLeggings.setItemMeta(leatherLeggingsMeta);

		final ItemStack leatherBoots = new ItemStack(Material.LEATHER_BOOTS, 1);
		final LeatherArmorMeta leatherBootsMeta = (LeatherArmorMeta) leatherBoots.getItemMeta();
		leatherBootsMeta.setColor(FireworkUtils.getColor(new Random().nextInt(17)));
		leatherBoots.setItemMeta(leatherBootsMeta);

		player.getInventory().setHelmet(leatherHelmet);
		player.getInventory().setChestplate(leatherChestPlate);
		player.getInventory().setLeggings(leatherLeggings);
		player.getInventory().setBoots(leatherBoots);
	}

}
