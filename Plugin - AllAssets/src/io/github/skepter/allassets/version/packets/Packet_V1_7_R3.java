package io.github.skepter.allassets.version.packets;

import io.github.skepter.allassets.AllAssets;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Packet_V1_7_R3 implements Packet {

	Plugin plugin;

	public Packet_V1_7_R3(AllAssets allAssets) {
		plugin = allAssets;
	}
	
	@Override
	public boolean sendActionBarMessage(Player player, String message) {
		//Not supported
		return false;
	}

}
