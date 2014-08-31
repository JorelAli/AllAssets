package io.github.Skepter.Commands;

import io.github.Skepter.AllAssets;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Utils.ErrorUtils;
import io.github.Skepter.Utils.TextUtils;

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
		if (args.getArgs().length == 1)
			doGameMode(player, args.getArgs()[0]);
		return;
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
		player.sendMessage(AllAssets.instance().title + "Changed gamemode to " + TextUtils.capitalize(parseGameMode(s).name().toLowerCase()));
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
