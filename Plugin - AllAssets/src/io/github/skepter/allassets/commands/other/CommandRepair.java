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
package io.github.skepter.allassets.commands.other;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.ItemUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandRepair {

	public CommandRepair(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "repair", aliases = { "fix" }, permission = "repair", description = "Repairs items")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null)
			switch (args.getArgs().length) {
				case 0:
					printHelp(player);
					return;
				case 1:
					switch (args.getArgs()[0].toLowerCase()) {
						case "all":
							int count = 0;
							for (int i = 0; i < player.getInventory().getSize(); i++) {
								ItemStack is = player.getInventory().getItem(i);
								if (new ItemUtils(is).isRepairable()) {
									is.setDurability((short) 0);
									player.getInventory().setItem(i, is);
								} else {
									ErrorUtils.cannotRepairItem(player);
								}
							}
							player.sendMessage(Strings.TITLE + count + " items repaired");
							return;
						case "hand":
							ItemStack is = player.getItemInHand();
							if (new ItemUtils(is).isRepairable()) {
								is.setDurability((short) 0);
								player.setItemInHand(is);
								player.sendMessage(Strings.TITLE + "Item repaired");
							} else {
								ErrorUtils.cannotRepairItem(player);
							}
							return;
					}
			}
		return;
	}

	@Help(name = "Repair")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "Repair", "/repair hand - Repairs the item in your hand", "repair all - Repairs all items");
	}
}
