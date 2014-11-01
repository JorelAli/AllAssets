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
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Commands;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.PlayerUtils;

import org.bukkit.entity.Player;

public class CommandGhost {

	public CommandGhost(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "ghost", aliases = { "semivanish" }, permission = "ghost", description = "Allows you to turn into a ghost", usage = "Use <command>")
	public void command(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		if (args.getArgs().length == 0) {
			if (AllAssets.instance().ghostFactory.isGhost(player)) {
				AllAssets.instance().ghostFactory.setGhost(player, false);
				player.sendMessage(AllAssets.title + "Ghost mode disabled");
				return;
			} else {
				AllAssets.instance().ghostFactory.setGhost(player, true);
				player.sendMessage(AllAssets.title + "Ghost mode enabled");
				return;
			}
		} else if (args.getArgs().length == 1) {
			final Player target = PlayerUtils.getPlayerFromString(args.getArgs()[0]);
			if (AllAssets.instance().ghostFactory.isGhost(target)) {
				AllAssets.instance().ghostFactory.setGhost(target, false);
				target.sendMessage(AllAssets.title + "Ghost mode disabled");
				return;
			} else {
				AllAssets.instance().ghostFactory.setGhost(target, true);
				target.sendMessage(AllAssets.title + "Ghost mode enabled");
				return;
			}
		} else {
			ErrorUtils.tooManyArguments(player);
			return;
		}
	}
}
