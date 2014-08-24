package io.github.Skepter.Utils;

import io.github.Skepter.AllAssets;
import io.github.Skepter.Config.ConfigHandler;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ErrorUtils {

	public static void cantEnchant(final Player player) {
		ConfigHandler.instance();
		player.sendMessage(AllAssets.instance().error + ConfigHandler.getMsg("cantEnchant"));
	}

	public static void error(final CommandSender sender, final String msg) {
		sender.sendMessage(AllAssets.instance().error + msg);
	}

	public static void notAnInteger(final Player player) {
		player.sendMessage(AllAssets.instance().error + ConfigHandler.getMsg("notANumber"));
	}

	public static void notEnoughArguments(final Player player) {
		player.sendMessage(AllAssets.instance().error + "Not enough arguments - use /help <command>!");
	}

	public static void playerNotFound(final Player player, final String target) {
		player.sendMessage(AllAssets.instance().error + target + " is offline");
	}

	public static void pluginNotFound(final CommandSender player, final String plugin) {
		player.sendMessage(AllAssets.instance().error + "Couldn't find the plugin " + plugin + "!");
	}

	public static void tooManyArguments(final Player player) {
		player.sendMessage(AllAssets.instance().error + "Too many arguments - use /help <command>!");
	}
	
	public static void tptoggle(final Player player, final String target) {
		player.sendMessage(AllAssets.instance().error + target + " has turned teleporting off");
	}
	
	public static void wrongConstruction(final Player player, final String construction) {
		player.sendMessage(AllAssets.instance().error + "Wrong construction, use the format: " + construction);
	}
}
