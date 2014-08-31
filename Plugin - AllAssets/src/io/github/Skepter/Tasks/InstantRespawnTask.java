package io.github.Skepter.Tasks;

import org.bukkit.entity.Player;

public class InstantRespawnTask implements Runnable {

	private final Player player;
	public InstantRespawnTask(final Player player) {
		this.player = player;
	}
	
	@Override
	public void run() {
		try {
			final Object nmsPlayer = player.getClass().getMethod("getHandle").invoke(player);
			Object packet = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".PacketPlayInClientCommand").newInstance();
			final Class<?> enumClass = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".EnumClientCommand");

			for (final Object object : enumClass.getEnumConstants())
				if (object.toString().equals("PERFORM_RESPAWN"))
					packet = packet.getClass().getConstructor(enumClass).newInstance(object);
			final Object connection = nmsPlayer.getClass().getField("playerConnection").get(nmsPlayer);
			connection.getClass().getMethod("a", packet.getClass()).invoke(connection, packet);
		} catch (final Throwable t) {
			t.printStackTrace();
		}
	}

}
