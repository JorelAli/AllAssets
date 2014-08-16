package io.github.Skepter;

import io.github.Skepter.Commands.CommandAFK;
import io.github.Skepter.Commands.CommandAllInOne;
import io.github.Skepter.Commands.CommandBack;
import io.github.Skepter.Commands.CommandBalance;
import io.github.Skepter.Commands.CommandBatch;
import io.github.Skepter.Commands.CommandBind;
import io.github.Skepter.Commands.CommandChestSearch;
import io.github.Skepter.Commands.CommandClear;
import io.github.Skepter.Commands.CommandClearchat;
import io.github.Skepter.Commands.CommandConsoleLog;
import io.github.Skepter.Commands.CommandDebug;
import io.github.Skepter.Commands.CommandDisable;
import io.github.Skepter.Commands.CommandEnable;
import io.github.Skepter.Commands.CommandEnchant;
import io.github.Skepter.Commands.CommandFly;
import io.github.Skepter.Commands.CommandForceChat;
import io.github.Skepter.Commands.CommandFramework;
import io.github.Skepter.Commands.CommandLaunch;
import io.github.Skepter.Commands.CommandLog;
import io.github.Skepter.Commands.CommandOplist;
import io.github.Skepter.Commands.CommandPTime;
import io.github.Skepter.Commands.CommandPWeather;
import io.github.Skepter.Commands.CommandPing;
import io.github.Skepter.Commands.CommandSet;
import io.github.Skepter.Commands.CommandSignEdit;
import io.github.Skepter.Commands.CommandTime;
import io.github.Skepter.Commands.CommandTp;
import io.github.Skepter.Commands.CommandWorlds;
import io.github.Skepter.Config.ConfigHandler;
import io.github.Skepter.Config.PlayerData;
import io.github.Skepter.Config.UUIDData;
import io.github.Skepter.Listeners.ChatListener;
import io.github.Skepter.Listeners.ConsoleSayListener;
import io.github.Skepter.Listeners.EnchantGuiListener;
import io.github.Skepter.Listeners.LogListener;
import io.github.Skepter.Listeners.MultiCommandListener;
import io.github.Skepter.Listeners.PlayerListener;
import io.github.Skepter.Listeners.PluginsCommandListener;
import io.github.Skepter.Listeners.ReloadCommandListener;
import io.github.Skepter.Listeners.SignListener;
import io.github.Skepter.Tasks.TPS;
import io.github.Skepter.Utils.JavaUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.milkbowl.vault.economy.Economy;

import org.apache.logging.log4j.LogManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/** AllInOne plugin, version 1.0
 * 
 * Thanks to (Yes, I give you guys credit here - this couldn't have been done
 * without you and for that I am very grateful for your hard work!):
 * 
 * @BukkitForums Plo124 - IPUtils
 * @BukkitDev AmoebaMan - ReflectionUtil (DarkBlade12)
 * @BukkitDev mkremins - 'Fanciful' Messaging format
 * @BukkitDev Minnymin3 - CommandFramework class
 * @BukkitDev Comphenix - GhostFactory
 * @BukkitDev Logout400 - SimpleConfig, SimpleConfigManager
 * @BukkitDev Desht - ExperienceUtils
 * @BukkitDev DPOHVAR - ReflectionUtils
 * @BukkitDev RainoBot97 - SimpleScoreboard
 * @SpecialThanks EssentialsTeam - Plugin which this idea was based on
 * @SpecialThanks BukkitTeam - Making the entire thing possible
 * 
 * 
 * @author Skepter */

// something like WE with /replace <block> <radius>
// onJoinAction - fireworks, command etc.
// firework interface like Enchant - use For loops to generate it

// custom beacon?

// Explore the ResourceBundle for setting Locale
// A way to mute a player which stops all other chat being sent to that player
// except admin

// run multiple commands - | seperator - config available
// set command
// sort out switch statements on strings and use toLowerCase to make it case safe
// add messages after commands (e.g. you successfully set the time to day etc.)
// delete plugin command (command that deletes plugins) + questioner
// command allinone pluginFile instead of devPluginFile
// how many unique players
// plugin config manager thingy
public class AllInOne extends JavaPlugin {

	/* These will be in the messages.yml (when I feel ready to do so) */
	public final String title = ChatColor.BLUE + "[" + ChatColor.AQUA + "AllInOne" + ChatColor.BLUE + "]" + ChatColor.WHITE + " ";
	public final String titleNoColor = "[AllInOne] ";
	public final String error = ChatColor.DARK_RED + "[" + ChatColor.RED + "AllInOne" + ChatColor.DARK_RED + "]" + ChatColor.RED + " ";
	public final String houseStyleColor = ChatColor.AQUA + "";

	public boolean hasVault = false;
	public Economy economy = null;
	public CommandFramework framework;

	public Map<UUID, Long> tempTimeMap;

	//public GhostFactory ghostFactory;

