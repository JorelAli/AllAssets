package io.github.Skepter.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/** ReflectionUtils created entirely by Skepter. I use this because it's easier
 * to code with a class which I've created, than one which another person has
 * created. */
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
	final public Object dedicatedServer;

	/** Classes */
	final public Class<?> iChatBaseComponentClass;
	final public Class<?> packetClass;
	final public Class<?> enumClientCommandClass;
	final public Class<?> minecraftServer;

	/** Object classes */
	final public Object emptyPacketPlayOutChat;
	final public Object emptyChatSerializer;
	final public Object emptyPacketPlayInClientCommand;

	public ReflectionUtils(Player player) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, NoSuchFieldException {
		this.player = player;
		nmsPlayer = player.getClass().getMethod("getHandle").invoke(player);
		getConnection = getField(nmsPlayer, "playerConnection");
		craftServer = Bukkit.getServer();
		ping = (int) getField(nmsPlayer, "ping");
		locale = (String) getPrivateField(nmsPlayer, "locale");

		packageName = nmsPlayer.getClass().getPackage().getName();
		dedicatedServer = getPrivateField(craftServer, "console");

		packetClass = Class.forName(packageName + ".Packet");
		iChatBaseComponentClass = Class.forName(packageName + ".IChatBaseComponent");
		enumClientCommandClass = Class.forName(packageName + ".EnumClientCommand");
		minecraftServer = dedicatedServer.getClass().getSuperclass();

		emptyPacketPlayOutChat = Class.forName(packageName + ".PacketPlayOutChat").newInstance();
		emptyChatSerializer = Class.forName(packageName + ".ChatSerializer").newInstance();
		emptyPacketPlayInClientCommand = Class.forName(packageName + ".PacketPlayInClientCommand").newInstance();
	}

	public Object chatSerialize(String string) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return emptyChatSerializer.getClass().getMethod("a", String.class).invoke(emptyChatSerializer, string);
	}

	public Object getEnum(Class<?> enumClass, String enumName) throws NullPointerException {
		for (final Object object : enumClass.getEnumConstants())
			if (object.toString().equals(enumName))
				return object;
		throw new NullPointerException();
	}

	public Object getPrivateField(Object object, String fieldName) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		final Field field = object.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		return field.get(object);
	}
	
	public Object getField(Object object, String fieldName) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		return object.getClass().getDeclaredField(fieldName).get(object);
	}

}
