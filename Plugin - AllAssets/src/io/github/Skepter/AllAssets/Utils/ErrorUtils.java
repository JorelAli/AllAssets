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
package io.github.Skepter.AllAssets.Utils;

import static io.github.Skepter.AllAssets.AllAssets.*;
import static io.github.Skepter.AllAssets.Config.ConfigHandler.*;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ErrorUtils {

	public static void cantEnchant(final Player player) {
		player.sendMessage(error + getMsg("cantEnchant"));
	}

	public static void error(final CommandSender sender, final String msg) {
		sender.sendMessage(error + msg);
	}

	public static void notAnInteger(final CommandSender commandSender) {
		commandSender.sendMessage(error + getMsg("notANumber"));
	}

	public static void notEnoughArguments(final CommandSender commandSender) {
		commandSender.sendMessage(error + "Not enough arguments - use /help <command>!");
	}

	public static void playerNotFound(final CommandSender commandSender, final String target) {
		commandSender.sendMessage(error + target + " is offline");
	}

	public static void pluginNotFound(final CommandSender player, final String plugin) {
		player.sendMessage(error + "Couldn't find the plugin " + plugin + "!");
	}

	public static void tooManyArguments(final Player player) {
		player.sendMessage(error + "Too many arguments - use /help <command>!");
	}

	public static void tptoggle(final Player player, final String target) {
		player.sendMessage(error + target + " has turned teleporting off");
	}

	public static void wrongConstruction(final Player player, final String construction) {
		player.sendMessage(error + "Wrong construction, use the format: " + construction);
	}

	public static void playerOnly(final CommandSender sender) {
		sender.sendMessage(error + "You must be ingame in order to use that command");
	}

	public static void generalCommandError(final CommandSender sender) {
		sender.sendMessage(error + "There was an error while executing the command");
	}

	public static void onCooldown(final CommandSender sender, final long seconds) {
		sender.sendMessage(error + "You are on cooldown, you cannot use that command for another " + seconds + " seconds");
	}
}
