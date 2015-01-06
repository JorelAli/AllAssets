package io.github.Skepter.AllAssets.API;

import io.github.Skepter.AllAssets.API.LogEvent.LogType;
import io.github.Skepter.AllAssets.Commands.Administration.CommandLog;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class CustomConfig {

	private final File dataFile;
	private FileConfiguration fileConfiguration;
	private String usage;

	public CustomConfig(File storageLocation, String usage) {
		dataFile = storageLocation;
		this.usage = usage;
	}

	public void reloadDataFile() {
		fileConfiguration = YamlConfiguration.loadConfiguration(dataFile);
	}

	public FileConfiguration getDataFile() {
		if (fileConfiguration == null)
			reloadDataFile();
		return fileConfiguration;
	}

	public void saveDataFile() {
		getDataFile().getValues(false);
		if ((fileConfiguration == null) || (dataFile == null))
			return;
		else
			try {
				getDataFile().save(dataFile);
			} catch (final IOException ex) {
				CommandLog.addLog("Error saving data file for " + usage + "! ", LogType.ERROR);
			}
	}

}
