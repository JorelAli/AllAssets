/*******************************************************************************
 * Skepter's licence
 * Copyright 2014 
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
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. I've just made life easier for you :)
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
package io.github.Skepter.AllAssets;

import io.github.Skepter.AllAssets.CommandListeners.CommandAFK;
import io.github.Skepter.AllAssets.CommandListeners.CommandBind;
import io.github.Skepter.AllAssets.CommandListeners.CommandEnchant;
import io.github.Skepter.AllAssets.CommandListeners.CommandFileBrowser;
import io.github.Skepter.AllAssets.CommandListeners.CommandFileEditor;
import io.github.Skepter.AllAssets.CommandListeners.CommandFirework;
import io.github.Skepter.AllAssets.CommandListeners.CommandGod;
import io.github.Skepter.AllAssets.CommandListeners.CommandSilence;
import io.github.Skepter.AllAssets.CommandListeners.CommandStaffChat;
import io.github.Skepter.AllAssets.Commands.CommandBackup;
import io.github.Skepter.AllAssets.Commands.CommandClear;
import io.github.Skepter.AllAssets.Commands.CommandGhost;
import io.github.Skepter.AllAssets.Commands.CommandGlow;
import io.github.Skepter.AllAssets.Commands.CommandGrief;
import io.github.Skepter.AllAssets.Commands.CommandMore;
import io.github.Skepter.AllAssets.Commands.CommandNear;
import io.github.Skepter.AllAssets.Commands.CommandPTime;
import io.github.Skepter.AllAssets.Commands.CommandPWeather;
import io.github.Skepter.AllAssets.Commands.CommandRename;
import io.github.Skepter.AllAssets.Commands.CommandWorkbench;
import io.github.Skepter.AllAssets.Commands.Administration.CommandAllAssets;
import io.github.Skepter.AllAssets.Commands.Administration.CommandAnnouncer;
import io.github.Skepter.AllAssets.Commands.Administration.CommandButcher;
import io.github.Skepter.AllAssets.Commands.Administration.CommandChestSearch;
import io.github.Skepter.AllAssets.Commands.Administration.CommandClearchat;
import io.github.Skepter.AllAssets.Commands.Administration.CommandConsoleLog;
import io.github.Skepter.AllAssets.Commands.Administration.CommandDisable;
import io.github.Skepter.AllAssets.Commands.Administration.CommandEnable;
import io.github.Skepter.AllAssets.Commands.Administration.CommandFly;
import io.github.Skepter.AllAssets.Commands.Administration.CommandForceChat;
import io.github.Skepter.AllAssets.Commands.Administration.CommandGamemode;
import io.github.Skepter.AllAssets.Commands.Administration.CommandInventory;
import io.github.Skepter.AllAssets.Commands.Administration.CommandLog;
import io.github.Skepter.AllAssets.Commands.Administration.CommandNMSGod;
import io.github.Skepter.AllAssets.Commands.Administration.CommandOplist;
import io.github.Skepter.AllAssets.Commands.Administration.CommandSignEdit;
import io.github.Skepter.AllAssets.Commands.Administration.CommandTime;
import io.github.Skepter.AllAssets.Commands.Administration.CommandWeather;
import io.github.Skepter.AllAssets.Commands.Debug.CommandBatch;
import io.github.Skepter.AllAssets.Commands.Debug.CommandDebug;
import io.github.Skepter.AllAssets.Commands.Debug.CommandPing;
import io.github.Skepter.AllAssets.Commands.Economy.CommandBalance;
import io.github.Skepter.AllAssets.Commands.Economy.CommandBalancetop;
import io.github.Skepter.AllAssets.Commands.Fun.CommandDiscoArmor;
import io.github.Skepter.AllAssets.Commands.Fun.CommandLaunch;
import io.github.Skepter.AllAssets.Commands.Teleportation.CommandBack;
import io.github.Skepter.AllAssets.Commands.Teleportation.CommandTp;
import io.github.Skepter.AllAssets.Commands.Teleportation.CommandTphere;
import io.github.Skepter.AllAssets.Commands.Teleportation.CommandWorlds;
import io.github.Skepter.AllAssets.Config.ConfigHandler;
import io.github.Skepter.AllAssets.Config.PlayerData;
import io.github.Skepter.AllAssets.Config.UUIDData;
import io.github.Skepter.AllAssets.Libs.ComphenixsGhostFactory;
import io.github.Skepter.AllAssets.Listeners.ChatListener;
import io.github.Skepter.AllAssets.Listeners.CommandCooldownListener;
import io.github.Skepter.AllAssets.Listeners.ConsoleSayListener;
import io.github.Skepter.AllAssets.Listeners.CustomUnknownCommandListener;
import io.github.Skepter.AllAssets.Listeners.LogListener;
import io.github.Skepter.AllAssets.Listeners.MultiCommandListener;
import io.github.Skepter.AllAssets.Listeners.PlayerListener;
import io.github.Skepter.AllAssets.Listeners.PluginsCommandListener;
import io.github.Skepter.AllAssets.Listeners.ReloadCommandListener;
import io.github.Skepter.AllAssets.Listeners.ServerListingListener;
import io.github.Skepter.AllAssets.Listeners.SignListener;
import io.github.Skepter.AllAssets.Listeners.SkeletonArrowListener;
import io.github.Skepter.AllAssets.Listeners.StopCommandListener;
import io.github.Skepter.AllAssets.Tasks.TPS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/** AllAssets plugin, version 0.4 Alpha
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
 * @idkwho - TabText
 * @SpecialThanks EssentialsTeam - Plugin which this idea was based on
 * @SpecialThanks BukkitTeam - Making the entire thing possible
 * 
 * 
 * @author Skepter */

