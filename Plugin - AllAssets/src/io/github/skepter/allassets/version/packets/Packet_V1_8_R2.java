package io.github.skepter.allassets.version.packets;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.version.packets.PacketEnums.AnimationType;
import net.minecraft.server.v1_8_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R2.PacketPlayInClientCommand;
import net.minecraft.server.v1_8_R2.PacketPlayInClientCommand.EnumClientCommand;
import net.minecraft.server.v1_8_R2.PacketPlayOutAnimation;
import net.minecraft.server.v1_8_R2.PacketPlayOutChat;

import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Packet_V1_8_R2 implements Packet {
	
	Plugin plugin;

	public Packet_V1_8_R2(AllAssets allAssets) {
		plugin = allAssets;
	}
	
	@Override
	public boolean sendActionBarMessage(Player player, String message) {
		PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a(message), (byte) 2);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
		return true;
	}

	@Override
	public void instantRespawn(Player player) {
		PacketPlayInClientCommand packet = new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN);
		((CraftPlayer) player).getHandle().playerConnection.a(packet);	
	}
	
	@Override
	public void doAnimation(Player player, AnimationType type) {
		PacketPlayOutAnimation packet = new PacketPlayOutAnimation(((CraftPlayer) player).getHandle(), type.getId());
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}
}
