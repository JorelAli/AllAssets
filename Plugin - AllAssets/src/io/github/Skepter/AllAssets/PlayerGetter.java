package io.github.Skepter.AllAssets;

import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.Utils.UtilClasses.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.UtilClasses.PlayerUtils;

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
