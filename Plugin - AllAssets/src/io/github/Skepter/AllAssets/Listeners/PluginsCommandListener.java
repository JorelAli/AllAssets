/*******************************************************************************
 * Skepter's licence
 * Copyright 2014 
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
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. I've just made life easier for you :)
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
package io.github.Skepter.AllAssets.Listeners;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.Config.ConfigHandler;
import io.github.Skepter.AllAssets.Utils.TextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

public class PluginsCommandListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void commandExecuteEvent(final PlayerCommandPreprocessEvent event) {
		final String cmd = event.getMessage().split(" ")[0].replace("/", "").toLowerCase();
		switch (cmd) {
		case "pl":
		case "plugins":
		case "plugin":
		case "?":
			if (event.getPlayer().hasPermission("AllAssets.plugins")) {
				event.setCancelled(true);
				event.getPlayer().sendMessage(TextUtils.title("Plugins"));
				event.getPlayer().sendMessage(AllAssets.title + "There are currently " + (Bukkit.getPluginManager().getPlugins().length) + " plugins:");
				final List<String> pluginList = new ArrayList<String>();
				for (final Plugin plugin : Bukkit.getPluginManager().getPlugins())
					if (ConfigHandler.instance().config().getBoolean("pluginsShowAuthors")) {
						String authors = "";
						for (final String s : plugin.getDescription().getAuthors())
							authors = authors + s + ", ";
						if (authors.length() != 0)
							authors = authors.substring(0, authors.length() - 2);
						else
							authors = "undefined";
						if (plugin.isEnabled())
							pluginList.add(ChatColor.GREEN + plugin.getName() + ChatColor.WHITE + ": v" + plugin.getDescription().getVersion() + "\n " + ChatColor.GRAY + "Authors: " + authors);
						else
							pluginList.add(ChatColor.RED + plugin.getName() + ChatColor.WHITE + ": v" + plugin.getDescription().getVersion() + "\n " + ChatColor.GRAY + "Authors: " + authors);
					} else if (plugin.isEnabled())
						pluginList.add(ChatColor.GREEN + plugin.getName() + ChatColor.WHITE + ": v" + plugin.getDescription().getVersion());
					else
						pluginList.add(ChatColor.RED + plugin.getName() + ChatColor.WHITE + ": v" + plugin.getDescription().getVersion());
				Collections.sort(pluginList);
				for (final String s : pluginList) {
					final String[] str = s.split("\n");
					for (final String m : str)
						event.getPlayer().sendMessage(m);
				}

			}
		default:
			return;
		}
	}
}
