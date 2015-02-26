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
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.commands.debug;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.CommandFramework.Completer;
import io.github.skepter.allassets.tasks.TPS;
import io.github.skepter.allassets.utils.EncryptionUtils;
import io.github.skepter.allassets.utils.Files;
import io.github.skepter.allassets.utils.IDReader;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.FileUtils;
import io.github.skepter.allassets.utils.utilclasses.MathUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;
import io.github.skepter.allassets.utils.utilclasses.TimeUtils;
import io.github.skepter.allassets.utils.utilclasses.WorldUtils;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
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

	@CommandHandler(name = "debug.unloadworld", permission = "debug", description = "Unloads a world")
	public void unloadWorld(final CommandArgs args) {
		if (args.getArgs().length != 1) {
			ErrorUtils.notEnoughArguments(args.getSender());
			return;
		}
		WorldUtils utils = null;
		try {
			utils = new WorldUtils(args.getPlayer());
		} catch (Exception e1) {
		}
		try {
			utils.forceUnloadWorld();
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
		list.add("conflicts");
		list.add("testencrypt");
		list.add("testdecrypt");
		list.add("testid");
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
