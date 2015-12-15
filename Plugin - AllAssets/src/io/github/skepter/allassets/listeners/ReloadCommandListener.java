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

import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ReloadCommandListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommand(final PlayerCommandPreprocessEvent event) {
		final String cmd = event.getMessage().split(" ")[0].replace("/", "").toLowerCase();
		if (cmd.equals("reload") && event.getPlayer().hasPermission("AllAssets.reload")) {
			event.setCancelled(true);
			final String[] args = event.getMessage().split(" ");
			if (args.length == 1) {
				Bukkit.reload();
				event.getPlayer().sendMessage(Strings.TITLE + "Reload complete");
				return;
			}
			if (args.length == 2) {
				if (args[1].equalsIgnoreCase("AllAssets")) {
					ErrorUtils.cannotReloadAllAssets(event.getPlayer());
					return;
				}
				try {
					Bukkit.getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin(args[1]));
					Bukkit.getPluginManager().enablePlugin(Bukkit.getPluginManager().getPlugin(args[1]));
					event.getPlayer().sendMessage(Strings.TITLE + args[1] + " successfully reloaded");
				} catch (final Exception e) {
					ErrorUtils.pluginNotFound(event.getPlayer(), args[1]);
				}
			}
		}
	}
}
