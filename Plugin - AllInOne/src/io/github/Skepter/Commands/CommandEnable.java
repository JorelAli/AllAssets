package io.github.Skepter.Commands;

import io.github.Skepter.AllInOne;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Utils.ErrorUtils;

import org.bukkit.Bukkit;

public class CommandEnable {

	public CommandEnable(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "enable", permission = "AllInOne.enable", description = "Enables a plugin", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		try {
			Bukkit.getPluginManager().enablePlugin(Bukkit.getPluginManager().getPlugin(args.getArgs()[0]));
			args.getSender().sendMessage(AllInOne.instance().title + "Plugin enabled successfully");
		} catch (final Exception e) {
			ErrorUtils.pluginNotFound(args.getSender(), args.getArgs()[0]);
		}
		return;
	}

}
