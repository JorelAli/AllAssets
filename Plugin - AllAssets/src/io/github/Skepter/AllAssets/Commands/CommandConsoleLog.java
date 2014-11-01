/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Commands;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

public class CommandConsoleLog {

	public CommandConsoleLog(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	public static List<UUID> players = new ArrayList<UUID>();

	@CommandHandler(name = "consolelog", aliases = { "clog" }, permission = "consolelog", description = "Toggles the log of the console", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		if (players.contains(player.getUniqueId())) {
			players.remove(player.getUniqueId());
			player.sendMessage(AllAssets.title + "You are no longer viewing the console");
		} else {
			players.add(player.getUniqueId());
			player.sendMessage(AllAssets.title + "You are now viewing the console");
		}
	}

}
