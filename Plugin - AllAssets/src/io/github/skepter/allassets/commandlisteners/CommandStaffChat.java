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

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;

import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/*TODO - only works IF the receiver has sc turned on as well*/
public class CommandStaffChat implements Listener {

	public CommandStaffChat(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	private final CopyOnWriteArraySet<UUID> players = new CopyOnWriteArraySet<UUID>();

	@CommandHandler(name = "staffchat", aliases = { "sc", "adminchat", "ac", "a" }, permission = "staffchat", description = "Toggles the staff chat")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		if (players.remove(player.getUniqueId()))
			player.sendMessage(Strings.TITLE + "Staff chat is now off");
		else {
			players.add(player.getUniqueId());
			player.sendMessage(Strings.TITLE + "Staff chat is now on");
		}
	}

	@EventHandler
	public void playerChat(final AsyncPlayerChatEvent event) {
		if (players.contains(event.getPlayer().getUniqueId())) {
			event.setCancelled(true);
			for (final UUID uuid : players) {
				final String format = AllAssets.instance().getAAConfig().config().getSpecialString("staffChat");
				final String format1 = format.replace("{MESSAGE}", event.getMessage());
				final String format2 = format1.replace("{PLAYERNAME}", event.getPlayer().getName());
				Bukkit.getPlayer(uuid).sendMessage(ChatColor.translateAlternateColorCodes('&', format2));
			}
		}
	}

}
