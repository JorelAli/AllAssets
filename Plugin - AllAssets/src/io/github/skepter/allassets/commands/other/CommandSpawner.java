/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter 
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter 
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
import io.github.skepter.allassets.utils.InputParser;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.PlayerUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class CommandSpawner {

	public CommandSpawner(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "spawner", permission = "spawner", description = "Changes the spawner mob")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		switch(args.getArgs().length) {
			case 0:
				printHelp(player);
				break;
			case 1:
				if(PlayerUtils.getTargetBlock(player).getType().equals(Material.MOB_SPAWNER)) {
					CreatureSpawner spawner = (CreatureSpawner) PlayerUtils.getTargetBlock(player);
					InputParser parser = new InputParser(args.getArgs()[0]);
					EntityType e = parser.parseMob();
					if(e.equals(EntityType.UNKNOWN)) {
						ErrorUtils.inexistantMob(player);
						return;
					} else {
						spawner.setSpawnedType(e);	
					}
				}
				break;
		}
	}

	@Help(name = "Spawner")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "Spawner", "/spawner <mob> - Sets a spawner to that mob");
	}
}
