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
package io.github.skepter.allassets.reflection;

import io.github.skepter.allassets.reflection.PacketBuilder.PacketType;

import org.bukkit.entity.Player;

/**
 * @deprecated Use NMS and Packets instead
 * 
 */

@Deprecated
public class ReflectionPlayer {

	public enum GameStateEffects {
		INVALID_BED(0),
		END_RAINING(1),
		BEGIN_RAINING(2),
		CHANGE_GAMEMODE(3),
		ENTER_CREDITS(4),
		DEMO_MESSAGE(5),
		ARROW_HIT(6),
		FADE_VALUE(7),
		FADE_TIME(8),
		ELDER_GUARDIAN_APPEARANCE(10);

		private final int id;

		GameStateEffects(int id) {
			this.id = id;
		}
	}

	private final Player player;

	public ReflectionPlayer(final Player player) {
		this.player = player;
	}

	/**
	 * Take case when setting values! Use 0 if no value is required:
	 * 
	 * @param effect
	 *            The game effect to use
	 * @param value
	 *            CHANGE_GAMEMODE: 0 = survival, 1 = creative, 2 = adventure, 3
	 *            = spectator
	 * @param value
	 *            DEMO_MESSAGE: 0 = welcome screen, 101 = controls, 102 = jump
	 *            control, 103 = inventory control
	 * @param value
	 *            FADE_VALUE: 1 = dark, 0 = bright;
	 * @param value
	 *            FADE_TIME: ticks for the sky to fade
	 */
	public void doGameStateChange(final GameStateEffects effect, final int value) {
		try {
			final PacketBuilder packet = new PacketBuilder(player, PacketType.PLAY_OUT_GAME_STATE_CHANGE);
			int effectCode = effect.id;
			packet.setInt("b", effectCode).set("c", Float.valueOf(value)).send();
		} catch (final Exception exception) {
		}
	}

}
