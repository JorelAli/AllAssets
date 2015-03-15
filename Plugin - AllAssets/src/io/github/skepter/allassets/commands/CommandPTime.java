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

package io.github.skepter.allassets.commands;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.PlayerUtils;

import org.bukkit.entity.Player;

public class CommandPTime {

	public CommandPTime(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "ptime", aliases = { "playertime" }, permission = "ptime", description = "Sets your time")
	public void onCommand(final CommandArgs args) {
		Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
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
		}
		return;
	}

}
