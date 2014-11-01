/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Commands;

import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandHandler;

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
