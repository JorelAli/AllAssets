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
