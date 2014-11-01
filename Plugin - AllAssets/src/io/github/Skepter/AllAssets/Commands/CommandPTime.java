/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Commands;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.PlayerUtils;

import org.bukkit.entity.Player;

public class CommandPTime {

	public CommandPTime(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "ptime", aliases = { "playertime" }, permission = "ptime", description = "Sets your time", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		if (args.getArgs().length == 1)
			try {
				player.setPlayerTime(Long.parseLong(args.getArgs()[0]), false);
				player.sendMessage(AllAssets.title + "Time set to " + args.getArgs()[0]);
				return;
			} catch (final NumberFormatException e) {
				switch (args.getArgs()[0].toLowerCase()) {
				case "day":
					player.setPlayerTime(1000, false);
					player.sendMessage(AllAssets.title + "Time set to day");
					break;
				case "midday":
					player.setPlayerTime(6000, false);
					player.sendMessage(AllAssets.title + "Time set to midday");
					break;
				case "night":
					player.setPlayerTime(14000, false);
					player.sendMessage(AllAssets.title + "Time set to night");
					break;
				case "midnight":
					player.setPlayerTime(18000, false);
					player.sendMessage(AllAssets.title + "Time set to midnight");
					break;
				case "reset":
				case "normal:":
					player.resetPlayerTime();
					break;
				}
				return;
			}
		if (args.getArgs().length == 2) {
			Player target = null;
			try {
				target = PlayerUtils.getOnlinePlayerFromString(args.getArgs()[0]);
			} catch (final Exception e) {
				ErrorUtils.playerNotFound(player, args.getArgs()[0]);
			}
			try {
				target.setPlayerTime(Long.parseLong(args.getArgs()[1]), false);
				player.sendMessage(AllAssets.title + args.getArgs()[0] + "'s time set to " + args.getArgs()[1]);
				target.sendMessage(AllAssets.titleNoColor + player.getName() + " set your time to " + args.getArgs()[1]);
				return;
			} catch (final NumberFormatException e) {
				switch (args.getArgs()[0].toLowerCase()) {
				case "day":
					target.setPlayerTime(1000, false);
					player.sendMessage(AllAssets.title + "Time set to day");
					target.sendMessage(AllAssets.titleNoColor + player.getName() + " set your time to day");
					break;
				case "midday":
					target.setPlayerTime(6000, false);
					player.sendMessage(AllAssets.title + "Time set to midday");
					target.sendMessage(AllAssets.titleNoColor + player.getName() + " set your time to midday");
					break;
				case "night":
					target.setPlayerTime(14000, false);
					player.sendMessage(AllAssets.title + "Time set to night");
					target.sendMessage(AllAssets.titleNoColor + player.getName() + " set your time to night");
					break;
				case "midnight":
					target.setPlayerTime(18000, false);
					player.sendMessage(AllAssets.title + "Time set to midnight");
					target.sendMessage(AllAssets.titleNoColor + player.getName() + " set your time to midnight");
					break;
				case "reset":
				case "normal:":
					target.resetPlayerTime();
					break;
				}
				return;
			}
		}
		return;
	}

}
