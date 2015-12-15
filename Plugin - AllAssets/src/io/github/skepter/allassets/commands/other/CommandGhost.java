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
/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.commands.other;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.PlayerUtils;

import org.bukkit.entity.Player;

@Deprecated
public class CommandGhost {

	public CommandGhost(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "ghost", aliases = { "semivanish" }, permission = "ghost", description = "Allows you to turn into a ghost")
	public void command(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			switch (args.getArgs().length) {
				case 0:
					if (AllAssets.instance().ghostFactory.isGhost(player)) {
						AllAssets.instance().ghostFactory.setGhost(player, false);
						player.sendMessage(Strings.TITLE + "Ghost mode disabled");
					} else {
						AllAssets.instance().ghostFactory.setGhost(player, true);
						player.sendMessage(Strings.TITLE + "Ghost mode enabled");
					}
					return;
				case 1:
					final Player target = PlayerUtils.getOnlinePlayerFromString(args.getArgs()[0]);
					if (target == null)
						ErrorUtils.playerNotFound(args.getSender(), args.getArgs()[0]);
					if (AllAssets.instance().ghostFactory.isGhost(target)) {
						AllAssets.instance().ghostFactory.setGhost(target, false);
						target.sendMessage(Strings.TITLE + "Ghost mode disabled");
					} else {
						AllAssets.instance().ghostFactory.setGhost(target, true);
						target.sendMessage(Strings.TITLE + "Ghost mode enabled");
					}
					return;
			}
			ErrorUtils.tooManyArguments(player);
		}
		return;
	}
}
