package io.github.Skepter.Commands;

import io.github.Skepter.AllAssets;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Utils.ErrorUtils;
import io.github.Skepter.Utils.TextUtils;

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

	@CommandHandler(name = "near", permission = "near", description = "Shows entities near to you", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
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
			player.sendMessage(AllAssets.title + "No nearby entities could be found!");
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
				player.sendMessage(AllAssets.houseStyleColor + TextUtils.capitalize(entry.getKey().name().toLowerCase().replace("_", " ")) + ": " + entry.getValue());
				count += entry.getValue();
			}
			player.sendMessage(AllAssets.houseStyleColor + "Total nearby entities: " + count);
		}
		if (!entities.isEmpty()) {
			player.sendMessage(TextUtils.title("Nearby players"));
			for (final Entity entity : entities) {
				final Player target = (Player) entity;
				player.sendMessage(AllAssets.houseStyleColor + target.getName() + ": " + target.getLocation().distance(player.getLocation()));
			}
		}
	}
}
