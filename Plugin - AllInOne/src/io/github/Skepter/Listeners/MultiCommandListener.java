package io.github.Skepter.Listeners;

import io.github.Skepter.Config.ConfigHandler;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class MultiCommandListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommand(final PlayerCommandPreprocessEvent event) {
		if (event.getMessage().startsWith("/") && event.getMessage().contains(ConfigHandler.instance().config().getString("multiCommandSeparator"))) {
			final String[] commands = event.getMessage().split(ConfigHandler.instance().config().getString("multiCommandSeparator"));
			for (String command : commands) {
				if (!command.startsWith("/"))
					command = "/" + command;
				Bukkit.dispatchCommand(event.getPlayer(), command);
			}
		}
	}
}
