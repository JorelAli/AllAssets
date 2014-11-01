/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Commands;

import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;

import org.bukkit.entity.Player;

public class CommandWorkbench {

	public CommandWorkbench(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "workbench", aliases = { "wb" }, permission = "workbench", description = "Shows your ping", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		player.openWorkbench(null, true);
		return;
	}
}
