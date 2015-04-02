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
package io.github.skepter.allassets.commands.other;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;

import org.bukkit.WeatherType;
import org.bukkit.entity.Player;

public class CommandPWeather {

	public CommandPWeather(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "pweather", aliases = { "playerweather" }, permission = "pweather", description = "Sets your weather")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null)
			switch (args.getArgs().length) {
				case 0:
					return;
				case 1:
					switch (args.getArgs()[0].toLowerCase()) {
						case "downfall":
						case "rain":
							player.setPlayerWeather(WeatherType.DOWNFALL);
							break;
						case "clear":
						case "sun":
						case "day":
							player.setPlayerWeather(WeatherType.CLEAR);
							break;
						case "reset":
						case "normal":
							player.resetPlayerWeather();
							break;
					}
					return;
				case 2:
					final Player target = PlayerGetter.getTarget(args.getSender(), args.getArgs()[0]);
					if (target != null)
						switch (args.getArgs()[0].toLowerCase()) {
							case "downfall":
							case "rain":
								target.setPlayerWeather(WeatherType.DOWNFALL);
								break;
							case "clear":
							case "sun":
							case "day":
								target.setPlayerWeather(WeatherType.CLEAR);
								break;
							case "reset":
							case "normal":
								target.resetPlayerWeather();
								break;
						}
					return;
			}
		return;
	}
}
