package io.github.Skepter.Commands;

import io.github.Skepter.AllInOne;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Utils.ErrorUtils;
import io.github.Skepter.Utils.PlayerUtils;

import org.bukkit.entity.Player;

public class CommandFly {

	public CommandFly(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "fly", aliases = { "soar" }, permission = "AllInOne.fly", description = "Allows you to fly", usage = "Use <command>")
	public void command(final CommandArgs args) {
		final Player player = args.getPlayer();
		if (args.getArgs().length == 0) {
			if (player.getAllowFlight()) {
				player.setAllowFlight(false);
				player.setFlying(false);
				player.sendMessage(AllInOne.instance().ttlc + "Flying disabled");
				return;
			} else {
				player.setAllowFlight(true);
				player.sendMessage(AllInOne.instance().ttlc + "Flying enabled");
				return;
			}
		} else if (args.getArgs().length == 1) {
			final Player target = PlayerUtils.getPlayerFromString(args.getArgs()[0]);
			if(target != null) {
				if (target.getAllowFlight()) {
					target.setAllowFlight(false);
					player.setFlying(false);
					target.sendMessage(AllInOne.instance().ttlc + "Flying disabled");
					return;
				} else {
					target.setAllowFlight(true);
					target.sendMessage(AllInOne.instance().ttlc + "Flying enabled");
					return;
				}
			}				
		} else {
			ErrorUtils.tooManyArguments(player);
			return;
		}
	}
}
