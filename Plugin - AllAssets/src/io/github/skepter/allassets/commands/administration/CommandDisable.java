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
package io.github.skepter.allassets.commands.administration;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;

import org.bukkit.Bukkit;

public class CommandDisable {

	public CommandDisable(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "disable", permission = "disable", description = "Disables a plugin")
	public void onCommand(final CommandArgs args) {
		try {
			Bukkit.getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin(args.getArgs()[0]));
			args.getSender().sendMessage(Strings.TITLE + "Plugin disabled successfully");
		} catch (final Exception e) {
			ErrorUtils.pluginNotFound(args.getSender(), args.getArgs()[0]);
		}
		return;
	}

}
