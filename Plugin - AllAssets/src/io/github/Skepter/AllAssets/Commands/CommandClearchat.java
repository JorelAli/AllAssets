/*******************************************************************************
 * Skepter's licence
 * Copyright 2014 
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
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. I've just made life easier for you :)
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
package io.github.Skepter.AllAssets.Commands;

import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;

import org.bukkit.Bukkit;

public class CommandClearchat {

	public CommandClearchat(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "clearchat", aliases = { "cc" }, permission = "clearchat", description = "Clears the chat", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		if (args.getArgs().length == 0) {
			for (int i = 0; i < 120; i++)
				args.getSender().sendMessage("");
			return;
		} else if ((args.getArgs().length == 1) && args.getArgs()[0].equalsIgnoreCase("all")) {
			for (int i = 0; i < 120; i++)
				Bukkit.broadcastMessage("");
			return;
		}
	}
}
