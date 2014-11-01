/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Tasks;

import io.github.Skepter.AllAssets.API.User;
import io.github.Skepter.AllAssets.Reflection.ReflectionUtils;

import org.bukkit.entity.Player;

public class PingTask implements Runnable {

	private final Player player;
	public PingTask(final Player player) {
		this.player = player;
	}
	
	@Override
	public void run() {
		try {
			User.ping = new ReflectionUtils(player).ping;
		} catch (final Throwable t) {
			t.printStackTrace();
		}
	}

}
