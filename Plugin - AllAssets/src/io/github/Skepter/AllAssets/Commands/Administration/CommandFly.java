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
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.PlayerGetter;
import io.github.Skepter.AllAssets.Utils.Strings;
import io.github.Skepter.AllAssets.Utils.UtilClasses.ErrorUtils;

import org.bukkit.entity.Player;

public class CommandFly {

	public CommandFly(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "fly", aliases = { "soar" }, permission = "fly", description = "Allows you to fly")
	public void command(final CommandArgs args) {
		Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			switch (args.getArgs().length) {
			case 0:
				if (player.getAllowFlight()) {
					player.setAllowFlight(false);
					player.setFlying(false);
					player.sendMessage(Strings.TITLE + "Flying disabled");

				} else {
					player.setAllowFlight(true);
					player.sendMessage(Strings.TITLE + "Flying enabled");
				}
				return;
			case 1:
				final Player target = PlayerGetter.getTarget(player, args.getArgs()[0]);
				if (target != null) {
					if (target.getAllowFlight()) {
						target.setAllowFlight(false);
						player.setFlying(false);
						target.sendMessage(Strings.TITLE + "Flying disabled");
					} else {
						target.setAllowFlight(true);
						target.sendMessage(Strings.TITLE + "Flying enabled");
					}
					return;
				} else
					ErrorUtils.playerNotFound(args.getSender(), args.getArgs()[0]);
				return;
			}
			ErrorUtils.tooManyArguments(player);
		}
		return;
	}
}
