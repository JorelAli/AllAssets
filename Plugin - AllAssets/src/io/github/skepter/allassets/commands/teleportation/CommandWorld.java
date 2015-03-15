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
package io.github.skepter.allassets.commands.teleportation;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.CommandFramework.Completer;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.LocationUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandWorld {

	public CommandWorld(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "world", aliases = { "tpworld" }, permission = "world", description = "Teleports you to a certain world")
	public void onCommand(final CommandArgs args) {
		Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			switch (args.getArgs().length) {
			case 0:
				printHelp(player);
				return;
			case 1:
				for (World world : Bukkit.getWorlds())
					if (args.getArgs()[0].equalsIgnoreCase(world.getName())) {
						LocationUtils.teleport(world.getSpawnLocation(), player);
						player.sendMessage(Strings.TITLE + "You have been teleported to " + world.getName());
					}
				return;
			}
		}
		return;
	}

	/* Completer must use CamelCase name for the command.
	 * It returns a List<String> and it MUST have CommandArgs in the method.
	 * Here, I get a list of all of the worlds loaded, put their names into a
	 * new List<String> and return that. */
	@Completer(name = "world")
	public List<String> onComplete(final CommandArgs args) {
		List<String> list = new ArrayList<String>();
		for (World world : Bukkit.getWorlds())
			list.add(world.getName());
		return list;
	}

	@Help(name = "World")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "World", "/world <worldName> - Teleports you to that world");
	}
}
