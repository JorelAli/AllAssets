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
package io.github.skepter.allassets.test;

import io.github.skepter.allassets.api.CustomItemStack;
import io.github.skepter.allassets.api.builders.ItemBuilder;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class VanishPlayersItemStack extends CustomItemStack {

	private Set<UUID> vanishedPlayers;

	public VanishPlayersItemStack() {
		setItemStack(new ItemBuilder(Material.ENDER_PEARL).setDisplayName("Vanish players").build());
		if (vanishedPlayers == null)
			vanishedPlayers = new HashSet<UUID>();
	}

	@Override
	public void clickAction(final Player player) {
		if (vanishedPlayers.remove(player.getUniqueId())) {
			setItemStack(new ItemBuilder(Material.ENDER_PEARL).setDisplayName("Vanish players").build());
			Bukkit.broadcastMessage("Players are now visible");
			updateInventory(player);
			for (final Player target : Bukkit.getOnlinePlayers())
				player.showPlayer(target);
		} else {
			vanishedPlayers.add(player.getUniqueId());
			setItemStack(new ItemBuilder(Material.EYE_OF_ENDER).setDisplayName("Unvanish players").build());
			Bukkit.broadcastMessage("Players are now invisible");
			updateInventory(player);
			for (final Player target : Bukkit.getOnlinePlayers())
				player.hidePlayer(target);
		}
	}

}
