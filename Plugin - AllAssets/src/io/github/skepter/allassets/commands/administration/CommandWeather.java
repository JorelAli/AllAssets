/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and Tundra
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and Tundra
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
import io.github.skepter.allassets.reflection.ReflectionPlayer;
import io.github.skepter.allassets.reflection.ReflectionPlayer.GameStateEffects;
import io.github.skepter.allassets.utils.Strings;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class CommandWeather {

	public CommandWeather(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "weather", permission = "weather", description = "Sets the world weather")
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
						player.sendMessage(Strings.TITLE + "Weather set to downfall");
						break;
					case "clear":
					case "sun":
						player.getWorld().setStorm(false);
						player.getWorld().setThundering(false);
						new ReflectionPlayer(player).doGameStateChange(GameStateEffects.END_RAINING, 0);
						player.sendMessage(Strings.TITLE + "Weather set to clear");
						break;
					case "thunder":
						player.getWorld().setThundering(true);
						player.sendMessage(Strings.TITLE + "Weather set to thunder");
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
							args.getSender().sendMessage(Strings.TITLE + "Weather set to downfall");
							break;
						}
					case "clear":
					case "sun":
						for (final World world : Bukkit.getWorlds()) {
							world.setStorm(false);
							world.setThundering(false);
							args.getSender().sendMessage(Strings.TITLE + "Weather set to clear");
							break;
						}
					case "thunder":
						for (final World world : Bukkit.getWorlds()) {
							world.setThundering(true);
							args.getSender().sendMessage(Strings.TITLE + "Weather set to thunder");
							break;
						}
				}
			return;
		}

	}

	@CommandHandler(name = "sun", permission = "weather", description = "Sets the weather to sun", isListed = false)
	public void onCommandSun(final CommandArgs args) {
		if (args.isPlayer()) {
			Player player = null;
			try {
				player = args.getPlayer();
			} catch (final Exception e) {
			}
			player.getWorld().setStorm(false);
			player.getWorld().setThundering(false);
			new ReflectionPlayer(player).doGameStateChange(GameStateEffects.END_RAINING, 0);
			player.sendMessage(Strings.TITLE + "Weather set to clear");
		} else
			for (final World world : Bukkit.getWorlds()) {
				world.setStorm(false);
				world.setThundering(false);
				args.getSender().sendMessage(Strings.TITLE + "Weather set to clear");
			}
	}

	@CommandHandler(name = "rain", permission = "weather", description = "Sets the weather to rain", isListed = false)
	public void onCommandRain(final CommandArgs args) {
		if (args.isPlayer()) {
			Player player = null;
			try {
				player = args.getPlayer();
			} catch (final Exception e) {
			}
			player.getWorld().setStorm(true);
			player.getWorld().setThundering(false);
			player.sendMessage(Strings.TITLE + "Weather set to downfall");
		} else
			for (final World world : Bukkit.getWorlds()) {
				world.setStorm(true);
				world.setThundering(false);
				args.getSender().sendMessage(Strings.TITLE + "Weather set to downfall");
			}
	}

	@CommandHandler(name = "thunder", permission = "weather", description = "Sets the weather to thunder", isListed = false)
	public void onCommandThunder(final CommandArgs args) {
		if (args.isPlayer()) {
			Player player = null;
			try {
				player = args.getPlayer();
			} catch (final Exception e) {
			}
			player.getWorld().setThundering(true);
			player.sendMessage(Strings.TITLE + "Weather set to thunder");
		} else
			for (final World world : Bukkit.getWorlds()) {
				world.setThundering(true);
				args.getSender().sendMessage(Strings.TITLE + "Weather set to thunder");
			}
	}

}
