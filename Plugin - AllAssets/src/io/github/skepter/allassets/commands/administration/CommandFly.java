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
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;

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
