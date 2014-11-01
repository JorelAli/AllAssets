/*******************************************************************************
 * Skepter's licence
 * Copyright 2014 
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
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. I've just made life easier for you :)
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

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.API.LogEvent.LogType;
import io.github.Skepter.AllAssets.Commands.CommandConsoleLog;
import io.github.Skepter.AllAssets.Commands.CommandLog;

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

	public LogListener(final AllAssets allAssets) {
	}

	@Override
	public Result filter(final LogEvent event) {
		final String msg1 = event.getMessage().toString();
		final String msg = msg1.substring(22, msg1.length() - 1);
		for (final UUID u : CommandConsoleLog.players)
			Bukkit.getPlayer(u).sendMessage(ChatColor.BLUE + "[" + ChatColor.AQUA + "Console" + ChatColor.BLUE + "]" + ChatColor.WHITE + " " + ChatColor.GRAY + msg);
		if (msg.contains("at ") && msg.contains(".java:"))
			if (msg.contains("net.minecraft.server.") || msg.contains("org.bukkit.") || msg.contains("sun.reflect.") || msg.contains("java."))
				return null;
			else 
				CommandLog.addLog(AllAssets.houseStyleColor + stringBetween(msg, "(", ")"), LogType.ERROR);
		if (msg.contains("Exception"))
			CommandLog.addLog(msg, LogType.ERROR);
		return null;
	}

	public static void notifyPlayers(final String s) {
		for (final Player p : Bukkit.getOnlinePlayers())
			if (p.hasPermission("AllAssets.notify"))
				p.sendMessage(s);
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
