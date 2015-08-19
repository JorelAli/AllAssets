/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and MCSpartans
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and MCSpartans
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
package io.github.skepter.allassets.commands.administration;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.version.nms.NMS;

import org.bukkit.entity.Player;

public class CommandNMSGod {

	public CommandNMSGod(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "god", aliases = { "invincible", "invunerable" }, permission = "god", description = "Makes you invincible")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			NMS nms = AllAssets.instance().getNMS();
			if (nms.isInvunerable(player)) {
				nms.setInvunerability(player, false);
				player.sendMessage(Strings.TITLE + "You suddenly feel much more vunerable");
			} else {
				nms.setInvunerability(player, true);
				player.sendMessage(Strings.TITLE + "A higher power falls upon you");
			}
		}
		return;
	}
}
