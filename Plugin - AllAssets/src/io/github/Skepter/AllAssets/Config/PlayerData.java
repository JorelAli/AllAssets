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
/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Config;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.API.LogEvent.LogType;
import io.github.Skepter.AllAssets.Commands.Administration.CommandLog;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class PlayerData {

	private final String uuid;
	private final File dataFile;
	private FileConfiguration fileConfiguration;
	private final AllAssets plugin = AllAssets.instance();

	public PlayerData(final OfflinePlayer player) {
		uuid = player.getUniqueId().toString();
		dataFile = new File(plugin.getDataFolder() + File.separator + "Players", uuid + ".yml");
	}

	public void reloadPlayerData() {
		fileConfiguration = YamlConfiguration.loadConfiguration(dataFile);
	}

	public FileConfiguration getPlayerData() {
		if (fileConfiguration == null)
			reloadPlayerData();
		return fileConfiguration;
	}

	public void savePlayerData() {
		getPlayerData().getValues(false);
		if ((fileConfiguration == null) || (dataFile == null))
			return;
		else
			try {
				getPlayerData().save(dataFile);
			} catch (final IOException ex) {
				CommandLog.addLog("Error saving player data file! ", LogType.ERROR);
			}
	}

	public void saveDefaultPlayerData() {
		if (dataFile.exists())
			return;
		dataFile.getParentFile().mkdirs();
		try {
			dataFile.createNewFile();
		} catch (final IOException e) {
			CommandLog.addLog("Could not save player data to " + dataFile.toString(), LogType.ERROR);
		}
	}

	public static void saveAllPlayers() {
		for (final Player player : Bukkit.getOnlinePlayers()) {
			final PlayerData newPlayerData = new PlayerData(player);
			newPlayerData.savePlayerData();
		}
	}
}
