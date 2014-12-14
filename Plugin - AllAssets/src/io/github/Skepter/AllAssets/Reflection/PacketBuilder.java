package io.github.Skepter.AllAssets.Reflection;

import io.github.Skepter.AllAssets.API.LogEvent.LogType;
import io.github.Skepter.AllAssets.Commands.CommandLog;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/** A simplish way to create packets - does not work for EVERY one! */
public class PacketBuilder {

	public enum PacketType {
		PLAY_OUT_CHAT, PLAY_OUT_NAMED_ENTITY_SPAWN, PLAY_IN_CLIENT_COMMAND, PLAY_OUT_BED, PLAY_OUT_ANIMATION;
	}
	
	private ReflectionUtils utils;
	private Object packet;
	private PacketBuilder builder;
	
	public PacketBuilder(final Player player, PacketType type) {
		try {
			utils = new ReflectionUtils(player);
			packet = null;
			builder = this;
			switch(type) {
			case PLAY_IN_CLIENT_COMMAND:
				packet = utils.emptyPacketPlayInClientCommand;
				break;
			case PLAY_OUT_ANIMATION:
				packet = utils.emptyPacketPlayOutAnimation;
				break;
			case PLAY_OUT_BED:
				packet = utils.emptyPacketPlayOutBed;
				break;
			case PLAY_OUT_CHAT:
				packet = utils.emptyPacketPlayOutChat;
				break;
			case PLAY_OUT_NAMED_ENTITY_SPAWN:
				packet = utils.emptyPacketPlayOutNamedEntitySpawn;
				break;
			}
			packet = packet.getClass().getConstructor().newInstance();
		} catch (Exception e) {
		}
	}
	
	public PacketBuilder set(String name, Object data) {
		try {
			utils.setPrivateField(packet, name, data);
		} catch (Exception e) {
			CommandLog.addLog("Editing packet field failure", LogType.ERROR);
		}
		return builder;
	}
	
	public PacketBuilder setInt(String name, int data) {
		try {
			utils.setPrivateField(packet, name, Integer.valueOf(data));
		} catch (Exception e) {
			CommandLog.addLog("Editing packet field failure", LogType.ERROR);
		}
		return builder;
	}
	
	public PacketBuilder setLocation(String name1, String name2, String name3, Location data) {
		setInt(name1, (int) data.getX());
		setInt(name2, (int) data.getY());
		setInt(name3, (int) data.getZ());
		return builder;
	}

	public void send() {
		try {
			utils.sendPacket(packet);
		} catch (Exception e) {
			CommandLog.addLog("Packet sending failure", LogType.ERROR);
		}
	}
}
