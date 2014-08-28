package io.github.Skepter.Config;

import io.github.Skepter.AllAssets;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class UUIDData {

	public UUIDData() {
	}

	private FileConfiguration dataFile = null;
	private File dataFileFile = null;

	public void reloadDataFile() {
		if (dataFileFile == null)
			dataFileFile = new java.io.File(AllAssets.instance().getDataFolder(), "UUIDMap.yml");
		dataFile = YamlConfiguration.loadConfiguration(dataFileFile);
	}

	public FileConfiguration getDataFile() {
		if (dataFile == null)
			reloadDataFile();
		return dataFile;
	}

	public void saveDataFile() {
		if ((dataFile == null) || (dataFileFile == null))
			return;
		try {
			getDataFile().save(dataFileFile);
		} catch (final java.io.IOException ex) {
			AllAssets.instance().getLogger().log(Level.SEVERE, "Could not save data to " + dataFileFile, ex);
		}
	}

	public List<UUID> getValues() {
		final List<UUID> uuidList = new ArrayList<UUID>();
		for (final String s : getDataFile().getKeys(true))
			uuidList.add(UUID.fromString(getDataFile().getString(s)));
		return uuidList;
	}

	/** @return Playername: UUID */
	public Map<String, UUID> getUUIDMap() {
		final Map<String, UUID> uuidMap = new HashMap<String, UUID>();
		final Map<String, Object> objectMap = getDataFile().getValues(false);
		for (final Map.Entry<String, Object> entry : objectMap.entrySet())
			uuidMap.put(entry.getKey(), UUID.fromString(String.valueOf(entry.getValue())));
		return uuidMap;
	}

	/** @return UUID: PlayerName */
	public Map<UUID, String> getReversedUUIDMap() {
		final Map<UUID, String> reversedMap = new HashMap<UUID, String>();
		for (final Map.Entry<String, UUID> entry : getUUIDMap().entrySet())
			reversedMap.put(entry.getValue(), entry.getKey());
		return reversedMap;
	}
}
