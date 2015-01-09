/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
 *
 * AllAssets was created by Skepter (http://skepter.github.io/).
 *
 * You are able to:
 * * View AllAsset's source code on github
 * * Experiment with the code to your wish
 * * Download it to use on your server
 *
 * You are NOT able to:
 * * Sell AllAssets - it is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter
 * * Distribute it on any other website. The ONLY places where it can be downloaded from is github and the Bukkit repository
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. The only exception is the BukkitDev staff who I have allowed permission to decompile (to search for malicious code)
 *
 * You can not:
 * * Hold me liable for your actions:
 *     - If there is a bug in the program, you are allowed to notify me.
 *     - If other players abuse AllAssets, you are NOT allowed to hold me liable for that
 *     - If it was setup incorrectly or you used a non-official build, you can NOT hold me liable for that.
 * * Yell at me:
 *     - If something is wrong, please approach the situation calmly.
 * * Modify the code:
 *     - You are NOT allowed to 'steal' the whole code from Github, modify it, compile it yourself and distribute that.
 *
 * If you are to break from these implications, future use of this plugin will be forbidden.
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
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
		for (final Player p : Bukkit.getOnlinePlayers())
			if (p.hasPermission("AllAssets.notifications"))
				new NotificationsBoard(p).updateBoard();

	}
}
