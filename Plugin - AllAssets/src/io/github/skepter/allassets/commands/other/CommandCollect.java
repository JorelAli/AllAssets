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
package io.github.skepter.allassets.commands.other;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.api.utils.Sphere;
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

public class CommandCollect {

	public CommandCollect(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "collect", permission = "collect", description = "Collects nearby items")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null)
			switch (args.getArgs().length) {
				case 0:
					printHelp(player);
					return;
				case 1:
					if (TextUtils.isInteger(args.getArgs()[0])) {
						
						Sphere sphere = new Sphere(player.getLocation(), 20);
						for (Entity e : player.getNearbyEntities(20, 20, 20)) {
							if (sphere.contains(e.getLocation())) {
								if (e instanceof Item) {
									if (player.getInventory().firstEmpty() == -1) {
										return;
									}
									player.getInventory().addItem(((Item) e).getItemStack());
									e.remove();
								}
							}
						}
					} else
						ErrorUtils.notAnInteger(player);
			}
		return;
	}

	@Help(name = "Collect")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "Collect", "/collect <radius> - collects all items within the radius");
	}
}
