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
package io.github.Skepter.AllAssets.CommandListeners;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Serializers.ItemSerializer;
import io.github.Skepter.AllAssets.Utils.FireworkUtils;
import io.github.Skepter.AllAssets.Utils.LocationUtils;

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

	@CommandHandler(name = "$firework", permission = "$console", description = "Launches a firework", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		if (args.getSender() instanceof BlockCommandSender) {
			BlockCommandSender sender = (BlockCommandSender) args.getSender();
			ItemStack is = ItemSerializer.fromString(args.getArgs()[0]);
			FireworkUtils.spawnFireworkFromItemStack(LocationUtils.getCenter(sender.getBlock().getLocation()), is);
		}
		return;
	}

	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		if (event.getClickedBlock().getState() instanceof CommandBlock && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (event.getPlayer().getItemInHand().getType().equals(Material.FIREWORK)) {
				event.setCancelled(true);
				CommandBlock block = (CommandBlock) event.getClickedBlock().getState();
				block.setCommand("$firework " + ItemSerializer.toString(event.getPlayer().getItemInHand()));
				block.update(true);
				event.getPlayer().closeInventory();
				event.getPlayer().sendMessage(AllAssets.title + "Added firework to commandblock");
			}
		}
	}
}