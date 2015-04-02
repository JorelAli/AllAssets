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
import io.github.skepter.allassets.utils.IDReader;
import io.github.skepter.allassets.utils.utilclasses.PlayerUtils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandGive {

	public CommandGive(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@SuppressWarnings("deprecation")
	@CommandHandler(name = "give", aliases = { "i", "item" }, permission = "give", description = "Gives items")
	public void onCommand(final CommandArgs args) {
		//		final ItemInfo iF = net.milkbowl.vault.item.Items.itemByString(args.getArgs()[0]);
		//		try {
		//			args.getPlayer().getInventory().addItem(iF.toStack());
		//		} catch (final IllegalArgumentException e1) {
		//			// TODO Auto-generated catch block
		//			e1.printStackTrace();
		//		} catch (final Exception e1) {
		//			// TODO Auto-generated catch block
		//			e1.printStackTrace();
		//		}
		//		//give <player> <item> <amount>
		switch (args.getArgs().length) {
			case 1:
			case 2:
				return;
			case 3:
				try {
					final Player player = PlayerUtils.getOnlinePlayerFromString(args.getArgs()[0]);
					player.getInventory().addItem(new ItemStack(Material.getMaterial(Integer.parseInt(IDReader.readID(args.getArgs()[1].split(":")[0]))), Integer.parseInt(args.getArgs()[2])));
				} catch (final Exception e) {
					e.printStackTrace();
				}
		}
	}
}
