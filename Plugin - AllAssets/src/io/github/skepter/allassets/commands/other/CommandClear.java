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
package io.github.skepter.allassets.commands.other;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.config.ConfigHandler;
import io.github.skepter.allassets.utils.utilclasses.PlayerUtils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandClear {

	public CommandClear(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "clear", aliases = { "c", "ci", "clearinventory" }, permission = "clear", description = "Clears your inventory")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null)
			if (ConfigHandler.config().getBoolean("clearArmor")) {
				if (args.getArgs().length == 0)
					player.getInventory().clear();
				else if (args.getArgs().length == 1) {
					//TODO try/catch exception e
					final Player target = PlayerUtils.getOnlinePlayerFromString(args.getArgs()[0]);
					target.getInventory().clear();
				}
			} else if (args.getArgs().length == 0) {
				final ItemStack[] temp = player.getInventory().getArmorContents();
				player.getInventory().clear();
				player.getInventory().setArmorContents(temp);
			} else if (args.getArgs().length == 1) {
				final Player target = PlayerUtils.getOnlinePlayerFromString(args.getArgs()[0]);
				final ItemStack[] temp = target.getInventory().getArmorContents();
				target.getInventory().clear();
				target.getInventory().setArmorContents(temp);
			}
		return;
	}
}
