package io.github.Skepter.Commands;

import io.github.Skepter.AllAssets;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Commands.CommandFramework.Completer;
import io.github.Skepter.Tasks.TPS;
import io.github.Skepter.Utils.MathUtils;
import io.github.Skepter.Utils.TextUtils;

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
import org.bukkit.help.HelpTopic;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

public class CommandDebug {

	int taskID = 0;
	
	public CommandDebug(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "debug", permission = "debug", description = "Show server data", usage = "Use <command>")
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
		sender.sendMessage("Server uptime: " + MathUtils.formatDateAtASpecificPointInTime(ManagementFactory.getRuntimeMXBean().getStartTime()));
		sender.sendMessage("Server TPS: " + strTps);
		return;
	}

	@CommandHandler(name = "debug.full", permission = "debug", description = "Show server data", usage = "Use <command>")
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
		sender.sendMessage(" Server uptime: " + MathUtils.formatDateAtASpecificPointInTime(ManagementFactory.getRuntimeMXBean().getStartTime()));
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

	@CommandHandler(name = "debug.clean", permission = "debug", description = "Cleans garbage collection", usage = "Use <command>")
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
//	@CommandHandler(name = "debug.rclean", permission = "debug", description = "Cleans RAM at a regular interval", usage = "Use <command>")
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

	@CommandHandler(name = "debug.ram", permission = "debug", description = "Displays RAM information", usage = "Use <command>")
	public void ram(final CommandArgs args) {
		final CommandSender sender = args.getSender();
		sender.sendMessage(TextUtils.title("RAM"));
		sender.sendMessage("Maximum RAM: " + (Runtime.getRuntime().maxMemory() / 1024 / 1024) + "MB");
		sender.sendMessage("Total RAM: " + (Runtime.getRuntime().totalMemory() / 1024 / 1024) + "MB");
		sender.sendMessage("Free RAM: " + (Runtime.getRuntime().freeMemory() / 1024 / 1024) + "MB");
		sender.sendMessage("Available processors (cores): " + Runtime.getRuntime().availableProcessors());
	}

	/* Used to debug the Log feature */
//	@CommandHandler(name = "debug.error", permission = "debug", description = "Creates an error", usage = "Use <command>")
//	public void error(final CommandArgs args) {
//		final String[] arr = { "bob", "mark" };
//		final String s = arr[4];
//		Bukkit.broadcastMessage(s);
//	}

	//will be removed in future
//	@CommandHandler(name = "debug.test", permission = "debug", description = "Runs a test", usage = "Use <command>")
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
//		Bukkit.broadcastMessage(printedText);
//	}

	@CommandHandler(name = "debug.conflicts", permission = "debug", description = "Finds plugin conflicts", usage = "Use <command>", isListed = false)
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
		args.getSender().sendMessage(AllAssets.title + "There were " + conflict + " commands that conflicted. Conflicting plugins:");
		for (final String s : conflictingPlugins)
			args.getSender().sendMessage(AllAssets.houseStyleColor + s);

	}

	@Completer(name = "debug")
	public List<String> testCompleter(final CommandArgs args) {
		final List<String> list = new ArrayList<String>();
		list.add("full");
		list.add("ram");
		list.add("clean");
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