// Explore the ResourceBundle for setting Locale

//world backup system:
/*
 *  /backup (worldName) - can be null, it just backs up this world. Copies world directory and renames it?
 *  /revert/restore (worldName) - shows a list of worldBackups and then unloads world, replaces it with
 *  new one.
 *  Put YesNo because it will COMPLETELY REPLACE (and delete) the old world
 */

//color customisation -
//normalColor: 9
//emphasisColor: b
//@ajcozzo

//recent players command - like seen, but for recent players
//still able to tp when they're offline
//do YesNo conversation for payments etc. (/pay
//friend list to find friends etc.
//a way to parse PARTS  of a player's name in commands
//play with UUIDs AGAIN - GameProfile OF entity, UserCache, player.uniqueID, UUIDData
//work on documentation - ensure EVERYTHING is REALLY clear.
/* - Things NOT to export when releasing Alpha version - Permissions
 * ExperienceUtils ItemNames MessagePart Fanciful IPUtils Reflections (All of
 * the Libs) TabText Resources Builds */

/*
 * Climb vines
 * check out commandBin
 * recipes command
 * insta-mine command
 * disposal chest
 * custom swords with poison perhaps & arrows perhaps
 * jail
 * redstone light netherrack/pumpkins/glowstone
 *
 */

/*highlight text utility
 * When a player says your username, send you (message) that message,
 * but with your username highlighted.
 * 
 * e.g. Skepter says "Hello amoniuszko20"
 * on amon's screen, the word "amoniuszko20" is in bold yellow (for example)
 * but on Skepter's screen, it's totally normal */
/*
 * A data accessing class which caches all of the data for each player
 * and then saves NEW data.
 * When using /reload (for aa), it then recaches all of the data - hence
 * making super speedy data delivery.
 * When a player leaves, remove them from the data?
 * 
 * clean up configHandler - it's so damn freaking messy!
 */
public class AllAssets extends JavaPlugin {

	/* Messages - shouldn't really be here but meh -.- */
	public final static String title = ChatColor.BLUE + "[" + ChatColor.AQUA + "AllAssets" + ChatColor.BLUE + "]" + ChatColor.WHITE + " ";
	public final static String shortTitle = ChatColor.BLUE + "[" + ChatColor.AQUA + "AA" + ChatColor.BLUE + "]" + ChatColor.WHITE + " ";
	public final static String titleNoColor = "[AllAssets] ";
	public final static String shortTitleNoColor = "[AA] ";
	public final static String error = ChatColor.DARK_RED + "[" + ChatColor.RED + "AllAssets" + ChatColor.DARK_RED + "]" + ChatColor.RED + " ";
	public final static String houseStyleColor = ChatColor.AQUA + "";

	/* Vault stuff */
	public boolean hasVault = false;
	public Economy economy = null;
	public Permission permission = null;
	public Chat chat = null;

	/* Other stuff */
	public CommandFramework framework;

	public Map<UUID, Long> tempTimeMap;
	public ComphenixsGhostFactory ghostFactory;

	public static boolean masterSwitch = false;

	/** Block where developing stuff happens. Used for easy code removal Requires
	 * masterSwitch */
	private void dev() {
	}

