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
package io.github.skepter.allassets.commands.teleportation.teleporting;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.PlayerGetter;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandTpall {

	public CommandTpall(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "tpall", aliases = { "teleportall" }, permission = "tpall", description = "Teleport everyone to you")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			player.sendMessage(Strings.TITLE + "Teleported " + (Bukkit.getOnlinePlayers().size() - 1) + " players to you");
			for (Player p : Bukkit.getOnlinePlayers()) {
				p.teleport(player);
			}
		}
	}
}
