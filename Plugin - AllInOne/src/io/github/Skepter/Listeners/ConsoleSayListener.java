package io.github.Skepter.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

public class ConsoleSayListener implements Listener {

	@EventHandler
	public void onServerCommand(final ServerCommandEvent event) {
		String cmd = event.getCommand();
		if (cmd.startsWith("/")) {
			cmd = cmd.substring(1);
		} else {
			cmd = "say " + cmd;
		}
		event.setCommand(cmd);
	}
}