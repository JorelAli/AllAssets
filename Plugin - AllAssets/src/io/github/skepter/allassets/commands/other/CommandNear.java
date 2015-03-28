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
package io.github.skepter.allassets.commands.other;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class CommandNear {

	public CommandNear(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "near", permission = "near", description = "Shows entities near to you")
	public void onCommand(final CommandArgs args) {
		Player player = PlayerGetter.getPlayer(args);
		if (player != null) {

			/* Changing lookup distance */
			int distance = 200;
			if (args.getArgs().length == 1)
				if (TextUtils.isInteger(args.getArgs()[0]))
					distance = Integer.parseInt(args.getArgs()[0]);
				else
					ErrorUtils.notAnInteger(player);

			/* Checking for entities */
			final List<Entity> entities = player.getNearbyEntities(distance, distance, distance);
			if (entities.isEmpty()) {
				player.sendMessage(Strings.TITLE + "No nearby entities could be found!");
				return;
			}

			/* Dump them into a map for easy lookup */
			final Map<EntityType, Integer> map = new TreeMap<EntityType, Integer>();
			while (entities.iterator().hasNext()) {
				final Entity entity = entities.iterator().next();
				if (entity instanceof Player)
					continue;
				else {
					map.put(entity.getType(), map.get(entity.getType()) == null ? 1 : map.get(entity.getType()) + 1);
					entities.remove(entity);
				}
			}

			/* Print the info */
			if (!map.isEmpty()) {
				player.sendMessage(TextUtils.title("Nearby entities"));
				int count = 0;
				for (final Entry<EntityType, Integer> entry : map.entrySet()) {
					if (entry.getValue() == 0)
						continue;
					player.sendMessage(Strings.HOUSE_STYLE_COLOR + TextUtils.capitalize(entry.getKey().name().toLowerCase().replace("_", " ")) + ": " + entry.getValue());
					count += entry.getValue();
				}
				player.sendMessage(Strings.HOUSE_STYLE_COLOR + "Total nearby entities: " + count);
			}
			if (!entities.isEmpty()) {
				player.sendMessage(TextUtils.title("Nearby players"));
				for (final Entity entity : entities) {
					final Player target = (Player) entity;
					player.sendMessage(Strings.HOUSE_STYLE_COLOR + target.getName() + ": " + target.getLocation().distance(player.getLocation()));
				}
			}
		}
	}
}
