package io.github.Skepter.Config;

import io.github.Skepter.AllInOne;

import java.io.File;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class UUIDData {

	public UUIDData() {
	}
	
	private FileConfiguration dataFile = null;
	private File dataFileFile = null;
	
	public void reloadDataFile() {
		if (dataFileFile == null) {
			dataFileFile = new java.io.File(AllInOne.instance().getDataFolder(), "UUIDMap.yml");
		}
		dataFile = YamlConfiguration.loadConfiguration(dataFileFile);
	}

	public FileConfiguration getDataFile() {
		if (dataFile == null) {
			reloadDataFile();
		}
		return dataFile;
	}

	public void saveDataFile() {
		if (dataFile == null || dataFileFile == null) {
			return;
		}
		try {
			getDataFile().save(dataFileFile);
		} catch (final java.io.IOException ex) {
			AllInOne.instance().getLogger().log(Level.SEVERE, "Could not save data to " + dataFileFile, ex);
		}
	}
}
