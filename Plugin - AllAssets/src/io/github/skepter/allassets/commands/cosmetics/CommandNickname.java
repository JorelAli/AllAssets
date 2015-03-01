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
package io.github.skepter.allassets.commands.cosmetics;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandNickname {

	public CommandNickname(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "nickname", aliases = { "nick" }, permission = "nickname", description = "Gives yourself a nickname")
	public void onCommand(final CommandArgs args) {
		Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			switch (args.getArgs().length) {
			case 0:
				if (!player.getCustomName().equals(player.getName())) {
					player.setCustomName(player.getName());
					player.sendMessage(Strings.TITLE + "Removed your nickname");
				}
				printHelp(player);
				return;
			case 1:
				String username = TextUtils.getMsgStringFromArgs(args.getArgs(), 0, args.getArgs().length);
				player.setCustomName(ChatColor.translateAlternateColorCodes('&', username));
				player.sendMessage(Strings.TITLE + "Set your nickname to" + ChatColor.translateAlternateColorCodes('&', username));
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
