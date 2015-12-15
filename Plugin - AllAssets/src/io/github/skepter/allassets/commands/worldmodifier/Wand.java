/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter 
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter 
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
package io.github.skepter.allassets.commands.worldmodifier;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.api.builders.ItemBuilder;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.PlayerUtils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Wand implements Listener {

	public Wand(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	private ItemStack wmt = new ItemBuilder(Material.DIAMOND_AXE).setDisplayName("World Modifier tool").setLore("Left click to set position 1", "Right click to set position 2").addGlow().build();

	@EventHandler
	public void wandUse(final PlayerInteractEvent e) {
		e.setUseInteractedBlock(Result.ALLOW);
		e.setUseItemInHand(Result.ALLOW);
		WorldModifierData data = WorldModifierHandler.getData(e.getPlayer());
		if (data.isWandActive() || e.getPlayer().getItemInHand().equals(wmt))
			switch (e.getAction()) {
			case LEFT_CLICK_AIR: {
				e.setCancelled(true);
				final Location l = PlayerUtils.getTargetBlock(e.getPlayer()).getLocation();
				data.setPos1(l);
				e.getPlayer().sendMessage("pos1 = [" + l.getBlockX() + ", " + l.getBlockY() + ", " + l.getBlockZ() + "]");
				break;
			}
			case LEFT_CLICK_BLOCK: {
				e.setCancelled(true);
				final int x = e.getClickedBlock().getX(), y = e.getClickedBlock().getY(), z = e.getClickedBlock().getZ();
				data.setPos1(e.getClickedBlock().getLocation());
				e.getPlayer().sendMessage("pos1 = [" + x + ", " + y + ", " + z + "]");
				break;
			}
			case RIGHT_CLICK_AIR: {
				e.setCancelled(true);
				final Location l = PlayerUtils.getTargetBlock(e.getPlayer()).getLocation();
				data.setPos2(l);
				e.getPlayer().sendMessage("pos2 = [" + l.getBlockX() + ", " + l.getBlockY() + ", " + l.getBlockZ() + "]");
				break;
			}
			case RIGHT_CLICK_BLOCK: {
				e.setCancelled(true);
				final int x = e.getClickedBlock().getX(), y = e.getClickedBlock().getY(), z = e.getClickedBlock().getZ();
				data.setPos2(e.getClickedBlock().getLocation());
				e.getPlayer().sendMessage("pos2 = [" + x + ", " + y + ", " + z + "]");
				break;
			}
			default:
				break;
			}
	}

	@EventHandler
	public void zoomEvent(final PlayerMoveEvent e) {
		if (WorldModifierHandler.getData(e.getPlayer()).isWandActive() || e.getPlayer().getItemInHand().equals(wmt))
			if (e.getPlayer().isSneaking())
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100000, 100));
			else
				e.getPlayer().removePotionEffect(PotionEffectType.SLOW);
	}

	@CommandHandler(name = "worldmodifiertool", aliases = { "wand", "wmt" }, permission = "worldmodifier", description = "Gives you the world modifier tool")
	public void wand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null)
			if (!PlayerUtils.setItemInHand(player, wmt))
				ErrorUtils.cannotGiveItem(player);
			else
				player.sendMessage(Strings.TITLE + "Successfully given you the world modifier tool");
	}
}
