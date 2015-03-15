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
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.utils.InputParser;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.LocationUtils;
import io.github.skepter.allassets.utils.utilclasses.PlayerUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawnMob {

	public CommandSpawnMob(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "spawnmob", aliases = { "mob", "smob", "monster" }, permission = "spawnmob", description = "Allows you to spawn mob")
	public void command(final CommandArgs args) {
		Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			switch (args.getArgs().length) {
				case 0:
					printHelp(player);
					return;
				case 1:
					spawnmob(player, args.getArgs()[0]);
					return;
				case 2:
					if (TextUtils.isInteger(args.getArgs()[1]))
						for (int i = 0; i < Integer.parseInt(args.getArgs()[1]); i++)
							spawnmob(player, args.getArgs()[0]);
					else
						ErrorUtils.notAnInteger(player);
					return;
			}
			ErrorUtils.tooManyArguments(player);
			return;
		}
	}

	private void spawnmob(Player player, String mob) {
		Block b = PlayerUtils.getLastTwoTargetBlocks(player).get(1);
		Block spawnLocation = b.getRelative(b.getFace(PlayerUtils.getLastTwoTargetBlocks(player).get(0)));
		player.getWorld().spawnEntity(new LocationUtils(spawnLocation.getLocation()).getCenter(), new InputParser(mob).parseMob());
	}

	@Help(name = "Spawnmob")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "Spawnmob", "/spawnmob <mob> - spawns a mob", "/spawnmob <mob> <amount> - spawns <amount> of mobs");
	}
}
