package io.github.skepter.allassets.api.users;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class UserUtils {

	public static List<User> onlineUsers() {
		List<User> onlineUsers = new ArrayList<User>();
		for(Player p : Bukkit.getOnlinePlayers()) {
			onlineUsers.add(new User(p));
		}
		return onlineUsers;
	}
}
