package io.github.Skepter.AllAssets.Commands;

import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;

import org.bukkit.entity.Player;

public class CommandMore {

	public CommandMore(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "more", permission = "more", description = "Sets your itemstack size to 64", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		player.getItemInHand().setAmount(64);
		return;
	}

}
