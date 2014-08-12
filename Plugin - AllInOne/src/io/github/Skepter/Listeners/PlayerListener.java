package io.github.Skepter.Listeners;

import io.github.Skepter.AllInOne;
import io.github.Skepter.Commands.CommandLog;
import io.github.Skepter.Config.ConfigHandler;
import io.github.Skepter.Config.UUIDData;
import io.github.Skepter.Serializer.InventorySerializer;
import io.github.Skepter.Users.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
		AllInOne.instance().getLogger().info(event.getPlayer().getName() + "'s UUID is: " + event.getPlayer().getUniqueId().toString());

		final User user = new User(event.getPlayer());
		user.setJoinCount(user.getJoinCount() + 1);
		user.setUUID(event.getPlayer().getUniqueId());
		if (!user.IPs().contains(event.getPlayer().getAddress().getHostName())) {
			final List<String> ips = user.IPs();
			if (!ips.isEmpty()) {
				CommandLog.addOtherLog(event.getPlayer().getName() + " joined with a new IP");
			}
			ips.add(event.getPlayer().getAddress().getHostName());
			user.setIPs(ips);
		}

		AllInOne.instance().tempTimeMap.put(event.getPlayer().getUniqueId(), System.currentTimeMillis());
		final Long l = user.getTotalTimePlayed();
		if (ConfigHandler.features().getBoolean("JoinActions")) {
			if (ConfigHandler.features().getBoolean("UniquePlayers"))
				event.getPlayer().sendMessage(AllInOne.instance().title + Bukkit.getOfflinePlayers().length + " unique players have joined this server");
			if (ConfigHandler.features().getBoolean("TotalTime")) {
				long days = TimeUnit.MILLISECONDS.toDays(l);
				long hours = TimeUnit.MILLISECONDS.toHours(l) - (days * 60 * 60 * 24);
				long minutes = TimeUnit.MILLISECONDS.toMinutes(l) - (days * 60 * 60 * 24) - (hours * 60 * 60);
				long seconds = TimeUnit.MILLISECONDS.toSeconds(l) - (days * 60 * 60 * 24) - (hours * 60 * 60) - (minutes * 60);
				event.getPlayer().sendMessage(AllInOne.instance().title + "Total time played: " + days + " days " + hours + " hours " + minutes + " minutes " + seconds + " seconds");
			}
		}

		//AllInOne.instance().ghostUtils.addPlayer(event.getPlayer());
		//set it in the User (IUser) that the player can toggle if they have the scoreboard on or not (Admin only feature?)
		//		SimpleScoreboard board = new SimpleScoreboard(ChatColor.YELLOW + "Notifications");
		//		board.blankLine();
		//		board.add("Error Logs", 0);
		//		board.add("Spam Logs", 0);
		//		board.build();
		//		board.send(event.getPlayer());
	}

	@EventHandler
	public void onQuit(final PlayerQuitEvent event) {
		event.getPlayer().resetPlayerTime();
		event.getPlayer().resetPlayerWeather();
		final User user = new User(event.getPlayer());
		user.setTimeSinceLastPlay(System.currentTimeMillis());
		user.setTotalTimePlayed(user.getTotalTimePlayed() + (System.currentTimeMillis() - AllInOne.instance().tempTimeMap.get(event.getPlayer().getUniqueId())));
	}

	@EventHandler
	public void onTeleport(final PlayerTeleportEvent event) {
		final User user = new User(event.getPlayer());
		user.setLastLoc(event.getFrom());
	}

	@EventHandler
	public void onEvent(final PlayerJoinEvent event) {
		UUIDData data = new UUIDData();
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

		if (ConfigHandler.features().getBoolean("DeathCount")) {
			user.setDeathCount(user.getDeathCount() + 1);
			user.getPlayer().sendMessage(AllInOne.instance().title + "You have died " + user.getDeathCount() + " times!");
		}

		if (ConfigHandler.features().getBoolean("DeathSigns")) {
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
			if (blockEvent.canBuild()) {
				blockEvent.setCancelled(true);
			}
			Bukkit.getServer().getPluginManager().callEvent(blockEvent);
		}
	}

	@EventHandler
	public void onSwitchItem(final PlayerItemHeldEvent event) {
		final Player player = event.getPlayer();
		final ItemStack i = player.getInventory().getItem(event.getNewSlot());
		if (i == null || i.getType() == Material.AIR) {
			return;
		}
		//Work on this TODO
		final Set<Entry<Enchantment, Integer>> entrySet = i.getEnchantments().entrySet();
		for (final Entry<Enchantment, Integer> e : entrySet) {
			if (e.getValue() > 5) {
				if (!player.hasPermission("AllInOne.illegalitems")) {
					player.setItemInHand(null);
					CommandLog.addOtherLog(ChatColor.BLUE + player.getName() + ChatColor.WHITE + " had an illegal item!");
				}
			}
		}
	}

	@EventHandler
	public void onMove(final PlayerMoveEvent event) {
		if (ConfigHandler.features().getBoolean("FlyBreakSpeedModifier")) {
			if (event.getPlayer().isFlying() && event.getPlayer().getAllowFlight() && !event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
				event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 100000, 18));
			} else {
				event.getPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);
			}
		}
	}

	@EventHandler
	public void onPlayerDeath(final PlayerDeathEvent event) {
		if (ConfigHandler.features().getBoolean("InstantDeathRespawn")) {
			final Player p = event.getEntity();
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(AllInOne.instance(), new Runnable() {

				@Override
				public void run() {
					try {
						final Object nmsPlayer = p.getClass().getMethod("getHandle").invoke(p);
						Object packet = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".PacketPlayInClientCommand").newInstance();
						final Class<?> enumClass = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".EnumClientCommand");

						for (final Object ob : enumClass.getEnumConstants()) {
							if (ob.toString().equals("PERFORM_RESPAWN")) {
								packet = packet.getClass().getConstructor(enumClass).newInstance(ob);
							}
						}
						final Object con = nmsPlayer.getClass().getField("playerConnection").get(nmsPlayer);
						con.getClass().getMethod("a", packet.getClass()).invoke(con, packet);
					} catch (final Throwable t) {
						t.printStackTrace();
					}
				}

			}, 1L);
		}
	}

	@EventHandler
	public void creativeEnderpearl(final PlayerInteractEvent event) {
		if (ConfigHandler.features().getBoolean("CreativeEnderpearl")) {
			if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE) && (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) && event.getItem().getType().equals(Material.ENDER_PEARL)) {
				event.getPlayer().launchProjectile(EnderPearl.class);
			}
		}
	}

	public void getPing(final Player player) {
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(AllInOne.instance(), new Runnable() {
			@Override
			public void run() {
				try {
					final Object nmsPlayer = player.getClass().getMethod("getHandle").invoke(player);
					final int ping = (int) nmsPlayer.getClass().getField("ping").get(nmsPlayer);
					System.out.println(ping);
				} catch (final Throwable t) {
					t.printStackTrace();
				}
			}
		}, 1L);
	}

	/*
	 *   public int getPing(Player player) {
	CraftPlayer pingc = (CraftPlayer)player;
	EntityPlayer pinge = pingc.getHandle();
	return pinge.ping;
	}

	 */
}
