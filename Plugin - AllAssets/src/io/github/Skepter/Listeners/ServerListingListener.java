package io.github.Skepter.Listeners;

import io.github.Skepter.API.User;
import io.github.Skepter.Config.ConfigHandler;
import io.github.Skepter.Config.UUIDData;

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
	public void ping(final ServerListPingEvent event) {
		for (final UUID u : UUIDData.getValues()) {
			final User user = new User(Bukkit.getOfflinePlayer(u));
			if (getLastIP(user).contains(event.getAddress().toString().substring(1, event.getAddress().toString().length()))) {
				final String playerName = UUIDData.getReversedUUIDMap().get(Bukkit.getOfflinePlayer(u).getUniqueId());
				event.setMotd(ChatColor.translateAlternateColorCodes('&', ConfigHandler.getMsg("serverListMOTD")).replace("{PLAYERNAME}", playerName).replace("{JOINCOUNT}", String.valueOf(user.getJoinCount())));
				return;
			}
		}
	}

	@EventHandler
	public void login(final AsyncPlayerPreLoginEvent event) {
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
