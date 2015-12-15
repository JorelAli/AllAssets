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
package io.github.skepter.allassets.commands.debug;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.config.ConfigHandler;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

public class CommandConfig {

	public CommandConfig(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "config", permission = "config", description = "Views and modifies the config")
	public void onCommand(final CommandArgs args) {
		/** Command usage: /config list /config view - shows all keys/values
		 * /config view <file> /config set <config/features> <key> <value>
		 *
		 * - */
		ConfigHandler config = AllAssets.instance().getAAConfig();
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
						args.getSender().sendMessage(Strings.TITLE + "Config, Features");
					case "view":
						args.getSender().sendMessage(TextUtils.title("Config"));
						for (final String key : config.config().getKeys())
							args.getSender().sendMessage(Strings.HOUSE_STYLE_COLOR + key + ": " + String.valueOf(config.config().get(key)));
				}
				return;
			case 2:
				if (args.getArgs()[0].equalsIgnoreCase("view"))
					switch (args.getArgs()[1].toLowerCase()) {
						case "config":
							args.getSender().sendMessage(TextUtils.title("Config"));
							for (final String key : config.config().getKeys())
								args.getSender().sendMessage(Strings.HOUSE_STYLE_COLOR + key + ": " + String.valueOf(config.config().get(key)));
						case "features":
							args.getSender().sendMessage(TextUtils.title("Features"));
							for (final String key : config.features().getKeys())
								args.getSender().sendMessage(Strings.HOUSE_STYLE_COLOR + key + ": " + String.valueOf(config.features().get(key)));
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
