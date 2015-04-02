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
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class CommandHead {

	public CommandHead(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "head", permission = "head", description = "Spawns in a head")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null)
			switch (args.getArgs().length) {
				case 0:
					printHelp(player);
					return;
				case 1:
					final ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
					final SkullMeta skull = (SkullMeta) head.getItemMeta();
					skull.setOwner(args.getArgs()[0]);
					head.setItemMeta(skull);
					player.getInventory().addItem(head);
					player.updateInventory();
					player.sendMessage(Strings.TITLE + "Spawned in " + args.getArgs()[0] + "'s head");
					return;
				default:
					ErrorUtils.tooManyArguments(player);
					return;
			}
		return;
	}

	@Help(name = "Head")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "Head", "/head <player> - spawns in <player>'s head");
	}
}
