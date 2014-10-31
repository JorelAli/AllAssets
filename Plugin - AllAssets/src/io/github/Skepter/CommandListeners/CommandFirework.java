package io.github.Skepter.CommandListeners;

import io.github.Skepter.AllAssets;
import io.github.Skepter.API.AAFireworkBuilder;
import io.github.Skepter.Commands.CommandFramework;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Misc.FireworkInventories;
import io.github.Skepter.Utils.CustomObject;
import io.github.Skepter.Utils.ErrorUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Color;
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

	private Map<UUID, AAFireworkBuilder> map = new HashMap<UUID, AAFireworkBuilder>();

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
					map.put(player.getUniqueId(), builder);
					player.openInventory(FireworkInventories.chooseColor(false));
				}
				break;
			case "Firework - Color":
				if (check(event)) {
					AAFireworkBuilder builder = map.get(player.getUniqueId());
					builder.addColor(parseColor(item));
					map.put(player.getUniqueId(), builder);
					player.openInventory(FireworkInventories.chooseColor(true));
				}
				break;
			case "Firework - Fade":
				if (check(event)) {
					AAFireworkBuilder builder = map.get(player.getUniqueId());
					builder.addFade(parseColor(item));
					map.put(player.getUniqueId(), builder);
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
					AAFireworkBuilder builder = map.get(player.getUniqueId());
					builder.addFlicker(parseBoolean(item));
					map.put(player.getUniqueId(), builder);
					player.openInventory(FireworkInventories.chooseFlicker(true));
				}
				break;
			case "Do you want a trail?":
				if (check(event)) {
					AAFireworkBuilder builder = map.get(player.getUniqueId());
					builder.addTrail(parseBoolean(item));
					map.put(player.getUniqueId(), builder);
					player.openInventory(FireworkInventories.choosePower());
				}
				break;
			case "Choose a power size":
				if (check(event)) {
					AAFireworkBuilder builder = map.get(player.getUniqueId());
					builder.setPower(parsePower(item));
					map.remove(player.getUniqueId());
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
				return Color.BLACK;
			case "Gray":
				return Color.GRAY;
			case "Silver":
				return Color.SILVER;
			case "Maroon":
				return Color.MAROON;
			case "Navy":
				return Color.NAVY;
			case "Blue":
				return Color.BLUE;
			case "Teal":
				return Color.TEAL;
			case "Aqua":
				return Color.AQUA;
			case "Olive":
				return Color.OLIVE;
			case "Lime":
				return Color.LIME;
			case "Green":
				return Color.GREEN;
			case "Purple":
				return Color.PURPLE;
			case "Fuchsia":
				return Color.FUCHSIA;
			case "Red":
				return Color.RED;
			case "Orange":
				return Color.ORANGE;
			case "Yellow":
				return Color.YELLOW;
			case "White":
				return Color.WHITE;
			}
		else
			return Color.WHITE;
		return Color.WHITE;
	}
}