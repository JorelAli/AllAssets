package io.github.skepter.allassets.version.packets;

import io.github.skepter.allassets.version.packets.PacketEnums.AnimationType;

import org.bukkit.entity.Player;

public interface Packet {

	public boolean sendActionBarMessage(Player player, String message);
	
	public void instantRespawn(Player player);
	
	public void doAnimation(Player player, AnimationType type);
	
}
