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
package io.github.skepter.allassets.commands.administration;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.api.Paginator;
import io.github.skepter.allassets.config.ConfigHandler;
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CommandAllAssets {

	public CommandAllAssets(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "allassets", aliases = { "aa" }, permission = "allassets", description = "Shows help & stuff")
	public void onCommand(final CommandArgs args) {
		printHelp(args.getSender());
		return;
	}

	@CommandHandler(name = "allassets.commands", aliases = { "aa.cmds", "allassets.cmds", "aa.commands" }, permission = "AllAssets.allassets", description = "Views plugin commands")
	public void commands(final CommandArgs args) {
		final List<String> commandList = new ArrayList<String>();
		for (final CommandHandler command : CommandFramework.pluginCommands)
			if (args.getSender().hasPermission(command.permission()))
				commandList.add(ChatColor.BLUE + " /" + command.name().toLowerCase().replace(".", " ") + ChatColor.WHITE + " - " + ChatColor.AQUA + command.description());
		int arg = 1;
		if (args.getArgs().length == 1)
			arg = Integer.parseInt(args.getArgs()[0]);
		Collections.sort(commandList);
		final Paginator p = new Paginator(commandList, 12);
		p.send(args.getSender(), arg);
		//TextUtils.paginate(args.getSender(), commandList, 12, arg);
		args.getSender().sendMessage(Strings.TITLE + "Use /allassets commands <page number> to go to the next page");
		return;
	}

	@CommandHandler(name = "allassets.reload", aliases = { "aa.reload" }, permission = "AllAssets.allassets", description = "Reloads entire plugin")
	public void reload(final CommandArgs args) {
		ConfigHandler.config().reloadConfig();
		ConfigHandler.features().reloadConfig();
		args.getSender().sendMessage(Strings.TITLE + "Configuration reloaded");
		//		args.getSender().sendMessage(AllAssets.title + "Reloading...");
		//		/* We're currently in dev and dev file name isn't the same as the released name */
		//		final File devPluginFile = new File(AllAssets.instance().getDataFolder().getParent() + File.separator + "AllAssets.jar");
		//		//		final File pluginFile = new File(AllAssets.instance().getDataFolder().getParent() + File.separator + "AllAssets-" + AllAssets.instance().getDescription().getVersion() + ".jar");
		//		final String cachedTitle = AllAssets.title;
		//		new Timer().schedule(new TimerTask() {
		//
		//			@Override
		//			public void run() {
		//				try {
		//					args.getSender().sendMessage(cachedTitle + "AllAssets successfully reloaded");
		//					Bukkit.getPluginManager().loadPlugin(devPluginFile);
		//					//Bukkit.getPluginManager().enablePlugin(AllAssets.instance());
		//				} catch (final UnknownDependencyException
		//						| InvalidPluginException | InvalidDescriptionException e) {
		//					e.printStackTrace();
		//				}
		//			}
		//
		//		}, 3000L);
		//		Bukkit.getPluginManager().disablePlugin(AllAssets.instance());
		//		return;
	}

	@Help(name = "AllAssets")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "AllAssets", "/allassets commands - shows a list of commands", "/allassets reload - reloads the entire plugin");
	}
}
