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
package io.github.skepter.allassets.commands.administration;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.api.utils.Sphere;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class CommandExtinguish {

	public CommandExtinguish(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "extinguish", aliases = { "ext" }, permission = "extinguish", description = "Extinguishes fires")
	public void command(final CommandArgs args) {
		Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			switch (args.getArgs().length) {
				case 0:
					Sphere sphere = new Sphere(player.getLocation(), 120, Material.FIRE);
					for (Block b : sphere.getBlocks())
						b.setType(Material.AIR);
					player.sendMessage(Strings.TITLE + sphere.getBlocks().size() + " fires removed");
					return;
				case 1:
					if (TextUtils.isInteger(args.getArgs()[0])) {
						Sphere sphereWithCustomRadius = new Sphere(player.getLocation(), Integer.parseInt(args.getArgs()[0]), Material.FIRE);
						for (Block b : sphereWithCustomRadius.getBlocks())
							b.setType(Material.AIR);
						player.sendMessage(Strings.TITLE + sphereWithCustomRadius.getBlocks().size() + " fires removed");
					} else
						ErrorUtils.notAnInteger(args.getSender());
					return;
			}
			ErrorUtils.tooManyArguments(player);
		}
		return;
	}
}
