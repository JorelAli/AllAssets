package io.github.Skepter.Commands;

import io.github.Skepter.AllAssets;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Utils.TextUtils;

import java.util.ArrayList;

import net.minecraft.util.org.apache.commons.lang3.StringUtils;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandChestSearch {

	public CommandChestSearch(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@SuppressWarnings("deprecation")
	@CommandHandler(name = "chestsearch", aliases = { "csearch" }, permission = "chestsearch", description = "Search for items in chests", usage = "Use <command>")
	public void command(final CommandArgs args) {
		final Player player = args.getPlayer();
		if (args.getArgs().length == 0) {
			player.sendMessage(TextUtils.title("Chest search"));
			player.sendMessage("Use chest search to find items in chests in the world");
			player.sendMessage("Usage:");
			player.sendMessage("/chestsearch hand - search for chests with the item in your hand");
			player.sendMessage("/chestsearch <itemID> - search for chests with that item ID");
			return;
		} else if (args.getArgs().length == 1) {

			//probably store chests into a yaml file for searching for easy locations
			//of placed chests. or hook into LWC or something

			final ArrayList<Chest> chests = new ArrayList<Chest>();
			for (final Chunk chunk : player.getWorld().getLoadedChunks())
				for (final BlockState e : chunk.getTileEntities())
					if (e instanceof Chest)
						chests.add((Chest) e);
			final ArrayList<Location> locatedChests = new ArrayList<Location>();
			for (final Chest chest : chests)
				if (args.getArgs()[0].equalsIgnoreCase("hand")) {
					if (chest.getInventory().containsAtLeast(player.getItemInHand(), 1))
						locatedChests.add(chest.getLocation());
				} else if (StringUtils.isNumeric(args.getArgs()[0])) {
					final int i = Integer.parseInt(args.getArgs()[0]);
					if (chest.getInventory().containsAtLeast(new ItemStack(i), 1))
						locatedChests.add(chest.getLocation());
				}
			player.sendMessage(AllAssets.instance().title + "Located " + (locatedChests.size()) + " chests");
			for (final Location loc : locatedChests)
				player.sendMessage(TextUtils.subTitle("X: " + loc.getX() + " Y: " + loc.getY() + " Z: " + loc.getZ()));
		}
	}
}
