/*******************************************************************************

 * Skepter's licence
 * Copyright 2015 
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
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. The only exception is the BukkitDev staff who I have allowed permission to decompile (to search for malicious code)
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
package io.github.Skepter.AllAssets.Listeners;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.API.LogEvent.LogType;
import io.github.Skepter.AllAssets.API.User;
import io.github.Skepter.AllAssets.Commands.Administration.CommandLog;
import io.github.Skepter.AllAssets.Config.ConfigHandler;
import io.github.Skepter.AllAssets.Config.UUIDData;
import io.github.Skepter.AllAssets.Misc.NotificationsBoard;
import io.github.Skepter.AllAssets.Serializers.InventorySerializer;
import io.github.Skepter.AllAssets.Tasks.AnyLeashTask;
import io.github.Skepter.AllAssets.Tasks.InstantRespawnTask;
import io.github.Skepter.AllAssets.Utils.UtilClasses.FireworkUtils;
import io.github.Skepter.AllAssets.Utils.UtilClasses.MathUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerListener implements Listener {

	//ensure that if the plugin was added WHEN the player is ALREADY online
	//some data will not be initialized, so ensure that data is fixed and run for each player online.

	@EventHandler
	public void playerJoin(final PlayerJoinEvent event) {
		AllAssets.instance().getLogger().info(event.getPlayer().getName() + "'s UUID is: " + event.getPlayer().getUniqueId().toString());

		UUIDData.setData(event.getPlayer());

		final User user = new User(event.getPlayer());
		user.setJoinCount(user.getJoinCount() + 1);
		if (!user.IPs().contains(event.getPlayer().getAddress().getHostName())) {
			final List<String> ips = user.IPs();
			if (!ips.isEmpty())
				CommandLog.addLog(event.getPlayer().getName() + " joined with a new IP", LogType.OTHER);
			ips.add(event.getPlayer().getAddress().getHostName());
			user.setIPs(ips);
		}
		
		user.refreshPing();

		AllAssets.instance().tempTimeMap.put(event.getPlayer().getUniqueId(), System.currentTimeMillis());

		if (ConfigHandler.features().getBoolean("JoinActions")) {
			if (ConfigHandler.features().getBoolean("UniquePlayers"))
				event.getPlayer().sendMessage(AllAssets.TITLE + Bukkit.getOfflinePlayers().length + " unique players have joined this server");
			if (ConfigHandler.features().getBoolean("TotalTime"))
				event.getPlayer().sendMessage(AllAssets.TITLE + "Total time played: " + MathUtils.formatDate(user.getTotalTimePlayed()));
			if (ConfigHandler.features().getBoolean("FireworkOnJoin"))
				for (int i = 0; i < new Random().nextInt(5); i++)
					FireworkUtils.spawnRandomFirework(event.getPlayer().getLocation());
			if (ConfigHandler.features().getBoolean("CommandsOnJoin"))
				for (final String string : ConfigHandler.config().getStringList("commandsOnJoin"))
					Bukkit.dispatchCommand(event.getPlayer(), string.replace("{PLAYERNAME}", event.getPlayer().getName()));
		}

		AllAssets.instance().ghostFactory.addPlayer(event.getPlayer());

		new NotificationsBoard(event.getPlayer()).updateBoard();
		//TODO - see NotificationsBoard
	}

	@EventHandler
	public void playerLeave(final PlayerQuitEvent event) {
		final Player player = event.getPlayer();
		player.resetPlayerTime();
		player.resetPlayerWeather();
		final User user = new User(player);
		user.setTimeSinceLastPlay(System.currentTimeMillis());
		final Map<UUID, Long> map = AllAssets.instance().tempTimeMap;
		if (map.containsKey(player.getUniqueId())) {
			user.setTotalTimePlayed(user.getTotalTimePlayed() + (System.currentTimeMillis() - map.get(player.getUniqueId())));
			map.remove(player.getUniqueId());
		} else {
			//error! it should be there because it was added on player join...
			//this shouldn't be possible so -.- yeah
		}
	}

	@EventHandler
	public void playerPlaceBlockOnHead(final InventoryClickEvent event) {
		if (((event.isLeftClick() || event.isRightClick()) && event.getAction().equals(InventoryAction.PLACE_ONE)) || event.getAction().equals(InventoryAction.PLACE_ALL) || event.getAction().equals(InventoryAction.PLACE_SOME))
			if ((event.getSlot() == 39) && event.getInventory().getType().equals(InventoryType.CRAFTING) && ConfigHandler.features().getBoolean("BlockHeads")) {
				event.getWhoClicked().getInventory().setHelmet(event.getCursor());
				new BukkitRunnable() {
					@Override
					public void run() {
						event.getWhoClicked().setItemOnCursor(null);
					}
				}.runTaskLater(AllAssets.instance(), 1L);
			}
	}

	@EventHandler
	public void playerTeleport(final PlayerTeleportEvent event) {
		final User user = new User(event.getPlayer());
		user.setLastLoc(event.getFrom());
	}

	@EventHandler
	public void playerDeath(final PlayerDeathEvent event) {
		final User user = new User(event.getEntity());
		user.setLastLoc(event.getEntity().getLocation());

		final Inventory inv = event.getEntity().getInventory();
		user.setLastInventory(InventorySerializer.toString(inv));

		if (ConfigHandler.features().getBoolean("InstantDeathRespawn")) {
			final Player p = event.getEntity();
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(AllAssets.instance(), new InstantRespawnTask(p), 1L);
		}

		if (ConfigHandler.features().getBoolean("DeathCount")) {
			user.setDeathCount(user.getDeathCount() + 1);
			user.getPlayer().sendMessage(AllAssets.TITLE + "You have died " + user.getDeathCount() + " times!");
		}

		if (ConfigHandler.features().getBoolean("DeathSigns")) {
			final Location loc = event.getEntity().getLocation();
			loc.getBlock().setType(Material.AIR);
			loc.getBlock().setType(Material.SIGN_POST);
			final Sign sign = (Sign) loc.getBlock().getState();
			sign.setLine(0, event.getEntity().getName());
			sign.setLine(1, "Died here on");
			sign.setLine(2, new SimpleDateFormat("MMM d").format(new Date()));
			sign.setLine(3, new SimpleDateFormat("hh:mm a").format(new Date()));
			sign.update();

			final BlockPlaceEvent blockEvent = new BlockPlaceEvent(sign.getBlock(), sign.getBlock().getState(), sign.getBlock().getRelative(BlockFace.DOWN), null, user.getPlayer(), false);
			if (blockEvent.canBuild())
				blockEvent.setCancelled(true);
			Bukkit.getServer().getPluginManager().callEvent(blockEvent);
		}
	}

	@EventHandler
	public void playerSwitchItemInHand(final PlayerItemHeldEvent event) {
		final Player player = event.getPlayer();
		final ItemStack i = player.getInventory().getItem(event.getNewSlot());
		if ((i == null) || (i.getType() == Material.AIR))
			return;
		//Work on this TODO
		//		final Set<Entry<Enchantment, Integer>> entrySet = i.getEnchantments().entrySet();
		//		for (final Entry<Enchantment, Integer> e : entrySet)
		//			if (e.getValue() > 5)
		//				if (!player.hasPermission("AllAssets.illegalitems")) {
		//					player.setItemInHand(null);
		//					CommandLog.addOtherLog(ChatColor.BLUE + player.getName() + ChatColor.WHITE + " had an illegal item!");
		//				}
	}

	@EventHandler
	public void playerMove(final PlayerMoveEvent event) {
		if (ConfigHandler.features().getBoolean("FlyBreakSpeedModifier"))
			if (event.getPlayer().isFlying() && event.getPlayer().getAllowFlight() && !event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
				event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 100000, 18));
			else
				event.getPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);
	}

	@EventHandler
	public void playerUseEnderpearl(final PlayerInteractEvent event) {
		if (ConfigHandler.features().getBoolean("CreativeEnderpearl"))
			if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE) && (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) && event.getPlayer().getItemInHand().getType().equals(Material.ENDER_PEARL))
				event.getPlayer().launchProjectile(EnderPearl.class);
	}

	@EventHandler
	public void playerAddLeash(final PlayerInteractEntityEvent event) {
		if (ConfigHandler.features().getBoolean("AnyLeash"))
			if (event.getPlayer().getItemInHand().getType().equals(Material.LEASH) && event.getPlayer().hasPermission("AllAssets.anyleash") && (event.getRightClicked() instanceof LivingEntity)) {
				event.setCancelled(true);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(AllAssets.instance(), new AnyLeashTask(event.getPlayer(), event.getRightClicked()), 1L);
			}
	}
}
