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
package io.github.skepter.allassets.commands.cosmetics;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.api.builders.ItemBuilder;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;

import org.bukkit.entity.Player;

public class CommandGlow {

	public CommandGlow(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "glow", permission = "glow", description = "Makes the item in your hand glow")
	public void onCommand(final CommandArgs args) {
		Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			if (!player.getItemInHand().getType().isBlock()) {
				if (new ItemBuilder(player.getItemInHand()).hasGlow()) {
					player.setItemInHand(new ItemBuilder(player.getItemInHand()).removeGlow().build());
					player.sendMessage(Strings.TITLE + "Your item is no longer glowing!");
				} else {
					player.setItemInHand(new ItemBuilder(player.getItemInHand()).addGlow().build());
					player.sendMessage(Strings.TITLE + "Your item is now glowing!");
				}
			} else
				ErrorUtils.error(player, "You cannot make that item glow!");
		}
		return;
	}
}
