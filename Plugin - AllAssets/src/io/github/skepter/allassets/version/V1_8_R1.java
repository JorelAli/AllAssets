package io.github.skepter.allassets.version;

import io.github.skepter.allassets.AllAssets;

import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class V1_8_R1 implements NMS{

	Plugin plugin;
	
	public V1_8_R1(AllAssets allAssets) {
		this.plugin = allAssets;
	}

	@Override
	public int getPing(Player player) {
		return ((CraftPlayer) player).getHandle().ping;
	}
}
