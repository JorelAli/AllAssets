package io.github.Skepter.Utils;

import io.github.Skepter.AllInOne;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ErrorUtils {

	public static void cantEnchant(final Player player) {
		player.sendMessage(AllInOne.instance().error + "You cannot enchant that item!");
	}

	public static void error(final CommandSender sender, final String msg) {
		sender.sendMessage(AllInOne.instance().error + msg);
	}

	public static void notAnInteger(final Player player) {
		player.sendMessage(AllInOne.instance().error + "That is not a number!");
	}

	public static void notEnoughArguments(final Player player) {
		player.sendMessage(AllInOne.instance().error + "Not enough arguments - use /help <command>!");
	}

	public static void playerNotFound(final Player player, final String target) {
		player.sendMessage(AllInOne.instance().error + target + " is offline");
	}

	public static void pluginNotFound(final CommandSender player, final String plugin) {
		player.sendMessage(AllInOne.instance().error + "Couldn't find the plugin " + plugin + "!");
	}

	public static void tooManyArguments(final Player player) {
		player.sendMessage(AllInOne.instance().error + "Too many arguments - use /help <command>!");
	}
	
	public static void tptoggle(final Player player, final String target) {
		player.sendMessage(AllInOne.instance().error + target + " has turned teleporting off");
	}
	
	public static void vaultNotFound(final Player player) {
		player.sendMessage(AllInOne.instance().error + "Vault was not found so that action cannot be done");
	}
	
	public static void wrongConstruction(final Player player, final String construction) {
		player.sendMessage(AllInOne.instance().error + "Wrong construction, use the format: " + construction);
	}
}
