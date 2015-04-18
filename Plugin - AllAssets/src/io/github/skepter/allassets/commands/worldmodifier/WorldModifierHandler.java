package io.github.skepter.allassets.commands.worldmodifier;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.api.utils.PlayerMap;

import java.util.UUID;

import org.bukkit.entity.Player;

public class WorldModifierHandler {

	private static PlayerMap<WorldModifierData> map = new PlayerMap<WorldModifierData>(AllAssets.instance());

	public static WorldModifierData getData(Player player) {
		UUID uuid = player.getUniqueId();
		if (!map.containsPlayer(uuid))
			map.put(player, new WorldModifierData());
		return map.get(uuid);
	}
}
