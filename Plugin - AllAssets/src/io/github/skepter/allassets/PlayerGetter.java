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
package io.github.skepter.allassets;

import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.PlayerUtils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/** See CommandEnderchest class for PERFECT example */
public class PlayerGetter {

	public static Player getPlayer(CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
		}
		return player;
	}
	
	public static Player getTarget(CommandSender player, String playerName) {
		Player target = null;
		try {
			target = PlayerUtils.getOnlinePlayerFromString(playerName);
		} catch (final Exception e) {
			ErrorUtils.playerNotFound(player, playerName);
		}
		return target;
	}
	
}
