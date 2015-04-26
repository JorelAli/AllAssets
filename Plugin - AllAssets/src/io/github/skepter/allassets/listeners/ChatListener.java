/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and Tundra
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and Tundra
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.listeners;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.api.events.LogEvent.LogType;
import io.github.skepter.allassets.commands.administration.CommandLog;
import io.github.skepter.allassets.config.ConfigHandler;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

	@EventHandler
	public void playerHyperlinkChatEvent(final AsyncPlayerChatEvent event) {
		if (ConfigHandler.features().getBoolean("AntiHyperlink"))
			if (TextUtils.isHyperlink(event.getMessage()) && !event.getPlayer().hasPermission("AllAssets.hyperlink")) {
				CommandLog.addLog(ChatColor.BLUE + event.getPlayer().getName() + ChatColor.WHITE + " tried to post a link: " + ChatColor.BLUE + event.getMessage(), LogType.CHAT);
				event.getRecipients().clear();
			}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void playerSwearEvent(final AsyncPlayerChatEvent event) {
		if (ConfigHandler.features().getBoolean("AntiSwear"))
			if ((TextUtils.containsSwear(event.getMessage()) || TextUtils.containsSwearUsingFilter(event.getMessage())) && !event.getPlayer().hasPermission("AllAssets.swear")) {
				CommandLog.addLog(ChatColor.BLUE + event.getPlayer().getName() + ChatColor.WHITE + " tried to swear: " + ChatColor.BLUE + event.getMessage(), LogType.CHAT);
				event.getRecipients().clear();
			}
	}

	@EventHandler
	public void playerNickname(final AsyncPlayerChatEvent event) {
		String prefix = AllAssets.instance().chat.getPlayerPrefix(event.getPlayer());
		String suffix = AllAssets.instance().chat.getPlayerSuffix(event.getPlayer());
		if (prefix == null || prefix.equals("null"))
			prefix = "";
		if (suffix == null || suffix.equals("null"))
			suffix = "";
		prefix = ChatColor.translateAlternateColorCodes('&', prefix);
		suffix = ChatColor.translateAlternateColorCodes('&', suffix);
		if (event.getPlayer().getCustomName() != null) {
			if (ConfigHandler.features().getBoolean("ChatColor"))
				if (event.getPlayer().hasPermission("AllAssets.chatColor"))
					event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));

			event.setFormat(ChatColor.WHITE + "<" + prefix + event.getPlayer().getCustomName() + suffix + ChatColor.WHITE + "> " + event.getMessage());
		} else {
			event.setFormat("<" + prefix + "%s" + suffix + "> " + "%s");
		}
	}

	@EventHandler
	public void playerUseColorEvent(final AsyncPlayerChatEvent event) {
		if (ConfigHandler.features().getBoolean("ChatColor"))
			if (event.getPlayer().hasPermission("AllAssets.chatColor"))
				event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
	}

	//TODO add to features/config
}