	@Override
	public void onEnable() {
		getLogger().info("+---------------------------------+");
		getLogger().info("Initializing AllAssets version " + getDescription().getVersion());

		/* Some names will be removed - depends on whatever is in the Libs package */
		getLogger().info("AllAssets, created by Skepter. Special thanks to: Plo124, AmoebaMan, mkremins, Minnymin3, Comphenix, Logout400, Desht, DPOHVAR and RainoBot97");

		if (!new File(getDataFolder(), "Read me.txt").exists())
			saveResource("Read me.txt", false);

		/* A method of dealing with console errors and stuff ... I hope */
		((org.apache.logging.log4j.core.Logger) org.apache.logging.log4j.LogManager.getRootLogger()).addFilter(new LogListener(this));

		tempTimeMap = new HashMap<UUID, Long>();
		framework = new CommandFramework(this);

		new ConfigHandler();

		/* Used to check if vault is available. If not, then disable the vault-specific commands such as /balance etc. */
		if ((Bukkit.getPluginManager().getPlugin("Vault") == null) || !Bukkit.getPluginManager().getPlugin("Vault").isEnabled()) {
			getLogger().warning("Vault not found, so some features may not be available");
			/* I put this here because if the plugin reloads, it may be set to true, however the owner of a server could have removed vault, thus some features would crash */
			hasVault = false;
		} else {
			hasVault = true;
			setupVault();
			getLogger().info("Vault has been found and hooked into successfully");
		}

		ghostFactory = new ComphenixsGhostFactory(this);
		framework.registerCommands(this);

		if (masterSwitch)
			dev();
		//Nav

		/* This is the features.yml file which enables/disables features according to the users will */
		getLogger().info("Initializing commands according to features.yml");
		if (ConfigHandler.instance().features().getBoolean("AFK"))
			r(new CommandAFK(framework));
		if (ConfigHandler.instance().features().getBoolean("AllAssets"))
			new CommandAllAssets(framework);
		if (ConfigHandler.instance().features().getBoolean("Announcer"))
			new CommandAnnouncer(framework);
		if (ConfigHandler.instance().features().getBoolean("Back"))
			new CommandBack(framework);
		if (ConfigHandler.instance().features().getBoolean("Backup"))
			new CommandBackup(framework);
		if (ConfigHandler.instance().features().getBoolean("Batch"))
			new CommandBatch(framework);
		if (ConfigHandler.instance().features().getBoolean("Bind"))
			r(new CommandBind(framework));
		if (ConfigHandler.instance().features().getBoolean("Butcher"))
			new CommandButcher(framework);
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
		if (ConfigHandler.instance().features().getBoolean("DiscoArmor"))
			new CommandDiscoArmor(framework);
		if (ConfigHandler.instance().features().getBoolean("Enchant"))
			r(new CommandEnchant(framework));
		if (ConfigHandler.instance().features().getBoolean("Enable"))
			new CommandEnable(framework);
		if (ConfigHandler.instance().features().getBoolean("ForceChat"))
			new CommandForceChat(framework);
		if (ConfigHandler.instance().features().getBoolean("FileBrowser"))
			r(new CommandFileBrowser(framework));
		if (ConfigHandler.instance().features().getBoolean("FileEditor"))
			r(new CommandFileEditor(framework));
		if (ConfigHandler.instance().features().getBoolean("Firework"))
			r(new CommandFirework(framework));
		if (ConfigHandler.instance().features().getBoolean("Fly"))
			new CommandFly(framework);
		if (ConfigHandler.instance().features().getBoolean("Gamemode"))
			new CommandGamemode(framework);
		if (ConfigHandler.instance().features().getBoolean("Ghost"))
			new CommandGhost(framework);
		if (ConfigHandler.instance().features().getBoolean("Glow"))
			new CommandGlow(framework);
		if (ConfigHandler.instance().features().getBoolean("God"))
			if (ConfigHandler.instance().config().getBoolean("useNMSGod"))
				new CommandNMSGod(framework);
			else
				r(new CommandGod(framework));
		if (ConfigHandler.instance().features().getBoolean("Grief"))
			new CommandGrief(framework);
		if (ConfigHandler.instance().features().getBoolean("Inventory"))
			new CommandInventory(framework);
		if (ConfigHandler.instance().features().getBoolean("Launch"))
			new CommandLaunch(framework);
		if (ConfigHandler.instance().features().getBoolean("Log"))
			new CommandLog(framework);
		if (ConfigHandler.instance().features().getBoolean("More"))
			new CommandMore(framework);
		if (ConfigHandler.instance().features().getBoolean("Near"))
			new CommandNear(framework);
		if (ConfigHandler.instance().features().getBoolean("Oplist"))
			new CommandOplist(framework);
		if (ConfigHandler.instance().features().getBoolean("Ping"))
			new CommandPing(framework);
		if (ConfigHandler.instance().features().getBoolean("Plugins"))
			r(new PluginsCommandListener());
		if (ConfigHandler.instance().features().getBoolean("PTime"))
			new CommandPTime(framework);
		if (ConfigHandler.instance().features().getBoolean("PWeather"))
			new CommandPWeather(framework);
		if (ConfigHandler.instance().features().getBoolean("Reload"))
			r(new ReloadCommandListener());
		if (ConfigHandler.instance().features().getBoolean("Rename"))
			new CommandRename(framework);
		if (ConfigHandler.instance().features().getBoolean("SignEdit"))
			new CommandSignEdit(framework);
		if (ConfigHandler.instance().features().getBoolean("Silence"))
			r(new CommandSilence(framework));
		if (ConfigHandler.instance().features().getBoolean("StaffChat"))
			r(new CommandStaffChat(framework));
		if (ConfigHandler.instance().features().getBoolean("Time"))
			new CommandTime(framework);
		if (ConfigHandler.instance().features().getBoolean("Tp"))
			new CommandTp(framework);
		if (ConfigHandler.instance().features().getBoolean("Tphere"))
			new CommandTphere(framework);
		if (ConfigHandler.instance().features().getBoolean("Weather"))
			new CommandWeather(framework);
		if (ConfigHandler.instance().features().getBoolean("Worlds"))
			new CommandWorlds(framework);
		if (ConfigHandler.instance().features().getBoolean("Workbench"))
			new CommandWorkbench(framework);

		/* Vault commands. Only loads them if Vault is enabled so that:
		 * [1] Unused commands aren't loaded
		 * [2] It's pointless having commands which don't work */
		if (hasVault) {
			if (ConfigHandler.instance().features().getBoolean("Balance"))
				new CommandBalance(framework);
			if (ConfigHandler.instance().features().getBoolean("Balancetop"))
				new CommandBalancetop(framework);
		}

		/* Listeners */
		r(new CommandCooldownListener());
		r(new ChatListener());
		r(new SignListener());
		r(new PlayerListener());
		r(new CustomUnknownCommandListener());
		//TODO put into a thingymajig
		r(new StopCommandListener());
		if (ConfigHandler.instance().features().getBoolean("ConsoleSay"))
			r(new ConsoleSayListener());
		if (ConfigHandler.instance().features().getBoolean("MultiCommands"))
			r(new MultiCommandListener());
		if (ConfigHandler.instance().features().getBoolean("PickupSkeletonArrows"))
			r(new SkeletonArrowListener());
		if (ConfigHandler.instance().features().getBoolean("ServerListMOTDCustomisation"))
			r(new ServerListingListener());
		//Buggy and deprecated until fixed
		//r(new BlockPoweredListener());

		/* Update UUIDData file */
		UUIDData.reloadDataFile();
		for (final Player p : Bukkit.getOnlinePlayers())
			UUIDData.setData(p);

		/* Start TPS counter */
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new TPS(), 100L, 1L);

