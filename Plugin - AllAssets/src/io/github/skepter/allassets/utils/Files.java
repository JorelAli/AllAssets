/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and MCSpartans
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and MCSpartans
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

public class Files {
	
	public static File getDirectory(Directory dir) {
		final File file = new File(AllAssets.instance().getDataFolder() + File.separator + dir.getDirectoryName());
		if (!file.exists())
			file.mkdirs();
		return file;
	}

	public enum Directory {
		PLAYERS("Players"),
		WORLD("Worlds"),
		STORAGE("Storage"),
		BACKUP("Backups");

		private String dir;

		Directory(String dir) {
			this.dir = dir;
		}

		String getDirectoryName() {
			return dir;
		}
	}

}
