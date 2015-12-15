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
/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.commands.other;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.CommandFramework.Completer;
import io.github.skepter.allassets.utils.DoubleMap;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils.Seperator;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.help.HelpTopic;
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
		switch (args.getArgs().length) {
			case 0:
				args.getSender().sendMessage(TextUtils.title("Help"));
				for (final Plugin plugin : Bukkit.getPluginManager().getPlugins())
					TextUtils.printInformation(args.getSender(), plugin.getName(), Seperator.COLON, "Show information about " + plugin.getName());
				break;
			case 1:
				//check plugin info
				for (final Plugin plugin : Bukkit.getPluginManager().getPlugins())
					if (args.getArgs()[0].toLowerCase().equals(plugin.getName().toLowerCase())) {
						args.getSender().sendMessage(TextUtils.title(plugin.getName()));
						String authors = "";
						for (final String s : plugin.getDescription().getAuthors())
							authors = authors + s + ", ";
						if (authors.length() != 0)
							authors = authors.substring(0, authors.length() - 2);
						else
							authors = "undefined";
						
						// TODO: Add if description is empty
						TextUtils.printInformation(args.getSender(), "Authors", Seperator.COLON, authors);
						TextUtils.printInformation(args.getSender(), "Version", Seperator.COLON, plugin.getDescription().getVersion());
						TextUtils.printInformation(args.getSender(), "Description", Seperator.COLON, plugin.getDescription().getDescription());
						return;
					}

				//check @Help annotations
				final String lookup = args.getArgs()[0].toLowerCase();
				for (final Object key : map.keySet())
					if (String.valueOf(key).equals(lookup))
						try {
							map.getValue1(lookup).invoke(map.getValue2(lookup), args.getSender());
							return;
						} catch (final Exception e) {
							e.printStackTrace();
						}

				//check available helptopics
				for (final HelpTopic topic : Bukkit.getHelpMap().getHelpTopics())
					if (topic.getName().equalsIgnoreCase(args.getArgs()[0])) {
						args.getSender().sendMessage(topic.getFullText(args.getSender()));
						return;
					}

				ErrorUtils.cannotFindHelpTopic(args.getSender());
				break;
		}

		return;
	}

	@Completer(name = "help")
	public List<String> onComplete(final CommandArgs args) {
		final List<String> helpTopics = new ArrayList<String>();
		for (final Entry<String, List<Object>> entry : map.entrySet())
			helpTopics.add(String.valueOf(entry.getKey()));
		return helpTopics;

	}

}
