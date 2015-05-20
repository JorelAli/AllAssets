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

import io.github.skepter.allassets.api.users.User;
import io.github.skepter.allassets.config.ConfigHandler;
import io.github.skepter.allassets.config.UUIDData;

import java.util.Arrays;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListingListener implements Listener {

	@EventHandler
	public void multiplayerPing(final ServerListPingEvent event) {
		for (final UUID u : UUIDData.getValues()) {
			final User user = new User(Bukkit.getOfflinePlayer(u));
			try {
				if (user.getStoredIps().contains(event.getAddress().toString().substring(1, event.getAddress().toString().length()))) {
					final String playerName = UUIDData.getReversedUUIDMap().get(Bukkit.getOfflinePlayer(u).getUniqueId());
					event.setMotd(ChatColor.translateAlternateColorCodes('&', ConfigHandler.getSpecialMsg("serverListMOTD")).replace("{PLAYERNAME}", playerName).replace("{JOINCOUNT}", String.valueOf(user.getJoinCount())));
					return;
				}
			} catch (final Exception e) {
				event.setMotd(ChatColor.translateAlternateColorCodes('&', ConfigHandler.getSpecialMsg("serverListMOTD")).replace(" {PLAYERNAME}", "").replace("{JOINCOUNT}", String.valueOf(user.getJoinCount())));
				return;
			}
		}
	}

	@EventHandler
	public void playerLogin(final AsyncPlayerPreLoginEvent event) {
		final String save = event.getAddress().toString().substring(1, event.getAddress().toString().length());
		for (final UUID u : UUIDData.getValues()) {
			final User user = new User(Bukkit.getOfflinePlayer(u));
			try {
				if (user.getPlayer().getName().equals(event.getName()))
					user.setIps(Arrays.asList(new String[] { save }));
			} catch (final Exception e) {
				/* User is not found */
			}
		}
	}
}
