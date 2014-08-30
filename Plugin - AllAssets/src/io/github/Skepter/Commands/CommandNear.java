package io.github.Skepter.Commands;

import io.github.Skepter.AllAssets;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
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
		List<Entity> entities = player.getNearbyEntities(256, 256, 256);
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
		player.sendMessage(TextUtils.title("Nearby entities"));
		for (Entry<EntityType, Integer> entry : map.entrySet()) {
			if (map.isEmpty())
				player.sendMessage(AllAssets.instance().houseStyleColor + "There are no nearby entities");
			if (entry.getValue() == 0)
				continue;
			player.sendMessage(AllAssets.instance().houseStyleColor + TextUtils.capitalize(entry.getKey().name().toLowerCase()) + ": " + entry.getValue());
		}
		player.sendMessage(TextUtils.title("Nearby players"));
		if (entities.isEmpty())
			player.sendMessage(AllAssets.instance().houseStyleColor + "There are no nearby players");
		for (Entity entity : entities) {
			Player target = (Player) entity;
			player.sendMessage(AllAssets.instance().houseStyleColor + target.getName() + ": " + target.getLocation().distance(player.getLocation()));

		}
	}
}
