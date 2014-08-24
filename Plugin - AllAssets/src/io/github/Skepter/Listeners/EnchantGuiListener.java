package io.github.Skepter.Listeners;

import io.github.Skepter.AllAssets;
import io.github.Skepter.Utils.EnchantmentInventories;

import java.util.Map;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

public class EnchantGuiListener implements Listener {

	@EventHandler
	public void onClick(final InventoryClickEvent event) {
		final Player player = (Player) event.getWhoClicked();
		switch (event.getInventory().getName()) {
		case "Enchant - Armor":
			if (!event.getAction().equals(InventoryAction.PICKUP_ONE))
				event.setCancelled(true);
			if (((event.getSlot() != 52) || (event.getSlot() != 53))) {
				final Map<Enchantment, Integer> eMap = event.getInventory().getItem(event.getSlot()).getItemMeta().getEnchants();
				player.getItemInHand().addUnsafeEnchantments(eMap);
				player.closeInventory();
				player.sendMessage(AllAssets.instance().title + "You successfully enchanted your item in your hand");
			} else if (event.getSlot() == 53)
				player.openInventory(EnchantmentInventories.page1()); // page 2
			else if (event.getSlot() == 52)
				player.openInventory(EnchantmentInventories.page1()); // page 2
				//page 4
		}
	}
}
