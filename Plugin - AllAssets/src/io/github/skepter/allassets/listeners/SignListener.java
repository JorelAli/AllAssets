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
