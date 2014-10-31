package io.github.Skepter.AllAssets.Listeners;

import io.github.Skepter.AllAssets.API.User;
import io.github.Skepter.AllAssets.Config.ConfigHandler;
import io.github.Skepter.AllAssets.Config.UUIDData;

import java.util.ArrayList;
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
				if (getLastIP(user).contains(event.getAddress().toString().substring(1, event.getAddress().toString().length()))) {
					final String playerName = UUIDData.getReversedUUIDMap().get(Bukkit.getOfflinePlayer(u).getUniqueId());
					event.setMotd(ChatColor.translateAlternateColorCodes('&', ConfigHandler.getSpecialMsg("serverListMOTD")).replace("{PLAYERNAME}", playerName).replace("{JOINCOUNT}", String.valueOf(user.getJoinCount())));
					return;
				}
			} catch (Exception e) {
				event.setMotd(ChatColor.translateAlternateColorCodes('&', ConfigHandler.getSpecialMsg("serverListMOTD")).replace("{PLAYERNAME}", "").replace("{JOINCOUNT}", String.valueOf(user.getJoinCount())));
				return;
			}
		}
	}

	@EventHandler
	public void playerLogin(final AsyncPlayerPreLoginEvent event) {
		final String save = event.getAddress().toString().substring(1, event.getAddress().toString().length());
		final User user = new User(event.getName());
		final ArrayList<String> s = new ArrayList<String>();
		s.add(save);
		user.setIPs(s);
	}

	private String getLastIP(final User user) {
		if (!user.IPs().isEmpty())
			return user.IPs().get(user.IPs().size() - 1);
		return null;
	}
}
