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
package io.github.skepter.allassets.commands.debug;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.api.utils.PlayerMap;
import io.github.skepter.allassets.config.ConfigHandler;
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.utils.CustomObject;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/** Batch command - designed to run a specific command multiple times.
 * Technically a 'for loop emulator' for Minecraft, but designed to be more
 * advanced
 * 
 * @author Skepter */
public class CommandBatch {

	public CommandBatch(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	private final Map<Integer, Integer> runnableMap = new HashMap<Integer, Integer>();
	private final PlayerMap<Integer> runnablesMap = new PlayerMap<Integer>(AllAssets.instance());

	@CommandHandler(name = "batch", permission = "batch", description = "Run a command multiple times")
	public void onCommand(final CommandArgs args) {
		Player player = PlayerGetter.getPlayer(args);
		if (player != null) {

			final Player cachedPlayer = player;
			if (args.getArgs().length == 0) {
				printHelp(args.getSender());
				return;
			}
			if (args.getArgs()[0].equals("stop")) {
				int id = 0;
				try {
					id = Integer.parseInt(args.getArgs()[1]);
				} catch (final Exception e) {
					id = runnablesMap.get(player);
				}
				Bukkit.getScheduler().cancelTask(id);
				player.sendMessage(Strings.TITLE + "ID " + id + " stopped successfully");
				return;
			}

			int amount = 1;
			try {
				amount = Integer.parseInt(args.getArgs()[0]);
			} catch (final NumberFormatException e) {
				ErrorUtils.notAnInteger(player);
				return;
			}
			//limit in config
			if (amount > ConfigHandler.config().getInt("batchLimit")) {
				ErrorUtils.error(player, "Amount cannot be larger than 500!");
				return;
			}

			final int cachedAmount = amount;

			//change format from [##=<VALUE>] to <VALUE>##
			if (args.getArgs()[1].contains("ms") || args.getArgs()[1].contains("s") || args.getArgs()[1].contains("m")) {
				long time = 0L;
				final int value = new CustomObject(args.getArgs()[1]).stripInteger();
				final String type = new CustomObject(args.getArgs()[1]).stripString();
				if (type.equals("ms"))
					time = (value / 1000) * 20;
				else if (type.equals("s"))
					time = value * 20;
				else if (type.equals("m"))
					time = value * 60 * 20;
				String s = TextUtils.join(TextUtils.getMsgFromArgs(args.getArgs(), 2, args.getArgs().length), " ");
				if (s.startsWith("/"))
					s = s.substring(1);

				final String cachedCommand = s;
				final int taskID = new BukkitRunnable() {
					@Override
					public void run() {
						runnableMap.put(getTaskId(), runnableMap.get(getTaskId()) == null ? 1 : runnableMap.get(getTaskId()) + 1);
						if (runnableMap.get(getTaskId()) == cachedAmount) {
							runnableMap.remove(getTaskId());
							Bukkit.getScheduler().cancelTask(getTaskId());
						}

						cachedPlayer.performCommand(cachedCommand);
					}
				}.runTaskTimer(AllAssets.instance(), 0, time).getTaskId();
				runnablesMap.put(player, taskID);
				player.sendMessage(Strings.TITLE + "Use /batch stop to stop the batch command, or /batch stop " + taskID + " to stop it later");
				return;
			}

			String s = TextUtils.join(TextUtils.getMsgFromArgs(args.getArgs(), 1, args.getArgs().length), " ");
			if (s.startsWith("/"))
				s = s.substring(1);
			for (int i = 1; i <= amount; i++)
				player.performCommand(s);
		}
	}

	@Help(name = "Batch")
	private void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "Batch", "/batch - Runs a command multiple times", "/batch <number of times> (time delay) <command> - Runs <command> the <number of times> every (time delay)", "Example: /batch 5 /say hi will say 'hi' 5 times", "Example: /batch 5 10s /broadcast Welcome to my server will broadcast 'Welcome to my server' every 10 seconds, 5 times");
	}
}