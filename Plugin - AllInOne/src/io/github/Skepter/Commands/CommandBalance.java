package io.github.Skepter.Commands;

import io.github.Skepter.AllInOne;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Utils.ErrorUtils;

import org.bukkit.entity.Player;

public class CommandBalance {

	public CommandBalance(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "balance", aliases = { "bal" }, permission = "AllInOne.balance", description = "Displays your balance", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		final Player player = args.getPlayer();
		if (AllInOne.instance().hasVault) {
			player.sendMessage(AllInOne.instance().title + "Balance: " + AllInOne.instance().economy.getBalance(player.getName()));
		} else {
			ErrorUtils.pluginNotFound(player, "Vault");
		}
	}
}
