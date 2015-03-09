package io.github.skepter.allassets.test;

import io.github.skepter.allassets.api.CustomInventory;
import io.github.skepter.allassets.api.CustomItemStack;
import io.github.skepter.allassets.api.builders.ItemBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class VanishPlayersItemStack extends CustomItemStack {

	private List<UUID> vanishedPlayers;
	//I'm working on it.....

	public VanishPlayersItemStack() {
		setItemStack(new ItemBuilder(Material.ENDER_PEARL).setDisplayName("Vanish players").build());
		if (vanishedPlayers == null)
			vanishedPlayers = new ArrayList<UUID>();
		else {
//			for(UUID u : vanishedPlayers) {
//				setItemStack(new ItemBuilder(Material.EYE_OF_ENDER).setDisplayName("Unvanish players").build());
//			}
		}
	}

	@Override
	public void clickAction(Player player) {
		if (vanishedPlayers.contains(player.getUniqueId())) {
			vanishedPlayers.remove(player.getUniqueId());
			setItemStack(new ItemBuilder(Material.ENDER_PEARL).setDisplayName("Vanish players").build());
			Bukkit.broadcastMessage("Players are now visible");
			CustomInventory.updateInventory(player, this);
			for (Player target : Bukkit.getOnlinePlayers())
				player.showPlayer(target);
		} else {
			//this never shows. I'm working on it.
			vanishedPlayers.add(player.getUniqueId());
			setItemStack(new ItemBuilder(Material.EYE_OF_ENDER).setDisplayName("Unvanish players").build());
			Bukkit.broadcastMessage("Players are now invisible");
			CustomInventory.updateInventory(player, this);
			for (Player target : Bukkit.getOnlinePlayers())
				player.hidePlayer(target);
		}
	}

}
