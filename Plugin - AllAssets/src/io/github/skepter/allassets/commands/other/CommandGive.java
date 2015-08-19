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
package io.github.skepter.allassets.commands.other;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.items.BlockInfo;
import io.github.skepter.allassets.utils.InputParser;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.PlayerUtils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandGive {

	public CommandGive(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "give", aliases = { "i", "item" }, permission = "give", description = "Gives items")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			switch (args.getArgs().length) {
				case 1: {
					BlockInfo input = new InputParser(args.getArgs()[0]).parseBlockInfo();
					if (input != null) {
						ItemStack is = new ItemStack(input.getMaterial(), 64);
						is.setDurability(input.getData());
						player.getInventory().addItem(is);
					} else
						ErrorUtils.itemNotFound(player);

					return;
				}
				case 2: {
					if (PlayerUtils.isOnline(args.getArgs()[0])) {
						BlockInfo input = new InputParser(args.getArgs()[0]).parseBlockInfo();
						if (input != null) {
							ItemStack is = new ItemStack(input.getMaterial(), 64);
							is.setDurability(input.getData());
							player.getInventory().addItem(is);
						} else
							ErrorUtils.itemNotFound(player);
						return;
					} else {
						BlockInfo input = new InputParser(args.getArgs()[0]).parseBlockInfo();
						if (input != null) {
							ItemStack is = new ItemStack(input.getMaterial(), Integer.parseInt(args.getArgs()[1]));
							is.setDurability(input.getData());
							player.getInventory().addItem(is);
						} else
							ErrorUtils.itemNotFound(player);
					}
					return;
				}
				case 3: {
					Player target = PlayerGetter.getTarget(player, args.getArgs()[0]);
					if(target != null) {
						BlockInfo input = new InputParser(args.getArgs()[1]).parseBlockInfo();
						if (input != null) {
							ItemStack is = new ItemStack(input.getMaterial(), Integer.parseInt(args.getArgs()[2]));
							is.setDurability(input.getData());
							player.getInventory().addItem(is);
						} else
							ErrorUtils.itemNotFound(player);
					}
					return;
				}
			}
		}
	}
}
