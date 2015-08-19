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

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.api.builders.FireworkBuilder;
import io.github.skepter.allassets.api.utils.PlayerMap;
import io.github.skepter.allassets.misc.FireworkInventories;
import io.github.skepter.allassets.utils.CustomObject;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;

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
	private final PlayerMap<FireworkBuilder> map = new PlayerMap<FireworkBuilder>(AllAssets.instance());

	@CommandHandler(name = "firework", permission = "firework", description = "Creates a custom firework")
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
			final ItemStack item = event.getInventory().getItem(event.getSlot());
			switch (event.getInventory().getName()) {
				case "Firework - Type":
					if (check(event)) {
						final FireworkBuilder builder = new FireworkBuilder(1);
						builder.setType(parseType(item));
						map.put(player, builder);
						player.openInventory(FireworkInventories.chooseColor(false));
					}
					break;
				case "Firework - Color":
					if (check(event)) {
						final FireworkBuilder builder = map.get(player);
						builder.addColor(parseColor(item));
						map.put(player, builder);
						player.openInventory(FireworkInventories.chooseColor(true));
					}
					break;
				case "Firework - Fade":
					if (check(event)) {
						final FireworkBuilder builder = map.get(player);
						builder.addFade(parseColor(item));
						map.put(player, builder);
						player.openInventory(FireworkInventories.anotherColor());
					}
					break;
				case "Do you want another color?":
					if (check(event))
						if (parseBoolean(item))
							player.openInventory(FireworkInventories.chooseColor(false));
						else
							player.openInventory(FireworkInventories.chooseFlicker(false));
					break;
				case "Do you want flickering?":
					if (check(event)) {
						final FireworkBuilder builder = map.get(player);
						if (parseBoolean(item))
							builder.addFlicker();
						map.put(player, builder);
						player.openInventory(FireworkInventories.chooseFlicker(true));
					}
					break;
				case "Do you want a trail?":
					if (check(event)) {
						final FireworkBuilder builder = map.get(player);
						if (parseBoolean(item))
							builder.addTrail();
						map.put(player, builder);
						player.openInventory(FireworkInventories.choosePower());
					}
					break;
				case "Choose a power size":
					if (check(event)) {
						final FireworkBuilder builder = map.get(player);
						builder.setPower(parsePower(item));
						map.remove(player);
						player.getInventory().addItem(builder.build());
						player.sendMessage(Strings.TITLE + "Firework created!");
						player.closeInventory();
					}
					break;
			}
		}
	}

	/* Checks to make sure that the event is valid (not clicking outside of the inventory etc.) */
	private boolean check(final InventoryClickEvent event) {
		if (!event.getAction().equals(InventoryAction.PICKUP_ALL) || (event.getSlot() == -999) || (event.getInventory().getItem(event.getSlot()) == null))
			return false;
		event.setCancelled(true);
		return true;
	}

	private boolean parseBoolean(final ItemStack item) {
		if ((item != null) && item.hasItemMeta() && item.getItemMeta().hasDisplayName())
			switch (item.getItemMeta().getDisplayName()) {
				case "Yes":
					return true;
				case "No":
					return false;
			}
		return false;
	}

	private int parsePower(final ItemStack item) {
		if ((item != null) && item.hasItemMeta() && item.getItemMeta().hasDisplayName())
			return new CustomObject(item.getItemMeta().getDisplayName()).stripInteger();
		else
			return 0;
	}

	private Type parseType(final ItemStack item) {
		if ((item != null) && item.hasItemMeta() && item.getItemMeta().hasDisplayName())
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

	private Color parseColor(final ItemStack item) {
		if ((item != null) && item.hasItemMeta() && item.getItemMeta().hasDisplayName())
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
