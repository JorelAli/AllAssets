package io.github.Skepter.Listeners;

import io.github.Skepter.AllAssets;
import io.github.Skepter.API.LogEvent.LogType;
import io.github.Skepter.Commands.CommandConsoleLog;
import io.github.Skepter.Commands.CommandLog;
import io.github.Skepter.Utils.TextUtils;

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

	// Caused by: java.lang.NullPointerException
	// at com.droppages.Skepter.Other.MPlayer.getFile(MPlayer.java:19) ~[?:?]

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
				CommandLog.addLog(AllAssets.houseStyleColor + TextUtils.stringBetween(msg, "(", ")"), LogType.ERROR);
		if (msg.contains("Exception"))
			CommandLog.addLog(msg, LogType.ERROR);
		return null;
	}

	public static void notifyPlayers(final String s) {
		for (final Player p : Bukkit.getOnlinePlayers())
			if (p.hasPermission("AllAssets.notify"))
				p.sendMessage(s);
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
