package io.github.Skepter.Tasks;

import io.github.Skepter.API.User;

import org.bukkit.entity.Player;

public class PingTask implements Runnable {

	private final Player player;
	public PingTask(final Player player) {
		this.player = player;
	}
	
	@Override
	public void run() {
		try {
			final Object nmsPlayer = player.getClass().getMethod("getHandle").invoke(player);
			final int ping = (int) nmsPlayer.getClass().getField("ping").get(nmsPlayer);
			User.ping = ping;
		} catch (final Throwable t) {
			t.printStackTrace();
		}
	}

}
