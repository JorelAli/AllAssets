/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
 *
 * AllAssets was created by Skepter and Tundra (http://skepter.github.io/).
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
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. The only exception is the BukkitDev staff who I have allowed permission to decompile (to search for malicious code)
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
package io.github.skepter.allassets.utils.utilclasses;

import static io.github.skepter.allassets.config.ConfigHandler.getMsg;
import io.github.skepter.allassets.api.LogEvent.LogType;
import io.github.skepter.allassets.commands.administration.CommandLog;
import io.github.skepter.allassets.utils.Strings;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.conversations.Conversable;
import org.bukkit.entity.Player;

public class ErrorUtils {

	public static void cantEnchant(final Player player) {
		player.sendMessage(Strings.ERROR + getMsg("cantEnchant"));
	}

	public static void error(final CommandSender sender, final String msg) {
		sender.sendMessage(Strings.ERROR + msg);
	}

	public static void notAnInteger(final CommandSender commandSender) {
		commandSender.sendMessage(Strings.ERROR + getMsg("notANumber"));
	}

	public static void notEnoughArguments(final CommandSender commandSender) {
		commandSender.sendMessage(Strings.ERROR + "Not enough arguments - use /help <command>!");
	}

	public static void playerNotFound(final CommandSender commandSender, final String target) {
		commandSender.sendMessage(Strings.ERROR + target + " is offline");
	}

	public static void pluginNotFound(final CommandSender player, final String plugin) {
		player.sendMessage(Strings.ERROR + "Couldn't find the plugin " + plugin + "!");
	}

	public static void tooManyArguments(final Player player) {
		player.sendMessage(Strings.ERROR + "Too many arguments - use /help <command>!");
	}

	public static void tptoggle(final Player player, final String target) {
		player.sendMessage(Strings.ERROR + target + " has turned teleporting off");
	}

	public static void wrongConstruction(final Player player, final String construction) {
		player.sendMessage(Strings.ERROR + "Wrong construction, use the format: " + construction);
	}

	public static void playerOnly(final CommandSender sender) {
		sender.sendMessage(Strings.ERROR + "You must be ingame in order to use that command");
	}

	public static void generalCommandError(final CommandSender sender) {
		sender.sendMessage(Strings.ERROR + "There was an error while executing the command");
	}

	public static void onCooldown(final CommandSender sender, final long seconds) {
		sender.sendMessage(Strings.ERROR + "You are on cooldown, you cannot use that command for another " + seconds + " seconds");
	}
	
	public static void worldNotFound(final CommandSender sender, final String worldName) {
		sender.sendMessage(Strings.ERROR + worldName + " could not be found!");
	}

	public static void conversableError(final Conversable forWhom, final String msg) {
		forWhom.sendRawMessage(Strings.ERROR + msg);
	}
	
	public static void printErrorToConsole(final String msg) {
		Bukkit.getLogger().severe(msg);
		CommandLog.addLog(msg, LogType.ERROR);
	}
}
