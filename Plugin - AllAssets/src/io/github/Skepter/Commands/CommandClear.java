package io.github.Skepter.Commands;

import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Config.ConfigHandler;
import io.github.Skepter.Utils.ErrorUtils;
import io.github.Skepter.Utils.PlayerUtils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandClear {

	public CommandClear(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "clear", aliases = { "c", "ci" }, permission = "clear", description = "Clears your inventory", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		if (ConfigHandler.instance().config().getBoolean("clearArmor")) {
			if (args.getArgs().length == 0)
				player.getInventory().clear();
			else if (args.getArgs().length == 1) {
				final Player target = PlayerUtils.getPlayerFromString(args.getArgs()[0]);
				target.getInventory().clear();
			}
		} else if (args.getArgs().length == 0) {
			final ItemStack[] temp = player.getInventory().getArmorContents();
			player.getInventory().clear();
			player.getInventory().setArmorContents(temp);
		} else if (args.getArgs().length == 1) {
			final Player target = PlayerUtils.getPlayerFromString(args.getArgs()[0]);
			final ItemStack[] temp = target.getInventory().getArmorContents();
			target.getInventory().clear();
			target.getInventory().setArmorContents(temp);
		}
		return;
	}
}
