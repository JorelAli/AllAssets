/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
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
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. The only exception is the BukkitDev staff who I have allowed permission to decompile (to search for malicious code)
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
import io.github.Skepter.AllAssets.CommandFramework.Completer;
import io.github.Skepter.AllAssets.API.PlayerMap;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.ItemUtils;
import io.github.Skepter.AllAssets.Utils.MathUtils;
import io.github.Skepter.AllAssets.Utils.YesNoConversation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.conversations.BooleanPrompt;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CommandFileEditor implements Listener {

	public CommandFileEditor(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	public PlayerMap<UUID, String> directoryMap = new PlayerMap<UUID, String>(AllAssets.instance());
	public PlayerMap<UUID, String> fileMap = new PlayerMap<UUID, String>(AllAssets.instance());

	@CommandHandler(name = "fileeditor", aliases = { "fe" }, permission = "fileeditor", description = "Edits files", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		switch (args.getArgs().length) {
		case 0:
		case 1:
			openInventory(player, new File("."));
			directoryMap.put(player, ".");
			return;
		case 2:
			final File dataFile = new File(fileMap.get(player));
			final YamlConfiguration config = new YamlConfiguration();
			try {
				config.load(dataFile);
			} catch (final Exception e) {
				ErrorUtils.error(player, "That file could not be read!");
				return;
			}
			new YesNoConversation(player, new EditFilePrompt(dataFile, config, args.getArgs()[0], args.getArgs()[1]), "Are you sure you want to change " + args.getArgs()[0] + " to " + args.getArgs()[1] + " - this cannot be undone!");
			return;
		}

	}

	@Completer(name = "fileeditor", aliases = { "fe" })
	public List<String> testCompleter(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
		}
		final File dataFile = new File(fileMap.get(player));
		final YamlConfiguration config = new YamlConfiguration();
		try {
			config.load(dataFile);
		} catch (final Exception e) {
			ErrorUtils.error(player, "That file could not be read!");
		}
		final List<String> list = new ArrayList<String>();
		for (final String string : config.getKeys(true))
			list.add(string);
		return list;
	}

	@EventHandler
	public void onInventoryClick(final InventoryClickEvent event) {
		try {
			if (!event.getAction().equals(InventoryAction.PICKUP_ALL) || (event.getSlot() == -999) || (event.getInventory().getItem(event.getSlot()) == null))
				return;
			event.setCancelled(true);
			/* If they open a file inventory */
			if (event.getInventory().getName().startsWith(ChatColor.BLUE + "Choose a file...")) {
				final ItemStack item = event.getInventory().getItem(event.getSlot());
				final Player player = (Player) event.getWhoClicked();
				final String dM = directoryMap.get(player);
				switch (item.getType()) {
				/* If they click a book, open that directory */
				case BOOK:
					directoryMap.put(player, openInventory(player, new File(dM, File.separator + ItemUtils.getDisplayName(item))));
					return;
					/* If they click an arrow, go up another level */
				case ARROW:
					/* Prevents them idiots from getting out of the server folder and causing havoc :) */
					if (Arrays.asList(new File(dM).list()).contains("server.properties"))
						return;
					directoryMap.put(player, openInventory(player, new File(dM).getParentFile()));
					return;
					/* Read the file */
				case PAPER:
					player.closeInventory();
					final File dataFile = new File(dM, ItemUtils.getDisplayName(item));
					if (dataFile.getName().contains(".yml")) {
						final YamlConfiguration config = new YamlConfiguration();
						try {
							config.load(dataFile);
						} catch (final Exception e) {
							ErrorUtils.error(player, "That file could not be read!");
							return;
						}
						fileMap.put(player, dataFile.getAbsolutePath());
						player.sendMessage(AllAssets.title + dataFile.getName() + " chosen. Use /fe <setting> <value> to edit the file.");
						return;
					}
				default:
					return;
				}

			}
		} catch (final Exception e) {
			System.out.println("Error");
		}
	}

	private String openInventory(final Player player, final File currentDirectory) {
		int fileCountRoot = 1;
		final String[] supportedFileTypes = new String[] { ".yml" };
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
		final Inventory inv = Bukkit.createInventory(null, MathUtils.toInt(MathUtils.roundUp(fileCountRoot, 9)), ChatColor.BLUE + "Choose a file...");
		for (final File file : currentDirectory.listFiles()) {
			for (final String s : supportedFileTypes)
				if (file.getName().contains(s))
					inv.addItem(ItemUtils.setDisplayName(new ItemStack(Material.PAPER, 1), file.getName()));
			if (file.isDirectory())
				inv.addItem(ItemUtils.setDisplayName(new ItemStack(Material.BOOK, 1), file.getName()));
		}
		if (!Arrays.asList(currentDirectory.list()).contains("server.properties"))
			inv.setItem(inv.getSize() - 1, ItemUtils.setDisplayName(new ItemStack(Material.ARROW), "Go up - " + ((pName == null) || pName.equals(".") ? "Server root folder" : pName)));
		player.openInventory(inv);
		return currentDirectory.getAbsolutePath();
	}
}

class EditFilePrompt extends BooleanPrompt {

	File file;
	YamlConfiguration config;
	String setting;
	String value;

	public EditFilePrompt(final File file, final YamlConfiguration config, final String setting, final String value) {
		this.file = file;
		this.config = config;
		this.setting = setting;
		this.value = value;
	}

	@Override
	public String getPromptText(final ConversationContext context) {
		return YesNoConversation.getPromptText();
	}

	@Override
	protected Prompt acceptValidatedInput(final ConversationContext context, final boolean b) {
		if (b) {
			config.set(setting, value);
			try {
				config.save(file);
			} catch (final IOException e) {
				context.getForWhom().sendRawMessage(AllAssets.error + "There was an error whilst changing that value");
				return Prompt.END_OF_CONVERSATION;
			}
			context.getForWhom().sendRawMessage(AllAssets.title + "Changed " + setting + " to " + value);
		}
		return Prompt.END_OF_CONVERSATION;
	}
}
