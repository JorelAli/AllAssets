/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
 *
 * AllAssets was created by Skepter (http://skepter.github.io/).
 *
 * You are able to:
 * * View AllAsset's source code on github
 * * Experiment with the code to your wish
 * * Download it to use on your server
 *
 * You are NOT able to:
 * * Sell AllAssets - it is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter
 * * Distribute it on any other website. The ONLY places where it can be downloaded from is github and the Bukkit repository
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. The only exception is the BukkitDev staff who I have allowed permission to decompile (to search for malicious code)
 *
 * You can not:
 * * Hold me liable for your actions:
 *     - If there is a bug in the program, you are allowed to notify me.
 *     - If other players abuse AllAssets, you are NOT allowed to hold me liable for that
 *     - If it was setup incorrectly or you used a non-official build, you can NOT hold me liable for that.
 * * Yell at me:
 *     - If something is wrong, please approach the situation calmly.
 * * Modify the code:
 *     - You are NOT allowed to 'steal' the whole code from Github, modify it, compile it yourself and distribute that.
 *
 * If you are to break from these implications, future use of this plugin will be forbidden.
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.commands.administration;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class CommandRemove {

	public CommandRemove(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "remove", permission = "remove", description = "Removes entities")
	public void command(final CommandArgs args) {
		Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			//remove <radius>
			//remove <entity> <radius>
			//remove (all) (120)
			//remove entity (120)
			switch (args.getArgs().length) {
			case 0:
				//arrow,boat,item
				int count = 0;
				for (Entity e : player.getNearbyEntities(120, 120, 120)) {
					if (e.getType().equals(EntityType.DROPPED_ITEM)) {
						e.remove();
						count++;
					}
				}
				player.sendMessage(Strings.TITLE + count + (count == 1 ? " item removed" : " items removed"));
				return;
			case 1:
				if (TextUtils.isInteger(args.getArgs()[0])) {
					int i = Integer.parseInt(args.getArgs()[0]);
					int count2 = 0;
					for (Entity e : player.getNearbyEntities(i, i, i)) {
						if (e.getType().equals(EntityType.DROPPED_ITEM))
							e.remove();
						count2++;
					}
					player.sendMessage(Strings.TITLE + count2 + (count2 == 1 ? " item removed" : " items removed"));
					return;
				} else {
					ErrorUtils.notAnInteger(args.getSender());
				}
				return;
			}
			ErrorUtils.tooManyArguments(player);
			return;
		}
	}
}
