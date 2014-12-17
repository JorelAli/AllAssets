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
import io.github.Skepter.AllAssets.API.User;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CommandAnnouncer {

	public CommandAnnouncer(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "announcer", aliases = { "announce" }, permission = "announcer", description = "Configure the scheduled announcer", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		
		final User user = new User(player);
		final Location l = player.getLocation();
		player.teleport(user.getLastLoc());
		user.setLastLoc(l);
		player.sendMessage(AllAssets.title + "Teleported to your last location");
		return;
	}
	
	@CommandHandler(name = "announcer.list", permission = "announcer", description = "Configure the scheduled announcer", usage = "Use <command>")
	public void listAnnouncements(final CommandArgs args) {
		//list all announcements
	}
	
	@CommandHandler(name = "announcer.add", permission = "announcer", description = "Configure the scheduled announcer", usage = "Use <command>")
	public void addAnnouncement(final CommandArgs args) {
		//adds a new announcment
	}
	
	@CommandHandler(name = "announcer.remove", permission = "announcer", description = "Configure the scheduled announcer", usage = "Use <command>")
	public void removeAnnouncement(final CommandArgs args) {
		//removes an announcement based on the announcement ID which is stored when adding an announcement
		//store the announcement by using ID's based on the amount of announcements there are
		// sp that way, when a new announcement is created, it saves it under the next ID.
		/*
		 * e.g. store first announcement as ID 1
		 * store the second announcement as ID 2
		 * if you delete ID 2, then next announcement stored will become ID 2
		 * 
		 * Ensure that all announcements are read in real-time (not cached) to prevent
		 * memory leaks and allow for dynamic announcements.
		 */
	}

}
