/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Config;

import io.github.Skepter.AllAssets.AllAssets;

import java.io.File;
import java.util.Arrays;

public class ConfigHandler {

	private SimpleConfigManager configManager;
	private static SimpleConfigManager messagesManager;
	private SimpleConfigManager featuresManager;
	private SimpleConfig config;
	private static SimpleConfig messages;
	private SimpleConfig features;
	private static ConfigHandler instance;

	public ConfigHandler() {
		if (configManager == null)
			configManager = new SimpleConfigManager(AllAssets.instance());
		if (messagesManager == null)
			messagesManager = new SimpleConfigManager(AllAssets.instance());
		if (featuresManager == null)
			featuresManager = new SimpleConfigManager(AllAssets.instance());

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
			createFeatures();
		else
			features = featuresManager.getNewConfig("features.yml");
		
		//find a way to check for missing keys in configurations perhaps? - that way it can 'auto update'
		
		instance = this;
	}

	public static ConfigHandler instance() {
		return instance;
	}

	private void createConfig() {
		final String[] header = { AllAssets.titleNoColor, "Copyright 2014 - Skepter", "All Rights Reserved", "Config.yml - File to store plugin configuration" };

		config = configManager.getNewConfig("config.yml", header);

		config.set("maxLogAmount", "20", "The maximum amount of logs to store temporarily");
		config.set("afkProtect", "true", "Prevents players from getting hurt by mobs when AFK");
		// does it not like that 'm' in perform?
		config.set("bindRight", "true", "Performs action with a right click (set to false to perform action with left click)");
		config.set("useIPInformation", "false", "Allows ");
		config.set("clearArmor", "true", "Clears the armor from your inventory when using /clear");
		config.set("multiCommandSeparator", "|", "The separator used in multi-commands");
		config.set("staffChat", "'[&bStaff - {PLAYERNAME}&f] {MESSAGE}'", "The prefix for the staff chat");
		config.set("pluginsShowAuthors", "true", "If true, the plugin command will show the authors of the plugin");
		config.set("useNMSGod", "true", "If true, mobs don't target you when in godmode");
		config.set("commandsOnJoin", Arrays.asList(new String[] { "/broadcast {PLAYERNAME} joined the game!" }), "CommandOnJoin must be enabled in features.yml");
		config.set("commandCooldown", "0", "Amount of seconds to have a cooldown between each command");
		config.set("debugMode", "false", "Enables debugging messages and features");
		
		config.saveConfig();
	}

	private static void createMessages() {
		final String[] header = { AllAssets.titleNoColor, "Copyright 2014 - Skepter", "All Rights Reserved", "Messages.yml - File to store and retrive messages used throughout the plugin" };

		messages = messagesManager.getNewConfig("messages.yml", header);

		messages.set("cantEnchant", "You cannot enchant that item!", "Error messages");
		messages.set("notANumber", "That is not a number!");
		messages.set("serverListMOTD", "'&bWelcome {PLAYERNAME}! You have joined {JOINCOUNT} times!'");
		messages.saveConfig();
	}

	private void createFeatures() {
		final String[] header = { AllAssets.titleNoColor, "Copyright 2014 - Skepter", "All Rights Reserved", "Features.yml - Control all aspects of what the plugin does" };

		features = featuresManager.getNewConfig("features.yml", header);

		features.set("AFK", "true", new String[] { "Commands", "Enable commands by setting the value to true", "Disable commands by setting the value to false" });
		features.set("AllAssets", "true");
		features.set("Back", "true");
		features.set("Balance", "true");
		features.set("Balancetop", "true");
		features.set("Batch", "true");
		features.set("Bind", "true");
		features.set("Butcher", "true");
		features.set("ChestSearch", "true");
		features.set("Clear", "true");
		features.set("ClearChat", "true");
		features.set("ConsoleLog", "true");
		features.set("Debug", "true");
		features.set("Disable", "true");
		features.set("DiscoArmor", "true");
		features.set("Enable", "true");
		features.set("Enchant", "true");
		features.set("Firework", "true");
		features.set("Fly", "true");
		features.set("ForceChat", "true");
		features.set("Gamemode", "true");
		features.set("Ghost", "true");
		features.set("Glow", "true");
		features.set("God", "true");
		features.set("Grief", "true");
		features.set("Inventory", "true");
		features.set("Launch", "true");
		features.set("Log", "true");
		features.set("More", "true");
		features.set("Near", "true");
		features.set("Oplist", "true");
		features.set("Ping", "true");
		features.set("Plugins", "true");
		features.set("PTime", "true");
		features.set("PWeather", "true");
		// work on this feature more - still a bit buggy and kinda unreliable
		features.set("Reload", "false"); // default to FALSE when actually Nav
										 // exported
		features.set("Rename", "true");
		features.set("SignEdit", "true");
		features.set("StaffChat", "true");
		features.set("Time", "true"); // includes day/midday/night/midnight etc.
		features.set("Tp", "true");
		features.set("Tphere", "true");
		features.set("Weather", "true");
		features.set("Worlds", "true");

		features.set("AntiHyperlink", "true", new String[] { "Listeners", "Actions which occur on events can be disabled here" });
		features.set("AntiSwear", "true");
		features.set("ChatColor", "true");
		features.set("ConsoleSay", "false", "Allows the console to speak in chat without having to use /say");
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

		features.set("JoinActions", "true", new String[] { "Join Actions", "Actions to be carried out when a player joins" });
		features.set("UniquePlayers", "true", "Display the amount of unique players that have joined the server");
		features.set("TotalTime", "true", "Display the total time the player has played on the server for");
		features.set("FireworkOnJoin", "true", "JoinActions in features.yml must be turned on");
		features.set("CommandsOnJoin", "true", "Commands to run when a player joins - see config to add commands");

		features.set("BlockHeads", "true", new String[] { "Cosmetics", "Cosmetic features to comply with the EULA" });
		//		features.set("StaffChat", "true", new String[] { "Staff chat system - see config to configure it" });
		features.saveConfig();
	}

	public SimpleConfig config() {
		if (config != null)
			return config;
		else {
			createConfig();
			return config;
		}
	}

	public static String getMsg(final String msg) {
		if (messages != null)
			return messages.getString(msg);
		else {
			createMessages();
			return messages.getString(msg);
		}
	}

	public static String getSpecialMsg(final String msg) {
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
			createFeatures();
			return features;
		}
	}
}