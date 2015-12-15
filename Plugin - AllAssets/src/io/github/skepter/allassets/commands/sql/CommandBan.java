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
package io.github.skepter.allassets.commands.sql;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.sqlite.SQLiteBan;
import io.github.skepter.allassets.sqlite.SQLiteLoader;
import io.github.skepter.allassets.sqlite.SQLiteLoader.SQLiteType;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class CommandBan implements Listener {

	private final SQLiteBan sqlite;

	public CommandBan(final CommandFramework framework) {
		framework.registerCommands(this);
		sqlite = (SQLiteBan) new SQLiteLoader().getSQLiteManager(SQLiteType.BAN);
	}

	@CommandHandler(name = "ban", permission = "ban", description = "Bans a player")
	public void onCommand(final CommandArgs args) {
		if (args.getArgs().length < 1) {
			ErrorUtils.notEnoughArguments(args.getSender());
			return;
		}
		final Player target = PlayerGetter.getTarget(args.getSender(), args.getArgs()[0]);
		if (target != null)
			sqlite.banPlayer(args.getSender(), target, TextUtils.getMsgStringFromArgs(args.getArgs(), 1, args.getArgs().length));
		return;
	}

	@EventHandler
	public void onJoin(final PlayerLoginEvent event) {
		if (sqlite.isBanned(event.getPlayer().getName()))
			event.disallow(Result.KICK_BANNED, sqlite.getBannedMessage(event.getPlayer().getName()));
	}

	@Help(name = "Ban")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "Ban", "/ban <player> <reason> - Bans a player for a certain reason");
	}
}
