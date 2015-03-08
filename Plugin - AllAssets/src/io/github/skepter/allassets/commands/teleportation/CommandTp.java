/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
 *
 * AllAssets was created by Skepter and Tundra (http://skepter.github.io/).
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
package io.github.skepter.allassets.commands.teleportation;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.api.OfflineUser;
import io.github.skepter.allassets.api.User;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.PlayerUtils;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class CommandTp {

	public CommandTp(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "tp", aliases = { "teleport" }, permission = "tp", description = "Teleport to another user")
	public void onCommand(final CommandArgs args) {
		Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			if (args.getArgs().length == 0) {
				ErrorUtils.notEnoughArguments(player);
				return;
			}
			final Player onlineTarget = PlayerUtils.getOnlinePlayerFromString(args.getArgs()[0]);
			final OfflinePlayer offlineTarget = PlayerUtils.getOfflinePlayerFromString(args.getArgs()[0]);
			if (offlineTarget == null && onlineTarget == null) {
				ErrorUtils.playerNotFound(args.getSender(), args.getArgs()[0]);
				return;
			}
			final User user = new User(player);
			final OfflineUser target = new OfflineUser(offlineTarget);
			if (user.canTp()) {//TODO sort this out - user can tp? target can tp? which one?!
				user.setLastLoc();
				if (onlineTarget == null) {
					player.teleport(target.getLastLoc());
					player.sendMessage(Strings.TITLE + "Successfully teleported to " + offlineTarget.getName());
				} else {
					player.teleport(onlineTarget);
					player.sendMessage(Strings.TITLE + "Successfully teleported to " + onlineTarget.getName());
				}
				return;
			} else {
				ErrorUtils.tptoggle(player, args.getArgs()[0]);
				return;
			}
		}
	}
}
