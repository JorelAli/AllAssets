/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
 *
 * AllAssets was created by Skepter and Tundra (http://skepter.github.io/).
 *
 * You are able to:
 * * View AllAsset's source code on github
 * * Experiment with the code to your wish
 * * Download it to use on your server
 *
 * You are NOT able to:
 * * Sell AllAssets - it is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter
 * * Distribute it on any other website. The ONLY places where it can be downloaded from is github and the Bukkit repository
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. The only exception is the BukkitDev staff who I have allowed permission to decompile (to search for malicious code)
 *
 * You can not:
 * * Hold me liable for your actions:
 *     - If there is a bug in the program, you are allowed to notify me.
 *     - If other players abuse AllAssets, you are NOT allowed to hold me liable for that
 *     - If it was setup incorrectly or you used a non-official build, you can NOT hold me liable for that.
 * * Yell at me:
 *     - If something is wrong, please approach the situation calmly.
 * * Modify the code:
 *     - You are NOT allowed to 'steal' the whole code from Github, modify it, compile it yourself and distribute that.
 *
 * If you are to break from these implications, future use of this plugin will be forbidden.
 *******************************************************************************/
package io.github.skepter.allassets.reflection;

import io.github.skepter.allassets.api.LogEvent.LogType;
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
