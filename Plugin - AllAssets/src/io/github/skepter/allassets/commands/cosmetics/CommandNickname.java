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
package io.github.skepter.allassets.commands.cosmetics;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.PlayerUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandNickname {

	public CommandNickname(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "nickname", aliases = { "nick" }, permission = "nickname", description = "Gives yourself a nickname")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			switch (args.getArgs().length) {
				case 0:
					if(player.getCustomName() == null) {
						player.setCustomName(player.getName());
						player.sendMessage(Strings.TITLE + "Removed your nickname");
						return;
					}
					if (!player.getCustomName().equals(player.getName())) {
						player.setCustomName(player.getName());
						player.sendMessage(Strings.TITLE + "Removed your nickname");
					} else
						printHelp(player);
					return;
			}
			if (args.getArgs().length > 0)
				if (PlayerUtils.getOnlinePlayerFromString(args.getArgs()[0]) != null) {
					final Player target = PlayerGetter.getTarget(player, args.getArgs()[0]);
					final String username = TextUtils.getMsgStringFromArgs(args.getArgs(), 0, args.getArgs().length).trim();
					target.setCustomName(ChatColor.translateAlternateColorCodes('&', username));
					target.sendMessage(Strings.TITLE + "Set your nickname to " + ChatColor.translateAlternateColorCodes('&', username));
					target.setCustomNameVisible(true);
				} else {
					final String username = TextUtils.getMsgStringFromArgs(args.getArgs(), 0, args.getArgs().length).trim();
					player.setCustomName(ChatColor.translateAlternateColorCodes('&', username));
					player.sendMessage(Strings.TITLE + "Set your nickname to " + ChatColor.translateAlternateColorCodes('&', username));
					player.setCustomNameVisible(true);
				}
		}
		return;
	}

	@Help(name = "Nickname")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "Nickname", "/nickname <name> - Sets your nickname to that name");
	}
}
