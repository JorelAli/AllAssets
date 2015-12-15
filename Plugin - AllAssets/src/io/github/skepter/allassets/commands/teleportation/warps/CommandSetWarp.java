/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and MCSpartans
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and MCSpartans
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
package io.github.skepter.allassets.commands.teleportation.warps;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.config.ConfigHandler;
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.serializers.LocationSerializer;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetWarp {

	public CommandSetWarp(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "setwarp", permission = "setwarp", description = "Sets a warp")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		ConfigHandler config = AllAssets.instance().getAAConfig();
		if (player != null)
			switch (args.getArgs().length) {
				case 0:
					printHelp(player);
					return;
				case 1:
					//Name is stored differently to the actual name due to capitals and stuff.
					config.warps().set(args.getArgs()[0].toLowerCase() + ".name", args.getArgs()[0]);
					config.warps().set(args.getArgs()[0].toLowerCase() + ".loc", LocationSerializer.locToString(player.getLocation()));
					player.sendMessage(Strings.TITLE + "Warp " + args.getArgs()[0] + " created successfully");
					return;
				default:
					String description = TextUtils.getMsgStringFromArgs(args.getArgs(), 1, args.getArgs().length);
					config.warps().set(args.getArgs()[0].toLowerCase() + ".name", args.getArgs()[0]);
					config.warps().set(args.getArgs()[0].toLowerCase() + ".loc", LocationSerializer.locToString(player.getLocation()));
					config.warps().set(args.getArgs()[0].toLowerCase() + ".description", description);
					player.sendMessage(Strings.TITLE + "Warp " + args.getArgs()[0] + " created successfully");
					return;
			}
		return;
	}

	@Help(name = "SetWarp")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "SetWarp", "/setwarp <warpname> - Sets a new warp", "/setwarp <warpname> <description> - Sets a new warp with a description", "/setwarp <warpname> <description> - Update the description of an existing warp");
	}
}
