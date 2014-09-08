package io.github.Skepter.CommandListeners;

import io.github.Skepter.AllAssets;
import io.github.Skepter.Commands.CommandFramework;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Misc.EnchantmentInventories;
import io.github.Skepter.Utils.ErrorUtils;

import java.util.Map;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CommandEnchant implements Listener {

	public CommandEnchant(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "enchant", permission = "enchant", description = "Enchants an item", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		player.openInventory(EnchantmentInventories.page1());
		return;
	}
	
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
			player.sendMessage(AllAssets.title + "You successfully enchanted your item in your hand");
		}
	}
}

/*
 * Protection 1-4 Armour FireProtection 1-4 Armour Thorns 1-3 Armour
 * BlastProtection 1-4 Armour ProjProtection 1-4 Armour Respiration 1-3 Helmet
 * AquaAffinity 1 Helmet FeatherFalling 1-4 Boots
 * 
 * Sharpness 1-5 Sword/Axe Smite 1-5 Sword/Axe BaneOfArthropods 1-5 Sword/Axe
 * Knock back 1-2 Sword Fire Aspect 1-2 Sword Looting 1-3 Sword
 * 
 * Efficiency 1-5 Pick/Shovel/Axe/Shears Silk Touch 1 Pick/Shovel/Axe/Shears
 * Fortune 1-3 Pick/Shovel/Axe Unbreaking 1-3 Pick/Shovel/Axe/Shears
 * Sword/FishingRod/Bow Hoe/Flint-Steel/CarrotStick
 * 
 * Power 1-5 Bow Punch 1-2 Bow Flame 1 Bow Infinity 1 Bow
 * 
 * Luck of the Sea 1-3 FishingRod Lure 1-3 FishingRod
 * 
 * 24 enchantments (4x6 enchantments - doublechest size)
 */
