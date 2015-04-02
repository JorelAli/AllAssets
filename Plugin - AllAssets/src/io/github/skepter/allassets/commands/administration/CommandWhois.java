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
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null)
			switch (args.getArgs().length) {
				case 0:
					printHelp(args.getSender());
					return;
				case 1:
					final Player target = PlayerGetter.getTarget(player, args.getArgs()[0]);
					final User user = new User(target);
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
		return;
	}

	@Help(name = "Whois")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "Whois", "/whois <player> - finds information about the player");
	}

}
