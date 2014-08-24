package io.github.Skepter.Commands;

import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Config.ConfigHandler;
import io.github.Skepter.Events.LogEvent;
import io.github.Skepter.Events.LogEvent.LogType;
import io.github.Skepter.Utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandLog {

	private static List<String> chatLog = new ArrayList<String>();
	private static List<String> errorLog = new ArrayList<String>();
	private static List<String> otherLog = new ArrayList<String>();
	private static int max = 0;

	public CommandLog(final CommandFramework framework) {
		framework.registerCommands(this);
		max = ConfigHandler.instance().config().getInt("maxLogAmount");
	}

	@CommandHandler(name = "log", permission = "log", description = "Shows log information", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		final Player player = args.getPlayer();

		player.sendMessage(TextUtils.title("Error logs"));
		for (final String s : errorLog)
			player.sendMessage(s);
		player.sendMessage(TextUtils.title("Chat logs"));
		for (final String s : chatLog)
			player.sendMessage(s);
		player.sendMessage(TextUtils.title("Other logs"));
		for (final String s : otherLog)
			player.sendMessage(s);
	}

	public static void addChatLog(final String s) {
		final LogEvent event = new LogEvent(s, LogType.CHAT);
		Bukkit.getServer().getPluginManager().callEvent(event);
		chatLog.add(s);
		if (chatLog.size() == max)
			chatLog.remove(1);
	}

	public static void addErrorLog(final String s) {
		final LogEvent event = new LogEvent(s, LogType.ERROR);
		Bukkit.getServer().getPluginManager().callEvent(event);
		errorLog.add(s);
		if (errorLog.size() == max)
			errorLog.remove(1);
	}

	public static void addOtherLog(final String s) {
		final LogEvent event = new LogEvent(s, LogType.OTHER);
		Bukkit.getServer().getPluginManager().callEvent(event);
		otherLog.add(s);
		if (otherLog.size() == max)
			otherLog.remove(1);
	}

}
