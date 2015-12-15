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
/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.commandlisteners;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.PlayerUtils;

import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class CommandSilence implements Listener {

	public CommandSilence(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	public CopyOnWriteArraySet<UUID> players = new CopyOnWriteArraySet<UUID>();

	@CommandHandler(name = "silence", permission = "silence", description = "Silences a player")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = PlayerUtils.getOnlinePlayerFromString(args.getArgs()[0]);
		} catch (final Exception e) {
			ErrorUtils.playerNotFound(args.getSender(), args.getArgs()[0]);
			return;
		}

		if (players.remove(player.getUniqueId()))
			args.getSender().sendMessage(Strings.TITLE + player.getName() + " is no longer silenced");
		else {
			players.add(player.getUniqueId());
			args.getSender().sendMessage(Strings.TITLE + player.getName() + " is now silenced");
		}
		return;
	}

	@EventHandler
	public void playerInteract(final AsyncPlayerChatEvent event) {
		for (final UUID u : players)
			event.getRecipients().remove(Bukkit.getPlayer(u));
	}

}
