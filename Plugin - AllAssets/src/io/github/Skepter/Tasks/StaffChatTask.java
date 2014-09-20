package io.github.Skepter.Tasks;

import io.github.Skepter.Config.ConfigHandler;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class StaffChatTask implements Runnable {

	private final Player player;
	private final List<UUID> players;
	private final String message;
	private final AsyncPlayerChatEvent event;

	public StaffChatTask(AsyncPlayerChatEvent event, final Player player, final List<UUID> players, final String message) {
		this.event = event;
		this.player = player;
		this.players = players;
		this.message = message;
	}

	@Override
	public void run() {
		if (players.contains(player.getUniqueId()))
			for (final UUID uuid : players) {
				event.setCancelled(true);
				Bukkit.getPlayer(uuid).sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.instance().config().getSpecialString("staffChat").replace("{MESSAGE}", message)));
			}
	}

}
