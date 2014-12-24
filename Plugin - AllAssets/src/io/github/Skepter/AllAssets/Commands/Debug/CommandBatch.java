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
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Commands.Debug;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.API.PlayerMap;
import io.github.Skepter.AllAssets.Config.ConfigHandler;
import io.github.Skepter.AllAssets.Utils.CustomObject;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.TextUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
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
	private final PlayerMap<UUID, Integer> runnablesMap = new PlayerMap<UUID, Integer>(AllAssets.instance());

	@CommandHandler(name = "batch", permission = "batch", description = "Run a command multiple times", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}

		final Player cachedPlayer = player;
		if (args.getArgs().length == 0) {
			player.sendMessage(TextUtils.title("Batch help"));
			player.sendMessage(AllAssets.houseStyleColor + "/batch 5 /say hi");
			player.sendMessage(AllAssets.houseStyleColor + "/batch 5 1s /say hi");
			player.sendMessage(AllAssets.houseStyleColor + "/batch 20 500ms /say hi");
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
			player.sendMessage(AllAssets.title + "ID " + id + " stopped successfully");
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
			player.sendMessage(AllAssets.title + "Use /batch stop to stop the batch command, or /batch stop " + taskID + " to stop it later");
			return;
		}

		String s = TextUtils.join(TextUtils.getMsgFromArgs(args.getArgs(), 1, args.getArgs().length), " ");
		if (s.startsWith("/"))
			s = s.substring(1);
		for (int i = 1; i <= amount; i++)
			player.performCommand(s);
	}
}
