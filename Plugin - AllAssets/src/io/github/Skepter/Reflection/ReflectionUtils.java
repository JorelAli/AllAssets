package io.github.Skepter.Reflection;

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

	/** Misc & other objects */
	final public String packageName;
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

	/** Object classes */
	final public Object emptyPacketPlayOutChat;
	final public Object emptyChatSerializer;
	final public Object emptyPacketPlayInClientCommand;

	public ReflectionUtils(Player player) throws Exception {
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

		packetClass = Class.forName(packageName + ".Packet");
		iChatBaseComponentClass = Class.forName(packageName + ".IChatBaseComponent");
		enumClientCommandClass = Class.forName(packageName + ".EnumClientCommand");
		gameProfileClass = Class.forName(authLibPackageName + ".GameProfile");
		minecraftServerClass = dedicatedServer.getClass().getSuperclass();
		nmsWorldClass = worldServer.getClass().getSuperclass();

		emptyPacketPlayOutChat = Class.forName(packageName + ".PacketPlayOutChat").newInstance();
		emptyChatSerializer = Class.forName(packageName + ".ChatSerializer").newInstance();
		emptyPacketPlayInClientCommand = Class.forName(packageName + ".PacketPlayInClientCommand").newInstance();
	}

	public Object chatSerialize(String string) throws Exception {
		return emptyChatSerializer.getClass().getMethod("a", String.class).invoke(emptyChatSerializer, string);
	}

	public Object getEnum(Class<?> enumClass, String enumName) throws NullPointerException {
		for (final Object object : enumClass.getEnumConstants())
			if (object.toString().equals(enumName))
				return object;
		throw new NullPointerException();
	}

	public Object getPrivateField(Object object, String fieldName) throws Exception {
		final Field field = object.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		return field.get(object);
	}

	public Object getField(Object object, String fieldName) throws Exception {
		return object.getClass().getDeclaredField(fieldName).get(object);
	}

	public Object getGameProfile() throws Exception {
		return gameProfileClass.getConstructor(UUID.class, String.class).newInstance(player.getUniqueId(), player.getName());
	}

}
