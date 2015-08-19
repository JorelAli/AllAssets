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
import io.github.skepter.allassets.utils.utilclasses.PlayerUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

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
			if (PlayerUtils.isOnlineExact(op[0]))
				args.getSender().sendMessage(ChatColor.GREEN + "[Online] " + ChatColor.WHITE + op[0] + " (" + op[1] + ")");
			else
				args.getSender().sendMessage(ChatColor.RED + "[Offline] " + ChatColor.WHITE + (op[0] == null ? "Couldn't find username" : op[0]) + " (" + op[1] + ")");
		}

	}

}
