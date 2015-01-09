/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
 *
 * AllAssets was created by Skepter (http://skepter.github.io/).
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
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Reflection;

import java.lang.reflect.Field;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/** ReflectionUtils created entirely by Skepter. I use this because it's easier
 * to code with a class which I've created, than one which another person has
 * created. */
//way to generate NMS GameProfile objects
//add nmsWorld
//play with playerAbilities in EntityHuman
public class ReflectionUtils {

	/** Main objects */
	final public Player player;
	final public Object nmsPlayer;
	final public Object getConnection;
	final public Object craftServer;

	/** Fields */
	final public int ping;
	final public String locale;
	final public Object abilities;

	/** Misc & other objects */
	final private String packageName;
	final public String authLibPackageName = "net.minecraft.util.com.mojang.authlib";
	final public Object dedicatedServer;
	final public Object worldServer;

	/** Classes */
	final public Class<?> craftWorldClass;
	final public Class<?> iChatBaseComponentClass;
	final public Class<?> packetClass;
	final public Class<?> enumClientCommandClass;
	final public Class<?> gameProfileClass;
	final public Class<?> minecraftServerClass;
	final public Class<?> nmsWorldClass;
	final public Class<?> entityHumanClass;

	/** Object classes (Packets) */
	final public Object emptyChatSerializer;

	final public Object emptyPacketPlayOutChat;
	final public Object emptyPacketPlayInClientCommand;
	final public Object emptyPacketPlayOutNamedEntitySpawn;
	final public Object emptyPacketPlayOutBed;
	final public Object emptyPacketPlayOutAnimation;

	public ReflectionUtils(final Player player) throws Exception {
		this.player = player;
		nmsPlayer = player.getClass().getMethod("getHandle").invoke(player);
		entityHumanClass = nmsPlayer.getClass().getSuperclass();
		getConnection = getField(nmsPlayer, "playerConnection");
		craftServer = Bukkit.getServer();
		craftWorldClass = player.getWorld().getClass();

		ping = (int) getField(nmsPlayer, "ping");
		locale = (String) getPrivateField(nmsPlayer, "locale");

		dedicatedServer = getPrivateField(craftServer, "console");
		worldServer = craftWorldClass.getMethod("getHandle").invoke(player.getWorld());
		packageName = dedicatedServer.getClass().getPackage().getName();

		packetClass = getNMSClass("Packet");
		iChatBaseComponentClass = getNMSClass("IChatBaseComponent");
		enumClientCommandClass = getNMSClass("EnumClientCommand");
		gameProfileClass = Class.forName(authLibPackageName + ".GameProfile");
		minecraftServerClass = dedicatedServer.getClass().getSuperclass();
		nmsWorldClass = worldServer.getClass().getSuperclass();
		abilities = entityHumanClass.getField("abilities").get(nmsPlayer);

		emptyChatSerializer = getNMSClass("ChatSerializer").newInstance();

		emptyPacketPlayOutChat = getNMSClass("PacketPlayOutChat").newInstance();
		emptyPacketPlayInClientCommand = getNMSClass("PacketPlayInClientCommand").newInstance();
		emptyPacketPlayOutNamedEntitySpawn = getNMSClass("PacketPlayOutNamedEntitySpawn").newInstance();
		emptyPacketPlayOutBed = getNMSClass("PacketPlayOutBed").newInstance();
		emptyPacketPlayOutAnimation = getNMSClass("PacketPlayOutAnimation").newInstance();
	}

	public Object chatSerialize(final String string) throws Exception {
		return emptyChatSerializer.getClass().getMethod("a", String.class).invoke(emptyChatSerializer, string);
	}

	public Object getEnum(final Class<?> enumClass, final String enumName) throws NullPointerException {
		for (final Object object : enumClass.getEnumConstants())
			if (object.toString().equals(enumName))
				return object;
		throw new NullPointerException();
	}

	public Object getPrivateField(final Object object, final String fieldName) throws Exception {
		final Field field = object.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		return field.get(object);
	}

	public Object getField(final Object object, final String fieldName) throws Exception {
		return object.getClass().getDeclaredField(fieldName).get(object);
	}

	public void setPrivateField(final Object object, final String fieldName, final Object data) throws Exception {
		final Field field = object.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(object, data);
	}

	public Object getGameProfile() throws Exception {
		return gameProfileClass.getConstructor(UUID.class, String.class).newInstance(player.getUniqueId(), player.getName());
	}

	public Class<?> getNMSClass(final String className) throws ClassNotFoundException {
		return (Class.forName(packageName + "." + className));
	}

	public void sendOutgoingPacket(final Object packet) throws Exception {
		getConnection.getClass().getMethod("sendPacket", packetClass).invoke(getConnection, packet);
	}

	public void sendIncomingPacket(final Object packet) throws Exception {
		getConnection.getClass().getMethod("a", packet.getClass()).invoke(getConnection, packet);
	}
}
