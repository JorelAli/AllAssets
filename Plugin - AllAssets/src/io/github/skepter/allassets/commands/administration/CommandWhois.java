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
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.commands.administration;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.api.User;
import io.github.skepter.allassets.commandlisteners.CommandGod;
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils.SeperatorType;
import io.github.skepter.allassets.utils.utilclasses.TimeUtils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandWhois {

	public CommandWhois(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "whois", aliases = { "who" }, permission = "whois", description = "Find a user's true indentity")
	public void onCommand(final CommandArgs args) {
		Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			switch (args.getArgs().length) {
			case 0:
				printHelp(args.getSender());
				return;
			case 1:
				Player target = PlayerGetter.getTarget(player, args.getArgs()[0]);
				User user = new User(target);
				player.sendMessage(TextUtils.title("Whois " + target.getName()));
				TextUtils.printInformation(player, "UUID", SeperatorType.COLON, target.getUniqueId().toString());
				TextUtils.printInformation(player, "Total time played", SeperatorType.COLON, TimeUtils.formatDate(user.getTotalTimePlayed()));
				TextUtils.printInformation(player, "Has godmode", SeperatorType.COLON, TextUtils.booleanToString(CommandGod.players.contains(target.getUniqueId())));
				TextUtils.printInformation(player, "Is viewing console", SeperatorType.COLON, TextUtils.booleanToString(CommandConsoleLog.players.contains(target.getUniqueId())));
				TextUtils.printInformation(player, "Has fly mode", SeperatorType.COLON, TextUtils.booleanToString(target.getAllowFlight()));
				TextUtils.printInformation(player, "Gamemode", SeperatorType.COLON, TextUtils.capitalize(target.getGameMode().name().toLowerCase()));
				TextUtils.printInformation(player, "Is op", SeperatorType.COLON, TextUtils.booleanToString(target.isOp()));
				TextUtils.printInformation(player, "Is AFK", SeperatorType.COLON, TextUtils.booleanToString(user.isAFK()));
			}
		}
		return;
	}

	@Help(name = "Whois")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "Whois", "/whois <player> - finds information about the player");
	}

}
