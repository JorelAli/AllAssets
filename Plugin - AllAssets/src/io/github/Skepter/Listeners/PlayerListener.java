package io.github.Skepter.Listeners;

import io.github.Skepter.AllAssets;
import io.github.Skepter.API.User;
import io.github.Skepter.Commands.CommandLog;
import io.github.Skepter.Config.ConfigHandler;
import io.github.Skepter.Config.UUIDData;
import io.github.Skepter.Libs.SimpleScoreboard;
import io.github.Skepter.Serializer.InventorySerializer;
import io.github.Skepter.Tasks.InstantRespawnTask;
import io.github.Skepter.Tasks.TPS;
import io.github.Skepter.Utils.MathUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
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

public class PlayerListener implements Listener {

	//ensure that if the plugin was added WHEN the player is ALREADY online
	//some data will not be initialized, so ensure that data is fixed and run for each player online.

	@EventHandler
	public void onJoin(final PlayerJoinEvent event) {
		AllAssets.instance().getLogger().info(event.getPlayer().getName() + "'s UUID is: " + event.getPlayer().getUniqueId().toString());

		final User user = new User(event.getPlayer());
		user.setJoinCount(user.getJoinCount() + 1);
		user.setUUID(event.getPlayer().getUniqueId());
		if (!user.IPs().contains(event.getPlayer().getAddress().getHostName())) {
			final List<String> ips = user.IPs();
			if (!ips.isEmpty())
				CommandLog.addOtherLog(event.getPlayer().getName() + " joined with a new IP");
			ips.add(event.getPlayer().getAddress().getHostName());
			user.setIPs(ips);
		}

		AllAssets.instance().tempTimeMap.put(event.getPlayer().getUniqueId(), System.currentTimeMillis());
		if (ConfigHandler.instance().features().getBoolean("JoinActions")) {
			if (ConfigHandler.instance().features().getBoolean("UniquePlayers"))
				event.getPlayer().sendMessage(AllAssets.instance().title + Bukkit.getOfflinePlayers().length + " unique players have joined this server");
			if (ConfigHandler.instance().features().getBoolean("TotalTime"))
				event.getPlayer().sendMessage(AllAssets.instance().title + "Total time played: " + MathUtils.formatDate(user.getTotalTimePlayed()));
		}
		AllAssets.instance().ghostFactory.addPlayer(event.getPlayer());

		//set it in the User (IUser) that the player can toggle if they have the scoreboard on or not (Admin only feature?)
		final SimpleScoreboard board = new SimpleScoreboard(ChatColor.YELLOW + "Notifications");
		board.add("Current TPS", TPS.getTPSAsInt());
		board.add("Error Logs", 0);
		board.add("Spam Logs", 0);
		//current staff online
		board.build();
		board.send(event.getPlayer());
		SimpleScoreboard.scoreboardMap.put(event.getPlayer().getUniqueId(), board);
	}

	@EventHandler
	public void onQuit(final PlayerQuitEvent event) {
		event.getPlayer().resetPlayerTime();
		event.getPlayer().resetPlayerWeather();
		final User user = new User(event.getPlayer());
		user.setTimeSinceLastPlay(System.currentTimeMillis());
		if (AllAssets.instance().tempTimeMap.get(event.getPlayer().getUniqueId()) != null)
			user.setTotalTimePlayed(user.getTotalTimePlayed() + (System.currentTimeMillis() - AllAssets.instance().tempTimeMap.get(event.getPlayer().getUniqueId())));
	}

	/* Hopefully, if they click that slot, it places the item on their head :D */
	@EventHandler
	public void blockHeads(final InventoryClickEvent event) {
		//armor slot = 5
		if (event.getAction().equals(InventoryAction.PLACE_ONE) && (event.getSlot() == 5) && event.getInventory().getType().equals(InventoryType.PLAYER))
			event.getInventory().setItem(5, event.getCurrentItem());
	}

	@EventHandler
	public void onTeleport(final PlayerTeleportEvent event) {
		final User user = new User(event.getPlayer());
		user.setLastLoc(event.getFrom());
	}

	@EventHandler
	public void onEvent(final PlayerJoinEvent event) {
		final UUIDData data = new UUIDData();
		data.getDataFile().set(event.getPlayer().getName(), event.getPlayer().getUniqueId().toString());
		data.saveDataFile();
		return;
	}

	@EventHandler
	public void onDeath(final PlayerDeathEvent event) {
		final User user = new User(event.getEntity());
		user.setLastLoc(event.getEntity().getLocation());

		final Inventory inv = event.getEntity().getInventory();
		user.setLastInventory(InventorySerializer.toString(inv));

		if (ConfigHandler.instance().features().getBoolean("DeathCount")) {
			user.setDeathCount(user.getDeathCount() + 1);
			user.getPlayer().sendMessage(AllAssets.instance().title + "You have died " + user.getDeathCount() + " times!");
		}

		if (ConfigHandler.instance().features().getBoolean("DeathSigns")) {
			final Location loc = event.getEntity().getLocation();
			// if(player canBuild in the location loc)
			loc.getBlock().setType(Material.AIR);
			loc.getBlock().setType(Material.SIGN_POST);
			final Sign sign = (Sign) loc.getBlock().getState();
			sign.setLine(0, event.getEntity().getName());
			sign.setLine(1, "Died here on");
			sign.setLine(2, new SimpleDateFormat("MMM W").format(new Date()));
			sign.setLine(3, new SimpleDateFormat("hh:mm a").format(new Date()));
			sign.update();

			final BlockPlaceEvent blockEvent = new BlockPlaceEvent(sign.getBlock(), sign.getBlock().getState(), sign.getBlock().getRelative(BlockFace.DOWN), null, user.getPlayer(), false);
			if (blockEvent.canBuild())
				blockEvent.setCancelled(true);
			Bukkit.getServer().getPluginManager().callEvent(blockEvent);
		}
	}

	@EventHandler
	public void onSwitchItem(final PlayerItemHeldEvent event) {
		final Player player = event.getPlayer();
		final ItemStack i = player.getInventory().getItem(event.getNewSlot());
		if ((i == null) || (i.getType() == Material.AIR))
			return;
		//Work on this TODO
		final Set<Entry<Enchantment, Integer>> entrySet = i.getEnchantments().entrySet();
		for (final Entry<Enchantment, Integer> e : entrySet)
			if (e.getValue() > 5)
				if (!player.hasPermission("AllAssets.illegalitems")) {
					player.setItemInHand(null);
					CommandLog.addOtherLog(ChatColor.BLUE + player.getName() + ChatColor.WHITE + " had an illegal item!");
				}
	}

	@EventHandler
	public void onMove(final PlayerMoveEvent event) {
		if (ConfigHandler.instance().features().getBoolean("FlyBreakSpeedModifier"))
			if (event.getPlayer().isFlying() && event.getPlayer().getAllowFlight() && !event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
				event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 100000, 18));
			else
				event.getPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);
	}

	@EventHandler
	public void onPlayerDeath(final PlayerDeathEvent event) {
		if (ConfigHandler.instance().features().getBoolean("InstantDeathRespawn")) {
			final Player p = event.getEntity();
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(AllAssets.instance(), new InstantRespawnTask(p), 1L);
		}
	}

	@EventHandler
	public void creativeEnderpearl(final PlayerInteractEvent event) {
		if (ConfigHandler.instance().features().getBoolean("CreativeEnderpearl"))
			if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE) && (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) && event.getItem().getType().equals(Material.ENDER_PEARL))
				event.getPlayer().launchProjectile(EnderPearl.class);
	}
}
