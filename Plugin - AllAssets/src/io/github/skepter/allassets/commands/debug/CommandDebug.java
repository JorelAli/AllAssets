/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and Tundra
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and Tundra
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.commands.debug;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.CommandFramework.Completer;
import io.github.skepter.allassets.api.CustomInventory;
import io.github.skepter.allassets.api.utils.Debugger;
import io.github.skepter.allassets.libs.JSON;
import io.github.skepter.allassets.reflection.MinecraftReflectionUtils;
import io.github.skepter.allassets.reflection.PacketBuilder;
import io.github.skepter.allassets.reflection.PacketBuilder.PacketType;
import io.github.skepter.allassets.reflection.ReflectionPlayer;
import io.github.skepter.allassets.reflection.ReflectionPlayer.GameStateEffects;
import io.github.skepter.allassets.reflection.ReflectionUtils;
import io.github.skepter.allassets.tasks.TPS;
import io.github.skepter.allassets.test.VanishPlayersItemStack;
import io.github.skepter.allassets.utils.EncryptionUtils;
import io.github.skepter.allassets.utils.Files;
import io.github.skepter.allassets.utils.Files.Directory;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.FileUtils;
import io.github.skepter.allassets.utils.utilclasses.MathUtils;
import io.github.skepter.allassets.utils.utilclasses.PlayerUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;
import io.github.skepter.allassets.utils.utilclasses.TimeUtils;
import io.github.skepter.allassets.utils.utilclasses.VectorUtils;

