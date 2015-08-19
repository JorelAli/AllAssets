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
package io.github.skepter.allassets.commands.teleportation.warps;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.config.ConfigHandler;
import io.github.skepter.allassets.serializers.LocationSerializer;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils.Seperator;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CommandNearbyWarps {

	public CommandNearbyWarps(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "nearbywarps", aliases = { "nearwarps", "nearbywarp", "nearwarp" }, permission = "nearbywarps", description = "Shows a list of nearby warps")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			boolean found = false;
			List<String> nearbyWarps = new ArrayList<String>();
			for (String key : ConfigHandler.warps().getKeys()) {
				String name = ConfigHandler.warps().getString(key + ".name");
				String locationString = ConfigHandler.warps().getString(key + ".loc");
				Location location = LocationSerializer.locFromString(locationString);
				double cachedDistance = player.getLocation().distance(location);
				int distance = (int) Math.round(cachedDistance);
				if (distance < 256) {
					nearbyWarps.add(Strings.HOUSE_STYLE_COLOR + name + Strings.ACCENT_COLOR + Seperator.COLON.toString() + distance + " blocks away");
					found = true;
				}
			}
			if (!found)
				ErrorUtils.noNearbyWarps(player);
			else {
				player.sendMessage(TextUtils.title("Nearby warps"));
				for (String str : nearbyWarps)
					player.sendMessage(str);
			}

		}
		return;
	}
}
