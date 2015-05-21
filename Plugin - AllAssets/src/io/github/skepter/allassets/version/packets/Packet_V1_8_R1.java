package io.github.skepter.allassets.version.packets;

import io.github.skepter.allassets.AllAssets;
import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.PacketPlayOutChat;

import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Packet_V1_8_R1 implements Packet {

	Plugin plugin;
	
	public Packet_V1_8_R1(AllAssets allAssets) {
		plugin = allAssets;
	}

	@Override
	public boolean sendActionBarMessage(Player player, String message) {
		PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a(message), (byte) 2);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
		return true;
	}

}
