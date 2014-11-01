/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Commands;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.API.User;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;

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
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		final User user = new User(player);
		user.getPlayer().sendMessage(AllAssets.title + "Your ping is " + user.getPing() + "ms");
		return;
	}
}
