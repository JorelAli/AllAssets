/*******************************************************************************
 * Skepter's licence
 * Copyright 2014 
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
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. I've just made life easier for you :)
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

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.TextUtils;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/** Batch command - designed to run a specific command multiple times.
 * Technically a 'for loop emulator' for Minecraft, but designed to be more
 * advanced
 * 
 * @author Skepter */
public class CommandBatch {

	public CommandBatch(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	//put a delay between each execution as a variable

	//how to do it:
	//FIRSTLY, analyse the code, see what it is GOING to do FIRST
	//then do the adjustments (so on the 3rd one put the value in etc.)
	/*
	 * Change args from [i=n:x] to:
	 * [i=<VALUE>] = time to be run at (what run number)
	 * e.g. /batch 5 /say hi [i]
	 * 			returns hi 1, hi 2, hi 3, hi 4, hi 5
	 * 		/batch 5 /say hi [i=3]
	 * 			returns hi, hi, hi 3, hi 4, hi 5
	 * 		/batch 5 /say hi [i=$3]
	 * 			returns hi, hi, hi 3, hi, hi 
	 * 
	 * [r=<VALUE>] = reverse of i, only works in form of [r=INTEGER]
	 * e.g. /batch 5 /say hi [r=3]
	 * 			returns hi 1, hi 2, hi 3, hi, hi
	 * 
	 * [s=<VALUE>] = delay between each execution - only supports INTEGERS
	 * Used as an argument value:
	 * e.g. /batch 5 [s=5] /say hi
	 * will run /say hi every 5 seconds
	 * 
	 * Change s to m or ms (does NOT support h)
	 * e.g. /batch 5 [ms=500] /say hi
	 * will run /say hi every 500 milliseconds (half a second)
	 */

	@CommandHandler(name = "batch", permission = "batch", description = "Run a command multiple times", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		if (args.getArgs().length == 0) {
			player.sendMessage("[i] = the number in which the time is being run at");
			player.sendMessage("[i=NUMBER] = the number to start from");
			player.sendMessage("[r=NUMBER] = the number to start from, in reverse");
			player.sendMessage("[ms/s/m=NUMBER] = the delay");
			return;
			// massive tut
		}
		int amount = 1;
		try {
			amount = Integer.parseInt(args.getArgs()[0]);
		} catch (final NumberFormatException e) {
			ErrorUtils.notAnInteger(player);
			return;
		}
		if (amount > 500) {
			ErrorUtils.error(player, "Amount cannot be larger than 500!");
			return;
		}
		//batch 5 /say hi
		final String s = TextUtils.join(TextUtils.getMsgFromArgs(args.getArgs(), 1, args.getArgs().length), " ");
		List<String> iVariables = TextUtils.multipleStringBetween(s, "[i=", "]");
		List<String> rVariables = TextUtils.multipleStringBetween(s, "[r=", "]");
		for (int i = 1; i <= amount; i++) {
			String cache = s;
			for (String variable : iVariables) {
				if(Integer.parseInt(variable) >= i) {
					cache.replace("[i=" + variable + "]", String.valueOf(i));
				}
			}
			for (String variable : rVariables) {
				if(Integer.parseInt(variable) <= i) {
					cache.replace("[r=" + variable + "]", String.valueOf(i));
				}
			}
			cache.replace("[i]", String.valueOf(i));
			player.performCommand(cache);
			//player.performCommand(s.replace("[i=" + str + "]", String.valueOf(((i - 1) * increment) + beginInt)));

		}
//		if (s.contains("[ms=") || s.contains("[s=") || s.contains("[m=")) {
//			//delay
//			new BukkitRunnable() {
//				public void run() {
//					//shoot - i and r uses for loops!
//				}
//			}.runTaskTimer(AllAssets.instance(), /*delay comes here*/0, /*do here the thingy erm amount?*/0);
//		}
	}
}
