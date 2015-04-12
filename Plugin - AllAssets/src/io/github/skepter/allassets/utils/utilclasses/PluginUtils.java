package io.github.skepter.allassets.utils.utilclasses;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class PluginUtils {

	public static String getAuthors(Plugin plugin) {
		String authors = TextUtils.listToString(plugin.getDescription().getAuthors());
		if(authors.length() == 0)
			authors = "undefined";
		return authors;
	}
	
	public static String getAuthors(PluginDescriptionFile plugin) {
		String authors = TextUtils.listToString(plugin.getAuthors());
		if(authors.length() == 0)
			authors = "undefined";
		return authors;
	}
	
}
