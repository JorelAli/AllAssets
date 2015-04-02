/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and Tundra
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and Tundra
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
package io.github.skepter.allassets.tasks;

import io.github.skepter.allassets.config.ConfigHandler;
import io.github.skepter.allassets.utils.utilclasses.MathUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class AnnouncerTask implements Runnable {

	private int count;

	public AnnouncerTask() {
		count = 0;
	}

	@Override
	public void run() {
		if (ConfigHandler.config().getBoolean("randomAnnouncer"))
			Bukkit.broadcastMessage(getAnnouncer(MathUtils.randomBetween(1, ConfigHandler.announcer().getKeys().size())));
		else
			try {
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', getAnnouncer(count)));
				count++;
			} catch (final Exception e) {
				//If the count is higher than in the data file, loop again
				count = 0;
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', getAnnouncer(count)));
			}

	}

	private String getAnnouncer(final int ID) {
		return ConfigHandler.announcer().getString(String.valueOf(ID));
	}

}
