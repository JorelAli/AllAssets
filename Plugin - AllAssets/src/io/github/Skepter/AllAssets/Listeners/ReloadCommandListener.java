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
package io.github.Skepter.AllAssets.Listeners;

import io.github.Skepter.AllAssets.Utils.Strings;
import io.github.Skepter.AllAssets.Utils.UtilClasses.ErrorUtils;

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
					ErrorUtils.error(event.getPlayer(), "You cannot reload AllAssets. Use /allassets reload instead");
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
