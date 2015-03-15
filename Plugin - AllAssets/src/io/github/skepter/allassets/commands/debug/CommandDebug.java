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
import io.github.skepter.allassets.api.utils.Cuboid;
import io.github.skepter.allassets.api.utils.Debugger;
import io.github.skepter.allassets.reflection.MinecraftReflectionUtils;
import io.github.skepter.allassets.reflection.ReflectionPlayer;
import io.github.skepter.allassets.reflection.ReflectionUtils;
import io.github.skepter.allassets.tasks.TPS;
import io.github.skepter.allassets.test.VanishPlayersItemStack;
import io.github.skepter.allassets.utils.EncryptionUtils;
import io.github.skepter.allassets.utils.Files;
import io.github.skepter.allassets.utils.IDReader;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.FileUtils;
import io.github.skepter.allassets.utils.utilclasses.MathUtils;
import io.github.skepter.allassets.utils.utilclasses.PlayerUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;
import io.github.skepter.allassets.utils.utilclasses.TimeUtils;
import io.github.skepter.allassets.utils.utilclasses.VectorUtils;

import java.io.File;
import java.lang.management.ManagementFactory;
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
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.help.HelpTopic;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

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
		sender.sendMessage(" Server uptime: " + TimeUtils.formatDateAtASpecificPointInTime(ManagementFactory.getRuntimeMXBean().getStartTime()));
		sender.sendMessage(" Server TPS: " + strTps);
		sender.sendMessage("");
		sender.sendMessage(" Worlds loaded: " + Bukkit.getWorlds().size());
		sender.sendMessage(" Items:");
		for (final World w : Bukkit.getWorlds()) {
			int i = 0;
			for (final Entity e : w.getEntities())
				if (e instanceof Item)
					i++;
			sender.sendMessage(" " + w.getName() + ": " + i + " items / " + w.getEntities().size() + " entities");
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
		sender.sendMessage(" " + i + " items out of " + all + " overall entities");
		sender.sendMessage("");
		sender.sendMessage(TextUtils.nonIndentedSubTitle("RAM"));
		sender.sendMessage(" Maximum RAM: " + (Runtime.getRuntime().maxMemory() / 1024 / 1024) + "MB");
		sender.sendMessage(" Total RAM: " + (Runtime.getRuntime().totalMemory() / 1024 / 1024) + "MB");
		sender.sendMessage(" Free RAM: " + (Runtime.getRuntime().freeMemory() / 1024 / 1024) + "MB");
		sender.sendMessage(" Available processors (cores): " + Runtime.getRuntime().availableProcessors());
		sender.sendMessage("");
		sender.sendMessage(TextUtils.nonIndentedSubTitle("OS"));
		sender.sendMessage(" Operating system: " + ManagementFactory.getRuntimeMXBean().getSystemProperties().get("os.name"));
		sender.sendMessage(" OS architecture: " + ManagementFactory.getRuntimeMXBean().getSystemProperties().get("os.arch"));
		sender.sendMessage(" Java version: " + ManagementFactory.getRuntimeMXBean().getSystemProperties().get("java.version"));
		sender.sendMessage("");
		sender.sendMessage(TextUtils.nonIndentedSubTitle("Bukkit"));
		if (Bukkit.getVersion().toLowerCase().contains("spigot"))
			sender.sendMessage(" Bukkit system: Spigot");
		else if (Bukkit.getVersion().toLowerCase().contains("mcpc"))
			sender.sendMessage(" Bukkit system: MCPC");
		else if (Bukkit.getVersion().toLowerCase().contains("forge"))
			sender.sendMessage(" Bukkit system: Forge");
		else
			sender.sendMessage(" Bukkit system: CraftBukkit");
		sender.sendMessage(" Suitable for Minecraft version " + TextUtils.stringBetween(Bukkit.getVersion(), "(MC: ", ")"));
		sender.sendMessage(" Using API version " + Bukkit.getBukkitVersion());
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

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Worldedit like function																																				   //
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//Simple toggles and maps to store player information 
	private List<UUID> wePlayers = new ArrayList<UUID>();
	private Map<UUID, Location> pos1 = new HashMap<UUID, Location>();
	private Map<UUID, Location> pos2 = new HashMap<UUID, Location>();

	//Adds the positions to the maps when they click whatever.
	@EventHandler
	public void we(PlayerInteractEvent e) {
		if (wePlayers.contains(e.getPlayer().getUniqueId())) {
			int x = e.getClickedBlock().getX(), y = e.getClickedBlock().getY(), z = e.getClickedBlock().getZ();
			switch (e.getAction()) {
				case LEFT_CLICK_BLOCK:
					pos1.put(e.getPlayer().getUniqueId(), e.getClickedBlock().getLocation());
					e.getPlayer().sendMessage("pos1 = [" + x + ", " + y + ", " + z + "]");
					e.setCancelled(true);
					break;
				case RIGHT_CLICK_BLOCK:
					pos2.put(e.getPlayer().getUniqueId(), e.getClickedBlock().getLocation());
					e.getPlayer().sendMessage("pos2 = [" + x + ", " + y + ", " + z + "]");
					e.setCancelled(true);
					break;
				default:
					break;

			}
		}
	}

	//main command
	@SuppressWarnings("deprecation")
	//Since we have a . here, it counts it as a separate argument and resets the args.
	//Only available in AllAssets. Don't worry too much about it
	//This changes the command from /debug <info> to /debug we <info>
	@CommandHandler(name = "debug.we", permission = "debug", description = "WorldEdit")
	public void we(final CommandArgs args) {
		try {
			//Toggles if they have worldedit mode on or not
			if (args.getArgs().length == 0)
				if (wePlayers.contains(args.getPlayer().getUniqueId())) {
					wePlayers.remove(args.getPlayer().getUniqueId());
					args.getPlayer().sendMessage("WorldEdit mode off");
					return;
				} else {
					wePlayers.add(args.getPlayer().getUniqueId());
					args.getPlayer().sendMessage("WorldEdit mode on");
					return;
				}
			else
				//sets the blocks. Only has the (//set) feature as of now
				//Uses a switch statement (in other words, if the player types /debug we set)
				switch (args.getArgs()[0]) {
				//If they type /debug we set
					case "set":
						//gets all of the blocks between the two points
						List<Block> blocks = Cuboid.blocksFromTwoPoints(pos1.get(args.getPlayer().getUniqueId()), pos2.get(args.getPlayer().getUniqueId()));
						final Material mat = Material.getMaterial(Integer.parseInt(args.getArgs()[1]));

						//Removes unnecessary blocks to speed up process :D
						for (Block b : blocks) {
							if (b.getType().equals(mat))
								blocks.remove(b);
						}
						args.getPlayer().sendMessage(Strings.TITLE + "Setting " + blocks.size() + " blocks to " + TextUtils.capitalize(mat.name().toLowerCase()));
						//splits up the task into 250 'chunks' (sets 250 blocks at a time)
						int divisor = 250;

						//Clean up the rest of the blocks which didn't get finished
						//E.g. we have 104 blocks, 4 of them won't be set since
						//104 divided by 100 = 1 (remainder 4)
						for (Block b : blocks.subList((blocks.size() - divisor) + (blocks.size() % divisor), blocks.size()))
							b.setType(mat);

						//advanced for loop. Don't panic, it just loops through all of the blocks.
						for (int i = 0; i < blocks.size() - divisor; i += divisor) {

							//Gets all of the blocks (since we have 'chunked' them together
							//get the 'chunked' blocks
							final List<Block> blocksList = blocks.subList(i, i + divisor);

							//Use a delayed task to set the blocks. Setting them all at once
							//creates lots of server lag for more blocks.
							Bukkit.getScheduler().scheduleSyncDelayedTask(AllAssets.instance(), new Runnable() {

								@Override
								public void run() {
									//Sets the blocks.
									for (Block b : blocksList)
										b.setType(mat);
								}
								//Uses some maths to calculate when to do the next delayed task
							}, (i / divisor) * 5);
						}

						//We're inside a switch statement. We exit it by using break (advanced)
						break;
				}
		} catch (Exception e) {
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// End of Worldedit like function																																				   //
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@CommandHandler(name = "debug.regen", permission = "debug", description = "Regenerate a chunk")
	public void regen(final CommandArgs args) {
		try {
			Chunk c = args.getPlayer().getWorld().getChunkAt(PlayerUtils.getTargetBlock(args.getPlayer()).getLocation());
			args.getPlayer().getWorld().regenerateChunk(c.getX(), c.getZ());
		} catch (Exception e) {
		}
	}

	@CommandHandler(name = "debug.inv", permission = "debug", description = "Test the custom inventory")
	public void inv(final CommandArgs args) {
		try {
			CustomInventory inv = new CustomInventory(AllAssets.instance(), "My custom inv", 1);
			inv.addCustomItemStack(new VanishPlayersItemStack());
			inv.open(args.getPlayer());
		} catch (Exception e) {
		}
	}

	@CommandHandler(name = "debug.velocity", permission = "debug", description = "Tests the velocity")
	public void velocity(final CommandArgs args) {
		try {
			args.getPlayer().setVelocity(VectorUtils.getVectorBetweenExpensive(args.getPlayer().getLocation(), PlayerUtils.getTargetBlock(args.getPlayer()).getLocation()).multiply(2));
		} catch (Exception e) {
		}
	}

	@SuppressWarnings("deprecation")
	@CommandHandler(name = "debug.set", permission = "debug", description = "Does something........")
	public void set(final CommandArgs args) {
		try {
			args.getPlayer().sendBlockChange(PlayerUtils.getTargetBlock(args.getPlayer()).getLocation(), Material.DIAMOND_BLOCK, (byte) 0);
		} catch (Exception e) {
		}
	}

	@CommandHandler(name = "debug.remove", permission = "debug", description = "Removes all entities in a 256 block range.")
	public void remove(final CommandArgs args) {
		try {
			int count = 0;
			for (Entity e : args.getPlayer().getNearbyEntities(256, 256, 256))
				if (!(e instanceof Player)) {
					e.remove();
					count++;
				}
			args.getPlayer().sendMessage(Strings.TITLE + "Cleared " + count + " entities");
		} catch (Exception e) {
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

	@CommandHandler(name = "debug.testid", permission = "debug", description = "Runs a test")
	public void testID(final CommandArgs args) {
		System.out.println(args.getArgs()[0]);
		args.getSender().sendMessage("The ID for " + args.getArgs()[0] + " is " + IDReader.readID(args.getArgs()[0]));
	}

	@CommandHandler(name = "debug.testencrypt", permission = "debug", description = "Runs a test")
	public void testEncrypt(final CommandArgs args) {
		if (args.getArgs().length != 2) {
			ErrorUtils.notEnoughArguments(args.getSender());
			return;
		}
		final EncryptionUtils ec = new EncryptionUtils(args.getArgs()[0]);
		try {
			final File file = new File(Files.getStorage(), "data.bin");
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
			final byte[] bytes = FileUtils.loadBytesSecurely(new File(Files.getStorage(), "data.bin"));
			System.out.println("Decrypted message: " + ec.decrypt(bytes));
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@CommandHandler(name = "debug.anvil", permission = "debug", description = "Unloads a world")
	public void openAnvil(final CommandArgs args) {
		try {
			new ReflectionPlayer(args.getPlayer()).openAnvil();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@CommandHandler(name = "debug.unloadworld", permission = "debug", description = "Unloads a world")
	public void unloadWorld(final CommandArgs args) {
		try {
			Player player = args.getPlayer();
			MinecraftReflectionUtils utils = new MinecraftReflectionUtils(player);

			Object handle = utils.worldServer;
			Object craftServer = ReflectionUtils.getPrivateFieldValue(handle, "server");
			for (java.lang.reflect.Field f : craftServer.getClass().getFields()) {
				player.sendMessage(f.getName());
				if (f.getName().equals("worlds")) {
					f.setAccessible(true);
					Map<String, World> cbWorlds = (Map<String, World>) f.get(craftServer);
					Debugger.printMap(cbWorlds);
				}

			}

			Map<String, World> cbWorlds = (Map<String, World>) ReflectionUtils.getPrivateFieldValue(craftServer, "worlds");
			cbWorlds.remove(player.getWorld().getName().toLowerCase());
			ReflectionUtils.setFinalStaticField(ReflectionUtils.getPrivateField(utils.craftServer, "worlds"), cbWorlds);

			Object console = ReflectionUtils.getPrivateFieldValue(craftServer, "console");
			List<?> worlds = (List<?>) ReflectionUtils.getPrivateFieldValue(console, "worlds");
			worlds.remove(worlds.indexOf(handle));
			ReflectionUtils.setPrivateField(console, "worlds", worlds);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Used to debug the Log feature */
	//	@CommandHandler(name = "debug.error", permission = "debug", description = "Creates an error")
	//	public void error(final CommandArgs args) {
	//		final String[] arr = { "bob", "mark" };
	//		final String s = arr[4];
	//		AllAssets.instance().getServer().broadcastMessage(s);
	//	}

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
	public void onExplode(EntityExplodeEvent event) {
		if (!explosions) {
			event.setCancelled(true);
			event.setYield(0.0F);
		}
	}

	@EventHandler
	public void onPhysics(BlockPhysicsEvent event) {
		if (!physics)
			event.setCancelled(true);
	}

	@EventHandler
	public void onBlockFall(EntityChangeBlockEvent event) {
		if (!physics)
			event.setCancelled(true);
	}

	@EventHandler
	public void onLiquidFlow(BlockFromToEvent event) {
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
		list.add("testid");
		list.add("set");
		list.add("regen");
		list.add("inv");
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
