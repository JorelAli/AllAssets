package io.github.Skepter.Commands;

import io.github.Skepter.AllAssets;
import io.github.Skepter.API.User;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Utils.ErrorUtils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CommandBack {

	public CommandBack(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "back", aliases = { "lastloc" }, permission = "back", description = "Teleports you to your last location", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		final User user = new User(player);
		final Location l = player.getLocation();
		player.teleport(user.getLastLoc());
		user.setLastLoc(l);
		player.sendMessage(AllAssets.instance().title + "Teleported to your last location");
		return;
	}

}
