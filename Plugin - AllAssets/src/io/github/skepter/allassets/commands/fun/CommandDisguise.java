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
package io.github.skepter.allassets.commands.fun;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.CommandFramework.Completer;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.libs.DisguiseLib;
import io.github.skepter.allassets.libs.EntityDisguise;
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils.SeperatorType;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDisguise {

	public CommandDisguise(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "disguise", permission = "disguise", description = "Disguise as a mob")
	public void onCommand(final CommandArgs args) {
		Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			switch (args.getArgs().length) {
				case 0:
					printHelp(player);
					return;
				case 1: {
					if (exists(args.getArgs()[0])) {
						DisguiseLib d = new DisguiseLib(player, EntityDisguise.valueOf(args.getArgs()[0].toUpperCase()));
						d.setItemInHand(player.getItemInHand());
						d.setHelmet(player.getInventory().getHelmet());
						d.setChestplate(player.getInventory().getChestplate());
						d.setLeggings(player.getInventory().getLeggings());
						d.setBoots(player.getInventory().getBoots());
						d.sendDisguise(Bukkit.getOnlinePlayers());
						player.sendMessage(Strings.TITLE + "You have been disguised as a " + TextUtils.capitalize(d.getType().name().toLowerCase()));
					} else
						ErrorUtils.inexistantMob(player);
					return;
				}
				case 2: {
					if (exists(args.getArgs()[0])) {
						DisguiseLib d = new DisguiseLib(player, EntityDisguise.valueOf(args.getArgs()[0].toUpperCase()), args.getArgs()[0]);
						d.setItemInHand(player.getItemInHand());
						d.setHelmet(player.getInventory().getHelmet());
						d.setChestplate(player.getInventory().getChestplate());
						d.setLeggings(player.getInventory().getLeggings());
						d.setBoots(player.getInventory().getBoots());
						d.sendDisguise(Bukkit.getOnlinePlayers());
						player.sendMessage(Strings.TITLE + "You have been disguised as a " + TextUtils.capitalize(d.getType().name().toLowerCase()));
					} else
						ErrorUtils.inexistantMob(player);
					return;
				}
			}
		}
		return;
	}

	private boolean exists(String mob) {
		for (EntityDisguise g : EntityDisguise.values()) {
			if (g.name().equalsIgnoreCase(mob))
				return true;
		}
		return false;
	}

	@Completer(name = "disguise")
	public List<String> testCompleter(final CommandArgs args) {
		final List<String> list = new ArrayList<String>();
		for (EntityDisguise g : EntityDisguise.values()) {
			list.add(TextUtils.capitalize(g.name().toLowerCase()));
		}
		return list;
	}

	@Help(name = "Disguise")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "Disguise", "/disguise <mob> - disguise as a mob");
		TextUtils.printHelp(sender, "Disguise", "/disguise <mob> <name> - disguise as a mob with <name> as your name above your head");
		String disguises = "";
		for (final EntityDisguise g : EntityDisguise.values()) {
			disguises = disguises + TextUtils.capitalize(g.name().toLowerCase()) + ", ";
		}
		if (disguises.length() != 0)
			disguises = disguises.substring(0, disguises.length() - 2);
		TextUtils.printInformation(sender, "Disguises", SeperatorType.COLON, disguises);

	}
}
