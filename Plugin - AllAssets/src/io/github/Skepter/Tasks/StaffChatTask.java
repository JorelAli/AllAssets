package io.github.Skepter.Tasks;

import io.github.Skepter.Config.ConfigHandler;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class StaffChatTask implements Runnable {

	private final Player player;
	private final List<UUID> players;
	private final String message;

	public StaffChatTask(final Player player, final List<UUID> players, final String message) {
		this.player = player;
		this.players = players;
		this.message = message;
	}

	@Override
	public void run() {
		if (players.contains(player.getUniqueId()))
			for (final UUID uuid : players)
				Bukkit.getPlayer(uuid).sendMessage(ConfigHandler.instance().config().getString("staffChat").replace("{MESSAGE}", message));
	}

}
