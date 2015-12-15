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
import io.github.skepter.allassets.api.CustomConfig;
import io.github.skepter.allassets.utils.Files;
import io.github.skepter.allassets.utils.Files.Directory;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import java.io.File;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommandRules {

	public CommandRules(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "rules", permission = "rules", description = "Displays the server rules")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			player.sendMessage(TextUtils.title("Rules"));
			for (final String string : new Rules().getDataFile().getStringList("rules"))
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', string));
		}
		return;
	}

	/* Add Rules remove */

	/* By having the name of the command followed by a . and then another word, it can
	 * act as a new command 'argument'. For example, if the user types /rules add, it would
	 * execute this code. The arguments start from 0 again, so
	 * /rules add hi -> args.getArgs()[0] == hi*/
	@CommandHandler(name = "rules.add", permission = "rules.add", description = "Adds a new rule")
	public void onAdd(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			final Rules rules = new Rules();
			final List<String> currentRules = rules.getDataFile().getStringList("rules");
			currentRules.add(TextUtils.getMsgStringFromArgs(args.getArgs(), 0, args.getArgs().length));
			rules.getDataFile().set("rules", currentRules);
		}
		return;
	}

	/* Uses custom configuration to store rules. It's in this class
	 * since this is the only class where rules will be used. It's a private
	 * class since it's only used in this class. */
	private class Rules extends CustomConfig {
		public Rules() {
			super(new File(Files.getDirectory(Directory.STORAGE), "rules.yml"), "rules");
		}
	}
}
