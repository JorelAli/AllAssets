package io.github.Skepter;

import io.github.Skepter.Commands.CommandConsoleLog;
import io.github.Skepter.Commands.CommandLog;
import io.github.Skepter.Utils.TextUtils;

import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class LogHandler extends Handler {

	ArrayList<String> javaExceptions;
	ArrayList<String> bukkitExceptions;
	
	public LogHandler(AllAssets allAssets) {
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
	}

	@Override
	public void close() throws SecurityException {
	}

	@Override
	public void flush() {
	}

	@Override
	public void publish(LogRecord record) {
		final String msg = record.getMessage().toString();
		//final String msg = msg1.substring(22, msg1.length() - 1);
		for (final UUID u : CommandConsoleLog.players)
			Bukkit.getPlayer(u).sendMessage(ChatColor.BLUE + "[" + ChatColor.AQUA + "Console" + ChatColor.BLUE + "]" + ChatColor.WHITE + " " + ChatColor.GRAY + msg);
		if (msg.contains("at ") && msg.contains(".java:"))
			if (msg.contains("net.minecraft.server.") || msg.contains("org.bukkit.") || msg.contains("sun.reflect.") || msg.contains("java."))
				return;
			else {
				CommandLog.addErrorLog(AllAssets.instance().houseStyleColor + TextUtils.stringBetween(msg, "(", ")"));
				//CommandLog.addErrorLog(msg.replace(TextUtils.stringBetween(msg, "(", ")"), AllAssets.instance().houseStyleColor + TextUtils.stringBetween(msg, "(", ")") + ChatColor.RESET));
			}
		for (final String s : javaExceptions)
			if (msg.contains(s))
				CommandLog.addErrorLog(msg);
		for (final String s : bukkitExceptions)
			if (msg.contains(s))
				CommandLog.addErrorLog(msg);
		if(record.getLevel().equals(Level.SEVERE) || record.getLevel().equals(Level.WARNING) && record.getThrown() != null) {
			StackTraceElement[] source = record.getThrown().getStackTrace();
			CommandLog.addErrorLog("");
			for(StackTraceElement e : source) {
				CommandLog.addErrorLog(e.getClassName() + " - " + e.getMethodName() + ": " + e.getLineNumber());				
			}
		}
		return;
	}

}
