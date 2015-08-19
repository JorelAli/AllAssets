/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and MCSpartans
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and MCSpartans
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.commands.administration;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.CommandFramework.Completer;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class CommandTime {

	public CommandTime(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "time", permission = "time", description = "Sets the world time")
	public void onCommand(final CommandArgs args) {
		switch (args.getArgs().length) {
			case 0:
				ErrorUtils.notEnoughArguments(args.getSender());
			case 1:
				int time = 0;
				switch (args.getArgs()[0].toLowerCase()) {
					case "day":
						time = 1000;
						args.getSender().sendMessage(Strings.TITLE + "Time set to day");
						break;
					case "midday":
						time = 6000;
						args.getSender().sendMessage(Strings.TITLE + "Time set to midday");
						break;
					case "night":
						time = 14000;
						args.getSender().sendMessage(Strings.TITLE + "Time set to night");
						break;
					case "midnight":
						time = 18000;
						args.getSender().sendMessage(Strings.TITLE + "Time set to midnight");
						break;
					default:
						if (!TextUtils.isInteger(args.getArgs()[0])) {
							ErrorUtils.cannotSetTime(args.getSender());
							return;
						}
						time = Integer.parseInt(args.getArgs()[0]);
						args.getSender().sendMessage(Strings.TITLE + "Time set to " + args.getArgs()[0]);
						return;
				}
				for (final World world : Bukkit.getWorlds())
					world.setTime(time);
				return;
		}
		return;
	}

	@CommandHandler(name = "day", permission = "time", description = "Sets the time to day", isListed = false)
	public void onCommandDay(final CommandArgs args) {
		for (final World world : Bukkit.getWorlds())
			world.setTime(1000);
		args.getSender().sendMessage(Strings.TITLE + "Time set to day");
	}

	@CommandHandler(name = "midday", permission = "time", description = "Sets the time to midday", isListed = false)
	public void onCommandMidday(final CommandArgs args) {
		for (final World world : Bukkit.getWorlds())
			world.setTime(6000);
		args.getSender().sendMessage(Strings.TITLE + "Time set to midday");
	}

	@CommandHandler(name = "night", permission = "time", description = "Sets the time to night", isListed = false)
	public void onCommandNight(final CommandArgs args) {
		for (final World world : Bukkit.getWorlds())
			world.setTime(14000);
		args.getSender().sendMessage(Strings.TITLE + "Time set to night");
	}

	@CommandHandler(name = "midnight", permission = "time", description = "Sets the time to midnight", isListed = false)
	public void onCommandMidnight(final CommandArgs args) {
		for (final World world : Bukkit.getWorlds())
			world.setTime(18000);
		args.getSender().sendMessage(Strings.TITLE + "Time set to midnight");
	}

	@Completer(name = "time")
	public List<String> timeCompleter(final CommandArgs args) {
		final List<String> list = new ArrayList<String>();
		list.add("day");
		list.add("midday");
		list.add("night");
		list.add("midnight");
		return list;
	}

}
