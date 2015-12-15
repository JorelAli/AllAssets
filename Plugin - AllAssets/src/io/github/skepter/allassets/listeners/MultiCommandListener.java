/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and MCSpartans
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and MCSpartans
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.listeners;

import io.github.skepter.allassets.AllAssets;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class MultiCommandListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void playerCommand(final PlayerCommandPreprocessEvent event) {
		if (event.getMessage().startsWith("/") && event.getMessage().contains(AllAssets.instance().getAAConfig().config().getString("multiCommandSeparator"))) {
			final String[] commands = event.getMessage().split(AllAssets.instance().getAAConfig().config().getString("multiCommandSeparator"));
			for (String command : commands) {
				if (!command.startsWith("/"))
					command = "/" + command;
				Bukkit.dispatchCommand(event.getPlayer(), command);
			}
		}
	}
}
