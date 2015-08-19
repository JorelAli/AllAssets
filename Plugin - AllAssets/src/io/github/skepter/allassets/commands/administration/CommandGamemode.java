/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and MCSpartans
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and MCSpartans
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
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class CommandGamemode {

	public CommandGamemode(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "gamemode", aliases = { "gm" }, permission = "gamemode", description = "Changes your gamemode")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
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
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null)
			doGameMode(player, "survival");
	}

	@CommandHandler(name = "gmc", permission = "gamemode", description = "Changes your gamemode to creative", isListed = false)
	public void onGmc(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null)
			doGameMode(player, "creative");
	}

	@CommandHandler(name = "gma", permission = "gamemode", description = "Changes your gamemode to adventure", isListed = false)
	public void onGma(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
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
