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
package io.github.skepter.allassets.commands.other;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandCalculate {

	public CommandCalculate(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "calculate", aliases = { "calc", "sum", "solve" }, permission = "calculate", description = "Calculates an expression")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null)
			if (args.getArgs().length == 0) {
				printHelp(player);
				return;
			} else {
				String msg = TextUtils.getMsgStringFromArgs(args.getArgs(), 0, args.getArgs().length);
				final ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
				String parsedOut = msg.replace("pi", "Math.PI").replace("e", "Math.E").replace("sqrt", "Math.sqrt").replace("root", "Math.sqrt").replace("pow", "Math.pow");
				try {
					String out = String.valueOf(engine.eval(parsedOut));
					player.sendMessage(Strings.HOUSE_STYLE_COLOR + msg.substring(5, msg.length()));
					player.sendMessage(Strings.ACCENT_COLOR + "= " + out);
				} catch (ScriptException e) {
					ErrorUtils.cannotCalculateExpression(player);
				}
			}
		return;
	}

	@Help(name = "Calculate")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "Calculate", "/calculate <expression> - Calculates an expression", "/calculate 2 + 3 - Calculates 2 + 3 (= 5)");
	}
}
