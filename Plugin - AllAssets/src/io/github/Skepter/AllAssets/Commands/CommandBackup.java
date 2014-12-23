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

import java.io.IOException;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.TextUtils;

import org.bukkit.Bukkit;
import org.bukkit.World;

import com.google.common.io.Files;

public class CommandBackup {

	public CommandBackup(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "backup", permission = "backup", description = "Backs up a world", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		switch (args.getArgs().length) {
		case 0:
			ErrorUtils.notEnoughArguments(args.getSender());
			return;
		case 1:
			if (args.getArgs()[0].equalsIgnoreCase("list")) {
				args.getSender().sendMessage(TextUtils.title("List of worlds"));
				for (World world : Bukkit.getWorlds()) {
					args.getSender().sendMessage(world.getName());
				}
				return;
			} else {
				World world = null;
				try {
					world = Bukkit.getWorld(args.getArgs()[0]);
				} catch (Exception e) {
					ErrorUtils.worldNotFound(args.getSender(), args.getArgs()[0]);
					return;
				}
				final World w = world;
				Bukkit.getScheduler().runTaskAsynchronously(AllAssets.instance(), new Runnable() {
					@Override
					public void run() {
						try {
							Files.copy(w.getWorldFolder(), AllAssets.getWorldStorage());
						} catch (IOException e) {
							ErrorUtils.error(args.getSender(), "There was an error whilst backing up the world");
						}
					}
				});
				args.getSender().sendMessage(AllAssets.title + args.getArgs()[0] + " was backed up successfully ");
				return;
			}
		}
	}

}