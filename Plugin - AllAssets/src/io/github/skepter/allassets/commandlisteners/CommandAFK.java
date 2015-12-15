/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter 
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter 
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
package io.github.skepter.allassets.commandlisteners;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.api.users.User;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class CommandAFK implements Listener {

	public CommandAFK(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "afk", permission = "afk", description = "Sets your status as away from keyboard")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		final User user = new User(player);
		if (!user.isAFK()) {
			AllAssets.instance().getServer().broadcastMessage(Strings.TITLE + player.getName() + " is now AFK");
			user.setAFKStatus(true);
		} else {
			AllAssets.instance().getServer().broadcastMessage(Strings.TITLE + player.getName() + " is no longer AFK");
			user.setAFKStatus(false);
		}
		return;
	}

	@EventHandler
	public void onJoin(final PlayerJoinEvent event) {
		//TODO add to the features file
		final StringBuilder builder = new StringBuilder();
		for (final Player player : Bukkit.getOnlinePlayers())
			if (new User(player).isAFK())
				builder.append(player.getName() + ", ");
		if (!builder.toString().isEmpty())
			event.getPlayer().sendMessage(Strings.TITLE + "List of AFK players: " + builder.toString().substring(0, builder.toString().length() - 2));
	}

	@EventHandler
	public void playerHurt(final EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			final Player player = (Player) event.getEntity();
			final User user = new User(player);
			if (AllAssets.instance().getAAConfig().config().getBoolean("afkProtect") && user.isAFK()) {
				event.setDamage(0.0D);
				event.setCancelled(false);
			}
		}
	}

	@EventHandler
	public void playerMove(final PlayerMoveEvent event) {
		final Player player = event.getPlayer();
		final User user = new User(player);
		if (user.isAFK()) {
			AllAssets.instance().getServer().broadcastMessage(Strings.TITLE + player.getName() + " is no longer AFK");
			user.setAFKStatus(false);
		}
	}
}
