/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Commands;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.API.User;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.PlayerUtils;

import org.bukkit.entity.Player;

public class CommandTp {

	public CommandTp(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "tp", aliases = { "teleport" }, permission = "tp", description = "Teleport to another user", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		if (args.getArgs().length == 0) {
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
				player.sendMessage(AllAssets.title + "Successfully teleported to " + t.getName());
				return;
			} else {
				ErrorUtils.tptoggle(player, args.getArgs()[0]);
				return;
			}
		}
	}
}
