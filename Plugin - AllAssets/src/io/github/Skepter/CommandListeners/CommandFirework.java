package io.github.Skepter.CommandListeners;

import io.github.Skepter.Commands.CommandFramework;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Misc.FireworkInventories;
import io.github.Skepter.Utils.CustomFireworkBuilder;
import io.github.Skepter.Utils.ErrorUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
					//open next inventory
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
}