/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Commands;

import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.PlayerUtils;

import org.bukkit.entity.Player;

public class CommandInventory {

	public CommandInventory(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "inventory", aliases = { "invsee", "inv" }, permission = "inventory", description = "Views a players inventory", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		if (args.getArgs().length == 1)
			try {
				player.openInventory(PlayerUtils.getOnlinePlayerFromString(args.getArgs()[0]).getInventory());
			} catch (final Exception e) {
				ErrorUtils.playerNotFound(player, args.getArgs()[0]);
			}
		return;
		//TODO editable system so it can/cannot be edited.
		//set inv name to <playername>'s Inventory
	}
}
