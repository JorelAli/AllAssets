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
