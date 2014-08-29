package io.github.Skepter.Tasks;

import org.bukkit.entity.Player;

public class InstantRespawnTask implements Runnable {

	private Player player;
	public InstantRespawnTask(final Player player) {
		this.player = player;
	}
	
	@Override
	public void run() {
		try {
			final Object nmsPlayer = player.getClass().getMethod("getHandle").invoke(player);
			Object packet = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".PacketPlayInClientCommand").newInstance();
			final Class<?> enumClass = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".EnumClientCommand");

			for (final Object ob : enumClass.getEnumConstants())
				if (ob.toString().equals("PERFORM_RESPAWN"))
					packet = packet.getClass().getConstructor(enumClass).newInstance(ob);
			final Object con = nmsPlayer.getClass().getField("playerConnection").get(nmsPlayer);
			con.getClass().getMethod("a", packet.getClass()).invoke(con, packet);
		} catch (final Throwable t) {
			t.printStackTrace();
		}
	}

}
