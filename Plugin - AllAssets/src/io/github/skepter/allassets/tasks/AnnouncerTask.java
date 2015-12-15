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
package io.github.skepter.allassets.tasks;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.config.ConfigHandler;
import io.github.skepter.allassets.utils.utilclasses.MathUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class AnnouncerTask implements Runnable {

	private int count;
	private ConfigHandler config;

	public AnnouncerTask() {
		count = 0;
		config = AllAssets.instance().getAAConfig();
	}

	@Override
	public void run() {
		if (config.config().getBoolean("randomAnnouncer"))
			Bukkit.broadcastMessage(getAnnouncer(MathUtils.randomBetween(1, config.announcer().getKeys().size())));
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
		return config.announcer().getString(String.valueOf(ID));
	}

}
