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

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMail {

	public CommandMail(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "mail", permission = "mail", description = "Sends mail & shows help")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null)
			printHelp(player);
		return;
	}
	
	@CommandHandler(name = "mail.send", permission = "mail", description = "Sends mail & shows help")
	public void sendMail(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null)
			
		return;
	}

	@Help(name = "Mail")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "Mail", "/mail send <player> <message> - Sends a mail to a certain player", "/mail clear - Clears all of your mails", "/mail read - Displays your mail");
	}
}
