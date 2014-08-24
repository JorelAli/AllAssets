package io.github.Skepter.Commands;

import io.github.Skepter.AllAssets;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Utils.ErrorUtils;
import io.github.Skepter.Utils.PlayerUtils;

import org.bukkit.entity.Player;

public class CommandPTime {

	public CommandPTime(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "ptime", aliases = { "playertime" }, permission = "AllInOne.ptime", description = "Sets your time", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		final Player player = args.getPlayer();
		if(args.getArgs().length == 1)
			try {
				player.setPlayerTime(Long.parseLong(args.getArgs()[0]), false);
				player.sendMessage(AllAssets.instance().title + "Time set to " + args.getArgs()[0]);
				return;
			} catch (final NumberFormatException e) {
				switch(args.getArgs()[0].toLowerCase()) {
				case "day":
					player.setPlayerTime(1000, false);
					player.sendMessage(AllAssets.instance().title + "Time set to day");
				case "midday":
					player.setPlayerTime(6000, false);
					player.sendMessage(AllAssets.instance().title + "Time set to midday");
				case "night":
					player.setPlayerTime(14000, false);
					player.sendMessage(AllAssets.instance().title + "Time set to night");
				case "midnight":
					player.setPlayerTime(18000, false);
					player.sendMessage(AllAssets.instance().title + "Time set to midnight");
				case "reset":
				case "normal:":
					player.resetPlayerTime();
				}
				return;
			}
		if(args.getArgs().length == 2) {
			Player target = null;
			try {
				target = PlayerUtils.getOnlinePlayerFromString(args.getArgs()[0]);
			} catch (final Exception e) {
				ErrorUtils.playerNotFound(player, args.getArgs()[0]);
			}
			try {
				target.setPlayerTime(Long.parseLong(args.getArgs()[1]), false);
				player.sendMessage(AllAssets.instance().title + args.getArgs()[0] + "'s time set to " + args.getArgs()[1]);
				target.sendMessage(AllAssets.instance().titleNoColor + player.getName() + " set your time to " + args.getArgs()[1]);
				return;
			} catch (final NumberFormatException e) {
				switch(args.getArgs()[0].toLowerCase()) {
				case "day":
					target.setPlayerTime(1000, false);
					player.sendMessage(AllAssets.instance().title + "Time set to day");
					target.sendMessage(AllAssets.instance().titleNoColor + player.getName() + " set your time to day");
				case "midday":
					target.setPlayerTime(6000, false);
					player.sendMessage(AllAssets.instance().title + "Time set to midday");
					target.sendMessage(AllAssets.instance().titleNoColor + player.getName() + " set your time to midday");
				case "night":
					target.setPlayerTime(14000, false);
					player.sendMessage(AllAssets.instance().title + "Time set to night");
					target.sendMessage(AllAssets.instance().titleNoColor + player.getName() + " set your time to night");
				case "midnight":
					target.setPlayerTime(18000, false);
					player.sendMessage(AllAssets.instance().title + "Time set to midnight");
					target.sendMessage(AllAssets.instance().titleNoColor + player.getName() + " set your time to midnight");
				case "reset":
				case "normal:":
					target.resetPlayerTime();
				}
				return;
			}
		}
		return;
	}

}
