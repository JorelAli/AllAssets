/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Commands;

import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.PlayerUtils;

import org.bukkit.WeatherType;
import org.bukkit.entity.Player;

public class CommandPWeather {

	public CommandPWeather(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "pweather", aliases = { "playerweather" }, permission = "pweather", description = "Sets your weather", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		if (args.getArgs().length == 1)
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
		if (args.getArgs().length == 2) {
			Player target = null;
			try {
				target = PlayerUtils.getOnlinePlayerFromString(args.getArgs()[0]);
			} catch (final Exception e) {
				ErrorUtils.playerNotFound(player, args.getArgs()[0]);
			}
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
		}
		return;
	}

}
