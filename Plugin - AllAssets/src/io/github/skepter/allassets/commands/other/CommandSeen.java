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
package io.github.skepter.allassets.commands.other;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.api.OfflineUser;
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.PlayerUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;
import io.github.skepter.allassets.utils.utilclasses.TimeUtils;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSeen {

	public CommandSeen(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "seen", permission = "seen", description = "Shows when the player was last online")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null)
			switch (args.getArgs().length) {
				case 0:
					printHelp(player);
					return;
				case 1:
					OfflinePlayer target = PlayerUtils.getOfflinePlayerFromString(args.getArgs()[0]);
					if (target != null) {
						long t = new OfflineUser(target).getTimeSinceLastPlay();
						if (t != 0)
							player.sendMessage(Strings.TITLE + "Last seen " + target.getName() + " " + TimeUtils.formatDate(System.currentTimeMillis() - (t)));
						else
							ErrorUtils.playerNotFound(player, args.getArgs()[0]);
					}
			}
		return;
	}

	@Help(name = "Seen")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "Seen", "/seen <player> - Shows when the player was last online");
	}
}
