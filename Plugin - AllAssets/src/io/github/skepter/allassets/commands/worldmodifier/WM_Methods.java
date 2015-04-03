package io.github.skepter.allassets.commands.worldmodifier;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.api.utils.Cuboid;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class WM_Methods {

	//LOWERCASENAME: /back
	//CAMELCASENAME: /Back

	//	@CommandHandler(name = "LOWERCASENAME", aliases = { "ALIASES" }, permission = "LOWERCASENAME", description = "DESCRIPTION")
	//	public void onCommand(final CommandArgs args) {
	//		Player player = PlayerGetter.getPlayer(args);
	//		if (player != null) {
	//			switch (args.getArgs().length) {
	//				case 0:
	//					printHelp(player);
	//					return;
	//				case 1:
	//					Player target = PlayerGetter.getTarget(player, args.getArgs()[0]);
	//					if (target != null) {
	//
	//					}
	//			}
	//		}
	//		return;
	//	}
	//
	//	@Help(name = "CAMELCASENAME")
	//	public void printHelp(final CommandSender sender) {
	//		TextUtils.printHelp(sender, "CAMELCASENAME", "/LOWERCASENAME ARGUMENT - DESCRIPTION");
	//	}

	public WM_Methods(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@SuppressWarnings("deprecation")
	@CommandHandler(name = "worldmodifier", aliases = { "wm" }, permission = "worldmodifier", description = "World Modifier")
	public void wm(final CommandArgs args) {
		try {
			if (args.getArgs().length == 0)
				if (WorldModifierHandler.getWmPlayers().remove(args.getPlayer().getUniqueId())) {
					args.getPlayer().sendMessage("World Modifier mode off");
					return;
				} else {
					WorldModifierHandler.getWmPlayers().add(args.getPlayer().getUniqueId());
					args.getPlayer().sendMessage("World Modifier mode on");
					return;
				}
			else {
				final int divisor = 1000;
				switch (args.getArgs()[0]) {
					case "set": {
						final Material mat = Material.getMaterial(Integer.parseInt(args.getArgs()[1]));
						final List<Block> blocks = Cuboid.blocksFromTwoPointsEx(WorldModifierHandler.getPos1().get(args.getPlayer()), WorldModifierHandler.getPos2().get(args.getPlayer()), mat);

						args.getPlayer().sendMessage(Strings.TITLE + "Setting " + blocks.size() + " blocks to " + TextUtils.capitalize(mat.name().toLowerCase().replace('_', ' ')) + " (Estimate " + ((blocks.size() / divisor) / 4) + " seconds)");
						if (blocks.size() < divisor)
							for (final Block b : blocks)
								b.setType(mat);
						for (final Block b : blocks.subList(((blocks.size() + (blocks.size() % divisor)) - divisor), blocks.size()))
							b.setType(mat);
						Bukkit.getScheduler().scheduleSyncDelayedTask(AllAssets.instance(), new Runnable() {
							@Override
							public void run() {
								try {
									args.getPlayer().sendMessage(Strings.TITLE + "Complete!");
								} catch (final Exception e) {
									e.printStackTrace();
								}
							}
						}, (blocks.size() - divisor) / divisor * 5);
						for (int i = 0; i < blocks.size() - divisor; i += divisor) {
							final List<Block> blocksList = blocks.subList(i, i + divisor);
							Bukkit.getScheduler().scheduleSyncDelayedTask(AllAssets.instance(), new Runnable() {
								@Override
								public void run() {
									for (final Block b : blocksList)
										b.setType(mat);
								}
							}, (i / divisor) * 5);
						}
						break;
					}
					case "regen": {
						final List<Block> blocks = Cuboid.getChunkData(WorldModifierHandler.getPos1().get(args.getPlayer()), WorldModifierHandler.getPos2().get(args.getPlayer()));
						final Set<Chunk> chunks = new HashSet<Chunk>();
						for (final Block b : blocks)
							chunks.add(args.getPlayer().getWorld().getChunkAt(b));
						for (final Chunk c : chunks)
							args.getPlayer().getWorld().regenerateChunk(c.getX(), c.getZ());
						break;
					}
					case "replace":
					case "repl": {
						final Material mat = Material.getMaterial(Integer.parseInt(args.getArgs()[1]));
						final Material matToReplaceWith = Material.getMaterial(Integer.parseInt(args.getArgs()[2]));
						final List<Block> blocks = Cuboid.blocksFromTwoPointsInc(WorldModifierHandler.getPos1().get(args.getPlayer()), WorldModifierHandler.getPos2().get(args.getPlayer()), mat);
						args.getPlayer().sendMessage(Strings.TITLE + "Replacing " + blocks.size() + " blocks to " + TextUtils.capitalize(matToReplaceWith.name().toLowerCase()) + " (Estimate " + ((blocks.size() / divisor) / 4) + " seconds)");
						if (blocks.size() < divisor)
							for (final Block b : blocks)
								b.setType(matToReplaceWith);
						for (final Block b : blocks.subList(((blocks.size() + (blocks.size() % divisor)) - divisor), blocks.size()))
							b.setType(matToReplaceWith);

						Bukkit.getScheduler().scheduleSyncDelayedTask(AllAssets.instance(), new Runnable() {

							@Override
							public void run() {
								try {
									args.getPlayer().sendMessage(Strings.TITLE + "Complete!");
								} catch (final Exception e) {
									e.printStackTrace();
								}
							}

						}, (blocks.size() - divisor) / divisor * 5);
						for (int i = 0; i < blocks.size() - divisor; i += divisor) {
							final List<Block> blocksList = blocks.subList(i, i + divisor);

							Bukkit.getScheduler().scheduleSyncDelayedTask(AllAssets.instance(), new Runnable() {

								@Override
								public void run() {
									for (final Block b : blocksList)
										b.setType(matToReplaceWith);
								}
							}, (i / divisor) * 5);
						}
						break;
					}
					case "expand":
					case "max": {
						final Location a = WorldModifierHandler.getPos1().get(args.getPlayer());
						final Location b = WorldModifierHandler.getPos2().get(args.getPlayer());
						a.setY(256);
						b.setY(0);
						WorldModifierHandler.getPos1().put(args.getPlayer(), a);
						WorldModifierHandler.getPos2().put(args.getPlayer(), b);
						args.getPlayer().sendMessage(Strings.TITLE + "Expanded selection");
					}
				}

			}
		} catch (final Exception e) {
		}
	}
}
