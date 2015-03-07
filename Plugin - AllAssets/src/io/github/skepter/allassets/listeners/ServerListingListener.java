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
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.listeners;

import io.github.skepter.allassets.api.OfflineUser;
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
			final OfflineUser user = new OfflineUser(Bukkit.getOfflinePlayer(u));
			try {
				if (user.IPs().contains(event.getAddress().toString().substring(1, event.getAddress().toString().length()))) {
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
			final OfflineUser user = new OfflineUser(Bukkit.getOfflinePlayer(u));
			try {
				if (user.getPlayer().getName().equals(event.getName()))
					user.setIPs(Arrays.asList(new String[] { save }));
			} catch (Exception e) {
				/* User is not found */
			}
		}
	}
}