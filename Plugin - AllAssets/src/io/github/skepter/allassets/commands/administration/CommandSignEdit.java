/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
 *
 * AllAssets was created by Skepter (http://skepter.github.io/).
 *
 * You are able to:
 * * View AllAsset's source code on github
 * * Experiment with the code to your wish
 * * Download it to use on your server
 *
 * You are NOT able to:
 * * Sell AllAssets - it is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter
 * * Distribute it on any other website. The ONLY places where it can be downloaded from is github and the Bukkit repository
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. The only exception is the BukkitDev staff who I have allowed permission to decompile (to search for malicious code)
 *
 * You can not:
 * * Hold me liable for your actions:
 *     - If there is a bug in the program, you are allowed to notify me.
 *     - If other players abuse AllAssets, you are NOT allowed to hold me liable for that
 *     - If it was setup incorrectly or you used a non-official build, you can NOT hold me liable for that.
 * * Yell at me:
 *     - If something is wrong, please approach the situation calmly.
 * * Modify the code:
 *     - You are NOT allowed to 'steal' the whole code from Github, modify it, compile it yourself and distribute that.
 *
 * If you are to break from these implications, future use of this plugin will be forbidden.
 *******************************************************************************/
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
		Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
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
}
