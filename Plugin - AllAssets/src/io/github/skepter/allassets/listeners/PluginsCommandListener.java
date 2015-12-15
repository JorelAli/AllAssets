/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter 
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter 
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
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.PluginUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

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
					event.getPlayer().sendMessage(Strings.TITLE + "There are currently " + (Bukkit.getPluginManager().getPlugins().length) + " plugins:");
					final List<String> pluginList = new ArrayList<String>();
					for (final Plugin plugin : Bukkit.getPluginManager().getPlugins())
						if (AllAssets.instance().getAAConfig().config().getBoolean("pluginsShowAuthors")) {
							String authors = PluginUtils.getAuthors(plugin);
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
