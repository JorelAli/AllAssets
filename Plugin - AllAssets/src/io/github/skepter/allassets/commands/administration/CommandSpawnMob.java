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
/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.commands.administration;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.utils.InputParser;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.LocationUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import java.util.HashSet;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawnMob {

	public CommandSpawnMob(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "spawnmob", aliases = { "mob", "smob", "monster" }, permission = "spawnmob", description = "Allows you to spawn mob")
	public void command(final CommandArgs args) {
		Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			switch (args.getArgs().length) {
			case 0:
				printHelp(player);
				return;
			case 1:
				Block b = player.getTargetBlock(new HashSet<Material>(), 256);
				Block spawnLocation = b.getRelative(BlockFace.UP);
				player.getWorld().spawnEntity(LocationUtils.getCenter(spawnLocation.getLocation()), new InputParser(args.getArgs()[0]).parseMob());
				return;
			case 2:
				Block b1 = player.getTargetBlock(new HashSet<Material>(), 256);
				Block spawnLocation1 = b1.getRelative(BlockFace.UP);
				if (TextUtils.isInteger(args.getArgs()[1]))
					for (int i = 0; i < Integer.parseInt(args.getArgs()[1]); i++)
						player.getWorld().spawnEntity(LocationUtils.getCenter(spawnLocation1.getLocation()), new InputParser(args.getArgs()[0]).parseMob());
				ErrorUtils.notAnInteger(player);
			}
			ErrorUtils.tooManyArguments(player);
			return;
		}
	}

	@Help(name = "Spawnmob")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "Spawnmob", "/spawnmob <mob> - spawns a mob", "/spawnmob <mob> <amount> - spawns <amount> of mobs");
	}
}
