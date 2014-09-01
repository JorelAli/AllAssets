package io.github.Skepter.Commands;

import io.github.Skepter.AllAssets;
import io.github.Skepter.API.User;
import io.github.Skepter.Utils.ErrorUtils;
import io.github.Skepter.Utils.PlayerUtils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTphere implements CommandExecutor {

	@SuppressWarnings("unused")
	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
		if (1 == 2) {
			final Player player = (Player) sender;
			if (args.length == 0) {
				ErrorUtils.notEnoughArguments(player);
				return true;
			}
			final Player target = PlayerUtils.getPlayerFromString(args[0]);
			final User user = new User(target);
			user.setLastLoc(); // can tp check
			target.teleport(player);
			player.sendMessage(AllAssets.title + "Teleported " + args[0] + "to you");
			return true;

		}
		return false;
	}

}
