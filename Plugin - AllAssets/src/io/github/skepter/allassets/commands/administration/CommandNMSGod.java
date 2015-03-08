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
package io.github.skepter.allassets.commands.administration;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.reflection.MinecraftReflectionUtils;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;

import org.bukkit.entity.Player;

public class CommandNMSGod {

	public CommandNMSGod(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "god", aliases = { "invincible", "invunerable" }, permission = "god", description = "Makes you invincible")
	public void onCommand(final CommandArgs args) {
		Player player = PlayerGetter.getPlayer(args);
		if (player != null)
			try {
				new MinecraftReflectionUtils(args.getPlayer());
				final MinecraftReflectionUtils utils = new MinecraftReflectionUtils(args.getPlayer());
				if (utils.abilities.getClass().getField("isInvulnerable").getBoolean(utils.abilities)) {
					utils.abilities.getClass().getField("isInvulnerable").setBoolean(utils.abilities, false);
					player.sendMessage(Strings.TITLE + "You suddenly feel much more vunerable");
				} else {
					utils.abilities.getClass().getField("isInvulnerable").setBoolean(utils.abilities, true);
					player.sendMessage(Strings.TITLE + "A higher power falls upon you");
				}
			} catch (final Exception t) {
				ErrorUtils.generalCommandError(player);
			}
		return;
	}

}
