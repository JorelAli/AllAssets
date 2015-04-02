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
			FireworkUtils.spawnFireworkFromItemStack(new LocationUtils(sender.getBlock().getLocation()).getCenter(), is);
		}
		return;
	}

	@EventHandler
	public void onClick(final PlayerInteractEvent event) {
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getPlayer().getItemInHand().getType().equals(Material.FIREWORK))
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
