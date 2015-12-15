/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter 
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter 
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
package io.github.skepter.allassets.commands.other;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandHelpop {

	public CommandHelpop(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "helpop", permission = "helpop", description = "Sends a message to staff")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			for(Player p : Bukkit.getOnlinePlayers())
				if(p.hasPermission("AllAssets.helpop.receive"))
					p.sendMessage(Strings.customTitle("HelpOp - " + player.getName()) + TextUtils.getMsgStringFromArgs(args.getArgs(), 0, args.getArgs().length));
			player.sendMessage(Strings.TITLE + "Helpop message sent!");
		}
		return;
	}
}