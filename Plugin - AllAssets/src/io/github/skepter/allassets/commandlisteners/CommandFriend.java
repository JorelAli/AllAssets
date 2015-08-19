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
package io.github.skepter.allassets.commandlisteners;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.api.PlayerRequest;
import io.github.skepter.allassets.api.PlayerRequest.PlayerRequestEvent;
import io.github.skepter.allassets.api.users.User;
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.Utils;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.PlayerUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CommandFriend implements Listener {

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
		final User user = new User(player);
		final Set<UUID> friends = user.getFriendList();
		for (final UUID u : friends) {
			final User offlineFriend = new User(u);
			player.sendMessage(Strings.HOUSE_STYLE_COLOR + offlineFriend.getPlayer().getName() + " - " + offlineFriend.getLastLoc().distance(player.getLocation()) + " blocks away");
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
		if (PlayerUtils.getOnlinePlayerFromString(args.getArgs()[0]) == null) {
			ErrorUtils.playerNotFound(player, args.getArgs()[0]);
			return;
		}
		final Player target = PlayerUtils.getOnlinePlayerFromString(args.getArgs()[0]);
		player.sendMessage(Strings.TITLE + "You have sent a friend request to " + target.getName());
		new PlayerRequest(player, target, "add you as a friend. Do you accept " + target.getName() + "'s request?", -1L);
	}

	@EventHandler
	public void onAccept(final PlayerRequestEvent event) {
		if (event.getResult()) {
			final User user = new User(event.getFrom());
			if (!user.getFriendList().isEmpty())
				user.setFriendList((Set<UUID>) Utils.add(user.getFriendList(), event.getTo().getUniqueId()));
			else
				user.setFriendList(new HashSet<UUID>() {
					private static final long serialVersionUID = 1L;
					{
						add(event.getTo().getUniqueId());
					}
				});
		}

	}

	@Help(name = "Friend")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "Friend", "/friend add <player> - adds <player> as a friend", "/friend list - lists your friends");
	}
}
