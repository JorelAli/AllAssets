package io.github.Skepter.Commands;

import io.github.Skepter.AllAssets;
import io.github.Skepter.API.User;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Utils.ErrorUtils;

import org.bukkit.entity.Player;

public class CommandPing {

	public CommandPing(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "ping", permission = "ping", description = "Shows your ping", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		final User user = new User(player);
		user.getPlayer().sendMessage(AllAssets.instance().title + "Your ping is " + user.getPing() + "ms");
		return;
	}
}
