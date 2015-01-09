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
package io.github.Skepter.AllAssets.Commands;

import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.IDReader;
import io.github.Skepter.AllAssets.Utils.PlayerUtils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandGive {

	public CommandGive(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@SuppressWarnings("deprecation")
	@CommandHandler(name = "give", aliases = { "i", "item" }, permission = "give", description = "Gives items", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		//give <player> <item> <amount>
		switch(args.getArgs().length) {
		case 1:
		case 2:
			return;
		case 3:
			try {
				Player player = PlayerUtils.getOnlinePlayerFromString(args.getArgs()[0]);
				player.getInventory().addItem(new ItemStack(Material.getMaterial(Integer.parseInt(IDReader.readID(args.getArgs()[1].split(":")[0]))), Integer.parseInt(args.getArgs()[2])));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
