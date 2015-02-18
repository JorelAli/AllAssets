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
package io.github.Skepter.AllAssets.CommandListeners;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Help;
import io.github.Skepter.AllAssets.API.OfflineUser;
import io.github.Skepter.AllAssets.API.PlayerRequest;
import io.github.Skepter.AllAssets.API.PlayerRequest.PlayerRequestEvent;
import io.github.Skepter.AllAssets.API.User;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.PlayerUtils;
import io.github.Skepter.AllAssets.Utils.TextUtils;
import io.github.Skepter.AllAssets.Utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CommandFriend implements Listener{

	public CommandFriend(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "friend", aliases = { "f" }, permission = "friend", description = "Access your friend information")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		printHelp(player);
	}
	
	@CommandHandler(name = "friend.list", aliases = { "friends" }, permission = "friend", description = "Show a list of all of your friends")
	public void onList(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		User user = new User(player);
		List<UUID> friends = user.getFriendList();
		for(UUID u : friends) {
			OfflineUser offlineFriend = new OfflineUser(u);
			player.sendMessage(AllAssets.houseStyleColor + offlineFriend.getPlayer().getName() + " - " +  offlineFriend.getLastLoc().distance(player.getLocation()) + " blocks away");
		}		
	}
	
	@CommandHandler(name = "friend.add", permission = "friend", description = "Add a friend")
	public void addFriend(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		if(PlayerUtils.getOnlinePlayerFromString(args.getArgs()[0]) == null) {
			ErrorUtils.playerNotFound(player, args.getArgs()[0]);
			return;
		}
		Player target = PlayerUtils.getOnlinePlayerFromString(args.getArgs()[0]);
		new PlayerRequest(player, target, "Do you want to add " + target.getName() + " add a friend?", -1L);
	}
	
	@EventHandler
	public void onAccept(PlayerRequestEvent event) {
		if(event.getResult()) {
			User user = new User(event.getFrom());
			user.setFriendList(Utils.add(user.getFriendList(), event.getTo().getUniqueId()));
		}
		
	}
	
	@Help(name="Friend")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "Friend", "/friend list - lists your friends");
	}
}
