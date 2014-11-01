/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Commands;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;

import org.bukkit.Bukkit;

public class CommandDisable {

	public CommandDisable(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "disable", permission = "disable", description = "Disables a plugin", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		try {
			Bukkit.getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin(args.getArgs()[0]));
			args.getSender().sendMessage(AllAssets.title + "Plugin disabled successfully");
		} catch (final Exception e) {
			ErrorUtils.pluginNotFound(args.getSender(), args.getArgs()[0]);
		}
		return;
	}

}
