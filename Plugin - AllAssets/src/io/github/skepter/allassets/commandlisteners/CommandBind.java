/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and MCSpartans
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and MCSpartans
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
package io.github.skepter.allassets.commandlisteners;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.api.builders.ItemBuilder;
import io.github.skepter.allassets.config.ConfigHandler;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

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
		player.sendMessage(Strings.HOUSE_STYLE_COLOR + "/bind add <command> - adds a command to the binded item");
		player.sendMessage(Strings.HOUSE_STYLE_COLOR + "/bind remove <number> - removes a command to the binded item");
		return;
	}

	@SuppressWarnings("deprecation")
	@CommandHandler(name = "bind.add", permission = "bind", description = "Adds a command to the binded item")
	public void addBind(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		final ItemBuilder builder = new ItemBuilder(player.getItemInHand());
		final String[] command = TextUtils.getMsgFromArgs(args.getArgs(), 0, args.getArgs().length);
		final String lore = "/" + TextUtils.join(command, " ");
		builder.addLore(lore);//setLore(lore);
		try {
			builder.addGlow();
		} catch (final Exception e) {
		}
		player.setItemInHand(builder.build());
		player.updateInventory();
		player.sendMessage(Strings.TITLE + "Successfully added " + lore + "to your item!");
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

		final ItemBuilder builder = new ItemBuilder(player.getItemInHand());
		final List<String> lore = builder.getLore();
		if (!TextUtils.isInteger(args.getArgs()[0])) {
			ErrorUtils.notAnInteger(player);
			return;
		}
		final String s = lore.get(Integer.parseInt(args.getArgs()[0]) - 1);
		lore.remove((Integer.parseInt(args.getArgs()[0]) - 1));
		builder.setLore(lore);
		if (!containsCommand(player.getItemInHand()))
			builder.removeGlow();
		player.setItemInHand(builder.build());
		player.sendMessage(Strings.TITLE + "Successfully removed " + s + "from your item!");
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
