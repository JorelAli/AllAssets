package io.github.Skepter.Tasks;

import org.bukkit.entity.Player;

public class JSONMessageTask implements Runnable {

	private final Player player;
	private final String string;

	public JSONMessageTask(final Player player, String string) {
		this.player = player;
		this.string = string;
	}

	@Override
	public void run() {
		try {
			final Object nmsPlayer = player.getClass().getMethod("getHandle").invoke(player);
			final Object connection = nmsPlayer.getClass().getField("playerConnection").get(nmsPlayer);
			final Object chatSerializer = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".ChatSerializer").newInstance();
			Object serializedString = chatSerializer.getClass().getMethod("a", String.class).invoke(chatSerializer, string);
			final Class<?> iChatBaseComponentClass = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".IChatBaseComponent");
			Object packet = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".PacketPlayOutChat").newInstance();
			packet = packet.getClass().getConstructor(iChatBaseComponentClass).newInstance(serializedString);
			connection.getClass().getMethod("sendPacket", packet.getClass()).invoke(connection, packet);
		} catch (final Throwable t) {
			t.printStackTrace();
		}
	}
}
