package io.github.skepter.allassets.version.packets;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.version.packets.PacketEnums.AnimationType;
import net.minecraft.server.v1_7_R3.EnumClientCommand;
import net.minecraft.server.v1_7_R3.PacketPlayInClientCommand;
import net.minecraft.server.v1_7_R3.PacketPlayOutAnimation;

import org.bukkit.craftbukkit.v1_7_R3.entity.CraftPlayer;
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
