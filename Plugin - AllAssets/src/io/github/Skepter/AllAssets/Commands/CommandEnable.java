package io.github.Skepter.AllAssets.Commands;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;

import org.bukkit.Bukkit;

public class CommandEnable {

	public CommandEnable(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "enable", permission = "enable", description = "Enables a plugin", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		try {
			Bukkit.getPluginManager().enablePlugin(Bukkit.getPluginManager().getPlugin(args.getArgs()[0]));
			args.getSender().sendMessage(AllAssets.title + "Plugin enabled successfully");
		} catch (final Exception e) {
			ErrorUtils.pluginNotFound(args.getSender(), args.getArgs()[0]);
		}
		return;
	}

}
