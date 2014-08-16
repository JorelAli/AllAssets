package io.github.Skepter.Listeners;

import io.github.Skepter.Config.UUIDData;
import io.github.Skepter.Users.User;

import java.net.InetAddress;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListingListener implements Listener {

	@EventHandler
	public void ping(final ServerListPingEvent event) {
		final String search = compileInetAddress(event.getAddress());
		for(final User user : User.getAllUsers()) {
			if(getLastIP(user).contains(search)) {
				final UUIDData data = new UUIDData();
				event.setMotd(ChatColor.AQUA + "Welcome to the server " + data.getReversedUUIDMap().get(user.getUUID()) + "! You have joined " + user.getJoinCount() + " times!");
				return;
			}
		}
	}

	@EventHandler
	public void login(final AsyncPlayerPreLoginEvent event) {
		final String save = compileInetAddress(event.getAddress());
		final User user = new User(event.getName());
		final ArrayList<String> s = new ArrayList<String>();
		s.add(save);
		user.setIPs(s);
	}

	private String getLastIP(final User user) {
		return user.IPs().get(user.IPs().size() - 1);
	}

	private String compileInetAddress(final InetAddress address) {
		return address.toString().substring(1).replace(".", "_");
	}
}
