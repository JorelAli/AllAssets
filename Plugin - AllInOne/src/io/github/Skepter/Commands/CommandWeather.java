package io.github.Skepter.Commands;

import io.github.Skepter.AllInOne;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;

import org.bukkit.WeatherType;
import org.bukkit.entity.Player;

public class CommandWeather {

	public CommandWeather(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "weather", permission = "AllInOne.weather", description = "Sets the world weather", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		final Player player = args.getPlayer();
		if(args.getArgs().length == 1)
			switch(args.getArgs()[0].toLowerCase()) {
			case "downfall":
			case "rain":
				player.setPlayerWeather(WeatherType.DOWNFALL);
				player.sendMessage(AllInOne.instance().title + "Weather set to downfall");
			case "clear":
			case "sun":
			case "day":
				player.setPlayerWeather(WeatherType.CLEAR);
				player.sendMessage(AllInOne.instance().title + "Weather set to clear");
			}
		return;
	}

}
