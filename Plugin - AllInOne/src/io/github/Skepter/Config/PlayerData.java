package io.github.Skepter.Config;

import io.github.Skepter.AllInOne;
import io.github.Skepter.Commands.CommandLog;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class PlayerData {

	private final String uuid;
	private final File dataFile;
	private FileConfiguration fileConfiguration;
	private final AllInOne plugin = AllInOne.instance();

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
		if ((fileConfiguration == null) || (dataFile == null))
			return;
		else
			try {
				getPlayerData().save(dataFile);
			} catch (final IOException ex) {
				Bukkit.getLogger().log(Level.SEVERE, "Error saving player data file!");
			}
	}

	public void saveDefaultPlayerData() {
		if (dataFile.exists())
			return;
		dataFile.getParentFile().mkdirs();
		try {
			dataFile.createNewFile();
		} catch (final IOException e) {
			CommandLog.addErrorLog("Could not save player data to " + dataFile);
		}
	}

	public static void saveAllPlayers() {
		for (final Player player : Bukkit.getOnlinePlayers()) {
			final PlayerData newPlayerData = new PlayerData(player);
			newPlayerData.savePlayerData();
		}
	}
}
