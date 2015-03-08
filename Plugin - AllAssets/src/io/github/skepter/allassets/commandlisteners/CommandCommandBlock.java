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
package io.github.skepter.allassets.commandlisteners;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.serializers.ItemSerializer;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.FireworkUtils;
import io.github.skepter.allassets.utils.utilclasses.LocationUtils;

import org.bukkit.Material;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CommandCommandBlock implements Listener {

	public CommandCommandBlock(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "$firework", permission = "$console", description = "Launches a firework")
	public void onCommand(final CommandArgs args) {
		if (args.getSender() instanceof BlockCommandSender) {
			final BlockCommandSender sender = (BlockCommandSender) args.getSender();
			final ItemStack is = ItemSerializer.fromString(args.getArgs()[0]);
			FireworkUtils.spawnFireworkFromItemStack(LocationUtils.getCenter(sender.getBlock().getLocation()), is);
		}
		return;
	}

	@EventHandler
	public void onClick(final PlayerInteractEvent event) {
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getPlayer().getItemInHand().getType().equals(Material.FIREWORK)) {
			if ((event.getClickedBlock().getState() instanceof CommandBlock)) {
				event.setCancelled(true);
				final CommandBlock block = (CommandBlock) event.getClickedBlock().getState();
				block.setCommand("$firework " + ItemSerializer.toString(event.getPlayer().getItemInHand()));
				block.update(true);
				event.getPlayer().closeInventory();
				event.getPlayer().sendMessage(Strings.TITLE + "Added firework to commandblock");
			}
		}
	}
}
