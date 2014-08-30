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
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class CommandNear {

	public CommandNear(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "near", permission = "near", description = "Shows entities near to you", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		final Player player = args.getPlayer();
		int distance = 200;
		if(args.getArgs().length == 1) {
			if(TextUtils.isInteger(args.getArgs()[0]))
				distance = Integer.parseInt(args.getArgs()[0]);
			else
				ErrorUtils.notAnInteger(player);
		}
		List<Entity> entities = player.getNearbyEntities(distance, distance, distance);
		Map<EntityType, Integer> map = new TreeMap<EntityType, Integer>();
		while (entities.iterator().hasNext()) {
			Entity entity = entities.iterator().next();
			if (entity instanceof Player)
				continue;
			if (entity instanceof LivingEntity) {
				map.put(entity.getType(), map.get(entity.getType()) == null ? 1 : map.get(entity.getType()) + 1);
				entities.remove(entity);
			} else
				entities.remove(entity);
		}
		if (!map.isEmpty()) {
			player.sendMessage(TextUtils.title("Nearby entities"));
			for (Entry<EntityType, Integer> entry : map.entrySet()) {
				if (entry.getValue() == 0)
					continue;
				player.sendMessage(AllAssets.instance().houseStyleColor + TextUtils.capitalize(entry.getKey().name().toLowerCase()) + ": " + entry.getValue());
			}
		}
		if (entities.isEmpty())
			return;
		player.sendMessage(TextUtils.title("Nearby players"));
		for (Entity entity : entities) {
			Player target = (Player) entity;
			player.sendMessage(AllAssets.instance().houseStyleColor + target.getName() + ": " + target.getLocation().distance(player.getLocation()));

		}
	}
}
