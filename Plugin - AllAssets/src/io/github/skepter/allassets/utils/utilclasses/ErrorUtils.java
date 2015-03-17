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
package io.github.skepter.allassets.utils.utilclasses;

import static io.github.skepter.allassets.config.ConfigHandler.getMsg;
import io.github.skepter.allassets.api.events.LogEvent.LogType;
import io.github.skepter.allassets.commands.administration.CommandLog;
import io.github.skepter.allassets.utils.Strings;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.conversations.Conversable;
import org.bukkit.entity.Player;

public class ErrorUtils {

	//Externalise Strings next.
	
	public static void cantEnchant(final Player player) {
		player.sendMessage(Strings.ERROR + getMsg("cantEnchant"));
	}

	@Deprecated
	public static void error(final CommandSender sender, final String msg) {
		sender.sendMessage(Strings.ERROR + msg);
	}

	public static void cannotLeashMob(final Player player) {
		player.sendMessage("You cannot put a leash on that mob");
	}
	
	public static void cannotReloadAllAssets(final Player player) {
		player.sendMessage("You cannot reload AllAssets. Use /AllAssets reload instead.");
	}
	
	public static void itemInHandIsNull(final Player player) {
		player.sendMessage("The item in your hand cannot be nothing!");
	}

	public static void logError(final Player player, String message) {
		player.sendMessage("An error appeared in the log: " + message);
	}

	public static void cannotFindHelpTopic(final CommandSender sender) {
		sender.sendMessage("Could not find that!");
	}

	public static void cannotJump(final Player player) {
		player.sendMessage("Could not jump to that location!");
	}

	public static void cannotUndisguise(final Player player) {
		player.sendMessage("There was an error whilst trying to remove your disguise");
	}

	public static void inexistantMob(final Player player) {
		player.sendMessage("That mob doesn't exist!");
	}

	public static void batchLimit(final Player player, int limit) {
		player.sendMessage("Batch amount cannot be larger than " + limit + "!");
	}

	public static void cannotSetTime(final CommandSender sender) {
		sender.sendMessage("There was an error whilst setting the world time");
	}

	public static void cannotGlowItem(final Player player) {
		player.sendMessage("You cannot make that item glow!");
	}

	public static void itemNotFound(final Player player) {
		player.sendMessage("That item could not be found!");
	}

	public static void backupError(final CommandSender sender) {
		sender.sendMessage("There was an error whilst backing up the world");
	}

	public static void cannotReadFile(final Player player) {
		player.sendMessage("That file could not be read!");
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
