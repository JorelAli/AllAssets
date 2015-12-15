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
