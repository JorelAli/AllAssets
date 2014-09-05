package io.github.Skepter.Utils;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.entity.Player;

/** ReflectionUtils created entirely by Skepter. I use this because it's easier
 * to code with a class which I've created, than one which another person has
 * created. */
public class ReflectionUtils {

	final public Player player;
	final public Object nmsPlayer;
	final public Object getConnection;
	final public int ping;
	
	final String packageName;

	final public Class<?> iChatBaseComponentClass;
	final public Class<?> packetClass;
	final public Class<?> enumClientCommandClass;
	
	final public Object emptyPacketPlayOutChat;
	final public Object emptyChatSerializer;
	final public Object emptyPacketPlayInClientCommand;

	public ReflectionUtils(Player player) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, NoSuchFieldException {
		this.player = player;
		nmsPlayer = player.getClass().getMethod("getHandle").invoke(player);
		getConnection = nmsPlayer.getClass().getField("playerConnection").get(nmsPlayer);
		ping = (int) nmsPlayer.getClass().getField("ping").get(nmsPlayer);
		packageName = nmsPlayer.getClass().getPackage().getName();

		enumClientCommandClass = Class.forName(packageName + ".EnumClientCommand");
		
		iChatBaseComponentClass = Class.forName(packageName + ".IChatBaseComponent");
		packetClass = Class.forName(packageName + ".Packet");
		
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

}
