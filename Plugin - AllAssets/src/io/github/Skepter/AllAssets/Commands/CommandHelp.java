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
import io.github.Skepter.AllAssets.Utils.ErrorUtils;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class CommandHelp {

	private static Map<String, String[]> helpData;

	public CommandHelp(final CommandFramework framework) {
		framework.registerCommands(this);
		helpData = new HashMap<String, String[]>();
	}

	public static void register(final String commandName, final String[] helpDocumentation) {
		helpData.put(commandName, helpDocumentation);
	}

	@CommandHandler(name = "help", permission = "help", description = "Shows help for a command", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		for (final Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
			if (args.getArgs()[0].equalsIgnoreCase(plugin.getName())) {
				//parse plugin data
			}
			return;
		}

		for (final String key : helpData.keySet()) {
			if (args.getArgs()[0].equalsIgnoreCase(key))
				for (final String string : helpData.get(key))
					args.getSender().sendMessage(string);
			return;
		}

		ErrorUtils.error(args.getSender(), "Could not find that!");
		return;
	}

}
