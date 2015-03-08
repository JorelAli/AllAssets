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
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.commands.teleportation;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.api.User;
import io.github.skepter.allassets.utils.Strings;

import org.bukkit.entity.Player;

public class CommandTpToggle {

	public CommandTpToggle(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "tptoggle", permission = "tptoggle", description = "Toggles your teleportation status")
	public void command(final CommandArgs args) {
		Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			User user = new User(player);
			if (user.canTp()) {
				user.setCanTP(false);
				player.sendMessage(Strings.TITLE + "TpToggle off. Players can not teleport to you");
			} else {
				user.setCanTP(true);
				player.sendMessage(Strings.TITLE + "TpToggle on. Players can teleport to you");
			}
		}
	}
}
