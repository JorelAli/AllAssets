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
/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.commands.fun;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommandFakeDeop {

	public CommandFakeDeop(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "fakedeop", aliases = { "fdeop" }, permission = "fakedeop", description = "Fakes a player deop status")
	public void onCommand(final CommandArgs args) {
		final Player target = PlayerGetter.getTarget(args.getSender(), args.getArgs()[0]);
		if (target != null) {
			target.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "[" + args.getSender().getName() + ": De-Opped " + target.getName() + "]");
			target.sendMessage(ChatColor.YELLOW + "You are no longer op!");
		}
		return;
	}

}
