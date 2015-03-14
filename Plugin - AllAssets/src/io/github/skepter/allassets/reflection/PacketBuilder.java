/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 * 
 * AllAssets, created by Skepter and Tundra
 * 
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 * 
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and Tundra
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 * 
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
package io.github.skepter.allassets.reflection;

import io.github.skepter.allassets.api.events.LogEvent.LogType;
import io.github.skepter.allassets.commands.administration.CommandLog;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/** A simplish way to create packets - does not work for EVERY one! */
public class PacketBuilder {

	public enum PacketType {
		PLAY_OUT_CHAT, PLAY_OUT_NAMED_ENTITY_SPAWN, PLAY_IN_CLIENT_COMMAND, PLAY_OUT_BED, PLAY_OUT_ANIMATION, PLAY_OUT_OPEN_WINDOW;
	}

	public enum PacketDirection {
		CLIENT_TO_SERVER, SERVER_TO_CLIENT;
	}

	private MinecraftReflectionUtils utils;
	private Object packet;
	private PacketBuilder builder;
	private PacketDirection direction;

	public PacketBuilder(final Player player, final PacketType type) {
		try {
			this.utils = new MinecraftReflectionUtils(player);
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
			case PLAY_OUT_OPEN_WINDOW:
				packet = utils.emptyPacketPlayOutOpenWindow;
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
			ReflectionUtils.setPrivateField(packet, name, data);
		} catch (final Exception e) {
			CommandLog.addLog("Editing packet field failure", LogType.ERROR);
		}
		return builder;
	}

	public PacketBuilder setInt(final String name, final int data) {
		try {
			ReflectionUtils.setPrivateField(packet, name, Integer.valueOf(data));
		} catch (final Exception e) {
			CommandLog.addLog("Editing packet field failure", LogType.ERROR);
		}
		return builder;
	}

	/**
	 * Sets 3 parameters to a location
	 * @param x - the name of the x field
	 * @param y - the name of the y field
	 * @param z - the name of the z field
	 * @param data - the Location object.
	 */
	public PacketBuilder setLocation(final String x, final String y, final String z, final Location data) {
		setInt(x, (int) data.getX());
		setInt(y, (int) data.getY());
		setInt(z, (int) data.getZ());
		return builder;
	}

	public PacketBuilder setEnum(final String fieldName, final String enumClassName, final String enumName) {
		try {
			set(fieldName, ReflectionUtils.getEnum(utils.getNMSClass(enumClassName), enumName));
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
