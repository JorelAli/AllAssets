package io.github.skepter.allassets.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.help.HelpTopic;

/***
 * Useful lists to compare with levenshteinDistance 
 * @author Skepter
 *
 */
public class Lists {

	public static List<String> onlinePlayers() {
		List<String> list = new ArrayList<String>();
		for(Player p : Bukkit.getOnlinePlayers())
			list.add(p.getName());
		return list;
	}
	
	public static List<String> offlinePlayers() {
		List<String> list = new ArrayList<String>();
		for(OfflinePlayer p : Bukkit.getOfflinePlayers())
			list.add(p.getName());
		return list;
	}
	
	public static List<String> commands() {
		List<String> list = new ArrayList<String>();
		for (HelpTopic topic : Bukkit.getServer().getHelpMap().getHelpTopics()) 
		    list.add(topic.getName());
		return list;

	}
	
}
