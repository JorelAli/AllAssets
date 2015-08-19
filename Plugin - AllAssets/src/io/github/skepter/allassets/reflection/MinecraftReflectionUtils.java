/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and MCSpartans
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and MCSpartans
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.reflection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/** ReflectionUtils created entirely by Skepter. I use this because it's easier
 * to code with a class which I've created, than one which another person has
 * created. */
//way to generate NMS GameProfile objects
//add nmsWorld
//play with playerAbilities in EntityHuman
@Deprecated
public class MinecraftReflectionUtils {

	/* Main objects */
	final public Player player;
	final public Object nmsPlayer;
	final public Object getConnection;
	final public Object craftServer;

	/* Fields */
	@Deprecated
	final public int ping;
	final public String locale;
	@Deprecated
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
	final public Class<?> craftItemStackClass;

	/* Object classes (Packets) */
	final public Object emptyChatSerializer;

	final public Object emptyPacketPlayOutChat;
	final public Object emptyPacketPlayInClientCommand;
	final public Object emptyPacketPlayOutNamedEntitySpawn;
	final public Object emptyPacketPlayOutBed;
	final public Object emptyPacketPlayOutAnimation;
	final public Object emptyPacketPlayOutOpenWindow;
	final public Object emptyPacketPlayOutEntityDestroy;
	final public Object emptyPacketPlayOutGameStateChange;
	final public Object emptyPacketPlayOutOpenSignEditor;

	public MinecraftReflectionUtils() throws Exception {

		player = null;
		worldServer = null;
		ping = 0;
		nmsWorldClass = null;
		nmsPlayer = null;
		locale = null;
		getConnection = null;
		entityHumanClass = null;
		craftWorldClass = null;
		abilities = null;

		obcPackageName = Bukkit.getServer().getClass().getPackage().getName();
		craftServer = getOBCClass("CraftServer").cast(Bukkit.getServer());
		dedicatedServer = ReflectionUtils.getPrivateFieldValue(craftServer, "console");
		packageName = dedicatedServer.getClass().getPackage().getName();

		packetClass = getNMSClass("Packet");
		iChatBaseComponentClass = getNMSClass("IChatBaseComponent");
		enumClientCommandClass = getNMSClass("EnumClientCommand");
		minecraftServerClass = dedicatedServer.getClass().getSuperclass();
		craftItemStackClass = getOBCClass("inventory.CraftItemStack");

		emptyChatSerializer = getNMSClass("ChatSerializer").newInstance();
		emptyPacketPlayOutChat = getNMSClass("PacketPlayOutChat").newInstance();
		emptyPacketPlayInClientCommand = getNMSClass("PacketPlayInClientCommand").newInstance();
		emptyPacketPlayOutNamedEntitySpawn = getNMSClass("PacketPlayOutNamedEntitySpawn").newInstance();
		emptyPacketPlayOutBed = getNMSClass("PacketPlayOutBed").newInstance();
		emptyPacketPlayOutAnimation = getNMSClass("PacketPlayOutAnimation").newInstance();
		emptyPacketPlayOutOpenWindow = getNMSClass("PacketPlayOutOpenWindow").newInstance();
		emptyPacketPlayOutEntityDestroy = getNMSClass("PacketPlayOutEntityDestroy").newInstance();
		emptyPacketPlayOutGameStateChange = getNMSClass("PacketPlayOutGameStateChange").newInstance();
		emptyPacketPlayOutOpenSignEditor = getNMSClass("PacketPlayOutGameStateChange").newInstance();
	}

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
		craftItemStackClass = getOBCClass("inventory.CraftItemStack");

		/* Create the class instances */
		emptyChatSerializer = getNMSClass("ChatSerializer").newInstance();

		emptyPacketPlayOutChat = getNMSClass("PacketPlayOutChat").newInstance();
		emptyPacketPlayInClientCommand = getNMSClass("PacketPlayInClientCommand").newInstance();
		emptyPacketPlayOutNamedEntitySpawn = getNMSClass("PacketPlayOutNamedEntitySpawn").newInstance();
		emptyPacketPlayOutBed = getNMSClass("PacketPlayOutBed").newInstance();
		emptyPacketPlayOutAnimation = getNMSClass("PacketPlayOutAnimation").newInstance();
		emptyPacketPlayOutOpenWindow = getNMSClass("PacketPlayOutOpenWindow").newInstance();
		emptyPacketPlayOutEntityDestroy = getNMSClass("PacketPlayOutEntityDestroy").newInstance();
		emptyPacketPlayOutGameStateChange = getNMSClass("PacketPlayOutGameStateChange").newInstance();
		emptyPacketPlayOutOpenSignEditor = getNMSClass("PacketPlayOutGameStateChange").newInstance();

	}

	/** Serialises a String (JSON stuff) */
	public Object chatSerialize(final String string) throws Exception {
		return emptyChatSerializer.getClass().getMethod("a", String.class).invoke(emptyChatSerializer, string);
	}
	
	/** Converts location to blockposition*/
	public Object getBlockPosition(final Location loc) throws Exception {
		return getNMSClass("BlockPosition").getConstructor(double.class, double.class, double.class).newInstance(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
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

	/** Sends an incoming packet (From client to server) - An example would be
	 * the instant revive */
	public void sendIncomingPacket(final Object packet) throws Exception {
		getConnection.getClass().getMethod("a", packet.getClass()).invoke(getConnection, packet);
	}
}
