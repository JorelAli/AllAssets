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
