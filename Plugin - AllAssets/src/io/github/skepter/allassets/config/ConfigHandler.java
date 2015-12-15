/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and MCSpartans
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and MCSpartans
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
package io.github.skepter.allassets.config;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.utils.Strings;

import java.io.File;
import java.util.Arrays;
import java.util.Map.Entry;

public class ConfigHandler {

	private SimpleConfigManager configManager;
	private SimpleConfigManager featuresManager;
	private SimpleConfigManager messagesManager;
	private SimpleConfigManager announcerManager;
	private SimpleConfigManager warpsManager;

	private SimpleConfig config;
	private SimpleConfig features;
	private SimpleConfig messages;
	private SimpleConfig announcer;
	private SimpleConfig warps;

	public ConfigHandler() {
		if (configManager == null)
			configManager = new SimpleConfigManager(AllAssets.instance());
		if (messagesManager == null)
			messagesManager = new SimpleConfigManager(AllAssets.instance());
		if (featuresManager == null)
			featuresManager = new SimpleConfigManager(AllAssets.instance());
		if (announcerManager == null)
			announcerManager = new SimpleConfigManager(AllAssets.instance());
		if (warpsManager == null)
			warpsManager = new SimpleConfigManager(AllAssets.instance());

		/** Checks that they exist, otherwise it will replace the content */
		if (!new File(AllAssets.instance().getDataFolder(), "config.yml").exists())
			createConfig();
		else
			config = configManager.getNewConfig("config.yml");

		if (!new File(AllAssets.instance().getDataFolder(), "messages.yml").exists())
			createMessages();
		else
			messages = messagesManager.getNewConfig("messages.yml");

		if (!new File(AllAssets.instance().getDataFolder(), "features.yml").exists())
			createFeatures("features.yml", features, featuresManager);
		else {
			features = featuresManager.getNewConfig("features.yml");
			/** Auto update Features.yml */
			SimpleConfig tempFeatures;
			final SimpleConfigManager tempFeaturesManager = new SimpleConfigManager(AllAssets.instance());
			tempFeatures = tempFeaturesManager.getNewConfig("tempFeatures.yml");
			createFeatures("tempFeatures.yml", tempFeatures, tempFeaturesManager);
			for (final Entry<String, Object> entry : tempFeatures.getValues().entrySet())
				if (!features.contains(entry.getKey()))
					features.set(entry.getKey(), entry.getValue());
			tempFeatures.getFile().delete();
		}

		announcer = announcerManager.getNewConfig("Storage" + File.separator + "announcer.yml");
		warps = warpsManager.getNewConfig("Storage" + File.separator + "warps.yml");

	}

	private void createConfig() {
		final String[] header = { Strings.NO_COLOR_TITLE, "Copyright 2014 - Skepter", "All Rights Reserved", "Config.yml - File to store plugin configuration" };

		config = configManager.getNewConfig("config.yml", header);

		config.set("maxLogAmount", "20", "The maximum amount of logs to store temporarily");
		config.set("afkProtect", "true", "Prevents players from getting hurt by mobs when AFK");
		config.set("batchLimit", "500", "The limit of amounts to execute a command");

		// does it not like that 'm' in perform?
		config.set("bindRight", "true", "Performs action with a right click", "(set to false to perform action with left click)");
		config.set("useIPInformation", "false", "Allows ");
		config.set("clearArmor", "true", "Clears the armor from your inventory when using /clear");
		config.set("multiCommandSeparator", "|", "The separator used in multi-commands");
		config.set("staffChat", "'[&bStaff - {PLAYERNAME}&f] {MESSAGE}'", "The prefix for the staff chat");
		config.set("pluginsShowAuthors", "true", "If true, the plugin command will show the authors of the plugin");
		config.set("useNMSGod", "true", "If true, mobs don't target you when in godmode");
		config.set("commandsOnJoin", Arrays.asList(new String[] { "/broadcast {PLAYERNAME} joined the game!" }), "CommandOnJoin must be enabled in features.yml");
		config.set("commandCooldown", "0", "Amount of seconds to have a cooldown between each command");
		config.set("debugMode", "false", "Enables debugging messages and features");
		config.set("randomAnnouncer", "true", "Selects announcements at random");
		config.set("announcerTime", "300", "The delay between each announcement in seconds");
		config.set("broadcastPrefix", "&1[&bBroadcast&1]&b", "The prefix for broadcasts");
		config.set("currencyName", "dollars", "The name for the currency (plural)");
		config.set("currencyNameSing", "dollar", "The name for the currency (singular)");
	}

