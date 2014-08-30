package io.github.Skepter.Commands;

import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Utils.EnchantmentInventories;
import io.github.Skepter.Utils.ErrorUtils;

import org.bukkit.entity.Player;

public class CommandEnchant {

	public CommandEnchant(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "enchant", permission = "enchant", description = "Enchants an item", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		player.openInventory(EnchantmentInventories.page1());
		return;
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
