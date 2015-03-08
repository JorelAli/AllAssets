/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
 *
 * AllAssets was created by Skepter and Tundra (http://skepter.github.io/).
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
package io.github.skepter.allassets.commands;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.CommandFramework.Completer;
import io.github.skepter.allassets.utils.DoubleMap;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class CommandHelp {

	public CommandHelp(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	static DoubleMap<String, Method, Object> map = new DoubleMap<String, Method, Object>();

	public static void register(final String commandName, final Method method, final Object obj) {
		map.put(commandName.toLowerCase(), method, obj);
	}

	@CommandHandler(name = "help", permission = "help", description = "Shows help for a command")
	public void onCommand(final CommandArgs args) {
		for (final Plugin plugin : Bukkit.getPluginManager().getPlugins())
			if (args.getArgs()[0].equalsIgnoreCase(plugin.getName()))
				//parse plugin data
				return;

		final String lookup = args.getArgs()[0].toLowerCase();
		for (final Object key : map.keySet())
			if (String.valueOf(key).equals(lookup))
				try {
					((Method) map.get(lookup, 1)).invoke(map.get(lookup, 2), args.getSender());
					return;
				} catch (final Exception e) {
					e.printStackTrace();
				}

		ErrorUtils.error(args.getSender(), "Could not find that!");
		return;
	}
	
	@Completer(name="help")
	public List<String> onComplete(final CommandArgs args) {
		List<String> helpTopics = new ArrayList<String>();
		for(Entry<Object, List<Object>> entry : map.entrySet()) {
			helpTopics.add(String.valueOf(entry.getKey()));
		}
		return helpTopics;
		
	}

}
