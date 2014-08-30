package io.github.Skepter.Listeners;

import io.github.Skepter.AllAssets;
import io.github.Skepter.Commands.CommandConsoleLog;
import io.github.Skepter.Commands.CommandLog;

import java.util.ArrayList;
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

	ArrayList<String> javaExceptions;
	ArrayList<String> bukkitExceptions;

	public LogListener(final AllAssets allAssets) {
		javaExceptions = new ArrayList<String>();
		bukkitExceptions = new ArrayList<String>();

		javaExceptions.add("ArithmeticException");
		javaExceptions.add("ArrayIndexOutOfBoundsException");
		javaExceptions.add("ArrayStoreException");
		javaExceptions.add("ClassCastException");
		javaExceptions.add("IllegalArgumentException");
		javaExceptions.add("IllegalMonitorStateException");
		javaExceptions.add("IllegalStateException");
		javaExceptions.add("IllegalThreadStateException");
		javaExceptions.add("IndexOutOfBoundsException");
		javaExceptions.add("NegativeArraySizeException");
		javaExceptions.add("NullPointerException");
		javaExceptions.add("NumberFormatException");
		javaExceptions.add("SecurityException");
		javaExceptions.add("StringIndexOutOfBounds");
		javaExceptions.add("UnsupportedOperationException");
		javaExceptions.add("ClassNotFoundException");
		javaExceptions.add("CloneNotSupportedException");
		javaExceptions.add("IllegalAccessException");
		javaExceptions.add("InstantiationException");
		javaExceptions.add("InterruptedException");
		javaExceptions.add("NoSuchFieldException");
		javaExceptions.add("NoSuchMethodException");

		bukkitExceptions.add("AuthorNagException");
		bukkitExceptions.add("ChannelNameTooLongException");
		bukkitExceptions.add("ChannelNotRegisteredException");
		bukkitExceptions.add("CommandException");
		bukkitExceptions.add("EventException");
		bukkitExceptions.add("IllegalPluginAccessException");
		bukkitExceptions.add("InvalidConfigurationException");
		bukkitExceptions.add("InvalidDescriptionException");
		bukkitExceptions.add("InvalidPluginException");
		bukkitExceptions.add("MessageTooLargeException");
		bukkitExceptions.add("MetadataConversionException");
		bukkitExceptions.add("MetadataEvaluationException");
		bukkitExceptions.add("ReservedChannelException");
		bukkitExceptions.add("UnknownDependencyException");
		// org.bukkit.command.CommandException
	}

	// Error occurred while enabling Essentials v2.12.2 (Is it up to date?)

	// Could not load 'plugins/EssentialsChat.jar' in folder 'plugins'
	// org.bukkit.plugin.UnknownDependencyException: Essentials

	// Caused by: java.lang.NullPointerException
	// at com.droppages.Skepter.Other.MPlayer.getFile(MPlayer.java:19) ~[?:?]

	@Override
	public Result filter(final LogEvent event) {
		final String msg1 = event.getMessage().toString();
		final String msg = msg1.substring(22, msg1.length() - 1);
		for (final UUID u : CommandConsoleLog.players)
			Bukkit.getPlayer(u).sendMessage(ChatColor.BLUE + "[" + ChatColor.AQUA + "Console" + ChatColor.BLUE + "]" + ChatColor.WHITE + " " + ChatColor.GRAY + msg);
		//		if (msg.contains("Error occurred while enabling") && msg.contains("(Is it up to date?)")) {
		//			final String result = TextUtils.stringBetween(msg, "Error occurred while enabling", "(Is it up to date?)");
		//			// String result =
		//			// msg.substring(msg.indexOf("Error occurred while enabling") +
		//			// 30, msg.indexOf("(Is it up to date?)") - 1);
		//			CommandLog.addErrorLog("Enabling error: " + result);
		//		}
		//		if (msg.contains("Could not load") && msg.contains("in folder")) {
		//			CommandLog.addErrorLog("Loading error: " + msg);
		//		}
		if (msg.contains("at ") && msg.contains(".java:"))
			if (msg.contains("net.minecraft.server.") || msg.contains("org.bukkit.") || msg.contains("sun.reflect.") || msg.contains("java."))
				return null;
//			else
//				CommandLog.addErrorLog(msg.replace(TextUtils.stringBetween(msg, "(", ")"), AllAssets.instance().houseStyleColor + TextUtils.stringBetween(msg, "(", ")") + ChatColor.RESET));
		for (final String s : javaExceptions)
			if (msg.contains(s))
				CommandLog.addErrorLog(msg);
		for (final String s : bukkitExceptions)
			if (msg.contains(s))
				CommandLog.addErrorLog(msg);
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
