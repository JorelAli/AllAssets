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
package io.github.Skepter.AllAssets.Commands.Administration;

import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.PlayerGetter;
import io.github.Skepter.AllAssets.Utils.Strings;
import io.github.Skepter.AllAssets.Utils.UtilClasses.TextUtils;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class CommandGamemode {

	public CommandGamemode(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "gamemode", aliases = { "gm" }, permission = "gamemode", description = "Changes your gamemode")
	public void onCommand(final CommandArgs args) {
		Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			if (args.getArgs().length == 0)
				doCycleGameMode(player);
			if (args.getArgs().length == 1)
				doGameMode(player, args.getArgs()[0]);
		}
	}

	private void doCycleGameMode(final Player player) {
		switch (player.getGameMode()) {
		case SURVIVAL:
			doGameMode(player, "creative");
			break;
		case ADVENTURE:
			doGameMode(player, "survival");
			break;
		case CREATIVE:
			doGameMode(player, "adventure");
			break;
		default:
			doGameMode(player, "survival");
			break;
		}

	}

	@CommandHandler(name = "gms", permission = "gamemode", description = "Changes your gamemode to survival", isListed = false)
	public void onGms(final CommandArgs args) {
		Player player = PlayerGetter.getPlayer(args);
		if (player != null)
			doGameMode(player, "survival");
	}

	@CommandHandler(name = "gmc", permission = "gamemode", description = "Changes your gamemode to creative", isListed = false)
	public void onGmc(final CommandArgs args) {
		Player player = PlayerGetter.getPlayer(args);
		if (player != null)
			doGameMode(player, "creative");
	}

	@CommandHandler(name = "gma", permission = "gamemode", description = "Changes your gamemode to adventure", isListed = false)
	public void onGma(final CommandArgs args) {
		Player player = PlayerGetter.getPlayer(args);
		if (player != null)
			doGameMode(player, "adventure");
	}

	private void doGameMode(final Player player, final String s) {
		player.setGameMode(parseGameMode(s));
		player.sendMessage(Strings.TITLE + "Changed gamemode to " + TextUtils.capitalize(parseGameMode(s).name().toLowerCase()));
	}

	private GameMode parseGameMode(final String s) {
		if (TextUtils.isInteger(s)) {
			final int i = Integer.parseInt(s);
			switch (i) {
			case 0:
				return GameMode.SURVIVAL;
			case 1:
				return GameMode.CREATIVE;
			case 2:
				return GameMode.ADVENTURE;
			}
		} else {
			if (s.toLowerCase().startsWith("s"))
				return GameMode.SURVIVAL;
			if (s.toLowerCase().startsWith("c"))
				return GameMode.CREATIVE;
			if (s.toLowerCase().startsWith("a"))
				return GameMode.ADVENTURE;
		}
		return GameMode.SURVIVAL;
	}
}
