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
package io.github.skepter.allassets.commands.fun;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.tasks.DiscoArmorTask;
import io.github.skepter.allassets.utils.DoubleMap;
import io.github.skepter.allassets.utils.Strings;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

public class CommandDiscoArmor {

	private static DoubleMap<UUID, Integer, ItemStack[]> map = new DoubleMap<UUID, Integer, ItemStack[]>();

	public CommandDiscoArmor(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "discoarmor", aliases = { "darmor", "partyarmor", "parmor" }, permission = "discoarmor", description = "Gives you flashing armor")
	public void onCommand(final CommandArgs args) {
		Player player = PlayerGetter.getPlayer(args);
		if (player != null)
			toggleArmor(player);
		return;
	}

	/** Checks if the player has disco armor enabled */
	public static boolean hasArmor(Player player) {
		return map.containsKey(player.getUniqueId());
	}

	public static void toggleArmor(Player player) {
		if (map.containsKey(player.getUniqueId())) {
			Bukkit.getScheduler().cancelTask((int) map.getValue1(player.getUniqueId()));
			player.getInventory().setArmorContents((ItemStack[]) map.getValue2(player.getUniqueId()));
			map.remove(player.getUniqueId());
			player.sendMessage(Strings.TITLE + "Your disco armor was removed");
		} else {
			final BukkitTask i = Bukkit.getScheduler().runTaskTimer(AllAssets.instance(), new DiscoArmorTask(player), 0L, 3L);
			map.put(player.getUniqueId(), i.getTaskId(), player.getInventory().getArmorContents());
			player.sendMessage(Strings.TITLE + "You are now wearing disco armor!");
		}
	}

}