import java.io.File;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.help.HelpTopic;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class CommandDebug implements Listener {

	int taskID = 0;

	public CommandDebug(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "debug", permission = "debug", description = "Show server data")
	public void command(final CommandArgs args) {
		final CommandSender sender = args.getSender();
		String strTps = "";
		final double tps = MathUtils.round(TPS.getTPS(), 2);
		if ((tps > 18) || (tps == 18))
			strTps = ChatColor.GREEN + String.valueOf(tps);
		else if ((tps > 13) && (tps < 18))
			strTps = ChatColor.YELLOW + String.valueOf(tps);
		else if ((tps < 13) || (tps == 13))
			strTps = ChatColor.RED + String.valueOf(tps);

		sender.sendMessage(TextUtils.title("Debug"));
		sender.sendMessage("Server uptime: " + TimeUtils.formatDateAtASpecificPointInTime(ManagementFactory.getRuntimeMXBean().getStartTime()));
		sender.sendMessage("Server TPS: " + strTps);
		return;
	}

	@CommandHandler(name = "debug.full", permission = "debug", description = "Show server data")
	public void full(final CommandArgs args) {
		final CommandSender sender = args.getSender();
		String strTps = "";
		final double tps = MathUtils.round(TPS.getTPS(), 2);
		if ((tps > 18) || (tps == 18))
			strTps = ChatColor.GREEN + String.valueOf(tps);
		else if ((tps > 13) && (tps < 18))
			strTps = ChatColor.YELLOW + String.valueOf(tps);
		else if ((tps < 13) || (tps == 13))
			strTps = ChatColor.RED + String.valueOf(tps);

		sender.sendMessage(TextUtils.title("Full debug info"));
		sender.sendMessage("Server uptime: " + TimeUtils.formatDateAtASpecificPointInTime(ManagementFactory.getRuntimeMXBean().getStartTime()));
		sender.sendMessage("Server TPS: " + strTps);
		sender.sendMessage("");
		sender.sendMessage("Worlds loaded: " + Bukkit.getWorlds().size());
		sender.sendMessage("Items:");
		for (final World w : Bukkit.getWorlds()) {
			int i = 0;
			for (final Entity e : w.getEntities())
				if (e instanceof Item)
					i++;
			sender.sendMessage(" • " + w.getName() + ": " + i + " items / " + w.getEntities().size() + " entities");
		}
		int i = 0;
		int en = 0;
		for (final World w : Bukkit.getWorlds())
			for (final Entity e : w.getEntities())
				if (e instanceof Item)
					i++;
				else
					en++;
		final int all = i + en;
		sender.sendMessage("" + i + " items out of " + all + " overall entities");
		sender.sendMessage("");
		sender.sendMessage(TextUtils.nonIndentedSubTitle("RAM"));
		sender.sendMessage("Maximum RAM: " + (Runtime.getRuntime().maxMemory() / 1024 / 1024) + "MB");
		sender.sendMessage("Total RAM: " + (Runtime.getRuntime().totalMemory() / 1024 / 1024) + "MB");
		sender.sendMessage("Free RAM: " + (Runtime.getRuntime().freeMemory() / 1024 / 1024) + "MB");
		sender.sendMessage("Available processors (cores): " + Runtime.getRuntime().availableProcessors());
		sender.sendMessage("");
		sender.sendMessage(TextUtils.nonIndentedSubTitle("OS"));
		sender.sendMessage("Operating system: " + ManagementFactory.getRuntimeMXBean().getSystemProperties().get("os.name"));
		sender.sendMessage("OS architecture: " + ManagementFactory.getRuntimeMXBean().getSystemProperties().get("os.arch"));
		sender.sendMessage("Java version: " + ManagementFactory.getRuntimeMXBean().getSystemProperties().get("java.version"));
		sender.sendMessage("");
		sender.sendMessage(TextUtils.nonIndentedSubTitle("Bukkit"));
		if (Bukkit.getVersion().toLowerCase().contains("spigot"))
			sender.sendMessage("Bukkit system: Spigot");
		else if (Bukkit.getVersion().toLowerCase().contains("mcpc"))
			sender.sendMessage("Bukkit system: MCPC");
		else if (Bukkit.getVersion().toLowerCase().contains("forge"))
			sender.sendMessage("Bukkit system: Forge");
		else
			sender.sendMessage("Bukkit system: CraftBukkit");
		sender.sendMessage("Suitable for Minecraft version " + TextUtils.stringBetween(Bukkit.getVersion(), "(MC: ", ")"));
		sender.sendMessage("Using API version " + Bukkit.getBukkitVersion());
		sender.sendMessage("");
		sender.sendMessage(TextUtils.nonIndentedSubTitle("Threads"));
		Bukkit.getScheduler().runTaskAsynchronously(AllAssets.instance(), new Runnable() {
			@Override
			public void run() {
				final HashMap<String, Integer> rawData = new HashMap<String, Integer>();
				final Plugin[] tmp = Bukkit.getPluginManager().getPlugins();
				for (int ij = 0; ij < Bukkit.getPluginManager().getPlugins().length; ++ij)
					try {
						rawData.put(tmp[ij].getName(), 0);
					} catch (final Exception e) {
					}
				for (final BukkitTask bt : Bukkit.getScheduler().getPendingTasks()) {
					final String name = bt.getOwner().getName();
					int count = 1;
					if (rawData.containsKey(name)) {
						count = rawData.get(name) + 1;
						rawData.put(name, count);
					} else
						rawData.put(name, count);
				}
				final LinkedList<ThreadCount> linkedList = new LinkedList<ThreadCount>();
				for (final String key : rawData.keySet())
					linkedList.add(new ThreadCount(key, rawData.get(key)));
				Collections.sort(linkedList, new ThreadCount("nullData", 0));
				int total = 0;
				sender.sendMessage(ChatColor.BLUE + "Threads     Plugins");
				for (final ThreadCount threads : linkedList) {
					total += threads.count;
					if (threads.count == 0)
						continue;
					if (threads.count < 10)
						sender.sendMessage("    " + threads.count + "          " + threads.name);
					else if (threads.count < 100)
						sender.sendMessage(ChatColor.RED + "" + threads.count + "         " + threads.name);
					else if (threads.count < 500)
						sender.sendMessage(ChatColor.DARK_RED + "    " + threads.count + "        " + threads.name);
					else
						sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "    " + threads.count + "        " + threads.name);
				}
				sender.sendMessage(ChatColor.AQUA + "    " + total + "          Total");
			}
		});
	}
	
	@CommandHandler(name = "debug.signedit", permission = "debug", description = "SignEdit packet")
	public void signedit(final CommandArgs args) {
		try {
			Player player = args.getPlayer();
			if (PlayerUtils.getTargetBlock(player).getType().equals(Material.SIGN_POST) || PlayerUtils.getTargetBlock(player).getType().equals(Material.WALL_SIGN)) {
				final Sign sign = (Sign) PlayerUtils.getTargetBlock(player).getState();
				new ReflectionPlayer(player).openSign(sign);
			}
		} catch (final Exception e) {
		}
	}

	@CommandHandler(name = "debug.gsc", permission = "debug", description = "Invoked GameStateChange packet")
	public void gsc(final CommandArgs args) {
		try {
			final ReflectionPlayer p = new ReflectionPlayer(args.getPlayer());
			final GameStateEffects eff = GameStateEffects.valueOf(args.getArgs()[0]);

			p.doGameStateChange(eff, Integer.parseInt(args.getArgs()[1]));
		} catch (final Exception e) {
		}
	}

	@CommandHandler(name = "debug.regen", permission = "debug", description = "Regenerate a chunk")
	public void regen(final CommandArgs args) {
		try {
			final Chunk c = args.getPlayer().getWorld().getChunkAt(PlayerUtils.getTargetBlock(args.getPlayer()).getLocation());
			args.getPlayer().getWorld().regenerateChunk(c.getX(), c.getZ());
		} catch (final Exception e) {
		}
	}

	private final Set<UUID> items = new HashSet<UUID>();
	private final Map<UUID, Material> itemMap = new HashMap<UUID, Material>();

	@EventHandler
	public void onMove(final PlayerMoveEvent event) {
		if (items.contains(event.getPlayer().getUniqueId())) {
			final WitherSkull base = event.getPlayer().getLocation().getWorld().spawn(event.getPlayer().getLocation(), WitherSkull.class);
			base.setDirection(new Vector(0, 0, 0));
			base.setVelocity(new Vector(0, 0, 0));
			final Entity e = event.getPlayer().getLocation().getWorld().dropItem(event.getPlayer().getLocation(), new ItemStack(itemMap.get(event.getPlayer().getUniqueId())));
			base.setPassenger(e);

			for (final Player p : Bukkit.getServer().getOnlinePlayers()) {
				final PacketBuilder builder = new PacketBuilder(p, PacketType.PLAY_OUT_ENTITY_DESTROY);
				builder.set("a", new int[] { base.getEntityId() });
				builder.send();
			}
		}
	}

	@SuppressWarnings("deprecation")
	@CommandHandler(name = "debug.item", permission = "debug", description = "Makes items follow you")
	public void item(final CommandArgs args) {
		try {
			switch (args.getArgs().length) {
				case 0:
					if (items.remove(args.getPlayer().getUniqueId())) {
						args.getPlayer().sendMessage("Item mode off");
						return;
					} else {
						items.add(args.getPlayer().getUniqueId());
						args.getPlayer().sendMessage("Item mode on");
						return;
					}
				case 1:
					itemMap.put(args.getPlayer().getUniqueId(), Material.getMaterial(Integer.parseInt(args.getArgs()[0])));
					break;
			}

		} catch (final Exception e) {
		}
	}

	@CommandHandler(name = "debug.inv", permission = "debug", description = "Test the custom inventory")
	public void inv(final CommandArgs args) {
		try {
			final CustomInventory inv = new CustomInventory(AllAssets.instance(), "My custom inv", 1);
			inv.addCustomItemStack(new VanishPlayersItemStack());
			inv.open(args.getPlayer());
		} catch (final Exception e) {
		}
	}

	@CommandHandler(name = "debug.pig", permission = "debug", description = "Test reflection of mob following")
	public void pig(final CommandArgs args) {
		try {
			final Player player = args.getPlayer();
			final LivingEntity entity = player.getWorld().spawn(player.getLocation(), Pig.class);
			final float f = 1.75F;
			Bukkit.getScheduler().scheduleSyncRepeatingTask(AllAssets.instance(), new Runnable() {
				@Override
				public void run() {
					try {
						MinecraftReflectionUtils utils = new MinecraftReflectionUtils(player);
						Object craftEntity = ReflectionUtils.getPerfectField(entity, entity.getClass(), "world");
//						Object craftEntity = entity.getClass().getDeclaredMethod("getHandle").invoke(entity);
						//						System.out.println("CraftEntity: " + craftEntity.getClass().getName());
						//						Object entityInsentient = utils.getNMSClass("EntityInsentient").cast(craftEntity);
						Class<?> eI = utils.getNMSClass("EntityInsentient");
						Class<?> nA = utils.getNMSClass("NavigationAbstract");
						
						//
						
//						Class<?> c = utils.nmsWorldClass;
						
						//
						
						
						MethodHandle mh = MethodHandles.lookup().findSpecial(eI, "getNavigation", MethodType.methodType(nA), craftEntity.getClass());
						Object navigation = mh.invoke(craftEntity);
						//						System.out.println("EntityInsentient: " + entityInsentient.getClass().getName());
						//						Object navigation = entityInsentient.getClass().getDeclaredMethod("getNavigation").invoke(entityInsentient);
						Method m = navigation.getClass().getDeclaredMethod("a", Double.class, Double.class, Double.class, Double.class);
						m.invoke(navigation, player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), f);
						//								
					} catch (Exception e) {
						//						CommandLog.addLog("Error with debug pig", LogType.ERROR);
						e.printStackTrace();
					}
					//((EntityInsentient) ((CraftEntity) e).getHandle()).getNavigation().a(p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), f);
					catch (Throwable e) {
						e.printStackTrace();
					}
				}
			}, 0, 40);
		} catch (final Exception e) {
		}
	}

	@CommandHandler(name = "debug.velocity", permission = "debug", description = "Tests the velocity")
	public void velocity(final CommandArgs args) {
		try {
			args.getPlayer().setVelocity(VectorUtils.getVectorBetweenExpensive(args.getPlayer().getLocation(), PlayerUtils.getTargetBlock(args.getPlayer()).getLocation()).multiply(2));
		} catch (final Exception e) {
		}
	}

	@SuppressWarnings("deprecation")
	@CommandHandler(name = "debug.set", permission = "debug", description = "Does something........")
	public void set(final CommandArgs args) {
		try {
			args.getPlayer().sendBlockChange(PlayerUtils.getTargetBlock(args.getPlayer()).getLocation(), Material.DIAMOND_BLOCK, (byte) 0);
		} catch (final Exception e) {
		}
	}

	@CommandHandler(name = "debug.remove", permission = "debug", description = "Removes all entities in a 256 block range.")
	public void remove(final CommandArgs args) {
		try {
			int count = 0;
			for (final Entity e : args.getPlayer().getNearbyEntities(256, 256, 256))
				if (!(e instanceof Player)) {
					e.remove();
					count++;
				}
			args.getPlayer().sendMessage(Strings.TITLE + "Cleared " + count + " entities");
		} catch (final Exception e) {
		}
	}

	@CommandHandler(name = "debug.clean", permission = "debug", description = "Cleans garbage collection")
	public void clean(final CommandArgs args) {
		final CommandSender sender = args.getSender();
		final long totalRamPre = Runtime.getRuntime().totalMemory() / 1024 / 1024;
		final long freeRamPre = Runtime.getRuntime().freeMemory() / 1024 / 1024;
		System.gc();
		final long totalRamPost = Runtime.getRuntime().totalMemory() / 1024 / 1024;
		final long freeRamPost = Runtime.getRuntime().freeMemory() / 1024 / 1024;
		sender.sendMessage(TextUtils.nonIndentedSubTitle("Clean"));
		sender.sendMessage("You now have " + totalRamPost + "MB from " + totalRamPre + "MB in total, by freeing " + (freeRamPost - freeRamPre) + "MB");
	}

	/* Not been fully tested yet */
	//	@CommandHandler(name = "debug.rclean", permission = "debug", description = "Cleans RAM at a regular interval")
	//	public void rClean(final CommandArgs args) {
	//		if (taskID == 0) {
	//			int length = 0;
	//			if ((args.getArgs().length == 1) && TextUtils.isInteger(args.getArgs()[0]))
	//				length = Integer.parseInt(args.getArgs()[0]);
	//			taskID = Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(AllAssets.instance(), new Runnable() {
	//				@Override
	//				public void run() {
	//					System.gc();
	//				}
	//			}, 0, (20 * length) == 0 ? 30 : length).getTaskId();
	//			args.getSender().sendMessage(AllAssets.title + "Cleaning RAM on a regular basis with an invertal of " + (length == 0 ? 30 : length));
	//		} else
	//			ErrorUtils.error(args.getSender(), "The interval cleaning is already running!");
	//	}

	@CommandHandler(name = "debug.ram", permission = "debug", description = "Displays RAM information")
	public void ram(final CommandArgs args) {
		final CommandSender sender = args.getSender();
		sender.sendMessage(TextUtils.title("RAM"));
		sender.sendMessage("Maximum RAM: " + (Runtime.getRuntime().maxMemory() / 1024 / 1024) + "MB");
		sender.sendMessage("Total RAM: " + (Runtime.getRuntime().totalMemory() / 1024 / 1024) + "MB");
		sender.sendMessage("Free RAM: " + (Runtime.getRuntime().freeMemory() / 1024 / 1024) + "MB");
		sender.sendMessage("Available processors (cores): " + Runtime.getRuntime().availableProcessors());
	}

	@CommandHandler(name = "debug.actionmsg", permission = "debug", description = "Sends a message with the action bar")
	public void actionmsg(final CommandArgs args) {
		try {
			String msg = JSON.getJSON(TextUtils.getMsgStringFromArgs(args.getArgs(), 0, args.getArgs().length));
			new ReflectionPlayer(args.getPlayer()).sendActionBar(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@CommandHandler(name = "debug.testencrypt", permission = "debug", description = "Runs a test")
	public void testEncrypt(final CommandArgs args) {
		if (args.getArgs().length != 2) {
			ErrorUtils.notEnoughArguments(args.getSender());
			return;
		}
		final EncryptionUtils ec = new EncryptionUtils(args.getArgs()[0]);
		try {
			final File file = new File(Files.getDirectory(Directory.STORAGE), "data.bin");
			if (!file.exists())
				file.createNewFile();
			FileUtils.saveBytesSecurely(ec.encrypt(args.getArgs()[1]), file);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		System.out.println("Key: " + args.getArgs()[0] + ", data: " + args.getArgs()[1]);
	}

	@CommandHandler(name = "debug.testdecrypt", permission = "debug", description = "Runs a test")
	public void testDecrypt(final CommandArgs args) {
		if (args.getArgs().length != 1) {
			ErrorUtils.notEnoughArguments(args.getSender());
			return;
		}
		final EncryptionUtils ec = new EncryptionUtils(args.getArgs()[0]);
		try {
			final byte[] bytes = FileUtils.loadBytesSecurely(new File(Files.getDirectory(Directory.STORAGE), "data.bin"));
			System.out.println("Decrypted message: " + ec.decrypt(bytes));
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@CommandHandler(name = "debug.anvil", permission = "debug", description = "Unloads a world")
	public void openAnvil(final CommandArgs args) {
		try {
			AllAssets.instance().getNMS().openAnvil(args.getPlayer());
			//new ReflectionPlayer(args.getPlayer()).openAnvil();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@CommandHandler(name = "debug.unloadworld", permission = "debug", description = "Unloads a world")
	public void unloadWorld(final CommandArgs args) {
		try {
			final Player player = args.getPlayer();
			final MinecraftReflectionUtils utils = new MinecraftReflectionUtils(player);

			final Object handle = utils.worldServer;
			final Object craftServer = ReflectionUtils.getPrivateFieldValue(handle, "server");
			for (final java.lang.reflect.Field f : craftServer.getClass().getFields()) {
				player.sendMessage(f.getName());
				if (f.getName().equals("worlds")) {
					f.setAccessible(true);
					final Map<String, World> cbWorlds = (Map<String, World>) f.get(craftServer);
					Debugger.printMap(cbWorlds);
				}

			}

			final Map<String, World> cbWorlds = (Map<String, World>) ReflectionUtils.getPrivateFieldValue(craftServer, "worlds");
			cbWorlds.remove(player.getWorld().getName().toLowerCase());
			ReflectionUtils.setFinalStaticField(ReflectionUtils.getPrivateField(utils.craftServer, "worlds"), cbWorlds);

			final Object console = ReflectionUtils.getPrivateFieldValue(craftServer, "console");
			final List<?> worlds = (List<?>) ReflectionUtils.getPrivateFieldValue(console, "worlds");
			worlds.remove(worlds.indexOf(handle));
			ReflectionUtils.setPrivateField(console, "worlds", worlds);

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/* Used to debug the Log feature */
		@CommandHandler(name = "debug.error", permission = "debug", description = "Creates an error")
		public void error(final CommandArgs args) {
			final String[] arr = { "bob", "mark" };
			final String s = arr[4];
			Bukkit.getServer().broadcastMessage(s);
		}

	//will be removed in future
	//	@CommandHandler(name = "debug.test", permission = "debug", description = "Runs a test")
	//	public void test(final CommandArgs args) {
	//		String multilineString = "Plugin                        `Thread\n";
	//
	//		multilineString += "AllInOne`30\n";
	//		multilineString += "Essentials`491";
	//		final TabText tt = new TabText(multilineString);
	//		tt.setPageHeight(10); // set page height and get number of pages
	//		tt.setTabs(16, 22, 20); // horizontal tabs positions
	//		tt.sortByFields(-2, 1); // sort by second column descending, then by first
	//		final String printedText = tt.getPage(0, false); // get your formatted page, for console or chat area
	//		AllAssets.instance().getServer().broadcastMessage(printedText);
	//	}

	@CommandHandler(name = "debug.conflicts", permission = "debug", description = "Finds plugin conflicts", isListed = false)
	public void test1(final CommandArgs args) {
		int conflict = 0;
		final Set<String> conflictingPlugins = new HashSet<String>();
		for (final HelpTopic cmdLabel : Bukkit.getServer().getHelpMap().getHelpTopics()) {
			final String s = cmdLabel.getName();
			if (s.contains(":")) {
				if (s.contains("allassets:") || s.contains("bukkit:") || s.contains("minecraft:"))
					continue;
				args.getSender().sendMessage(s);
				conflictingPlugins.add(s.split(":")[0]);
				conflict++;
			}
		}
		args.getSender().sendMessage(Strings.TITLE + "There are " + conflict + " conflicting commands" + (conflict == 0 ? "" : " - Conflicting plugins:"));
		for (final String s : conflictingPlugins)
			args.getSender().sendMessage(Strings.HOUSE_STYLE_COLOR + s);
	}

	@CommandHandler(name = "debug.reset", permission = "debug", description = "Resets a player's settings. Resets Config etc.", isListed = false)
	public void reset(final CommandArgs args) {
	}

	boolean physics = true;

	@CommandHandler(name = "debug.physics", permission = "debug", description = "Toggles server physics", isListed = false)
	public void stopPhysics(final CommandArgs args) {
		if (physics) {
			physics = false;
			Bukkit.broadcastMessage(Strings.TITLE + "Paused server physics");
		} else {
			physics = true;
			Bukkit.broadcastMessage(Strings.TITLE + "Resumed server physics");
		}
	}

	boolean explosions = true;

	@CommandHandler(name = "debug.explosions", permission = "debug", description = "Toggles explosions")
	public void toggleExplosions(final CommandArgs args) {
		if (explosions) {
			explosions = false;
			Bukkit.broadcastMessage(Strings.TITLE + "Explosions have been turned off");
		} else {
			explosions = true;
			Bukkit.broadcastMessage(Strings.TITLE + "Explosions have been turned on");
		}
	}

	@EventHandler
	public void onExplode(final EntityExplodeEvent event) {
		if (!explosions) {
			event.setCancelled(true);
			event.setYield(0.0F);
		}
	}

	@EventHandler
	public void onPhysics(final BlockPhysicsEvent event) {
		if (!physics)
			event.setCancelled(true);
	}

	@EventHandler
	public void onBlockFall(final EntityChangeBlockEvent event) {
		if (!physics)
			event.setCancelled(true);
	}

	@EventHandler
	public void onLiquidFlow(final BlockFromToEvent event) {
		if (!physics)
			event.setCancelled(true);
	}

	@Completer(name = "debug")
	public List<String> testCompleter(final CommandArgs args) {
		final List<String> list = new ArrayList<String>();
		list.add("full");
		list.add("ram");
		list.add("clean");
		list.add("physics");
		list.add("remove");
		list.add("explosions");
		list.add("conflicts");
		list.add("testencrypt");
		list.add("testdecrypt");
		list.add("set");
		list.add("regen");
		list.add("inv");
		list.add("gsc");
		list.add("actionmsg");
		list.add("pig");
		return list;
	}

	class ThreadCount implements Comparator<ThreadCount> {
		public int count;
		public String name;

		public ThreadCount(final String name, final int count) {
			this.count = count;
			this.name = name;
		}

		@Override
		public int compare(final ThreadCount a, final ThreadCount b) {
			if (a.count > b.count)
				return 1;
			if (a.count == b.count)
				return 0;
			return -1;
		}
	}
}
