package io.github.Skepter.Listeners;

import io.github.Skepter.Events.LogEvent;
import io.github.Skepter.Events.LogEvent.LogType;
import io.github.Skepter.Utils.ErrorUtils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CustomLogListener implements Listener {

	@EventHandler
	public void onHyperlinkPost(final LogEvent event) {
		if(event.getLogType().equals(LogType.ERROR))
			for(final Player p : Bukkit.getOnlinePlayers())
				if(p.hasPermission("AllAssets.log"))
					ErrorUtils.error(p, "An error appeared in the log: " + event.getMessage());
	}
}
