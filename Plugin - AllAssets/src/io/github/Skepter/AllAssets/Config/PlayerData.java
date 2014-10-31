package io.github.Skepter.AllAssets.Config;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.API.LogEvent.LogType;
import io.github.Skepter.AllAssets.Commands.CommandLog;

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
