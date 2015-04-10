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
import net.minecraft.server.v1_8_R1.Material;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CommandTop {

	public CommandTop(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "top", aliases = { "ascend" }, permission = "top", description = "Teleports you upwards")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			final Location l = player.getLocation();
			for (int i = l.getBlockY(); i < player.getWorld().getMaxHeight(); i++) {
				if (new Location(player.getWorld(), l.getBlockX(), i, l.getBlockZ()).getBlock().getType().equals(Material.AIR)) {
					if (new Location(player.getWorld(), l.getBlockX(), i + 1, l.getBlockZ()).getBlock().getType().equals(Material.AIR)) {
						player.teleport(new LocationUtils(new Location(player.getWorld(), l.getBlockX(), i, l.getBlockZ())).getCenter());
						break;
					}
				}
			}
			player.sendMessage(Strings.TITLE + "Teleported to the next level");
		}
		return;
	}

}
