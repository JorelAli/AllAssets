package io.github.Skepter.Commands;

import io.github.Skepter.AllAssets;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;

import org.bukkit.WeatherType;
import org.bukkit.entity.Player;

public class CommandWeather {

	public CommandWeather(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "weather", permission = "weather", description = "Sets the world weather", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		final Player player = args.getPlayer();
		if (args.getArgs().length == 1)
			switch (args.getArgs()[0].toLowerCase()) {
			case "downfall":
			case "rain":
				player.setPlayerWeather(WeatherType.DOWNFALL);
				player.sendMessage(AllAssets.instance().title + "Weather set to downfall");
			case "clear":
			case "sun":
				player.setPlayerWeather(WeatherType.CLEAR);
				player.sendMessage(AllAssets.instance().title + "Weather set to clear");
			}
		return;
	}
	
	@CommandHandler(name = "sun", permission = "weather", description = "Sets the weather to sun", usage = "Use <command>", isListed = false)
	public void onCommandSun(final CommandArgs args) {
		args.getPlayer().setPlayerWeather(WeatherType.CLEAR);
		args.getPlayer().sendMessage(AllAssets.instance().title + "Weather set to clear");
	}
	
	@CommandHandler(name = "rain", permission = "weather", description = "Sets the weather to rain", usage = "Use <command>", isListed = false)
	public void onCommandRain(final CommandArgs args) {
		args.getPlayer().setPlayerWeather(WeatherType.DOWNFALL);
		args.getPlayer().sendMessage(AllAssets.instance().title + "Weather set to downfall");
	}

}
