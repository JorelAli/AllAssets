package io.github.skepter.allassets.commands.worldmodifier;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.api.utils.PlayerMap;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.entity.Player;

public class WorldModifierHandler {

	private UUID uuid;

	private static Set<UUID> wmPlayers = new HashSet<UUID>();
	private static PlayerMap<WorldModifierData> map = new PlayerMap<WorldModifierData>(AllAssets.instance());

	public WorldModifierHandler(Player player) {
		this.uuid = player.getUniqueId();
	}

	public boolean hasWorldModifierEnabled() {
		return wmPlayers.contains(uuid);
	}

	public void toggleWorldModifierState() {
		if (!wmPlayers.remove(uuid))
			wmPlayers.add(uuid);
	}

	public void setWorldModifierEnabled() {
		wmPlayers.add(uuid);
	}

	public WorldModifierData getData() {
		if (!map.containsPlayer(uuid))
			return new WorldModifierData();
		else
			return map.get(uuid);
	}
}
