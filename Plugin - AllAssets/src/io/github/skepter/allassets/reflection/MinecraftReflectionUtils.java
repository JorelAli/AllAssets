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
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.reflection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/** ReflectionUtils created entirely by Skepter. I use this because it's easier
 * to code with a class which I've created, than one which another person has
 * created. */
//way to generate NMS GameProfile objects
//add nmsWorld
//play with playerAbilities in EntityHuman
public class MinecraftReflectionUtils {

	/* Main objects */
	final public Player player;
	final public Object nmsPlayer;
	final public Object getConnection;
	final public Object craftServer;

	/* Fields */
	final public int ping;
	final public String locale;
	final public Object abilities;

	/* Misc & other objects */
	final private String packageName;
	final private String obcPackageName;
	final public Object dedicatedServer;
	final public Object worldServer;

	/* Classes */
	final public Class<?> craftWorldClass;
	final public Class<?> iChatBaseComponentClass;
	final public Class<?> packetClass;
	final public Class<?> enumClientCommandClass;
	final public Class<?> minecraftServerClass;
	final public Class<?> nmsWorldClass;
	final public Class<?> entityHumanClass;

	/* Object classes (Packets) */
	final public Object emptyChatSerializer;

	final public Object emptyPacketPlayOutChat;
	final public Object emptyPacketPlayInClientCommand;
	final public Object emptyPacketPlayOutNamedEntitySpawn;
	final public Object emptyPacketPlayOutBed;
	final public Object emptyPacketPlayOutAnimation;
	final public Object emptyPacketPlayOutOpenWindow;

	/** Creates a new instance of ReflectionUtils and prepares the classes and
	 * stuff */
	public MinecraftReflectionUtils(final Player player) throws Exception {
		/* Load player classes, player connection, server and world */
		this.player = player;
		nmsPlayer = player.getClass().getMethod("getHandle").invoke(player);
		entityHumanClass = nmsPlayer.getClass().getSuperclass();
		getConnection = ReflectionUtils.getFieldValue(nmsPlayer, "playerConnection");
		
		obcPackageName = Bukkit.getServer().getClass().getPackage().getName();
		
		craftServer = getOBCClass("CraftServer").cast(Bukkit.getServer());
		craftWorldClass = player.getWorld().getClass();

		/* Get the player's ping and locale */
		ping = (int) ReflectionUtils.getFieldValue(nmsPlayer, "ping");
		locale = (String) ReflectionUtils.getPrivateFieldValue(nmsPlayer, "locale");

		/* Get the server, world server and the package name for reflection.
		 * The package name is retrieved dynamically from the server instead
		 * of using the default package name and then parsing the version number.
		 * It seems easier this way. */
		dedicatedServer = ReflectionUtils.getPrivateFieldValue(craftServer, "console");
		worldServer = craftWorldClass.getMethod("getHandle").invoke(player.getWorld());
		packageName = dedicatedServer.getClass().getPackage().getName();

		/* Create the class instances */
		packetClass = getNMSClass("Packet");
		iChatBaseComponentClass = getNMSClass("IChatBaseComponent");
		enumClientCommandClass = getNMSClass("EnumClientCommand");
		minecraftServerClass = dedicatedServer.getClass().getSuperclass();
		nmsWorldClass = worldServer.getClass().getSuperclass();
		abilities = entityHumanClass.getField("abilities").get(nmsPlayer);

		/* Create the class instances */
		emptyChatSerializer = getNMSClass("ChatSerializer").newInstance();

		emptyPacketPlayOutChat = getNMSClass("PacketPlayOutChat").newInstance();
		emptyPacketPlayInClientCommand = getNMSClass("PacketPlayInClientCommand").newInstance();
		emptyPacketPlayOutNamedEntitySpawn = getNMSClass("PacketPlayOutNamedEntitySpawn").newInstance();
		emptyPacketPlayOutBed = getNMSClass("PacketPlayOutBed").newInstance();
		emptyPacketPlayOutAnimation = getNMSClass("PacketPlayOutAnimation").newInstance();
		emptyPacketPlayOutOpenWindow = getNMSClass("PacketPlayOutOpenWindow").newInstance();
	}

	/** Serialises a String (JSON stuff) */
	public Object chatSerialize(final String string) throws Exception {
		return emptyChatSerializer.getClass().getMethod("a", String.class).invoke(emptyChatSerializer, string);
	}

	/** Retrieves a net.minecraft.server class by using the dynamic package from
	 * the dedicated server */
	public Class<?> getNMSClass(final String className) throws ClassNotFoundException {
		return (Class.forName(packageName + "." + className));
	}
	
	/** Retrieves a net.minecraft.server class by using the dynamic package from
	 * the dedicated server */
	public Class<?> getOBCClass(final String className) throws ClassNotFoundException {
		return (Class.forName(obcPackageName + "." + className));
	}

	/** Sends an outgoing packet (From server to client) */
	public void sendOutgoingPacket(final Object packet) throws Exception {
		getConnection.getClass().getMethod("sendPacket", packetClass).invoke(getConnection, packet);
	}

	/** Sends an incoming packet (From client to server) - An example would be the instant revive */
	public void sendIncomingPacket(final Object packet) throws Exception {
		getConnection.getClass().getMethod("a", packet.getClass()).invoke(getConnection, packet);
	}
}
