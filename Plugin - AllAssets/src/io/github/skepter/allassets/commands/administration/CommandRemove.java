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
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			//remove <radius>
			//remove <entity> <radius>
			//remove (all) (120)
			//remove entity (120)
			switch (args.getArgs().length) {
				case 0:
					//arrow,boat,item
					int count = 0;
					for (final Entity e : player.getNearbyEntities(120, 120, 120))
						if (e.getType().equals(EntityType.DROPPED_ITEM)) {
							e.remove();
							count++;
						}
					player.sendMessage(Strings.TITLE + count + (count == 1 ? " item removed" : " items removed"));
					return;
				case 1:
					if (TextUtils.isInteger(args.getArgs()[0])) {
						final int i = Integer.parseInt(args.getArgs()[0]);
						int count2 = 0;
						for (final Entity e : player.getNearbyEntities(i, i, i)) {
							if (e.getType().equals(EntityType.DROPPED_ITEM))
								e.remove();
							count2++;
						}
						player.sendMessage(Strings.TITLE + count2 + (count2 == 1 ? " item removed" : " items removed"));
						return;
					} else
						ErrorUtils.notAnInteger(args.getSender());
					return;
			}
			ErrorUtils.tooManyArguments(player);
			return;
		}
	}
}
