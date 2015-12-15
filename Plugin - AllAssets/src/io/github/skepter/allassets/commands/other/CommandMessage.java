/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter 
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter 
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
package io.github.skepter.allassets.commands.other;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.api.users.User;
import io.github.skepter.allassets.api.users.UserUtils;
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMessage {

	public CommandMessage(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "message", aliases = { "msg", "m" }, permission = "message", description = "Sends a private message to a player")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null)
			switch (args.getArgs().length) {
				case 0:
					printHelp(player);
					return;
				case 1:
					final Player target = PlayerGetter.getTarget(player, args.getArgs()[0]);
					if (target != null) {
						String message = TextUtils.getMsgStringFromArgs(args.getArgs(), 1, args.getArgs().length);
						target.sendMessage(incomingMessage(player.getName(), target.getName(), message));
						player.sendMessage(outgoingMessage(player.getName(), target.getName(), message));
						for(User user : UserUtils.onlineUsers()) {
							if(user.canSocialSpy()) {
								user.getPlayer().sendMessage(publicMessage(player.getName(), target.getName(), message));
							}
						}
					} else {
						ErrorUtils.playerNotFound(player, args.getArgs()[0]);
					}
					return;
			}
		return;
	}
	
	private String outgoingMessage(String player, String target, String message) {
		return Strings.HOUSE_STYLE_COLOR + "[You" + Strings.ACCENT_COLOR + " -> " + Strings.HOUSE_STYLE_COLOR + target + "] " + Strings.ACCENT_COLOR + message;
	}
	
	private String incomingMessage(String player, String target, String message) {
		return Strings.HOUSE_STYLE_COLOR + "[" + player + Strings.ACCENT_COLOR + " -> " + Strings.HOUSE_STYLE_COLOR + "You] " + Strings.ACCENT_COLOR + message;
	}
	
	private String publicMessage(String player, String target, String message) {
		return Strings.HOUSE_STYLE_COLOR + "[" + player + Strings.ACCENT_COLOR + " -> " + Strings.HOUSE_STYLE_COLOR + target + "] " + Strings.ACCENT_COLOR + message;
	}

	@Help(name = "Message")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "Message", "/message <player> <message> - Sends a private message to a player");
	}
}
