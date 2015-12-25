/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter 
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter 
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
package io.github.skepter.allassets.commands.teleportation.teleporting;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.api.users.User;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;

import org.bukkit.entity.Player;

public class CommandTphere {

	public CommandTphere(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "tphere", aliases = { "teleporthere" }, permission = "tphere", description = "Teleport another user to you")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			if (args.getArgs().length == 0) {
				ErrorUtils.notEnoughArguments(player);
				return;
			}
			final Player targetPlayer = PlayerGetter.getTarget(player, args.getArgs()[0]);
			if (targetPlayer != null) {
				final User user = new User(player);
				final User target = new User(targetPlayer);
				if (target.hasTPEnabled()) {
					user.setLastLoc();
					targetPlayer.teleport(player);
					player.sendMessage(Strings.TITLE + "Successfully teleported " + targetPlayer.getName() + " to you ");
					return;
				} else {
					ErrorUtils.tptoggle(player, args.getArgs()[0]);
					return;
				}
			} else {
				ErrorUtils.playerNotFound(player, args.getArgs()[0]);
				return;
			}
		}
	}
}
