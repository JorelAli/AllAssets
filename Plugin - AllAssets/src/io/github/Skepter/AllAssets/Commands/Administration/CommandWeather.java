/*******************************************************************************
 * Skepter's licence
 * Copyright 2014 
 *
 * AllAssets was created by Skepter (http://skepter.github.io/).
 *
 * You are able to:
 * * View AllAsset's source code on github
 * * Experiment with the code to your wish
 * * Download it to use on your server
 *
 * You are NOT able to:
 * * Sell AllAssets - it is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter
 * * Distribute it on any other website. The ONLY places where it can be downloaded from is github and the Bukkit repository
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. I've just made life easier for you :)
 *
 * You can not:
 * * Hold me liable for your actions:
 *     - If there is a bug in the program, you are allowed to notify me.
 *     - If other players abuse AllAssets, you are NOT allowed to hold me liable for that
 *     - If it was setup incorrectly or you used a non-official build, you can NOT hold me liable for that.
 * * Yell at me:
 *     - If something is wrong, please approach the situation calmly.
 * * Modify the code:
 *     - You are NOT allowed to 'steal' the whole code from Github, modify it, compile it yourself and distribute that.
 *
 * If you are to break from these implications, future use of this plugin will be forbidden.
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Commands.Administration;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class CommandWeather {

	public CommandWeather(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "weather", permission = "weather", description = "Sets the world weather", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		if (args.isPlayer()) {
			Player player = null;
			try {
				player = args.getPlayer();
			} catch (final Exception e) {
			}
			if (args.getArgs().length == 1)
				switch (args.getArgs()[0].toLowerCase()) {
				case "downfall":
				case "rain":
					player.getWorld().setStorm(true);
					player.getWorld().setThundering(false);
					player.sendMessage(AllAssets.title + "Weather set to downfall");
					break;
				case "clear":
				case "sun":
					player.getWorld().setStorm(false);
					player.getWorld().setThundering(false);
					player.sendMessage(AllAssets.title + "Weather set to clear");
					break;
				case "thunder":
					player.getWorld().setThundering(true);
					player.sendMessage(AllAssets.title + "Weather set to thunder");
					break;
				}
			return;
		} else {
			if (args.getArgs().length == 1)
				switch (args.getArgs()[0].toLowerCase()) {
				case "downfall":
				case "rain":
					for (final World world : Bukkit.getWorlds()) {
						world.setStorm(true);
						world.setThundering(false);
						args.getSender().sendMessage(AllAssets.title + "Weather set to downfall");
						break;
					}
				case "clear":
				case "sun":
					for (final World world : Bukkit.getWorlds()) {
						world.setStorm(false);
						world.setThundering(false);
						args.getSender().sendMessage(AllAssets.title + "Weather set to clear");
						break;
					}
				case "thunder":
					for (final World world : Bukkit.getWorlds()) {
						world.setThundering(true);
						args.getSender().sendMessage(AllAssets.title + "Weather set to thunder");
						break;
					}
				}
			return;
		}

	}

	@CommandHandler(name = "sun", permission = "weather", description = "Sets the weather to sun", usage = "Use <command>", isListed = false)
	public void onCommandSun(final CommandArgs args) {
		if (args.isPlayer()) {
			Player player = null;
			try {
				player = args.getPlayer();
			} catch (final Exception e) {
			}
			player.getWorld().setStorm(false);
			player.getWorld().setThundering(false);
			player.sendMessage(AllAssets.title + "Weather set to clear");
		} else
			for (final World world : Bukkit.getWorlds()) {
				world.setStorm(false);
				world.setThundering(false);
				args.getSender().sendMessage(AllAssets.title + "Weather set to clear");
			}
	}

	@CommandHandler(name = "rain", permission = "weather", description = "Sets the weather to rain", usage = "Use <command>", isListed = false)
	public void onCommandRain(final CommandArgs args) {
		if (args.isPlayer()) {
			Player player = null;
			try {
				player = args.getPlayer();
			} catch (final Exception e) {
			}
			player.getWorld().setStorm(true);
			player.getWorld().setThundering(false);
			player.sendMessage(AllAssets.title + "Weather set to downfall");
		} else
			for (final World world : Bukkit.getWorlds()) {
				world.setStorm(true);
				world.setThundering(false);
				args.getSender().sendMessage(AllAssets.title + "Weather set to downfall");
			}
	}

	@CommandHandler(name = "thunder", permission = "weather", description = "Sets the weather to thunder", usage = "Use <command>", isListed = false)
	public void onCommandThunder(final CommandArgs args) {
		if (args.isPlayer()) {
			Player player = null;
			try {
				player = args.getPlayer();
			} catch (final Exception e) {
			}
			player.getWorld().setThundering(true);
			player.sendMessage(AllAssets.title + "Weather set to thunder");
		} else
			for (final World world : Bukkit.getWorlds()) {
				world.setThundering(true);
				args.getSender().sendMessage(AllAssets.title + "Weather set to thunder");
			}
	}

}
