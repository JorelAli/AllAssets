package io.github.skepter.allassets.commands.worldmodifier;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.api.builders.ItemBuilder;
import io.github.skepter.allassets.api.utils.PlayerMap;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class WorldModifierHandler {

	private static Set<UUID> wmPlayers = new HashSet<UUID>();
	private static PlayerMap<Location> pos1 = new PlayerMap<Location>(AllAssets.instance());
	private static PlayerMap<Location> pos2 = new PlayerMap<Location>(AllAssets.instance());
	private static ItemStack wmt = new ItemBuilder(Material.DIAMOND_AXE).setDisplayName("World Modifier tool").setLore("Left click to set position 1", "Right click to set position 2").addGlow().build();
	private static PlayerMap<List<Block>> previousAction = new PlayerMap<List<Block>>(AllAssets.instance());

	public static Set<UUID> getWmPlayers() {
		return wmPlayers;
	}

	public static PlayerMap<Location> getPos1() {
		return pos1;
	}

	public static PlayerMap<Location> getPos2() {
		return pos2;
	}

	public static ItemStack getTool() {
		return wmt;
	}

	public WorldModifierHandler() {
		//handle stuff?
	}

	public static PlayerMap<List<Block>> getPreviousAction() {
		return previousAction;
	}
}
