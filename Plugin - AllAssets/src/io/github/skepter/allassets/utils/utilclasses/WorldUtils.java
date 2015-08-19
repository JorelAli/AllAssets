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

	private final World world;
	private Player player;

	public WorldUtils(final String worldName) {
		world = Bukkit.getWorld(worldName);
	}

	public WorldUtils(final Player player) {
		world = player.getWorld();
		this.player = player;
	}

	public void deletePlayerData() {
		final File playerData = new File(world.getWorldFolder(), "playerdata");
		final File playerDataFile = new File(playerData, player.getUniqueId().toString() + ".dat");
		if (playerDataFile.exists())
			playerDataFile.delete();

		final File playerStats = new File(world.getWorldFolder(), "stats");
		final File playerStatsFile = new File(playerStats, player.getUniqueId().toString() + ".json");
		if (playerStatsFile.exists())
			playerStatsFile.delete();
	}

	public void unloadWorld() {
		if (!world.equals(null))
			Bukkit.getServer().unloadWorld(world, true);
	}

	public void deleteWorld() {
		deleteWorld(world.getWorldFolder());
	}

	private boolean deleteWorld(final File path) {
		if (path.exists()) {
			final File files[] = path.listFiles();
			for (int i = 0; i < files.length; i++)
				if (files[i].isDirectory())
					deleteWorld(files[i]);
				else
					files[i].delete();
		}
		return (path.delete());
	}

	public void copyWorld(final File backupDirectory) {
		copyWorld(backupDirectory, world.getWorldFolder());
	}

	private void copyWorld(final File source, final File target) {
		try {
			final ArrayList<String> ignore = new ArrayList<String>(Arrays.asList("uid.dat", "session.dat"));
			if (!ignore.contains(source.getName()))
				if (source.isDirectory()) {
					if (!target.exists())
						target.mkdirs();
					final String files[] = source.list();
					for (final String file : files) {
						final File srcFile = new File(source, file);
						final File destFile = new File(target, file);
						copyWorld(srcFile, destFile);
					}
				} else {
					final InputStream in = new FileInputStream(source);
					final OutputStream out = new FileOutputStream(target);
					final byte[] buffer = new byte[1024];
					int length;
					while ((length = in.read(buffer)) > 0)
						out.write(buffer, 0, length);
					in.close();
					out.close();
				}
		} catch (final IOException e) {

		}
	}

}
