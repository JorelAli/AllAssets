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
/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.commandlisteners;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.api.Paginator;
import io.github.skepter.allassets.api.builders.ItemBuilder;
import io.github.skepter.allassets.api.utils.PlayerMap;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.MathUtils;
import io.github.skepter.allassets.utils.utilclasses.PluginUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils.SeperatorType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

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
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class CommandFileBrowser implements Listener {

	public CommandFileBrowser(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	public PlayerMap<String> directoryMap = new PlayerMap<String>(AllAssets.instance());
	public PlayerMap<List<String>> dataMap = new PlayerMap<List<String>>(AllAssets.instance());

	@CommandHandler(name = "filebrowser", aliases = { "fb" }, permission = "filebrowser", description = "Browses files and shows configs")
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
				directoryMap.put(player, ".");
				return;
			case 1:
				int arg = 1;
				if (args.getArgs().length == 1)
					arg = Integer.parseInt(args.getArgs()[0]);

				if (dataMap.get(player) == null) {
					ErrorUtils.cannotShowNextPage(player);
					return;
				}
				new Paginator(dataMap.get(player), 10).send(player, arg);
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
				final ItemStack item = event.getInventory().getItem(event.getSlot());
				final Player player = (Player) event.getWhoClicked();
				final String dM = directoryMap.get(player);
				switch (item.getType()) {
				/* If they click a book, open that directory */
					case BOOK:
						directoryMap.put(player, openInventory(player, new File(dM, File.separator + new ItemBuilder(item).getDisplayName())));
						return;
						/* If they click an arrow, go up another level */
					case ARROW:
						/* Prevents them idiots from getting out of the server folder and causing havoc :) */
						if (Arrays.asList(new File(dM).list()).contains("server.properties"))
							return;
						directoryMap.put(player, openInventory(player, new File(dM).getParentFile()));
						return;
						/* Read the file */
					case PAPER: {
						player.closeInventory();
						final File dataFile = new File(dM, new ItemBuilder(item).getDisplayName());
						player.sendMessage(TextUtils.title(dataFile.getName()));
						/* Only allow .yml, .txt, .properties files as they are each read differently */
						if (dataFile.getName().contains(".yml")) {
							final YamlConfiguration config = new YamlConfiguration();
							try {
								config.load(dataFile);
							} catch (final Exception e) {
								ErrorUtils.cannotReadFile(player);
								return;
							}
							final List<String> list = new ArrayList<String>();
							for (final String key : config.getKeys(true))
								list.add(Strings.HOUSE_STYLE_COLOR + key + Strings.ACCENT_COLOR + SeperatorType.COLON.getString() + (config.get(key).toString().contains("MemorySection[path=") ? "" : ChatColor.translateAlternateColorCodes('&', config.getString(key))));
							dataMap.put(player, list);
						}
						if (dataFile.getName().contains(".txt")) {
							final BufferedReader reader = new BufferedReader(new FileReader(dataFile));
							String line;
							final List<String> list = new ArrayList<String>();
							while ((line = reader.readLine()) != null)
								list.add(line);
							reader.close();
							dataMap.put(player, list);
						}
						if (dataFile.getName().contains(".properties")) {
							final Properties prop = new Properties();
							final InputStream inputStream = new FileInputStream(dataFile);
							prop.load(inputStream);
							final List<String> list = new ArrayList<String>();
							for (final Object key : prop.keySet())
								list.add(Strings.HOUSE_STYLE_COLOR + key.toString() + Strings.ACCENT_COLOR + SeperatorType.COLON.getString() + ChatColor.translateAlternateColorCodes('&', prop.get(key).toString()));
							dataMap.put(player, list);
						}
						break;
					}
					case EMPTY_MAP: {
						player.closeInventory();
						player.sendMessage(TextUtils.title(new ItemBuilder(item).getDisplayName()));
						PluginDescriptionFile p = Bukkit.getPluginManager().getPlugin(new ItemBuilder(item).getDisplayName()).getDescription();
						List<String> list = new ArrayList<String>();
						String s = SeperatorType.COLON.getString();
						list.add(Strings.HOUSE_STYLE_COLOR + "Main" + Strings.ACCENT_COLOR + s + p.getMain());
						list.add(Strings.HOUSE_STYLE_COLOR + "Name" + Strings.ACCENT_COLOR + s + p.getName());
						list.add(Strings.HOUSE_STYLE_COLOR + "Version" + Strings.ACCENT_COLOR + s + p.getVersion());
						list.add(Strings.HOUSE_STYLE_COLOR + "Description" + Strings.ACCENT_COLOR + s + p.getDescription());
						list.add(Strings.HOUSE_STYLE_COLOR + "Authors" + Strings.ACCENT_COLOR + s + PluginUtils.getAuthors(p));
						if (!TextUtils.listToString(p.getDepend()).equals(""))
							list.add(Strings.HOUSE_STYLE_COLOR + "Dependencies" + Strings.ACCENT_COLOR + s + TextUtils.listToString(p.getDepend()));
						if (!TextUtils.listToString(p.getSoftDepend()).equals(""))
							list.add(Strings.HOUSE_STYLE_COLOR + "Soft dependencies" + Strings.ACCENT_COLOR + s + TextUtils.listToString(p.getSoftDepend()));
						dataMap.put(player, list);
						break;
					}
					default:
						break;
				}
				/* Paginate data */
				if (dataMap.get(player).size() < 10)
					for (final String s : dataMap.get(player))
						player.sendMessage(s);
				else {
					new Paginator(dataMap.get(player), 10).send(player, 1);
					player.sendMessage(Strings.TITLE + "Use /filebrowser <page number> to go to the next page");
				}
			}
		} catch (final Exception e) {
			System.out.println("Error");
		}
	}

	private String openInventory(final Player player, final File currentDirectory) {
		int fileCountRoot = 1;
		final String[] supportedFileTypes = new String[] { ".yml", ".properties", ".txt" };
		for (final File file : currentDirectory.listFiles()) {
			for (final String s : supportedFileTypes)
				if (file.getName().contains(s))
					fileCountRoot++;
			if (file.isDirectory())
				fileCountRoot++;
		}
		String name = currentDirectory.getName();
		final String pName = currentDirectory.getName();
		if (name.equals("."))
			name = "Server root folder";
		if (Arrays.asList(currentDirectory.list()).contains("server.properties"))
			fileCountRoot--;
		final Inventory inv = Bukkit.createInventory(null, MathUtils.toInt(MathUtils.roundUp(fileCountRoot, 9)), ChatColor.BLUE + "File - " + name);
		for (final File file : currentDirectory.listFiles()) {
			for (final String s : supportedFileTypes)
				if (file.getName().contains(s))
					inv.addItem(new ItemBuilder(new ItemStack(Material.PAPER, 1)).setDisplayName(file.getName()).build());
			if (file.isDirectory())
				inv.addItem(new ItemBuilder(new ItemStack(Material.BOOK, 1)).setDisplayName(file.getName()).build());
			if (file.getName().equals("AllAssets.jar"))
				for (Plugin p : Bukkit.getPluginManager().getPlugins())
					inv.addItem(new ItemBuilder(new ItemStack(Material.EMPTY_MAP, 1)).setDisplayName(p.getName() + " plugin.yml").build());
		}
		if (!Arrays.asList(currentDirectory.list()).contains("server.properties"))
			inv.setItem(inv.getSize() - 1, new ItemBuilder(new ItemStack(Material.ARROW)).setDisplayName("Go up - " + ((pName == null) || pName.equals(".") ? "Server root folder" : pName)).build());
		player.openInventory(inv);
		return currentDirectory.getAbsolutePath();
	}
}
