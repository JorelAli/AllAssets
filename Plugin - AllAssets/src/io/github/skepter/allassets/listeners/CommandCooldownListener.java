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
import io.github.skepter.allassets.utils.CommandCooldown;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandCooldownListener implements Listener {

	@EventHandler
	public void onCommand(final PlayerCommandPreprocessEvent e) {
		if (AllAssets.instance().getAAConfig().config().getInt("commandCooldown") != 0)
			if (CommandCooldown.isOnCooldown(e.getPlayer(), e.getMessage().split(" ")[0])) {
				ErrorUtils.onCooldown(e.getPlayer(), CommandCooldown.getTimeLeft(e.getPlayer()));
				e.setCancelled(true);
			} else
				new CommandCooldown(e.getPlayer(), AllAssets.instance().getAAConfig().config().getInt("commandCooldown"), e.getMessage().split(" ")[0]);
	}
}
