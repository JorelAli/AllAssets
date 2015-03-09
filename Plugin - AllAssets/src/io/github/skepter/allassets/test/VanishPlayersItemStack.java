package io.github.skepter.allassets.test;

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
	
	public VanishPlayersItemStack() {
		setItemStack(new ItemBuilder(Material.ENDER_PEARL).setDisplayName("Vanish players").build());
		vanishedPlayers = new ArrayList<UUID>();
	}

	@Override
	public void clickAction(Player player) {
		if(vanishedPlayers.contains(player.getUniqueId())) {
			vanishedPlayers.remove(player.getUniqueId());
			setItemStack(new ItemBuilder(Material.ENDER_PEARL).setDisplayName("Vanish players").build());
			for(Player target : Bukkit.getOnlinePlayers())
				player.showPlayer(target);
		} else {
			vanishedPlayers.add(player.getUniqueId());
			setItemStack(new ItemBuilder(Material.EYE_OF_ENDER).setDisplayName("Unvanish players").build());
			for(Player target : Bukkit.getOnlinePlayers())
				player.hidePlayer(target);
		}
	}

}