		/* Update tempTimeMap.bin file */
		try {
			if (new File(getDataFolder(), "tempTimeMap.bin").exists())
				load(new File(getStorage(), "tempTimeMap.bin"));
		} catch (final Exception e) {
			e.printStackTrace();
		}

		getLogger().info(titleNoColor + "AllAssets has been enabled successfully");
		Bukkit.broadcast(title + "Plugin reloaded!", "AllAssets.allassets");
		getLogger().info("+---------------------------------+");
	}

	/* Writing getServer().... etc. is too much work :S */
	public void r(final Listener l) {
		if (masterSwitch)
			for (final Method method : l.getClass().getMethods())
				if (method.getAnnotation(EventHandler.class) != null)
					getLogger().info(shortTitleNoColor + "Added event: " + l.getClass().getSimpleName() + " - " + method.getName());
		getServer().getPluginManager().registerEvents(l, this);
	}

	@Override
	public void onDisable() {
		try {
			PlayerData.saveAllPlayers();
		} catch (final Exception e1) {
			getLogger().severe("There was an error saving the player data D:");
		}
		CommandConsoleLog.players.clear();
		getServer().getScheduler().cancelTasks(this);

		if (!tempTimeMap.isEmpty())
			try {
				save(tempTimeMap, new File(getStorage(), "tempTimeMap.bin"));
			} catch (final Exception e) {
				e.printStackTrace();
			}
		getLogger().info(titleNoColor + getDescription().getVersion() + " has been disabled successfully");
	}

	public static AllAssets instance() {
		return JavaPlugin.getPlugin(AllAssets.class);
	}

	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
		return framework.handleCommand(sender, label, command, args);
	}

	private void setupVault() {
		final RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null)
			economy = economyProvider.getProvider();
		final RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
		if (permissionProvider != null)
			permission = permissionProvider.getProvider();
		final RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
		if (chatProvider != null)
			chat = chatProvider.getProvider();
	}

	/** Saves an object to a file */
	public static void save(final Object obj, final File file) throws Exception {
		final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file, true));
		oos.writeObject(obj);
		oos.flush();
		oos.close();
	}

	/** Loads an object from a file */
	public static Object load(final File file) throws Exception {
		final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		final Object result = ois.readObject();
		ois.close();
		return result;
	}

	public static File getStorage() {
		return new File(AllAssets.instance().getDataFolder() + File.separator + "Storage");
	}
	
	public static File getWorldStorage() {
		return new File(AllAssets.instance().getDataFolder() + File.separator + "Backups");
	}
}
