/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter 
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter 
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

import io.github.skepter.allassets.api.events.LogEvent;
import io.github.skepter.allassets.misc.NotificationsBoard;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;

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
						ErrorUtils.logError(p, event.getMessage());
				break;
			case GRIEF:
				break;
			case OTHER:
				break;
			default:
				break;
		}
		for (final Player p : Bukkit.getOnlinePlayers())
			if (p.hasPermission("AllAssets.notifications"))
				new NotificationsBoard(p).updateBoard();

	}
}
