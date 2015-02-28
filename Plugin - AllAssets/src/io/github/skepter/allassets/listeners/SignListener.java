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
package io.github.skepter.allassets.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignListener implements Listener {

	//	@EventHandler
	//	public void playerClickSignEvent(final PlayerInteractEvent event) {
	//		//buy signs etc.
	//	}
	//
	//	@EventHandler
	//	public void playerSwearEvent(final SignChangeEvent event) {
	//		//swear signs etc.
	//	}
	//
	//	@EventHandler
	//	public void onCreate(final SignChangeEvent event) {
	//		//buy signs etc.
	//	}

	@EventHandler
	public void playerUseColor(final SignChangeEvent event) {
		if (event.getPlayer().hasPermission("AllAssets.signcolor"))
			for (int i = 0; i < 3; i++) {
				final String s = event.getLine(i);
				final String str = ChatColor.translateAlternateColorCodes('&', s);
				event.setLine(i, str);
			}
	}

}
