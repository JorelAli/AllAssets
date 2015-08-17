/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and Tundra
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and Tundra
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
import io.github.skepter.allassets.api.events.LogEvent.LogType;
import io.github.skepter.allassets.commands.administration.CommandConsoleLog;
import io.github.skepter.allassets.commands.administration.CommandLog;
import io.github.skepter.allassets.utils.Strings;

import java.util.UUID;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.message.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class LogListener implements Filter {

	@Override
	public Result filter(final LogEvent event) {
		Bukkit.getScheduler().runTaskAsynchronously(AllAssets.instance(), new Runnable() {
			@Override
			public void run() {
				final String msg1 = event.getMessage().toString();
				final String msg = msg1.substring(22, msg1.length() - 1);
				for (final UUID u : CommandConsoleLog.players)
					sendConsole(u, msg);
				if (msg.contains("at ") && msg.contains(".java:")) {
					if (msg.contains("net.minecraft.server.") || msg.contains("org.bukkit.") || msg.contains("sun.reflect.") || msg.contains("java."))
						return;
					else
						CommandLog.addLog(Strings.HOUSE_STYLE_COLOR + stringBetween(msg, "(", ")"), LogType.ERROR);
					return;
				} else if (msg.contains("Exception")) {
					CommandLog.addLog(msg, LogType.ERROR);
					return;
				}
			}
		});
		return null;
	}

	public static void notifyPlayers(final String s) {
		for (final Player p : Bukkit.getOnlinePlayers())
			if (p.hasPermission("AllAssets.notify"))
				p.sendMessage(s);
	}

	private void sendConsole(final UUID u, String message) {
		Bukkit.getPlayer(u).sendMessage(Strings.customTitle("Console") + ChatColor.WHITE + " " + ChatColor.GRAY + message);
	}

	/* Sometimes it's a bit fussy and so moving it to use this method should fix that problem. */
	private String stringBetween(final String overallString, final String firstString, final String secondString) {
		final String s = overallString.substring(overallString.indexOf(firstString) + firstString.length(), overallString.indexOf(secondString));
		return s;
	}

	@Override
	public Result filter(final Logger arg0, final org.apache.logging.log4j.Level arg1, final Marker arg2, final String arg3, final Object... arg4) {
		return null;
	}

	@Override
	public Result filter(final Logger arg0, final org.apache.logging.log4j.Level arg1, final Marker arg2, final Object arg3, final Throwable arg4) {
		return null;
	}

	@Override
	public Result filter(final Logger arg0, final org.apache.logging.log4j.Level arg1, final Marker arg2, final Message arg3, final Throwable arg4) {
		return null;
	}

	@Override
	public Result getOnMatch() {
		return null;
	}

	@Override
	public Result getOnMismatch() {
		return null;
	}
}
