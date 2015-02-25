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
package io.github.Skepter.AllAssets.Commands.Fun;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Tasks.DiscoArmorTask;
import io.github.Skepter.AllAssets.Utils.DoubleMap;
import io.github.Skepter.AllAssets.Utils.UtilClasses.ErrorUtils;

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
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		toggleArmor(player);
		return;
	}
	
	/** Checks if the player has disco armor enabled */
	public static boolean hasArmor(Player player) {
		return map.containsKey(player.getUniqueId());
	}
	
	public static void toggleArmor(Player player) {
		if (map.containsKey(player.getUniqueId())) {
			Bukkit.getScheduler().cancelTask((int) map.get(player.getUniqueId(), 1));
			player.getInventory().setArmorContents((ItemStack[]) map.get(player.getUniqueId(), 2));
			map.remove(player.getUniqueId());
			player.sendMessage(AllAssets.TITLE + "Your disco armor was removed");
		} else {
			final BukkitTask i = Bukkit.getScheduler().runTaskTimer(AllAssets.instance(), new DiscoArmorTask(player), 0L, 3L);
			map.put(player.getUniqueId(), i.getTaskId(), player.getInventory().getArmorContents());
			player.sendMessage(AllAssets.TITLE + "You are now wearing disco armor!");
		}
	}

}
