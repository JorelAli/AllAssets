package io.github.Skepter.Commands;

import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Config.ConfigHandler;
import io.github.Skepter.Utils.ErrorUtils;
import io.github.Skepter.Utils.TextUtils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

public class CommandSignEdit {

	public CommandSignEdit(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@SuppressWarnings("deprecation")
	@CommandHandler(name = "signedit", aliases = { "se" }, permission = "AllInOne.signedit", description = "Allows you to edit signs", usage = "Use <command>")
	public void command(final CommandArgs args) {
		final Player player = args.getPlayer();
		if (args.getArgs().length > 1) {
			if (TextUtils.isInteger(args.getArgs()[0])) {
				if (player.getTargetBlock(null, 256).getType().equals(Material.SIGN_POST) || player.getTargetBlock(null, 256).getType().equals(Material.WALL_SIGN)) {
					final Sign sign = (Sign) player.getTargetBlock(null, 256).getState();
					final String s = TextUtils.join(TextUtils.getMsgFromArgs(args.getArgs(), 1, args.getArgs().length), " ");
					sign.setLine(Integer.valueOf(args.getArgs()[0]) - 1, ConfigHandler.features().getBoolean("ChatColor") ? ChatColor.translateAlternateColorCodes('&', s.substring(0, s.length() - 1)) : s.substring(0, s.length() - 1));
					sign.update();

				}
			}
		} else if (TextUtils.isInteger(args.getArgs()[0])) {
			if (player.getTargetBlock(null, 256).getType().equals(Material.SIGN_POST) || player.getTargetBlock(null, 256).getType().equals(Material.WALL_SIGN)) {
				final Sign sign = (Sign) player.getTargetBlock(null, 256).getState();
				sign.setLine(Integer.valueOf(args.getArgs()[0]) - 1, "");
				sign.update();
			}
		} else {
			ErrorUtils.wrongConstruction(player, "/signedit <line number> <text>");
		}
	}
}
