package io.github.Skepter.Commands;

import io.github.Skepter.AllAssets;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Reflection.ReflectionUtils;
import io.github.Skepter.Utils.ErrorUtils;

import org.bukkit.entity.Player;

public class CommandNMSGod {

	public CommandNMSGod(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "god", aliases = { "invincible", "invunerable" }, permission = "god", description = "Makes you invincible", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		try {
			new ReflectionUtils(args.getPlayer());
			ReflectionUtils utils = new ReflectionUtils(args.getPlayer());
			if (utils.abilities.getClass().getField("isInvulnerable").getBoolean(utils.abilities)) {
				utils.abilities.getClass().getField("isInvulnerable").setBoolean(utils.abilities, false);
				player.sendMessage(AllAssets.title + "You suddenly feel much more vunerable");
			} else {
				utils.abilities.getClass().getField("isInvulnerable").setBoolean(utils.abilities, true);
				player.sendMessage(AllAssets.title + "A higher power falls upon you");
			}
		} catch (final Exception t) {
			ErrorUtils.generalCommandError(player);
		}
		return;
	}

}
