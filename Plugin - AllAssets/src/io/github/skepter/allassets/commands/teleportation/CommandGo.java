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
package io.github.skepter.allassets.commands.teleportation;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.LocationUtils;
import io.github.skepter.allassets.utils.utilclasses.PlayerUtils;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class CommandGo {

	public CommandGo(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "go", aliases = { "jump", "j" }, permission = "go", description = "Teleport to where you are pointing")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			final Block b = PlayerUtils.getLastTwoTargetBlocks(player, -1).get(1);
			final Block target = b.getRelative(b.getFace(PlayerUtils.getLastTwoTargetBlocks(player, -1).get(0)));
			if (target.getType().equals(Material.AIR))
				if (b.getType().equals(Material.AIR) && !player.getGameMode().equals(GameMode.CREATIVE)) {
					ErrorUtils.cannotJump(player);
					return;
				}
			new LocationUtils(new LocationUtils(target.getLocation()).getCenter()).teleport(player);
		}
		return;
	}
}
