package io.github.Skepter.AllAssets.Reflection;

import io.github.Skepter.AllAssets.API.LogEvent.LogType;
import io.github.Skepter.AllAssets.Commands.Administration.CommandLog;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/** A simplish way to create packets - does not work for EVERY one! */
public class PacketBuilder {

	public enum PacketType {
		PLAY_OUT_CHAT, PLAY_OUT_NAMED_ENTITY_SPAWN, PLAY_IN_CLIENT_COMMAND, PLAY_OUT_BED, PLAY_OUT_ANIMATION;
	}

	public enum PacketDirection {
		CLIENT_TO_SERVER, SERVER_TO_CLIENT;
	}

	private ReflectionUtils utils;
	private Object packet;
	private PacketBuilder builder;
	private PacketDirection direction;

	public PacketBuilder(final Player player, final PacketType type) {
		try {
			this.utils = new ReflectionUtils(player);
			this.packet = null;
			this.builder = this;

			if (type.name().startsWith("PLAY_IN"))
				direction = PacketDirection.CLIENT_TO_SERVER;
			else if (type.name().startsWith("PLAY_OUT"))
				direction = PacketDirection.SERVER_TO_CLIENT;

			switch (type) {
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
			default:
				break;
			}

			packet = packet.getClass().getConstructor().newInstance();
		} catch (final Exception e) {
		}
	}

	public PacketBuilder set(final String name, final Object data) {
		try {
			utils.setPrivateField(packet, name, data);
		} catch (final Exception e) {
			CommandLog.addLog("Editing packet field failure", LogType.ERROR);
		}
		return builder;
	}

	public PacketBuilder setInt(final String name, final int data) {
		try {
			utils.setPrivateField(packet, name, Integer.valueOf(data));
		} catch (final Exception e) {
			CommandLog.addLog("Editing packet field failure", LogType.ERROR);
		}
		return builder;
	}

	public PacketBuilder setLocation(final String name1, final String name2, final String name3, final Location data) {
		setInt(name1, (int) data.getX());
		setInt(name2, (int) data.getY());
		setInt(name3, (int) data.getZ());
		return builder;
	}

	public PacketBuilder setEnum(final String fieldName, final String enumClassName, final String enumName) {
		try {
			set(fieldName, utils.getEnum(utils.getNMSClass(enumClassName), enumName));
		} catch (final Exception e) {
			CommandLog.addLog("Error finding enumeration", LogType.ERROR);
		}
		return builder;
	}

	public void send() {
		try {
			if (direction.equals(PacketDirection.SERVER_TO_CLIENT))
				utils.sendOutgoingPacket(packet);
			else if (direction.equals(PacketDirection.CLIENT_TO_SERVER))
				utils.sendIncomingPacket(packet);
		} catch (final Exception e) {
			CommandLog.addLog("Packet sending failure", LogType.ERROR);
		}
	}
}
