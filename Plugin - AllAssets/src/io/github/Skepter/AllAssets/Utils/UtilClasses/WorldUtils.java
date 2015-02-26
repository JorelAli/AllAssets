package io.github.skepter.allassets.utils.utilclasses;

import io.github.skepter.allassets.reflection.MinecraftReflectionUtils;
import io.github.skepter.allassets.reflection.ReflectionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

	public void forceUnloadWorld() throws Exception {
		MinecraftReflectionUtils utils = new MinecraftReflectionUtils(player);

		Object handle = utils.worldServer;
		Object console = utils.craftServer.getClass().getMethod("getServer").invoke(null, (Object[]) null);
		List<?> worlds = (List<?>) ReflectionUtils.getFieldValue(console, "worlds");
		worlds.remove(worlds.indexOf(handle));
		ReflectionUtils.setPrivateField(console, "worlds", worlds);

		@SuppressWarnings("unchecked")
		Map<String, World> cbWorlds = (Map<String, World>) ReflectionUtils.getPrivateFieldValue(utils.craftServer, "worlds");
		cbWorlds.remove(world.getName().toLowerCase());
		ReflectionUtils.setFinalStaticField(ReflectionUtils.getPrivateField(utils.craftServer, "worlds"), cbWorlds);
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
