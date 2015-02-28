package io.github.skepter.allassets.utils;

import io.github.skepter.allassets.AllAssets;

import java.io.File;

public class Files {

	/** Returns the storage folder for player data */
	public static File getPlayerStorage() {
		final File file = new File(AllAssets.instance().getDataFolder() + File.separator + "Players");
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
