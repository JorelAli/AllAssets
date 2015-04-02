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
package io.github.skepter.allassets.tasks;

import io.github.skepter.allassets.reflection.PacketBuilder;
import io.github.skepter.allassets.reflection.PacketBuilder.PacketType;

import org.bukkit.entity.Player;

public class InstantRespawnTask implements Runnable {

	private final Player player;

	public InstantRespawnTask(final Player player) {
		this.player = player;
	}

	@Override
	public void run() {
		new PacketBuilder(player, PacketType.PLAY_IN_CLIENT_COMMAND).setEnum("a", "EnumClientCommand", "PERFORM_RESPAWN").send();
	}

}
