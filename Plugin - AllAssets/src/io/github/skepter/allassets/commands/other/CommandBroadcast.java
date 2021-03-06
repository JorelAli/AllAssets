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
package io.github.skepter.allassets.commands.other;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CommandBroadcast {

	public CommandBroadcast(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "broadcast", aliases = { "bc" }, permission = "broadcast", description = "Broadcasts a message")
	public void onCommand(final CommandArgs args) {
		if (args.getArgs().length == 0) {
			printHelp(args.getSender());
			return;
		} else
			Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', AllAssets.instance().getAAConfig().config().getString("broadcastPrefix").trim() + " " + TextUtils.getMsgStringFromArgs(args.getArgs(), 0, args.getArgs().length)));
		return;
	}

	@Help(name = "Broadcast")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "Broadcast", "/broadcast <message> - broadcasts a message to the entire server");
	}
}
