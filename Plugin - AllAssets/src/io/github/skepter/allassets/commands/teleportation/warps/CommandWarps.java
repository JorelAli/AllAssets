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
package io.github.skepter.allassets.commands.teleportation.warps;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.api.Paginator;
import io.github.skepter.allassets.config.ConfigHandler;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils.SeperatorType;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class CommandWarps {

	public CommandWarps(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "warps", aliases = { "warplist", "warpslist" }, permission = "warps", description = "Shows the list of warps")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			List<String> warps = new ArrayList<String>();
			for (String key : ConfigHandler.warps().getKeys()) {
				String name = ConfigHandler.warps().getString(key + ".name");
				String description = ConfigHandler.warps().getString(key + ".description");
				if (description == null)
					description = "No description available";
				warps.add(Strings.HOUSE_STYLE_COLOR + name + Strings.ACCENT_COLOR + SeperatorType.DASH.getString() + Strings.HOUSE_STYLE_COLOR + description);
			}
			if (warps.isEmpty()) {
				player.sendMessage(Strings.TITLE + "There are no warps available");
				return;
			}

			Paginator paginator = new Paginator(warps, 10);
			switch (args.getArgs().length) {
				case 0:
					paginator.send(player, 1);
					return;
				case 1:
					if (TextUtils.isInteger(args.getArgs()[0]))
						paginator.send(player, Integer.parseInt(args.getArgs()[0]));
					else
						ErrorUtils.notAnInteger(player);
					return;
			}
		}
		return;
	}
}
