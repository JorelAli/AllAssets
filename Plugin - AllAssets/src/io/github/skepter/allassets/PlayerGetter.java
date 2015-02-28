package io.github.skepter.allassets;

import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.PlayerUtils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/** See CommandEnderchest class for PERFECT example */
public class PlayerGetter {

	public static Player getPlayer(CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
		}
		return player;
	}
	
	public static Player getTarget(CommandSender player, String playerName) {
		Player target = null;
		try {
			target = PlayerUtils.getOnlinePlayerFromString(playerName);
		} catch (final Exception e) {
			ErrorUtils.playerNotFound(player, playerName);
		}
		return target;
	}
	
}
