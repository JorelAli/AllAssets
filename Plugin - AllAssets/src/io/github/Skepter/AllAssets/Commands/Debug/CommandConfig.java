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
package io.github.Skepter.AllAssets.Commands.Debug;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Config.ConfigHandler;
import io.github.Skepter.AllAssets.Utils.TextUtils;

public class CommandConfig {

	public CommandConfig(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "config", permission = "config", description = "Views and modifies the config", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		/** Command usage: /config list /config view - shows all keys/values
		 * /config view <file> /config set <config/features> <key> <value>
		 * 
		 * - */
		switch (args.getArgs().length) {
		case 0:
		case 3:
			//tut
			args.getSender().sendMessage("usage:");
			args.getSender().sendMessage("/config list - view list of configs");
			args.getSender().sendMessage("/config view - show list of config data");
			args.getSender().sendMessage("/config view <configName> - view list of configName's data");
			args.getSender().sendMessage("/config set <configName> <key> <value> - sets data");
			return;
		case 1:
			switch (args.getArgs()[0].toLowerCase()) {
			case "list":
				args.getSender().sendMessage(AllAssets.title + "Config, Features");
			case "view":
				args.getSender().sendMessage(TextUtils.title("Config"));
				for (final String key : ConfigHandler.config().getKeys())
					args.getSender().sendMessage(AllAssets.houseStyleColor + key + ": " + String.valueOf(ConfigHandler.config().get(key)));
			}
			return;
		case 2:
			if (args.getArgs()[0].equalsIgnoreCase("view"))
				switch (args.getArgs()[1].toLowerCase()) {
				case "config":
					args.getSender().sendMessage(TextUtils.title("Config"));
					for (final String key : ConfigHandler.config().getKeys())
						args.getSender().sendMessage(AllAssets.houseStyleColor + key + ": " + String.valueOf(ConfigHandler.config().get(key)));
				case "features":
					args.getSender().sendMessage(TextUtils.title("Features"));
					for (final String key : ConfigHandler.features().getKeys())
						args.getSender().sendMessage(AllAssets.houseStyleColor + key + ": " + String.valueOf(ConfigHandler.features().get(key)));
				}
			return;
		case 4:
			if (args.getArgs()[0].equalsIgnoreCase("set"))
				switch (args.getArgs()[1].toLowerCase()) {
				case "config":
					//what if they want to do a string........... use underscores?
				case "features":

				}
		}
		return;
	}

}
