package io.github.Skepter.Commands;

import io.github.Skepter.AllAssets;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Config.ConfigHandler;
import io.github.Skepter.Utils.TextUtils;

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
				for (final String key : ConfigHandler.instance().config().getKeys())
					args.getSender().sendMessage(AllAssets.houseStyleColor + key + ": " + String.valueOf(ConfigHandler.instance().config().get(key)));
			}
			return;
		case 2:
			if (args.getArgs()[0].equalsIgnoreCase("view"))
				switch (args.getArgs()[1].toLowerCase()) {
				case "config":
					args.getSender().sendMessage(TextUtils.title("Config"));
					for (final String key : ConfigHandler.instance().config().getKeys())
						args.getSender().sendMessage(AllAssets.houseStyleColor + key + ": " + String.valueOf(ConfigHandler.instance().config().get(key)));
				case "features":
					args.getSender().sendMessage(TextUtils.title("Features"));
					for (final String key : ConfigHandler.instance().features().getKeys())
						args.getSender().sendMessage(AllAssets.houseStyleColor + key + ": " + String.valueOf(ConfigHandler.instance().features().get(key)));
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