	@Override
	public void onEnable() {
		getLogger().info("+---------------------------------+");
		getLogger().info(titleNoColor + "Initializing AllInOne version " + getDescription().getVersion());
		/* Some names will be removed - depends on whatever is in the Libs package */
		getLogger().info("AllInOne, created by Skepter. Special thanks to: Plo124, AmoebaMan, mkremins, Minnymin3, Comphenix, Logout400, Desht, DPOHVAR and RainoBot97");
		/* A method of dealing with console errors and stuff ... I hope */
		((org.apache.logging.log4j.core.Logger) LogManager.getRootLogger()).addFilter(new LogListener(this));
		tempTimeMap = new HashMap<UUID, Long>();
		framework = new CommandFramework(this);
		new ConfigHandler();
		
		if (Bukkit.getPluginManager().getPlugin("Vault") == null || !Bukkit.getPluginManager().getPlugin("Vault").isEnabled()) {
			getLogger().warning(titleNoColor + "Vault not found, so some features may not be available");
		} else {
			hasVault = true;
			setupEconomy();
			getLogger().info(titleNoColor + "Vault has been found and hooked into successfully");
		}

		//ghostFactory = new GhostFactory(this);
		framework.registerCommands(this);

		/** This is the features.yml file which enables/disables features
		 * according to the users will */

		//add ping & put it into ConfigHandler
		getLogger().info("Initializing commands according to features.yml");
		if (ConfigHandler.instance().features().getBoolean("AFK"))
			r(new CommandAFK(framework));
		if (ConfigHandler.instance().features().getBoolean("AllInOne"))
			new CommandAllInOne(framework);
		if (ConfigHandler.instance().features().getBoolean("Back"))
			new CommandBack(framework);
		if (ConfigHandler.instance().features().getBoolean("Balance") && hasVault)
			new CommandBalance(framework);
		if (ConfigHandler.instance().features().getBoolean("Batch"))
			new CommandBatch(framework);
		if (ConfigHandler.instance().features().getBoolean("Bind"))
			r(new CommandBind(framework));
		if (ConfigHandler.instance().features().getBoolean("ChestSearch"))
			new CommandChestSearch(framework);
		if (ConfigHandler.instance().features().getBoolean("Clear"))
			new CommandClear(framework);
		if (ConfigHandler.instance().features().getBoolean("ClearChat"))
			new CommandClearchat(framework);
		if (ConfigHandler.instance().features().getBoolean("ConsoleLog"))
			new CommandConsoleLog(framework);
		if (ConfigHandler.instance().features().getBoolean("Debug"))
			new CommandDebug(framework);
		if (ConfigHandler.instance().features().getBoolean("Disable"))
			new CommandDisable(framework);
		if (ConfigHandler.instance().features().getBoolean("Enchant"))
			new CommandEnchant(framework);
		if (ConfigHandler.instance().features().getBoolean("Enable"))
			new CommandEnable(framework);
		if (ConfigHandler.instance().features().getBoolean("ForceChat"))
			new CommandForceChat(framework);
		if (ConfigHandler.instance().features().getBoolean("Fly"))
			new CommandFly(framework);
		if (ConfigHandler.instance().features().getBoolean("Launch"))
			new CommandLaunch(framework);
		if (ConfigHandler.instance().features().getBoolean("Log"))
			new CommandLog(framework);
		//if (ConfigHandler.instance().features().getBoolean("Ghost"))
		//new CommandGhost(framework);
		if (ConfigHandler.instance().features().getBoolean("Oplist"))
			new CommandOplist(framework);
		if (ConfigHandler.instance().features().getBoolean("Ping"))
			new CommandPing(framework);
		if (ConfigHandler.instance().features().getBoolean("PTime"))
			new CommandPTime(framework);
		if (ConfigHandler.instance().features().getBoolean("PWeather"))
			new CommandPWeather(framework);
		if (ConfigHandler.instance().features().getBoolean("Set"))
			r(new CommandSet(framework)); /* Finish */
		if (ConfigHandler.instance().features().getBoolean("SignEdit"))
			new CommandSignEdit(framework);
		if (ConfigHandler.instance().features().getBoolean("Time"))
			new CommandTime(framework);
		if (ConfigHandler.instance().features().getBoolean("Tp"))
			new CommandTp(framework);
		if (ConfigHandler.instance().features().getBoolean("Worlds"))
			new CommandWorlds(framework);

		/* Listeners */
		if (ConfigHandler.instance().features().getBoolean("Enchant"))
			r(new EnchantGuiListener());
		r(new ChatListener());
		r(new SignListener());
		r(new PlayerListener());
		if (ConfigHandler.instance().features().getBoolean("Plugins"))
			r(new PluginsCommandListener());
		if (ConfigHandler.instance().features().getBoolean("Reload"))
			r(new ReloadCommandListener());
		if (ConfigHandler.instance().features().getBoolean("MultiCommands"))
			r(new MultiCommandListener());

		// r(new SLP_MOTD());
		r(new ConsoleSayListener());

		UUIDData data = new UUIDData();
		data.reloadDataFile();
		for (final Player p : Bukkit.getOnlinePlayers()) {
			data.getDataFile().set(p.getName(), p.getUniqueId().toString());
		}

		/* Start TPS counter */
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new TPS(), 100L, 1L);

		try {
			if (new File(getDataFolder(), "tempTimeMap.bin").exists()) {
				JavaUtils.load(new File(getDataFolder(), "tempTimeMap.bin"));
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}

		getLogger().info(titleNoColor + "AllInOne has been enabled successfully");
		Bukkit.broadcast(title + "Plugin reloaded!", "AllInOne.allinone");
		getLogger().info("+---------------------------------+");
	}

	private void r(final Listener l) {
		getServer().getPluginManager().registerEvents(l, this);
	}

	@Override
	public void onDisable() {
		CommandConsoleLog.players.clear();
		Bukkit.getServer().getScheduler().cancelTasks(this);
		try {
			if (!tempTimeMap.isEmpty()) {
				JavaUtils.save(tempTimeMap, new File(getDataFolder(), "tempTimeMap.bin"));
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		PlayerData.saveAllPlayers();
		getLogger().info(titleNoColor + getDescription().getVersion() + " has been disabled successfully");
	}

	public static AllInOne instance() {
		return JavaPlugin.getPlugin(AllInOne.class);
	}

	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
		return framework.handleCommand(sender, label, command, args);
	}

	private boolean setupEconomy() {
		final RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null) {
			economy = economyProvider.getProvider();
		}

		return (economy != null);
	}
}
