package io.github.Skepter.CommandListeners;

import io.github.Skepter.Commands.CommandFramework;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Misc.FireworkInventories;
import io.github.Skepter.Utils.CustomFireworkBuilder;
import io.github.Skepter.Utils.ErrorUtils;
import io.github.Skepter.Utils.ItemUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Color;
import org.bukkit.Material;
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
	
	private Map<UUID, CustomFireworkBuilder> map = new HashMap<UUID, CustomFireworkBuilder>(); 

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
	public void onClick(final InventoryClickEvent event) {
		final Player player = (Player) event.getWhoClicked();
		if (event.getAction().equals(InventoryAction.PICKUP_SOME)) {
			switch (event.getInventory().getName()) {
			case "FireworkBuilder - Choose a firework type":
				if(check(event)) {
					CustomFireworkBuilder builder = new CustomFireworkBuilder(1);
					builder.setType(parseType(event.getInventory().getItem(event.getSlot())));
					map.put(player.getUniqueId(), builder);
					player.openInventory(FireworkInventories.chooseColor());
				}
			case "FireworkBuilder - Choose a color":
				if(check(event)) {
					CustomFireworkBuilder builder = map.get(player);
					builder.addColor(parseColor(event.getInventory().getItem(event.getSlot())));
					map.put(player.getUniqueId(), builder);
					player.openInventory(FireworkInventories.chooseColor());
				}
			}
		}
	}

	private boolean check(InventoryClickEvent event) {
		if (!event.getAction().equals(InventoryAction.PICKUP_ONE))
			return false;
		if ((event.getSlot() == -999) || (event.getInventory().getItem(event.getSlot()) == null))
			return false;
		return true;
	}
	
	private Type parseType(ItemStack item) {
		if(item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
			switch(item.getItemMeta().getDisplayName()) {
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
		}
		return Type.BALL;
	}
	
	private Color parseColor(ItemStack item) {
		if(item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
			switch(item.getItemMeta().getDisplayName()) {
			inv.setItem(0, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK), "Black"));
			inv.setItem(1, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 8), "Gray"));
			inv.setItem(2, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 7), "Silver"));
			inv.setItem(3, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 3), "Maroon"));
			inv.setItem(4, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 4), "Navy"));
			inv.setItem(5, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 12), "Blue"));
			inv.setItem(6, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 6), "Teal"));
			inv.setItem(6, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 12), "Aqua"));
			inv.setItem(7, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 2), "Olive"));
			inv.setItem(8, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 10), "Lime"));
			inv.setItem(9, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 2), "Green"));
			inv.setItem(10, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 5), "Purple"));
			inv.setItem(11, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 13), "Fuchsia"));
			inv.setItem(12, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 1), "Red"));
			inv.setItem(13, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 14), "Orange"));
			inv.setItem(14, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 11), "Yellow"));
			inv.setItem(15, ItemUtils.setDisplayName(new ItemStack(Material.INK_SACK, 1, (short) 15), "White"));		
			}
		}
	}
}