/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Config;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.API.LogEvent.LogType;
import io.github.Skepter.AllAssets.Commands.CommandLog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class UUIDData {

	private static FileConfiguration dataFile = null;
	private static File dataFileFile = null;

	public static void reloadDataFile() {
		if (dataFileFile == null)
			dataFileFile = new File(AllAssets.instance().getDataFolder(), "UUIDMap.yml");
		dataFile = YamlConfiguration.loadConfiguration(dataFileFile);
	}

	private static FileConfiguration getDataFile() {
		if (dataFile == null)
			reloadDataFile();
		return dataFile;
	}

	/** Adds a new entry to the UUIDData file */
	public static void setData(final Player player) {
		getDataFile().set(player.getName(), player.getUniqueId().toString());
		saveDataFile();
	}

	private static void saveDataFile() {
		if ((dataFile == null) || (dataFileFile == null))
			return;
		try {
			getDataFile().save(dataFileFile);
		} catch (final java.io.IOException ex) {
			CommandLog.addLog("Could not save data to " + dataFileFile.toString(), LogType.ERROR);
		}
	}

	public static List<UUID> getValues() {
		final List<UUID> uuidList = new ArrayList<UUID>();
		for (final String s : getDataFile().getKeys(true))
			uuidList.add(UUID.fromString(getDataFile().getString(s)));
		return uuidList;
	}

	/** @return Playername: UUID */
	public static Map<String, UUID> getUUIDMap() {
		final Map<String, UUID> uuidMap = new HashMap<String, UUID>();
		final Map<String, Object> objectMap = getDataFile().getValues(false);
		for (final Map.Entry<String, Object> entry : objectMap.entrySet())
			uuidMap.put(entry.getKey(), UUID.fromString(String.valueOf(entry.getValue())));
		return uuidMap;
	}

	/** @return UUID: PlayerName */
	public static Map<UUID, String> getReversedUUIDMap() {
		final Map<UUID, String> reversedMap = new HashMap<UUID, String>();
		for (final Map.Entry<String, UUID> entry : getUUIDMap().entrySet())
			reversedMap.put(entry.getValue(), entry.getKey());
		return reversedMap;
	}
}
