package io.github.Skepter.Utils;

import io.github.Skepter.AllAssets;
import io.github.Skepter.Config.ConfigHandler;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ErrorUtils {

	public static void cantEnchant(final Player player) {
		ConfigHandler.instance();
		player.sendMessage(AllAssets.error + ConfigHandler.getMsg("cantEnchant"));
	}

	public static void error(final CommandSender sender, final String msg) {
		sender.sendMessage(AllAssets.error + msg);
	}

	public static void notAnInteger(final CommandSender commandSender) {
		commandSender.sendMessage(AllAssets.error + ConfigHandler.getMsg("notANumber"));
	}

	public static void notEnoughArguments(final CommandSender commandSender) {
		commandSender.sendMessage(AllAssets.error + "Not enough arguments - use /help <command>!");
	}

	public static void playerNotFound(final Player player, final String target) {
		player.sendMessage(AllAssets.error + target + " is offline");
	}

	public static void pluginNotFound(final CommandSender player, final String plugin) {
		player.sendMessage(AllAssets.error + "Couldn't find the plugin " + plugin + "!");
	}

	public static void tooManyArguments(final Player player) {
		player.sendMessage(AllAssets.error + "Too many arguments - use /help <command>!");
	}

	public static void tptoggle(final Player player, final String target) {
		player.sendMessage(AllAssets.error + target + " has turned teleporting off");
	}

	public static void wrongConstruction(final Player player, final String construction) {
		player.sendMessage(AllAssets.error + "Wrong construction, use the format: " + construction);
	}

	public static void playerOnly(final CommandSender sender) {
		sender.sendMessage(AllAssets.error + "You must be ingame in order to use that command");
	}

	public static void generalCommandError(final CommandSender sender) {
		sender.sendMessage(AllAssets.error + "There was an error while executing the command");
	}

	public static void onCooldown(final CommandSender sender, long seconds) {
		sender.sendMessage(AllAssets.error + "You are on cooldown, you cannot use that command for another " + seconds + " seconds");
	}
}
