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
package io.github.skepter.allassets.api;

import io.github.skepter.allassets.api.events.LogEvent.LogType;
import io.github.skepter.allassets.commands.administration.CommandLog;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class CustomConfig {

	private final File dataFile;
	private FileConfiguration fileConfiguration;
	private final String usage;

	public CustomConfig(final File storageLocation, final String usage) {
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

	public File getFile() {
		return dataFile;
	}

}
