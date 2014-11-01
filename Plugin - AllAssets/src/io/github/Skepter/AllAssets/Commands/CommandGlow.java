/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Commands;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.ItemUtils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandGlow {

	public CommandGlow(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "glow", permission = "glow", description = "Makes the item in your hand glow", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		if(!player.getItemInHand().getType().isBlock()) {
			if (ItemUtils.hasGlow(player.getItemInHand())) {
				ItemStack is = ItemUtils.removeGlow(player.getItemInHand());
				player.setItemInHand(is);
				player.sendMessage(AllAssets.title + "Your item is no longer glowing!");
			} else {
				ItemStack is = ItemUtils.addGlow(player.getItemInHand());
				player.setItemInHand(is);
				player.sendMessage(AllAssets.title + "Your item is now glowing!");
			}
		} else {
			ErrorUtils.error(player, "You cannot make that item glow!");
		}
		return;
	}
}