	private void createMessages() {
		final String[] header = { Strings.NO_COLOR_TITLE, "Copyright 2014 - Skepter", "All Rights Reserved", "Messages.yml - File to store and retrive messages used throughout the plugin" };

		messages = messagesManager.getNewConfig("messages.yml", header);

		messages.set("cantEnchant", "You cannot enchant that item!", "Error messages");
		messages.set("notANumber", "That is not a number!");
		messages.set("serverListMOTD", "'&bWelcome {PLAYERNAME}! You have joined {JOINCOUNT} times!'");
	}

	private void createFeatures(final String fileName, SimpleConfig features, final SimpleConfigManager featuresManager) {
		final String[] header = { Strings.NO_COLOR_TITLE, "Copyright 2014 - Skepter", "All Rights Reserved", "Features.yml - Control all aspects of what the plugin does" };

		features = featuresManager.getNewConfig(fileName, header);

		features.set("AFK", "true", "--- Commands ---", "Enable commands by setting the value to true", "Disable commands by setting the value to false");
		features.set("AllAssets", "true");
		features.set("Announcer", "true");
		features.set("Ascend", "true");
		features.set("Back", "true");
		features.set("Backup", "true");
		features.set("Balance", "true");
		features.set("Balancetop", "true");
		features.set("Batch", "true");
		features.set("Bind", "true");
		features.set("Break", "true");
		features.set("Broadcast", "true");
		features.set("Butcher", "true");
		features.set("Calculate", "true");
		features.set("ChestSearch", "true");
		features.set("Clear", "true");
		features.set("ClearChat", "true");
		features.set("Collect", "true");
		features.set("ConsoleLog", "true");
		features.set("Debug", "true");
		features.set("DelWarp", "true");
		features.set("Descend", "true");
		features.set("Disable", "true");
		features.set("DiscoArmor", "true");
		features.set("Disguise", "true");
		features.set("Enable", "true");
		features.set("Enchant", "true");
		features.set("Enderchest", "true");
		features.set("Extinguish", "true");
		features.set("FakeDeop", "true");
		features.set("FakeOp", "true");
		features.set("FileBrowser", "true");
		features.set("FileEditor", "true");
		features.set("Firework", "true");
		features.set("Fly", "true");
		features.set("ForceChat", "true");
		features.set("ForceCommand", "true");
		features.set("Friend", "true");
		features.set("Gamemode", "true");
		features.set("Ghost", "true");
		features.set("Give", "true");
		features.set("Glow", "true");
		features.set("Go", "true");
		features.set("God", "true");
		features.set("Grief", "true");
		features.set("Hat", "true");
		features.set("Head", "true");
		features.set("Heal", "true");
		features.set("Help", "true");
		features.set("Helpop", "true");
		features.set("Inventory", "true");
		features.set("Launch", "true");
		features.set("Log", "true");
		features.set("More", "true");
		features.set("Near", "true");
		features.set("NearbyWarps", "true");
		features.set("Nickname", "true");
		features.set("Oplist", "true");
		features.set("Ping", "true");
		features.set("Plugins", "true");
		features.set("Prefix", "true");
		features.set("PTime", "true");
		features.set("PWeather", "true");
		// work on this feature more - still a bit buggy and kinda unreliable
		features.set("Reload", "false"); // default to FALSE when actually exported Nav
		features.set("Remove", "true");
		features.set("Rename", "true");
		//		features.set("Restore", "true");
		features.set("Repair", "true");
		features.set("Rules", "true");
		features.set("Seen", "true");
		features.set("SetBalance", "true");
		features.set("SetSpawn", "true");
		features.set("SetWarp", "true");
		features.set("SignEdit", "true");
		features.set("Silence", "true");
		features.set("Spawn", "true");
		features.set("SpawnItem", "true");
		features.set("SpawnMob", "true");
		features.set("StaffChat", "true");
		features.set("Suffix", "true");
		features.set("Suicide", "true");
		features.set("Time", "true"); // includes day/midday/night/midnight etc.
		features.set("Top", "true");
		features.set("Tp", "true");
		features.set("Tpall", "true");
		features.set("Tphere", "true");
		features.set("TpToggle", "true");
		features.set("Warp", "true");
		features.set("Warps", "true");
		features.set("Weather", "true");
		features.set("Whois", "true");
		features.set("World", "true");
		features.set("Worlds", "true");
		features.set("Workbench", "true");

		features.set("AntiHyperlink", "true", "--- Events ---", "Actions which occur on events can be disabled here");
		features.set("AntiSwear", "true");
		features.set("ChatColor", "true");
		features.set("ConsoleSay", "false", "Allows the console to n chat without having to use /say");
		features.set("Log", "true", "Disabling this prevents logging information in the log command");
		features.set("CreativeEnderpearl", "true");
		features.set("DeathSigns", "false");
		features.set("DeathCount", "true");
		features.set("InstantDeathRespawn", "false");
		//		features.set("AntiIllegalItems", "true");
		features.set("MultiCommands", "true", "Allows you to execute multiple commands");
		features.set("AnyLeash", "true", "Allows you to put leads on any mob");
		features.set("PickupSkeletonArrows", "false", "Allows you to pick up skeleton arrows");
		features.set("ServerListMOTDCustomisation", "true");
		//glowstone, obby; netherrack, fire; pumpkin,jackolantern
		features.set("PoweredBlocks.Glowstone", "true");
		features.set("PoweredBlocks.Netherrack", "true");
		features.set("PoweredBlocks.Pumpkin", "true");
		// add more modifiers to modify the mechanics of the game
		// e.g. amount of exp mobs drop when killed, drop probability, spawning
		// probability (perhaps?) (e.g. increase prob. spawn zombie+chicken
		features.set("FlyBreakSpeedModifier", "true", "Break blocks at normal speed when flying on survival");

		// may remove in the future unless more instants come into play.
		//		features.set("Instant eating", "false", new String[] { "Instants", "Instantly carry out actions" });
		//		features.set("Instant bows", "false");

		features.set("JoinActions", "true", "---Join Actions ---", "Actions to be carried out when a player joins");
		features.set("UniquePlayers", "true", "Display the amount of unique players that have joined");
		features.set("TotalTime", "true", "Display the total time the player has played");
		features.set("FireworkOnJoin", "true", "JoinActions in features.yml must be turned on");
		features.set("CommandsOnJoin", "true", "Commands to run when a player joins - see config to add commands");

		features.set("BlockHeads", "true", "--- Cosmetics ---", "Cosmetic features to comply with the EULA");
		
		//		features.set("StaffChat", "true", new String[] { "Staff chat system - see config to configure it" });

	}

	public SimpleConfig config() {
		if (config != null)
			return config;
		else {
			createConfig();
			return config;
		}
	}

	public SimpleConfig announcer() {
		return announcer;
	}

	public SimpleConfig warps() {
		return warps;
	}

	public String getMsg(final String msg) {
		if (messages != null)
			return messages.getString(msg);
		else {
			createMessages();
			return messages.getString(msg);
		}
	}

	public String getSpecialMsg(final String msg) {
		if (messages != null)
			return messages.getSpecialString(msg);
		else {
			createMessages();
			return messages.getSpecialString(msg);
		}
	}

	public SimpleConfig features() {
		if (features != null)
			return features;
		else {
			createFeatures("features.yml", features, featuresManager);
			return features;
		}
	}
}
