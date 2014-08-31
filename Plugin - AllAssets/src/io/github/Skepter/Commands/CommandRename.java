package io.github.Skepter.Commands;

import io.github.Skepter.AllAssets;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Utils.ErrorUtils;
import io.github.Skepter.Utils.ItemUtils;
import io.github.Skepter.Utils.TextUtils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommandRename {

	public CommandRename(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "rename", aliases = { "rn" }, permission = "rename", description = "Renames the current item in your hand", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		ItemUtils.setDisplayName(player.getItemInHand(), ChatColor.translateAlternateColorCodes('&', TextUtils.getMsgStringFromArgs(args.getArgs(), 0, args.getArgs().length)));
		player.sendMessage(AllAssets.instance().title + "Renamed item to " + ChatColor.translateAlternateColorCodes('&', TextUtils.getMsgStringFromArgs(args.getArgs(), 0, args.getArgs().length)));
		return;
	}

}
