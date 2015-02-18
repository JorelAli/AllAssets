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
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Commands.Administration;

import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.API.LogEvent;
import io.github.Skepter.AllAssets.API.LogEvent.LogType;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Config.ConfigHandler;
import io.github.Skepter.AllAssets.Utils.TextUtils;

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
