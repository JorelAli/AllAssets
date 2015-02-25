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
package io.github.Skepter.AllAssets.Commands;

import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.Strings;
import io.github.Skepter.AllAssets.Utils.UtilClasses.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.UtilClasses.PlayerUtils;

import org.bukkit.entity.Player;

public class CommandPTime {

	public CommandPTime(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "ptime", aliases = { "playertime" }, permission = "ptime", description = "Sets your time")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		if (args.getArgs().length == 1)
			try {
				player.setPlayerTime(Long.parseLong(args.getArgs()[0]), false);
				player.sendMessage(Strings.TITLE + "Time set to " + args.getArgs()[0]);
				return;
			} catch (final NumberFormatException e) {
				switch (args.getArgs()[0].toLowerCase()) {
				case "day":
					player.setPlayerTime(1000, false);
					player.sendMessage(Strings.TITLE + "Time set to day");
					break;
				case "midday":
					player.setPlayerTime(6000, false);
					player.sendMessage(Strings.TITLE + "Time set to midday");
					break;
				case "night":
					player.setPlayerTime(14000, false);
					player.sendMessage(Strings.TITLE + "Time set to night");
					break;
				case "midnight":
					player.setPlayerTime(18000, false);
					player.sendMessage(Strings.TITLE + "Time set to midnight");
					break;
				case "reset":
				case "normal:":
					player.resetPlayerTime();
					break;
				}
				return;
			}
		if (args.getArgs().length == 2) {
			Player target = null;
			try {
				target = PlayerUtils.getOnlinePlayerFromString(args.getArgs()[0]);
			} catch (final Exception e) {
				ErrorUtils.playerNotFound(player, args.getArgs()[0]);
			}
			try {
				target.setPlayerTime(Long.parseLong(args.getArgs()[1]), false);
				player.sendMessage(Strings.TITLE + args.getArgs()[0] + "'s time set to " + args.getArgs()[1]);
				target.sendMessage(Strings.NO_COLOR_TITLE + player.getName() + " set your time to " + args.getArgs()[1]);
				return;
			} catch (final NumberFormatException e) {
				switch (args.getArgs()[0].toLowerCase()) {
				case "day":
					target.setPlayerTime(1000, false);
					player.sendMessage(Strings.TITLE + "Time set to day");
					target.sendMessage(Strings.NO_COLOR_TITLE + player.getName() + " set your time to day");
					break;
				case "midday":
					target.setPlayerTime(6000, false);
					player.sendMessage(Strings.TITLE + "Time set to midday");
					target.sendMessage(Strings.NO_COLOR_TITLE + player.getName() + " set your time to midday");
					break;
				case "night":
					target.setPlayerTime(14000, false);
					player.sendMessage(Strings.TITLE + "Time set to night");
					target.sendMessage(Strings.NO_COLOR_TITLE + player.getName() + " set your time to night");
					break;
				case "midnight":
					target.setPlayerTime(18000, false);
					player.sendMessage(Strings.TITLE + "Time set to midnight");
					target.sendMessage(Strings.NO_COLOR_TITLE + player.getName() + " set your time to midnight");
					break;
				case "reset":
				case "normal:":
					target.resetPlayerTime();
					break;
				}
				return;
			}
		}
		return;
	}

}
