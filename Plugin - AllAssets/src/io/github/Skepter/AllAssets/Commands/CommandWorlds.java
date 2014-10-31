package io.github.Skepter.AllAssets.Commands;

import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.TextUtils;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

public class CommandWorlds {

	public CommandWorlds(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "worlds", aliases = { "ws" }, permission = "worlds", description = "Shows a list of all of the worlds", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		final CommandSender sender = args.getSender();
		sender.sendMessage(TextUtils.title("Worlds"));
		for (final World w : Bukkit.getWorlds())
			sender.sendMessage(w.getName());
		return;
	}

}
