package io.github.Skepter.Commands;

import io.github.Skepter.AllInOne;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Utils.ErrorUtils;

import org.bukkit.entity.Player;

public class CommandTime {

	public CommandTime(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "time", permission = "AllInOne.time", description = "Sets the world time", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		final Player player = args.getPlayer();
		switch (args.getArgs().length) {
		case 0:
			ErrorUtils.notEnoughArguments(player);
		case 1:
			try {
				player.setPlayerTime(Long.parseLong(args.getArgs()[0]), false);
				player.sendMessage(AllInOne.instance().ttlc + "Time set to " + args.getArgs()[0]);
				return;
			} catch (final NumberFormatException e) {
				switch (args.getArgs()[0].toLowerCase()) {
				case "day":
					player.getWorld().setTime(1000);
					player.sendMessage(AllInOne.instance().ttlc + "Time set to day");
				case "midday":
					player.getWorld().setTime(6000);
					player.sendMessage(AllInOne.instance().ttlc + "Time set to midday");
				case "night":
					player.getWorld().setTime(14000);
					player.sendMessage(AllInOne.instance().ttlc + "Time set to night");
				case "midnight":
					player.getWorld().setTime(18000);
					player.sendMessage(AllInOne.instance().ttlc + "Time set to midnight");
				}
				return;
			}
		}
		return;
	}

	@CommandHandler(name = "day", permission = "AllInOne.time", description = "Sets the time to day", usage = "Use <command>", isListed = false)
	public void onCommandDay(final CommandArgs args) {
		final Player player = args.getPlayer();
		player.getWorld().setThunderDuration(1000);
		player.sendMessage(AllInOne.instance().ttlc + "Time set to day");
	}
	
	@CommandHandler(name = "midday", permission = "AllInOne.time", description = "Sets the time to midday", usage = "Use <command>", isListed = false)
	public void onCommandMidday(final CommandArgs args) {
		final Player player = args.getPlayer();
		player.getWorld().setThunderDuration(6000);
		player.sendMessage(AllInOne.instance().ttlc + "Time set to midday");
	}
	
	@CommandHandler(name = "night", permission = "AllInOne.time", description = "Sets the time to night", usage = "Use <command>", isListed = false)
	public void onCommandNight(final CommandArgs args) {
		final Player player = args.getPlayer();
		player.getWorld().setThunderDuration(14000);
		player.sendMessage(AllInOne.instance().ttlc + "Time set to night");
	}

	
	@CommandHandler(name = "midnight", permission = "AllInOne.time", description = "Sets the time to midnight", usage = "Use <command>", isListed = false)
	public void onCommandMidnight(final CommandArgs args) {
		final Player player = args.getPlayer();
		player.getWorld().setThunderDuration(18000);
		player.sendMessage(AllInOne.instance().ttlc + "Time set to midnight");
	}

	
	
}
