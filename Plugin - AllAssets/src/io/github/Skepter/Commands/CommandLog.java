package io.github.Skepter.Commands;

import io.github.Skepter.API.LogEvent;
import io.github.Skepter.API.LogEvent.LogType;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Config.ConfigHandler;
import io.github.Skepter.Utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;

public class CommandLog {

	private static List<String> chatLog = new ArrayList<String>();
	private static List<String> errorLog = new ArrayList<String>();
	private static List<String> otherLog = new ArrayList<String>();
	private static List<String> griefLog = new ArrayList<String>();
	private static int max = 0;

	public CommandLog(final CommandFramework framework) {
		framework.registerCommands(this);
		max = ConfigHandler.instance().config().getInt("maxLogAmount");
	}

	@CommandHandler(name = "log", permission = "log", description = "Shows log information", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		args.getSender().sendMessage(TextUtils.title("Error logs"));
		for (final String s : errorLog)
			args.getSender().sendMessage(s);
		args.getSender().sendMessage(TextUtils.title("Chat logs"));
		for (final String s : chatLog)
			args.getSender().sendMessage(s);
		args.getSender().sendMessage(TextUtils.title("Grief logs"));
		for (final String s : griefLog)
			//if player, parse as JSON so they can teleport to the crime scene :)
			//else, just post as normal
			args.getSender().sendMessage(s);
		args.getSender().sendMessage(TextUtils.title("Other logs"));
		for (final String s : otherLog)
			args.getSender().sendMessage(s);
	}

	public static void addLog(final String s, final LogType type) {
		final LogEvent event = new LogEvent(s, type);
		List<String> log = null;
		switch (type) {
		case CHAT:
			log = chatLog;
			break;
		case ERROR:
			log = errorLog;
			break;
		case GRIEF:
			log = griefLog;
			break;
		case OTHER:
		default:
			log = otherLog;
			break;
		}
		Bukkit.getServer().getPluginManager().callEvent(event);
		log.add(s);
		if (log.size() == max)
			log.remove(1);

	}
}
