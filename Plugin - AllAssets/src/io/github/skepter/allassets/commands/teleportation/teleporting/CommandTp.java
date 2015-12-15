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
import io.github.skepter.allassets.utils.utilclasses.PlayerUtils;

import org.bukkit.entity.Player;

public class CommandTp {

	public CommandTp(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "tp", aliases = { "teleport" }, permission = "tp", description = "Teleport to another user")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			if (args.getArgs().length == 0) {
				ErrorUtils.notEnoughArguments(player);
				return;
			}
			final Player onlineTarget = PlayerUtils.getOnlinePlayerFromString(args.getArgs()[0]);
//			final OfflinePlayer offlineTarget = PlayerUtils.getOfflinePlayerFromStringExact(args.getArgs()[0]);
			if (/*offlineTarget == null && */onlineTarget == null) {
				ErrorUtils.playerNotFound(args.getSender(), args.getArgs()[0]);
				return;
			}
			final User user = new User(player);
			//final User target = new User(offlineTarget);
			if (user.canTp()) {//TODO sort this out - user can tp? target can tp? which one?!
				user.setLastLoc();
//				if (onlineTarget == null) {
//					player.teleport(target.getLastLoc());
//					player.sendMessage(Strings.TITLE + "Successfully teleported to " + offlineTarget.getName());
//				} else {
					player.teleport(onlineTarget);
					player.sendMessage(Strings.TITLE + "Successfully teleported to " + onlineTarget.getName());
//				}
				return;
			} else {
				ErrorUtils.tptoggle(player, args.getArgs()[0]);
				return;
			}
		}
	}
}
