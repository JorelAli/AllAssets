/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
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

package io.github.skepter.allassets.commands.teleportation;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.CommandFramework.Completer;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
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
						Location target = new Location(world, world.getSpawnLocation().getX(), world.getSpawnLocation().getY(), world.getSpawnLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());
						player.teleport(target);
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
