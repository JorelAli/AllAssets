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
package io.github.skepter.allassets.commands.teleportation;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.LocationUtils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class CommandTop {

	public CommandTop(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "top", permission = "top", description = "Teleports you to the top level")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			final Location l = player.getLocation();
			int i = player.getWorld().getMaxHeight();
			while (i >= l.getBlockY()) {
				Location l1 = new LocationUtils(new Location(player.getWorld(), l.getBlockX(), i, l.getBlockZ())).getCenter();
				Location l2 = new LocationUtils(new Location(player.getWorld(), l.getBlockX(), i + 1, l.getBlockZ())).getCenter();
				Location l3 = new LocationUtils(new Location(player.getWorld(), l.getBlockX(), i + 2, l.getBlockZ())).getCenter();
				if (!l3.getBlock().getType().equals(Material.AIR) && l2.getBlock().getType().equals(Material.AIR) && l1.getBlock().getType().equals(Material.AIR)) {
					Location l4 = new LocationUtils(new Location(player.getWorld(), l.getBlockX(), i + 3, l.getBlockZ())).getCenter();
					new LocationUtils(new LocationUtils(l4).getCenterForTeleporting()).teleport(player);
					player.sendMessage(Strings.TITLE + "Teleported to the top level");
					break;
				}
				i--;
			}
		}
		return;
	}
}
