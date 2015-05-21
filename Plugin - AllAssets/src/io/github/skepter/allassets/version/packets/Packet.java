package io.github.skepter.allassets.version.packets;

import org.bukkit.entity.Player;

public interface Packet {

	public boolean sendActionBarMessage(Player player, String message);
	
}
