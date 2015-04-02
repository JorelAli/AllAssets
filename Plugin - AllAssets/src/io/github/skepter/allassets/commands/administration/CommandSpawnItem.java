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
package io.github.skepter.allassets.commands.administration;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.CommandFramework.Completer;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.api.builders.ItemBuilder;
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandSpawnItem {

	public CommandSpawnItem(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	public static Map<String, ItemStack> items = new HashMap<String, ItemStack>();

	@CommandHandler(name = "spawnitem", aliases = { "si" }, permission = "spawnitem", description = "Spawns a custom item")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null) {

			switch (args.getArgs().length) {
				case 0:
					printHelp(player);
					break;
				case 1:
					final ItemStack is = getItem(args.getArgs()[0]);
					if (is != null) {
						player.getInventory().addItem(is);
						player.sendMessage(Strings.TITLE + "Spawned in a " + new ItemBuilder(is).getDisplayName());
					} else
						ErrorUtils.itemNotFound(player);
					return;

			}
			return;
		}
	}

	private ItemStack getItem(final String arg) {
		for (final String string : items.keySet())
			if (string.equalsIgnoreCase(arg))
				return items.get(string);
		return null;
	}

	@CommandHandler(name = "spawnitem.list", permission = "spawnitem", description = "Shows a list of items to spawn")
	public void startAnnouncer(final CommandArgs args) {
		args.getSender().sendMessage(TextUtils.title("Items"));
		for (final Entry<String, ItemStack> entry : items.entrySet())
			args.getSender().sendMessage(Strings.ACCENT_COLOR + entry.getKey());
		return;
	}

	@Completer(name = "spawnitem")
	public List<String> onTab(final CommandArgs args) {
		final List<String> i = new ArrayList<String>();
		i.addAll(items.keySet());
		return i;
	}

	@Help(name = "Spawnitem")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "Spawnitem", "/spawnitem list - shows a list of items to spawn", "/spawnitem <itemName> - spawns a custom item");
	}

}
