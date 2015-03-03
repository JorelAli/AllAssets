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

package io.github.skepter.allassets.commands;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.api.CustomConfig;
import io.github.skepter.allassets.utils.Files;
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
		Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			player.sendMessage(TextUtils.title("Rules"));
			for (String string : new Rules().getDataFile().getStringList("rules"))
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
		Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			Rules rules = new Rules();
			List<String> currentRules = rules.getDataFile().getStringList("rules");
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
			super(new File(Files.getStorage(), "rules.yml"), "rules");
		}
	}
}
