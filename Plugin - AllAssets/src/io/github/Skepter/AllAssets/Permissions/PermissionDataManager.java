package io.github.Skepter.AllAssets.Permissions;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.API.LogEvent.LogType;
import io.github.Skepter.AllAssets.Commands.Administration.CommandLog;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class PermissionDataManager {

	//manages data.
	//yeah :D
	//will use a bunch of methods to retrieve/set everything

	/* Sample format:
	 * 
	 * Worlds:
	 *   <WorldName>:
	 *     Groups:
	 *       <GroupName>:
	 *         Permissions:
	 *           - permsList
	 *         Prefix: [prefix]
	 *         Suffix: [suffix]
	 *       <GroupName>:
	 *         Permissions:
	 *           - permsList
	 *         Prefix: [prefix]
	 *         Suffix: [suffix]
	 *     Players:
	 *     	 <PlayerName>:
	 *         Permissions:
	 *           - permsList
	 *         Prefix: [prefix]
	 *         Suffix: [suffix]
	 *       <PlayerName>:
	 *         Permissions:
	 *           - permsList
	 *         Prefix: [prefix]
	 *         Suffix: [suffix]
	 * Global:
	 *   Groups:
	 *     <GroupName>:
	 *       Permissions:
	 *         - permsList
	 *       Prefix: [prefix]
	 *       Suffix: [suffix]
	 *     <GroupName>:
	 *       Permissions:
	 *         - permsList
	 *       Prefix: [prefix]
	 *       Suffix: [suffix]
	 *   Players:
	 *     <PlayerName>:
	 *       Permissions:
	 *       - permsList
	 *       Prefix: [prefix]
	 *       Suffix: [suffix]
	 *     <PlayerName>:
	 *       Permissions:
	 *         - permsList
	 *       Prefix: [prefix]
	 *       Suffix: [suffix]     
	 */

	private final File dataFile;
	private FileConfiguration fileConfiguration;

	public PermissionDataManager() {
		dataFile = new File(AllAssets.getStorage(), "PermissionData.yml");
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
				CommandLog.addLog("Error saving data file for permissions! ", LogType.ERROR);
			}
	}

	public List<String> getWorldPermissions(String worldName, GroupDataType type, String dataName) {
		String path = "Worlds.";
		switch (type) {
		case GROUP:
			path = path + worldName + ".Groups.";
			break;
		case PLAYER:
			path = path + worldName + ".Players.";
			break;
		}
		path = path + dataName + ".Permissions";
		return getDataFile().getStringList(path);
	}

	public List<String> getGlobalPermissions(GroupDataType type, String dataName) {
		String path = "Global.";
		switch (type) {
		case GROUP:
			path = path + ".Groups.";
			break;
		case PLAYER:
			path = path + ".Players.";
			break;
		}
		path = path + dataName + ".Permissions";
		return getDataFile().getStringList(path);
	}

	public String getWorldAffix(String worldName, GroupDataType type, String dataName, Affix affix) {
		String path = "Worlds.";
		switch (type) {
		case GROUP:
			path = path + worldName + ".Groups.";
			break;
		case PLAYER:
			path = path + worldName + ".Players.";
			break;
		}
		path = path + dataName + ".";
		switch (affix) {
		case PREFIX:
			path = path + "Prefix";
			break;
		case SUFFIX:
			path = path + "Suffix";
			break;
		}
		return getDataFile().getString(path);
	}

	public String getGlobalAffix(GroupDataType type, String dataName, Affix affix) {
		String path = "Global.";
		switch (type) {
		case GROUP:
			path = path + ".Groups.";
			break;
		case PLAYER:
			path = path + ".Players.";
			break;
		}
		path = path + dataName + ".";
		switch (affix) {
		case PREFIX:
			path = path + "Prefix";
			break;
		case SUFFIX:
			path = path + "Suffix";
			break;
		}
		return getDataFile().getString(path);
	}

	public enum GroupDataType {
		GROUP, PLAYER;
	}

	/* For those unfamiliar with the word 'affix', it's the technical term for a
	 * prefix or suffix (unlike GroupManager which just calls them 'variables')*/
	public enum Affix {
		PREFIX, SUFFIX;
	}
}
