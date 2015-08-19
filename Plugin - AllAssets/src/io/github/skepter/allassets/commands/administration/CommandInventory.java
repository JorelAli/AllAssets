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

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.PlayerUtils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class CommandInventory {

	public CommandInventory(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "inventory", aliases = { "invsee", "inv" }, permission = "inventory", description = "Views a players inventory")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null)
			if (args.getArgs().length == 1)
				try {
					final Inventory inv = PlayerUtils.getOnlinePlayerFromString(args.getArgs()[0]).getInventory();
					final Inventory targetInventory = Bukkit.createInventory(null, inv.getSize(), args.getArgs()[0]);
					targetInventory.setContents(inv.getContents());
					player.openInventory(targetInventory);
				} catch (final Exception e) {
					ErrorUtils.playerNotFound(player, args.getArgs()[0]);
				}
		return;
		//TODO editable system so it can/cannot be edited.
	}
}
