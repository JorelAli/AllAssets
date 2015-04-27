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
package io.github.skepter.allassets.vault.permissions;

import io.github.skepter.allassets.api.CustomConfig;
import io.github.skepter.allassets.utils.Files;
import io.github.skepter.allassets.utils.Files.Directory;

import java.io.File;
import java.util.List;

public class PermissionDataManager extends CustomConfig {

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

	public PermissionDataManager() {
		super(new File(Files.getDirectory(Directory.STORAGE), "PermissionData.yml"), "Permissions");
	}

	/** dataName = player or group name */
	public List<String> getWorldPermissions(final String worldName, final GroupDataType type, final String dataName) {
		final String path = getPath(type, "Worlds.", worldName) + dataName + ".Permissions";
		return getDataFile().getStringList(path);
	}

	/** dataName = player or group name */
	public List<String> getGlobalPermissions(final GroupDataType type, final String dataName) {
		final String path = getPath(type, "Global.") + dataName + ".Permissions";
		return getDataFile().getStringList(path);
	}

	public String getWorldAffix(final String worldName, final GroupDataType type, final String dataName, final Affix affix) {
		final String path = getAffix(affix, getPath(type, "Worlds.", worldName) + dataName + ".");
		return getDataFile().getString(path);
	}

	public String getGlobalAffix(final GroupDataType type, final String dataName, final Affix affix) {
		final String path = getAffix(affix, getPath(type, "Global.") + dataName + ".");
		return getDataFile().getString(path);
	}

	private String getAffix(final Affix affix, String path) {
		switch (affix) {
			case PREFIX:
				path = path + "Prefix";
				break;
			case SUFFIX:
				path = path + "Suffix";
				break;
		}
		return path;
	}

	private String getPath(final GroupDataType type, String path) {
		switch (type) {
			case GROUP:
				path = path + ".Groups.";
				break;
			case PLAYER:
				path = path + ".Players.";
				break;
		}
		return path;
	}

	private String getPath(final GroupDataType type, String path, final String worldName) {
		switch (type) {
			case GROUP:
				path = path + worldName + ".Groups.";
				break;
			case PLAYER:
				path = path + worldName + ".Players.";
				break;
		}
		return path;
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
