/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 * 
 * AllAssets, created by Skepter and Tundra
 * 
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 * 
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and Tundra
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 * 
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.config;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.api.events.LogEvent.LogType;
import io.github.skepter.allassets.commands.administration.CommandLog;
import io.github.skepter.allassets.utils.Utils;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

/** Retrieves UUID's from the UUIDMap.yml file
 * 
 * @author Skepter */
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

	public static Set<UUID> getValues() {
		final Set<UUID> uuidList = new HashSet<UUID>();
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
		return Utils.reverse(getUUIDMap());
	}
}
