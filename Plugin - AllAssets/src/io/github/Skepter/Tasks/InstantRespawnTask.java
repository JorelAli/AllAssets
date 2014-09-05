package io.github.Skepter.Tasks;

import io.github.Skepter.Utils.ReflectionUtils;

import org.bukkit.entity.Player;

public class InstantRespawnTask implements Runnable {

	private final Player player;

	public InstantRespawnTask(final Player player) {
		this.player = player;
	}

	@Override
	public void run() {
		try {
			ReflectionUtils utils = new ReflectionUtils(player);
			Object packet = utils.emptyPacketPlayInClientCommand;
			packet = packet.getClass().getConstructor(utils.enumClientCommandClass).newInstance(utils.getEnum(utils.enumClientCommandClass, "PERFORM_RESPAWN"));
			utils.getConnection.getClass().getMethod("a", packet.getClass()).invoke(utils.getConnection, packet);
		} catch (final Throwable t) {
			t.printStackTrace();
		}
	}

}
