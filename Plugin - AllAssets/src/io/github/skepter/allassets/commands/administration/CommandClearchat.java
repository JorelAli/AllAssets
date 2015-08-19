/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and MCSpartans
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and MCSpartans
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

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;

public class CommandClearchat {

	public CommandClearchat(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "clearchat", aliases = { "cc" }, permission = "clearchat", description = "Clears the chat")
	public void onCommand(final CommandArgs args) {
		if (args.getArgs().length == 0) {
			for (int i = 0; i < 120; i++)
				args.getSender().sendMessage("");
			return;
		} else if ((args.getArgs().length == 1) && args.getArgs()[0].equalsIgnoreCase("all")) {
			for (int i = 0; i < 120; i++)
				AllAssets.instance().getServer().broadcastMessage("");
			return;
		}
	}
}
