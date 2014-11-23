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
import io.github.Skepter.AllAssets.API.AAFireworkBuilder;
import io.github.Skepter.AllAssets.Misc.FireworkInventories;
import io.github.Skepter.AllAssets.Misc.PlayerMap;
import io.github.Skepter.AllAssets.Utils.CustomObject;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;

import java.util.UUID;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class CommandFirework implements Listener {

	public CommandFirework(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	//have a button to output it as a single lined command (for dispensers etc.)
	private PlayerMap<UUID, AAFireworkBuilder> map = new PlayerMap<UUID, AAFireworkBuilder>();

	@CommandHandler(name = "firework", permission = "firework", description = "Creates a custom firework", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		player.openInventory(FireworkInventories.chooseType());
		return;
	}

	@EventHandler
	public void inventoryClick(final InventoryClickEvent event) {
		final Player player = (Player) event.getWhoClicked();
		if (event.getAction().equals(InventoryAction.PICKUP_ALL)) {
			ItemStack item = event.getInventory().getItem(event.getSlot());
			switch (event.getInventory().getName()) {
			case "Firework - Type":
				if (check(event)) {
					AAFireworkBuilder builder = new AAFireworkBuilder(1);
					builder.setType(parseType(item));
					map.put(player, builder);
					player.openInventory(FireworkInventories.chooseColor(false));
				}
				break;
			case "Firework - Color":
				if (check(event)) {
					AAFireworkBuilder builder = map.get(player);
					builder.addColor(parseColor(item));
					map.put(player, builder);
					player.openInventory(FireworkInventories.chooseColor(true));
				}
				break;
			case "Firework - Fade":
				if (check(event)) {
					AAFireworkBuilder builder = map.get(player);
					builder.addFade(parseColor(item));
					map.put(player, builder);
					player.openInventory(FireworkInventories.anotherColor());
				}
				break;
			case "Do you want another color?":
				if (check(event)) {
					if (parseBoolean(item))
						player.openInventory(FireworkInventories.chooseColor(false));
					else
						player.openInventory(FireworkInventories.chooseFlicker(false));
				}
				break;
			case "Do you want flickering?":
				if (check(event)) {
					AAFireworkBuilder builder = map.get(player);
					builder.addFlicker(parseBoolean(item));
					map.put(player, builder);
					player.openInventory(FireworkInventories.chooseFlicker(true));
				}
				break;
			case "Do you want a trail?":
				if (check(event)) {
					AAFireworkBuilder builder = map.get(player);
					builder.addTrail(parseBoolean(item));
					map.put(player, builder);
					player.openInventory(FireworkInventories.choosePower());
				}
				break;
			case "Choose a power size":
				if (check(event)) {
					AAFireworkBuilder builder = map.get(player);
					builder.setPower(parsePower(item));
					map.remove(player);
					player.getInventory().addItem(builder.getFirework());
					player.sendMessage(AllAssets.title + "Firework created!");
					player.closeInventory();
				}
				break;
			}
		}
	}

	/* Checks to make sure that the event is valid (not clicking outside of the inventory etc.) */
	private boolean check(InventoryClickEvent event) {
		if (!event.getAction().equals(InventoryAction.PICKUP_ALL) || (event.getSlot() == -999) || (event.getInventory().getItem(event.getSlot()) == null))
			return false;
		event.setCancelled(true);
		return true;
	}

	private boolean parseBoolean(ItemStack item) {
		if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
			switch (item.getItemMeta().getDisplayName()) {
			case "Yes":
				return true;
			case "No":
				return false;
			}
		}
		return false;
	}

	private int parsePower(ItemStack item) {
		if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName())
			return new CustomObject(item.getItemMeta().getDisplayName()).stripInteger();
		else
			return 0;
	}

	private Type parseType(ItemStack item) {
		if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName())
			switch (item.getItemMeta().getDisplayName()) {
			case "Creeper":
				return Type.CREEPER;
			case "Ball":
				return Type.BALL;
			case "Large Ball":
				return Type.BALL_LARGE;
			case "Burst":
				return Type.BURST;
			case "Star":
				return Type.STAR;
			}
		else
			return Type.BALL;
		return Type.BALL;
	}

	private Color parseColor(ItemStack item) {
		if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName())
			switch (item.getItemMeta().getDisplayName()) {
			case "Black":
				return DyeColor.BLACK.getFireworkColor();
			case "Gray":
				return DyeColor.GRAY.getFireworkColor();
			case "Silver":
				return DyeColor.SILVER.getFireworkColor();
			case "Brown":
				return DyeColor.BROWN.getFireworkColor();
			case "Light Blue":
				return DyeColor.LIGHT_BLUE.getFireworkColor();
			case "Blue":
				return DyeColor.BLUE.getFireworkColor();
			case "Cyan":
				return DyeColor.CYAN.getFireworkColor();
			case "Lime":
				return DyeColor.LIME.getFireworkColor();
			case "Green":
				return DyeColor.GREEN.getFireworkColor();
			case "Magenta":
				return DyeColor.MAGENTA.getFireworkColor();
			case "Purple":
				return DyeColor.PURPLE.getFireworkColor();
			case "Pink":
				return DyeColor.PINK.getFireworkColor();
			case "Red":
				return DyeColor.RED.getFireworkColor();
			case "Orange":
				return DyeColor.ORANGE.getFireworkColor();
			case "Yellow":
				return DyeColor.YELLOW.getFireworkColor();
			case "White":
				return DyeColor.WHITE.getFireworkColor();
			}
		else
			return Color.WHITE;
		return Color.WHITE;
	}
}