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
package io.github.skepter.allassets.utils;

import io.github.skepter.allassets.AllAssets;

import java.io.File;
import java.util.UUID;

public class Files {

	/** Returns the storage folder for player data */
	public static File getPlayerStorage(UUID u) {
		final File file = new File(AllAssets.instance().getDataFolder() + File.separator + "Players" + File.separator + u.toString());
		if (!file.exists())
			file.mkdirs();
		return file;
	}

	/** Returns the storage folder to backing up worlds */
	public static File getWorldDataStorage() {
		final File file = new File(AllAssets.instance().getDataFolder() + File.separator + "Worlds");
		if (!file.exists())
			file.mkdirs();
		return file;
	}

	/** Returns the storage folder for storing data */
	public static File getStorage() {
		final File file = new File(AllAssets.instance().getDataFolder() + File.separator + "Storage");
		if (!file.exists())
			file.mkdirs();
		return file;
	}

	/** Returns the storage folder to backing up worlds */
	public static File getWorldBackupStorage() {
		final File file = new File(AllAssets.instance().getDataFolder() + File.separator + "Backups");
		if (!file.exists())
			file.mkdirs();
		return file;
	}

}
