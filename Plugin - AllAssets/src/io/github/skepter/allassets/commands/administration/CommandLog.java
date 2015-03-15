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
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.commands.administration;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.api.events.LogEvent;
import io.github.skepter.allassets.api.events.LogEvent.LogType;
import io.github.skepter.allassets.config.ConfigHandler;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

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
		try {
			max = ConfigHandler.config().getInt("maxLogAmount");
		} catch (final Exception e) {
			max = 20;
		}
	}

	@CommandHandler(name = "log", permission = "log", description = "Shows log information")
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
