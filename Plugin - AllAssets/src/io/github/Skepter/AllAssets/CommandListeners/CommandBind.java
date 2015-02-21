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
/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.CommandListeners;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Config.ConfigHandler;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.ItemUtils;
import io.github.Skepter.AllAssets.Utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandBind implements Listener {

	public CommandBind(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "bind", permission = "bind", description = "Binds a command to an item")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		player.sendMessage(TextUtils.title("Bind help"));
		player.sendMessage(AllAssets.HOUSE_STYLE_COLOR + "/bind add <command> - adds a command to the binded item");
		player.sendMessage(AllAssets.HOUSE_STYLE_COLOR + "/bind remove <number> - removes a command to the binded item");
		return;
	}

	@CommandHandler(name = "bind.add", permission = "bind", description = "Adds a command to the binded item")
	public void addBind(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		final ItemStack item = player.getItemInHand();
		final ItemMeta meta = item.getItemMeta();
		if (!meta.hasLore())
			meta.setLore(new ArrayList<String>());
		final List<String> lore = meta.getLore();
		final String[] command = TextUtils.getMsgFromArgs(args.getArgs(), 0, args.getArgs().length);
		final String s = "/" + TextUtils.join(command, " ");
		lore.add(s);
		meta.setLore(lore);
		item.setItemMeta(meta);
		try {
			ItemUtils.addGlow(item);
		} catch (final Exception e) {
		}
		player.sendMessage(AllAssets.TITLE + "Successfully added " + s + "to your item!");
		return;
	}

	@CommandHandler(name = "bind.remove", permission = "bind", description = "Removes a command to the binded item")
	public void removeBind(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		final ItemStack item = player.getItemInHand();
		final ItemMeta meta = item.getItemMeta();
		final List<String> lore = meta.getLore();
		if (!TextUtils.isInteger(args.getArgs()[0])) {
			ErrorUtils.notAnInteger(player);
			return;
		}
		final String s = lore.get(Integer.parseInt(args.getArgs()[0]) - 1);
		lore.remove((Integer.parseInt(args.getArgs()[0]) - 1));
		meta.setLore(lore);
		item.setItemMeta(meta);
		if (!containsCommand(item))
			ItemUtils.removeGlow(item);
		player.sendMessage(AllAssets.TITLE + "Successfully removed " + s + "from your item!");
		return;
	}

	@EventHandler
	public void playerInteract(final PlayerInteractEvent event) {
		if (ConfigHandler.config().getBoolean("bindRight"))
			if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
				performAction(event.getPlayer());
			else if (event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
				performAction(event.getPlayer());
	}

	private boolean containsCommand(final ItemStack itemStack) {
		if (itemStack.hasItemMeta())
			if (itemStack.getItemMeta().hasLore())
				for (final String s : itemStack.getItemMeta().getLore())
					if (s.startsWith("/"))
						return true;
		return false;
	}

	private void performAction(final Player player) {
		if (player.getItemInHand().hasItemMeta())
			if (player.getItemInHand().getItemMeta().hasLore())
				for (final String s : player.getItemInHand().getItemMeta().getLore())
					if (s.startsWith("/"))
						player.performCommand(s.substring(1));
	}
}
