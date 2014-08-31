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
		//page 1
		case "Enchant - Armor":
			if (event.getSlot() == 53) {
				player.openInventory(EnchantmentInventories.page2()); // page 2
				return;
			} else if (event.getSlot() == 52) {
				player.openInventory(EnchantmentInventories.page1()); // page 4
				return;
			}
			doEnchant(player, event, 54);
		case "Enchant - Tools":
			if (event.getSlot() == 26) {
				player.openInventory(EnchantmentInventories.page2()); // page 3
				return;
			} else if (event.getSlot() == 25) {
				player.openInventory(EnchantmentInventories.page1()); // page 1
				return;
			}
			doEnchant(player, event, 27);
		case "Enchant - Weapons":
			if (event.getSlot() == 53) {
				player.openInventory(EnchantmentInventories.page2()); // page 4
				return;
			} else if (event.getSlot() == 52) {
				player.openInventory(EnchantmentInventories.page1()); // page 2
				return;
			}
		}
	}

	private void doEnchant(final Player player, final InventoryClickEvent event, final int size) {
		if (!event.getAction().equals(InventoryAction.PICKUP_ONE))
			event.setCancelled(true);
		if ((event.getSlot() == -999) || (event.getInventory().getItem(event.getSlot()) == null))
			return;
		if (((event.getSlot() != (size - 2)) || (event.getSlot() != (size - 1)))) {
			final Map<Enchantment, Integer> eMap = event.getInventory().getItem(event.getSlot()).getItemMeta().getEnchants();
			player.getItemInHand().addUnsafeEnchantments(eMap);
			player.closeInventory();
			player.sendMessage(AllAssets.instance().title + "You successfully enchanted your item in your hand");
		}
	}
}
