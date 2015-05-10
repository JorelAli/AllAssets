/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and Tundra
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and Tundra
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.commands.administration;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.config.ConfigHandler;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.PlayerUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

public class CommandSignEdit {

	public CommandSignEdit(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "signedit", aliases = { "se" }, permission = "signedit", description = "Allows you to edit signs")
	public void command(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null) 
			if (args.getArgs().length > 1) {
				if (TextUtils.isInteger(args.getArgs()[0]))
					if (PlayerUtils.getTargetBlock(player).getType().equals(Material.SIGN_POST) || PlayerUtils.getTargetBlock(player).getType().equals(Material.WALL_SIGN)) {
						final Sign sign = (Sign) PlayerUtils.getTargetBlock(player).getState();
						final String s = TextUtils.join(TextUtils.getMsgFromArgs(args.getArgs(), 1, args.getArgs().length), " ");
						sign.setLine(Integer.valueOf(args.getArgs()[0]) - 1, ConfigHandler.features().getBoolean("ChatColor") ? ChatColor.translateAlternateColorCodes('&', s.substring(0, s.length() - 1)) : s.substring(0, s.length() - 1));
						sign.update();

					}
			} else if (TextUtils.isInteger(args.getArgs()[0])) {
				if (PlayerUtils.getTargetBlock(player).getType().equals(Material.SIGN_POST) || PlayerUtils.getTargetBlock(player).getType().equals(Material.WALL_SIGN)) {
					final Sign sign = (Sign) PlayerUtils.getTargetBlock(player).getState();
					sign.setLine(Integer.valueOf(args.getArgs()[0]) - 1, "");
					sign.update();
				}
			} else
				ErrorUtils.wrongConstruction(player, "/signedit <line number> <text>");
	}
}
