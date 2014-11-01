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
import io.github.Skepter.AllAssets.Misc.EnchantmentInventories;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;

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
	public void inventoryClick(final InventoryClickEvent event) {
		final Player player = (Player) event.getWhoClicked();
		if (event.getAction().equals(InventoryAction.PICKUP_SOME)) {
			switch (event.getInventory().getName()) {
			case "Enchant - Armor":
				if (event.getSlot() == 53) {
					player.openInventory(EnchantmentInventories.page2()); // page 2
					return;
				} else if (event.getSlot() == 52) {
					player.openInventory(EnchantmentInventories.page1()); // page 4
					return;
				}
				doEnchant(player, event);
			case "Enchant - Tools":
				if (event.getSlot() == 26) {
					player.openInventory(EnchantmentInventories.page2()); // page 3
					return;
				} else if (event.getSlot() == 25) {
					player.openInventory(EnchantmentInventories.page1()); // page 1
					return;
				}
				doEnchant(player, event);
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
	}

	private void doEnchant(final Player player, final InventoryClickEvent event) {
		if (!event.getAction().equals(InventoryAction.PICKUP_ONE))
			event.setCancelled(true);
		if ((event.getSlot() == -999) || (event.getInventory().getItem(event.getSlot()) == null))
			return;
		if (((event.getSlot() != (event.getInventory().getSize() - 2)) || (event.getSlot() != (event.getInventory().getSize() - 1)))) {
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
