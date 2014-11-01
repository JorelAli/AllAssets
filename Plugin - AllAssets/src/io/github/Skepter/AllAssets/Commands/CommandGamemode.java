/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Commands;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.TextUtils;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class CommandGamemode {

	public CommandGamemode(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "gamemode", aliases = { "gm" }, permission = "gamemode", description = "Changes your gamemode", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		if (args.getArgs().length == 0)
			doCycleGameMode(player);
		if (args.getArgs().length == 1)
			doGameMode(player, args.getArgs()[0]);
		return;
	}

	private void doCycleGameMode(Player player) {
		switch(player.getGameMode()) {
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

	@CommandHandler(name = "gms", permission = "gamemode", description = "Changes your gamemode to survival", usage = "Use <command>", isListed = false)
	public void onGms(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		doGameMode(player, "survival");
	}

	@CommandHandler(name = "gmc", permission = "gamemode", description = "Changes your gamemode to creative", usage = "Use <command>", isListed = false)
	public void onGmc(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		doGameMode(player, "creative");
	}

	@CommandHandler(name = "gma", permission = "gamemode", description = "Changes your gamemode to adventure", usage = "Use <command>", isListed = false)
	public void onGma(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		doGameMode(player, "adventure");
	}

	private void doGameMode(final Player player, final String s) {
		player.setGameMode(parseGameMode(s));
		player.sendMessage(AllAssets.title + "Changed gamemode to " + TextUtils.capitalize(parseGameMode(s).name().toLowerCase()));
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
