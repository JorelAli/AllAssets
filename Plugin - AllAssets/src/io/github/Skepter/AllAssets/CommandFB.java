/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets;

import io.github.Skepter.AllAssets.Commands.CommandFramework;
import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.ItemUtils;
import io.github.Skepter.AllAssets.Utils.MathUtils;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CommandFB implements Listener {

	/** --- TOP PRIORITY --- The FileBrowser: Uses GUI Shows ONLY YAML files and
	 * folders File = paper Folder = Chest Upon opening file Shows YAML content
	 * parsed (in chat) allows modifications Shows content in colors for easy
	 * viewing Use some edit command to edit the YAML values Auto saving upon
	 * yaml value changing */
	public CommandFB(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	public Map<UUID, String> directoryMap = new HashMap<UUID, String>();

	@CommandHandler(name = "fb", permission = "fb", description = "dev", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		openInventory(player, new File("."));
		directoryMap.put(player.getUniqueId(), ".");
	}

	@EventHandler
	public void onInventoryClick(final InventoryClickEvent event) {
		try {
			if (!event.getAction().equals(InventoryAction.PICKUP_ALL) || (event.getSlot() == -999) || (event.getInventory().getItem(event.getSlot()) == null))
				return;
			event.setCancelled(true);
			if (event.getInventory().getName().startsWith("File - ")) {
				ItemStack item = event.getInventory().getItem(event.getSlot());
				Player player = (Player) event.getWhoClicked();
				String dM = directoryMap.get(player.getUniqueId());
				switch (item.getType()) {
				case BOOK:
					directoryMap.put(player.getUniqueId(), openInventory(player, new File(dM, File.separator + ItemUtils.getDisplayName(item))));
					return;
				case ARROW:
					/* Prevents them idiots from getting out of the server folder and causing havoc :) */
					if (Arrays.asList(new File(dM).list()).contains("server.properties"))
						return;
					directoryMap.put(player.getUniqueId(), openInventory(player, new File(dM).getParentFile()));
					return;
				case PAPER:
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
		for (File file : currentDirectory.listFiles())
			if (file.getName().contains(".yml") || file.isDirectory())
				fileCountRoot++;
		String name = currentDirectory.getName();
		if (name.equals("."))
			name = "Server root folder";
		if (Arrays.asList(currentDirectory.list()).contains("server.properties"))
			fileCountRoot--;
		Inventory inv = Bukkit.createInventory(null, new Double(MathUtils.roundUp(fileCountRoot, 9)).intValue(), "File - " + name);
		for (File file : currentDirectory.listFiles())
			if (file.getName().contains(".yml")) {
				inv.addItem(ItemUtils.setDisplayName(new ItemStack(Material.PAPER, 1), file.getName()));
			} else if (file.isDirectory()) {
				inv.addItem(ItemUtils.setDisplayName(new ItemStack(Material.BOOK, 1), file.getName()));
			}
		if (!Arrays.asList(currentDirectory.list()).contains("server.properties"))
			inv.setItem(inv.getSize() - 1, ItemUtils.setDisplayName(new ItemStack(Material.ARROW), "Go up - " + currentDirectory.getParentFile().getName() == null ? "Server root folder" : currentDirectory.getParentFile().getName()));
		player.openInventory(inv);
		return currentDirectory.getAbsolutePath();
	}

	@EventHandler
	public void onInventoryClose(final InventoryCloseEvent event) {
		directoryMap.remove(((Player) event.getPlayer()).getUniqueId());
	}

}
