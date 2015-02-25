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
package io.github.Skepter.AllAssets.Commands.Administration;

import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.Strings;
import io.github.Skepter.AllAssets.Utils.UtilClasses.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.UtilClasses.TextUtils;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandChestSearch {

	public CommandChestSearch(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@SuppressWarnings("deprecation")
	@CommandHandler(name = "chestsearch", aliases = { "csearch" }, permission = "chestsearch", description = "Search for items in chests")
	public void command(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		if (args.getArgs().length == 0) {
			player.sendMessage(TextUtils.title("Chest search"));
			player.sendMessage("Use chest search to find items in chests in the world");
			player.sendMessage("Usage:");
			player.sendMessage("/chestsearch hand - search for chests with the item in your hand");
			player.sendMessage("/chestsearch <itemID> - search for chests with that item ID");
			return;
		} else if (args.getArgs().length == 1) {

			//probably store chests into a yaml file for searching for easy locations
			//of placed chests. or hook into LWC or something

			final ArrayList<Chest> chests = new ArrayList<Chest>();
			for (final Chunk chunk : player.getWorld().getLoadedChunks())
				for (final BlockState e : chunk.getTileEntities())
					if (e instanceof Chest)
						chests.add((Chest) e);
			final ArrayList<Location> locatedChests = new ArrayList<Location>();
			for (final Chest chest : chests)
				if (args.getArgs()[0].equalsIgnoreCase("hand")) {
					if (chest.getInventory().containsAtLeast(player.getItemInHand(), 1))
						locatedChests.add(chest.getLocation());
				} else if (StringUtils.isNumeric(args.getArgs()[0])) {
					final int i = Integer.parseInt(args.getArgs()[0]);
					if (chest.getInventory().containsAtLeast(new ItemStack(i), 1))
						locatedChests.add(chest.getLocation());
				}
			player.sendMessage(Strings.TITLE + "Located " + (locatedChests.size()) + " chests");
			for (final Location loc : locatedChests)
				player.sendMessage(TextUtils.subTitle("X: " + loc.getX() + " Y: " + loc.getY() + " Z: " + loc.getZ()));
		}
	}
}
