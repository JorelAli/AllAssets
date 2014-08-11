package io.github.Skepter.Commands;

import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Users.User;
import io.github.Skepter.Utils.ErrorUtils;
import io.github.Skepter.Utils.PlayerUtils;

import org.bukkit.entity.Player;

public class CommandTp {

	public CommandTp(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "tp", aliases = { "teleport" }, permission = "AllInOne.tp", description = "Teleport to another user", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		final Player player = args.getPlayer();
		if (args.getArgs().length == 0) {
			// help page?
			ErrorUtils.notEnoughArguments(player);
			return;
		}
		final Player t = PlayerUtils.getPlayerFromString(args.getArgs()[0]);
		if (t != null) {
			final User user = new User(player);
			user.setLastLoc();
			final User target = new User(t);
			if (target.canTp()) {
				player.teleport(t);
				return;
			} else {
				ErrorUtils.tptoggle(player, args.getArgs()[0]);
				return;
			}
		}
	}
}
