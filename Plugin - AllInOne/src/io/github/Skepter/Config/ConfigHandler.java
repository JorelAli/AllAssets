package io.github.Skepter.Config;

import io.github.Skepter.AllInOne;

public class ConfigHandler {

	private SimpleConfigManager manager, managerMessages,
			managerFeatures;
	private SimpleConfig config, messages, features;
	private static ConfigHandler instance;

	public ConfigHandler() {
		if (manager == null)
			manager = new SimpleConfigManager(AllInOne.instance());

		if (managerMessages == null)
			managerMessages = new SimpleConfigManager(AllInOne.instance());
		if (managerFeatures == null)
			managerFeatures = new SimpleConfigManager(AllInOne.instance());
		createConfig();
		createMessages();
		createFeatures();
	}
	
	public static ConfigHandler instance() {
		return instance;
	}

	private void createConfig() {
		final String[] header = { AllInOne.instance().titleNoColor, "Copyright 2014 - Skepter", "All Rights Reserved", "Config.yml - File to store plugin configuration" };

		config = manager.getNewConfig("config.yml", header);

		config.set("maxLogAmount", "20", "The maximum amount of logs to store temporarily");
		config.set("afkProtect", "true", "Prevents players from getting hurt by mobs when AFK");
		// does it not like that 'm' in perform?
		config.set("bindRight", "true", "Performs action with a right click (set to false to perform action with left click)");
		config.set("useIPInformation", "false", "Allows ");
		config.set("clearArmor", "true", "Clears the armor from your inventory when using /clear");
		config.saveConfig();
	}

	private void createMessages() {
		final String[] header = { AllInOne.instance().titleNoColor, "Copyright 2014 - Skepter", "All Rights Reserved", "Messages.yml - File to store and retrive messages used throughout the plugin" };

		messages = managerMessages.getNewConfig("messages.yml", header);

		messages.set("placeholder", "hi");
		messages.saveConfig();
	}

	private void createFeatures() {
		final String[] header = { AllInOne.instance().titleNoColor, "Copyright 2014 - Skepter", "All Rights Reserved", "Features.yml - Control all aspects of what the plugin does" };

		features = managerFeatures.getNewConfig("features.yml", header);

		features.set("AFK", "true", new String[] { "Commands", "Enable commands by setting the value to true", "Disable commands by setting the value to false" });
		features.set("AllInOne", "true");
		features.set("Back", "true");
		features.set("Balance", "true");
		features.set("Balancetop", "true");
		features.set("Batch", "true");
		features.set("Bind", "true");
		features.set("ChestSearch", "true");
		features.set("Clear", "true");
		features.set("ClearChat", "true");
		features.set("ConsoleLog", "true");
		features.set("Debug", "true");
		features.set("Disable", "true");
		features.set("Enable", "true");
		features.set("Enchant", "true");
		features.set("Fly", "true");
		features.set("ForceChat", "true");
		features.set("Ghost", "true");
		features.set("Launch", "true");
		features.set("Log", "true");
		features.set("Oplist", "true");
		features.set("Ping", "true");
		features.set("Plugins", "true");
		features.set("PluginsShowAuthors", "true", "If true, the plugin command will show the authors of the plugin");
		features.set("PTime", "true");
		features.set("PWeather", "true");
		// work on this feature more - still a bit buggy and kinda unreliable
		features.set("Reload", "true"); // default to FALSE when actually
										// exported

		features.set("Set", "true");
		features.set("SignEdit", "true");
		features.set("Time", "true"); // includes day/midday/night/midnight etc.
		features.set("Tp", "true");
		features.set("Tphere", "true");
		features.set("Worlds", "true");

		features.set("AntiHyperlink", "true", new String[] { "Listeners", "Actions which occur on events can be disabled here" });
		features.set("AntiSwear", "true");
		features.set("ChatColor", "true");
		features.set("PlayerTranslator", "true", "Changes {player} into the specified player");
		features.set("ConsoleSay", "false", "Allows the console to speak in chat without having to use /say");
		features.set("Log", "true", "Disabling this prevents logging information in the log command");
		features.set("CreativeEnderpearl", "true");
		features.set("DeathSigns", "false");
		features.set("DeathCount", "true");
		features.set("InstantDeathRespawn", "false");
		features.set("AntiIllegalItems", "true");
		// add more modifiers to modify the mechanics of the game
		// e.g. amount of exp mobs drop when killed, drop probability, spawning
		// probability (perhaps?) (e.g. increase prob. spawn zombie+chicken
		features.set("FlyBreakSpeedModifier", "true", "Break blocks at normal speed when flying on survival");

		// may remove in the future unless more instants come into play.
		features.set("Instant eating", "false", new String[] { "Instants", "Instantly carry out actions" });
		features.set("Instant bows", "false");

		features.set("JoinActions", "true", new String[] { "Join Actions", "Actions to be carried out when a player joins" });
		features.set("UniquePlayers", "true", "Display the amount of unique players that have joined the server");
		features.set("TotalTime", "true", "Display the total time the player has played on the server for");
		features.saveConfig();
	}

	public SimpleConfig getConfig() {
		if (config != null) {
			return config;
		} else {
			createConfig();
			return config;
		}
	}

	public SimpleConfig getMessages() {
		if (messages != null) {
			return messages;
		} else {
			createMessages();
			return messages;
		}
	}

	public SimpleConfig features() {
		if (features != null) {
			return features;
		} else {
			createFeatures();
			return features;
		}
	}
}
