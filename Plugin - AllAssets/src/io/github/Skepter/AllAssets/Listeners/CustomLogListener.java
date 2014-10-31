package io.github.Skepter.AllAssets.Listeners;

import io.github.Skepter.AllAssets.API.LogEvent;
import io.github.Skepter.AllAssets.Misc.NotificationsBoard;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CustomLogListener implements Listener {

	@EventHandler
	public void logError(final LogEvent event) {
		switch (event.getLogType()) {
		case CHAT:
			NotificationsBoard.addSpamLog();
			break;
		case ERROR:
			NotificationsBoard.addErrorLog();
			for (final Player p : Bukkit.getOnlinePlayers())
				if (p.hasPermission("AllAssets.log"))
					ErrorUtils.error(p, "An error appeared in the log: " + event.getMessage());
			break;
		case GRIEF:
			break;
		case OTHER:
			break;
		default:
			break;
		}
		for (final Player p : Bukkit.getOnlinePlayers()) {
			if (p.hasPermission("AllAssets.notifications")) {
				new NotificationsBoard(p).updateBoard();
			}
		}

	}
}
