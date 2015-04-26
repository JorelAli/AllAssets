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
package io.github.skepter.allassets.commands.other;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPrefix {

	public CommandPrefix(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "prefix", permission = "prefix", description = "Sets your prefix")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null)
			switch (args.getArgs().length) {
				case 0:
					printHelp(player);
					return;
				default:
					String prefix = TextUtils.getMsgStringFromArgs(args.getArgs(), 0, args.getArgs().length);
					AllAssets.instance().chat.setPlayerPrefix(player, prefix);
					return;
			}
		return;
	}

	@Help(name = "Prefix")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "Prefix", "/prefix <prefix> - Sets your prefix");
	}
}
