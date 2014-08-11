package io.github.Skepter.Commands;

import io.github.Skepter.AllInOne;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

public class CommandConsoleLog {

	public CommandConsoleLog(final CommandFramework framework) {
		framework.registerCommands(this);
	}
	
	public static List<UUID> players = new ArrayList<UUID>();

	@CommandHandler(name = "consolelog", aliases = { "clog" }, permission = "AllInOne.consolelog", description = "Toggles the log of the console", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		final Player player = args.getPlayer();
		if(players.contains(player.getUniqueId())) {
			players.remove(player.getUniqueId());
			player.sendMessage(AllInOne.instance().ttlc + "You are no longer viewing the console");
		} else {
			players.add(player.getUniqueId());
			player.sendMessage(AllInOne.instance().ttlc + "You are now viewing the console");
		}
	}

}
