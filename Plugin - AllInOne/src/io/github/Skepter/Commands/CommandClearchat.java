package io.github.Skepter.Commands;

import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandClearchat {

	public CommandClearchat(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "clearchat", aliases = { "cc" }, permission = "clearchat", description = "Clears the chat", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		final Player player = args.getPlayer();
		if (args.getArgs().length == 0) {
			for (int i = 0; i < 120; i++)
				player.sendMessage("");
			return;
		} else if (args.getArgs()[0].equalsIgnoreCase("all")) {
			for (int i = 0; i < 120; i++)
				Bukkit.broadcastMessage("");
			return;
		}
	}
}
