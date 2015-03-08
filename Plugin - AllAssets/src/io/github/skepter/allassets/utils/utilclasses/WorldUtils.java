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
package io.github.skepter.allassets.utils.utilclasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class WorldUtils {

	private World world;
	private Player player;

	public WorldUtils(String worldName) {
		world = Bukkit.getWorld(worldName);
	}

	public WorldUtils(Player player) {
		world = player.getWorld();
		this.player = player;
	}

	public void deletePlayerData() {
		File playerData = new File(world.getWorldFolder(), "playerdata");
		File playerDataFile = new File(playerData, player.getUniqueId().toString() + ".dat");
		if (playerDataFile.exists()) {
			playerDataFile.delete();
		}

		File playerStats = new File(world.getWorldFolder(), "stats");
		File playerStatsFile = new File(playerStats, player.getUniqueId().toString() + ".json");
		if (playerStatsFile.exists()) {
			playerStatsFile.delete();
		}
	}

	public void unloadWorld() {
		if (!world.equals(null)) {
			Bukkit.getServer().unloadWorld(world, true);
		}
	}

	public void deleteWorld() {
		deleteWorld(world.getWorldFolder());
	}

	private boolean deleteWorld(File path) {
		if (path.exists()) {
			File files[] = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteWorld(files[i]);
				} else {
					files[i].delete();
				}
			}
		}
		return (path.delete());
	}

	public void copyWorld(File backupDirectory) {
		copyWorld(backupDirectory, world.getWorldFolder());
	}

	private void copyWorld(File source, File target) {
		try {
			ArrayList<String> ignore = new ArrayList<String>(Arrays.asList("uid.dat", "session.dat"));
			if (!ignore.contains(source.getName())) {
				if (source.isDirectory()) {
					if (!target.exists())
						target.mkdirs();
					String files[] = source.list();
					for (String file : files) {
						File srcFile = new File(source, file);
						File destFile = new File(target, file);
						copyWorld(srcFile, destFile);
					}
				} else {
					InputStream in = new FileInputStream(source);
					OutputStream out = new FileOutputStream(target);
					byte[] buffer = new byte[1024];
					int length;
					while ((length = in.read(buffer)) > 0)
						out.write(buffer, 0, length);
					in.close();
					out.close();
				}
			}
		} catch (IOException e) {

		}
	}

}
