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
import io.github.Skepter.AllAssets.Utils.PlayerUtils;
import io.github.Skepter.AllAssets.Utils.TextUtils;

import java.util.ArrayList;
import java.util.Collections;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;

public class CommandOplist {

	public CommandOplist(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "oplist", aliases = { "ops" }, permission = "oplist", description = "Lists the players that have op")
	public void onCommand(final CommandArgs args) {
		final ArrayList<String> operators = new ArrayList<String>();
		for (final OfflinePlayer s : Bukkit.getOperators())
			operators.add(s.getName() + ":" + s.getUniqueId().toString());
		Collections.sort(operators);
		if (!operators.isEmpty())
			args.getSender().sendMessage(TextUtils.title("Operators"));
		for (final String operator : operators) {
			final String[] op = operator.split(":");
			if (PlayerUtils.isOnline(op[0]))
				args.getSender().sendMessage(ChatColor.GREEN + "[Online] " + ChatColor.WHITE + op[0] + " (" + op[1] + ")");
			else
				args.getSender().sendMessage(ChatColor.RED + "[Offline] " + ChatColor.WHITE + (op[0] == null ? "Couldn't find username" : op[0]) + " (" + op[1] + ")");
		}

	}

}
