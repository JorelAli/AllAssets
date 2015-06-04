package io.github.skepter.allassets.version.packets;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.version.packets.PacketEnums.AnimationType;
import net.minecraft.server.v1_8_R1.BlockPosition;
import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.EntityHuman;
import net.minecraft.server.v1_8_R1.EnumClientCommand;
import net.minecraft.server.v1_8_R1.PacketPlayInClientCommand;
import net.minecraft.server.v1_8_R1.PacketPlayOutAnimation;
import net.minecraft.server.v1_8_R1.PacketPlayOutBed;
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
	
	@Override
	public void putToBed(Player player) {
		BlockPosition blockPosition = new BlockPosition((int) player.getLocation().getX(), (int) player.getLocation().getY(), (int) player.getLocation().getZ());
		PacketPlayOutBed packet = new PacketPlayOutBed((EntityHuman)((CraftPlayer) player).getHandle(), blockPosition);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}

	@Override
	public void sendJSON(Player player, String jsonMessage) {
		PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a(jsonMessage));
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}

}
