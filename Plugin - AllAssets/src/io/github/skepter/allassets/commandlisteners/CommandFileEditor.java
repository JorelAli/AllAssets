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
import io.github.skepter.allassets.CommandFramework.Completer;
import io.github.skepter.allassets.api.builders.ItemBuilder;
import io.github.skepter.allassets.api.utils.PlayerMap;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.YesNoConversation;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.MathUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	public PlayerMap<String> directoryMap = new PlayerMap<String>(AllAssets.instance());
	public PlayerMap<String> fileMap = new PlayerMap<String>(AllAssets.instance());

	@CommandHandler(name = "fileeditor", aliases = { "fe" }, permission = "fileeditor", description = "Edits files")
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
					case PAPER:
						player.closeInventory();
						final File dataFile = new File(dM, new ItemBuilder(item).getDisplayName());
						if (dataFile.getName().contains(".yml")) {
							final YamlConfiguration config = new YamlConfiguration();
							try {
								config.load(dataFile);
							} catch (final Exception e) {
								ErrorUtils.error(player, "That file could not be read!");
								return;
							}
							fileMap.put(player, dataFile.getAbsolutePath());
							player.sendMessage(Strings.TITLE + dataFile.getName() + " chosen. Use /fe <setting> <value> to edit the file.");
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
					inv.addItem(new ItemBuilder(Material.PAPER).setDisplayName(file.getName()).build());
			if (file.isDirectory())
				inv.addItem(new ItemBuilder(Material.BOOK).setDisplayName(file.getName()).build());
		}
		if (!Arrays.asList(currentDirectory.list()).contains("server.properties"))
			inv.setItem(inv.getSize() - 1, new ItemBuilder(Material.ARROW).setDisplayName("Go up - " + ((pName == null) || pName.equals(".") ? "Server root folder" : pName)).build());
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
				context.getForWhom().sendRawMessage(Strings.ERROR + "There was an error whilst changing that value");
				return Prompt.END_OF_CONVERSATION;
			}
			context.getForWhom().sendRawMessage(Strings.TITLE + "Changed " + setting + " to " + value);
		}
		return Prompt.END_OF_CONVERSATION;
	}
}
