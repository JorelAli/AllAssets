/*******************************************************************************
 * Skepter's licence
 * Copyright 2014 
 *
 * AllAssets was created by Skepter (http://skepter.github.io/).
 *
 * You are able to:
 * * View AllAsset's source code on github
 * * Experiment with the code to your wish
 * * Download it to use on your server
 *
 * You are NOT able to:
 * * Sell AllAssets - it is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter
 * * Distribute it on any other website. The ONLY places where it can be downloaded from is github and the Bukkit repository
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. I've just made life easier for you :)
 *
 * You can not:
 * * Hold me liable for your actions:
 *     - If there is a bug in the program, you are allowed to notify me.
 *     - If other players abuse AllAssets, you are NOT allowed to hold me liable for that
 *     - If it was setup incorrectly or you used a non-official build, you can NOT hold me liable for that.
 * * Yell at me:
 *     - If something is wrong, please approach the situation calmly.
 * * Modify the code:
 *     - You are NOT allowed to 'steal' the whole code from Github, modify it, compile it yourself and distribute that.
 *
 * If you are to break from these implications, future use of this plugin will be forbidden.
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.CommandListeners;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.ItemUtils;
import io.github.Skepter.AllAssets.Utils.MathUtils;
import io.github.Skepter.AllAssets.Utils.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CommandFileBrowser implements Listener {

	public CommandFileBrowser(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	public Map<UUID, String> directoryMap = new HashMap<UUID, String>();
	public Map<UUID, List<String>> dataMap = new HashMap<UUID, List<String>>();

	@CommandHandler(name = "filebrowser", aliases = { "fb" }, permission = "filebrowser", description = "Browses files and shows configs", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		/* If args = 0, show filebrowser
		 * If args = 1, show last file (and next page) */
		switch (args.getArgs().length) {
		case 0:
			openInventory(player, new File("."));
			directoryMap.put(player.getUniqueId(), ".");
			return;
		case 1:
			int arg = 1;
			if (args.getArgs().length == 1)
				arg = Integer.parseInt(args.getArgs()[0]);

			if (dataMap.get(player.getUniqueId()) == null) {
				ErrorUtils.error(player, "You have no data, cannot show next page!");
				return;
			}
			TextUtils.paginate(player, dataMap.get(player.getUniqueId()), 10, arg);
			return;
		}

	}

	@EventHandler
	public void onInventoryClick(final InventoryClickEvent event) {
		try {
			if (!event.getAction().equals(InventoryAction.PICKUP_ALL) || (event.getSlot() == -999) || (event.getInventory().getItem(event.getSlot()) == null))
				return;
			event.setCancelled(true);
			/* If they open a file inventory */
			if (event.getInventory().getName().startsWith(ChatColor.BLUE + "File - ")) {
				ItemStack item = event.getInventory().getItem(event.getSlot());
				Player player = (Player) event.getWhoClicked();
				String dM = directoryMap.get(player.getUniqueId());
				switch (item.getType()) {
				/* If they click a book, open that directory */
				case BOOK:
					directoryMap.put(player.getUniqueId(), openInventory(player, new File(dM, File.separator + ItemUtils.getDisplayName(item))));
					return;
					/* If they click an arrow, go up another level */
				case ARROW:
					/* Prevents them idiots from getting out of the server folder and causing havoc :) */
					if (Arrays.asList(new File(dM).list()).contains("server.properties"))
						return;
					directoryMap.put(player.getUniqueId(), openInventory(player, new File(dM).getParentFile()));
					return;
					/* Read the file */
				case PAPER:
					player.closeInventory();
					File dataFile = new File(dM, ItemUtils.getDisplayName(item));
					player.sendMessage(TextUtils.title(dataFile.getName()));
					/* Only allow .yml, .txt, .properties files as they are each read differently */
					if (dataFile.getName().contains(".yml")) {
						YamlConfiguration config = new YamlConfiguration();
						try {
							config.load(dataFile);
						} catch (Exception e) {
							ErrorUtils.error(player, "That file could not be read!");
							return;
						}
						List<String> list = new ArrayList<String>();
						for (String key : config.getKeys(true)) {
							list.add(ChatColor.AQUA + key + ChatColor.WHITE + ": " + (config.get(key).toString().contains("MemorySection[path=") ? "" : ChatColor.translateAlternateColorCodes('&', config.getString(key))));
						}
						dataMap.put(player.getUniqueId(), list);
					}
					if (dataFile.getName().contains(".txt")) {
						BufferedReader reader = new BufferedReader(new FileReader(dataFile));
						String line;
						List<String> list = new ArrayList<String>();
						while ((line = reader.readLine()) != null) {
							list.add(line);
						}
						reader.close();
						dataMap.put(player.getUniqueId(), list);
					}
					if (dataFile.getName().contains(".properties")) {
						Properties prop = new Properties();
						InputStream inputStream = new FileInputStream(dataFile);
						prop.load(inputStream);
						List<String> list = new ArrayList<String>();
						for (Object key : prop.keySet())
							list.add(ChatColor.AQUA + key.toString() + ChatColor.WHITE + ": " + ChatColor.translateAlternateColorCodes('&', prop.get(key).toString()));
						dataMap.put(player.getUniqueId(), list);
					}
					/* Only show it if you need to! */
					if (dataMap.get(player.getUniqueId()).size() < 10) {
						for (String s : dataMap.get(player.getUniqueId()))
							player.sendMessage(s);
					} else {
						TextUtils.paginate(player, dataMap.get(player.getUniqueId()), 10, 1);
						player.sendMessage(AllAssets.title + "Use /filebrowser <page number> to go to the next page");
					}
					return;
				default:
					return;
				}

			}
		} catch (Exception e) {
			System.out.println("Error");
		}
	}

	private String openInventory(Player player, File currentDirectory) {
		int fileCountRoot = 1;
		String[] supportedFileTypes = new String[] { ".yml", ".properties", ".txt" };
		for (File file : currentDirectory.listFiles()) {
			for (String s : supportedFileTypes) {
				if (file.getName().contains(s))
					fileCountRoot++;
			}
			if (file.isDirectory())
				fileCountRoot++;
		}
		String name = currentDirectory.getName();
		String pName = currentDirectory.getName();
		if (name.equals("."))
			name = "Server root folder";
		if (Arrays.asList(currentDirectory.list()).contains("server.properties"))
			fileCountRoot--;
		Inventory inv = Bukkit.createInventory(null, new Double(MathUtils.roundUp(fileCountRoot, 9)).intValue(), ChatColor.BLUE + "File - " + name);
		for (File file : currentDirectory.listFiles()) {
			for (String s : supportedFileTypes) {
				if (file.getName().contains(s))
					inv.addItem(ItemUtils.setDisplayName(new ItemStack(Material.PAPER, 1), file.getName()));
			}
			if (file.isDirectory()) {
				inv.addItem(ItemUtils.setDisplayName(new ItemStack(Material.BOOK, 1), file.getName()));
			}
		}
		if (!Arrays.asList(currentDirectory.list()).contains("server.properties")) {
			inv.setItem(inv.getSize() - 1, ItemUtils.setDisplayName(new ItemStack(Material.ARROW), "Go up - " + (pName == null || pName.equals(".") ? "Server root folder" : pName)));
		}
		player.openInventory(inv);
		return currentDirectory.getAbsolutePath();
	}
}
