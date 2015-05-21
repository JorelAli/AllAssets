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
package io.github.skepter.allassets.commands.worldmodifier;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.api.utils.Cuboid;
import io.github.skepter.allassets.items.BlockInfo;
import io.github.skepter.allassets.utils.DoubleMap;
import io.github.skepter.allassets.utils.InputParser;
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
import org.bukkit.entity.Player;

public class WM_Methods {

	public WM_Methods(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@SuppressWarnings("deprecation")
	@CommandHandler(name = "worldmodifier", aliases = { "wm" }, permission = "worldmodifier", description = "World Modifier")
	public void wm(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		final WorldModifierData data = WorldModifierHandler.getData(player);
		Cuboid cuboid = new Cuboid(player);
		if (player != null) {
			if (args.getArgs().length == 0)
				data.toggleWandStatus();
			else {
//				final int divisor = 1000;
				switch (args.getArgs()[0]) {
					case "cut": {
						Bukkit.dispatchCommand(player, "/wm set 0");
						break;
					}
					case "set": {
						final BlockInfo info = new InputParser(args.getArgs()[1]).parseBlockInfo();
						final List<Block> blocks = cuboid.blocksFromTwoPointsEx(info);
						data.setPreviousAction(cuboid);
						player.sendMessage(Strings.TITLE + "Setting " + blocks.size() + " blocks to " + TextUtils.capitalize(info.getMaterial().name().toLowerCase().replace('_', ' ')) /*+ " (Estimate " + ((blocks.size() / divisor) / 4) + " seconds)"*/);
//						if (blocks.size() < divisor)
							for (final Block b : blocks) {
								AllAssets.instance().getNMS().setBlock(b.getLocation(), info.getMaterial().getId(), info.getData());
//								b.setType(info.getMaterial());
//								b.setData(info.getData());
							}
//						for (final Block b : blocks.subList(blocks.size() / divisor, blocks.size())) {
//							b.setType(info.getMaterial());
//							b.setData(info.getData());
//						}
//						Bukkit.getScheduler().scheduleSyncDelayedTask(AllAssets.instance(), new Runnable() {
//							@Override
//							public void run() {
//								try {
//									player.sendMessage(Strings.TITLE + "Complete!");
//								} catch (final Exception e) {
//									e.printStackTrace();
//								}
//							}
//						}, (blocks.size() - divisor) / divisor * 5);
//						for (int i = 0; i < blocks.size() - divisor; i += divisor) {
//							final List<Block> blocksList = blocks.subList(i, i + divisor);
//							Bukkit.getScheduler().scheduleSyncDelayedTask(AllAssets.instance(), new Runnable() {
//								@Override
//								public void run() {
//									for (final Block b : blocksList) {
//										b.setType(info.getMaterial());
//										b.setData(info.getData());
//									}
//								}
//							}, (i / divisor) * 5);
//						}
						break;
					}
					case "regen": {
						final List<Block> blocks = cuboid.getChunkData();
						final Set<Chunk> chunks = new HashSet<Chunk>();
						for (final Block b : blocks)
							chunks.add(player.getWorld().getChunkAt(b));
						for (final Chunk c : chunks)
							player.getWorld().regenerateChunk(c.getX(), c.getZ());
						break;
					}
					case "replace":
					case "repl": {
						final BlockInfo info = new InputParser(args.getArgs()[1]).parseBlockInfo();
						final BlockInfo info2 = new InputParser(args.getArgs()[2]).parseBlockInfo();

						final Material mat = info.getMaterial();
						final Material matToReplaceWith = info2.getMaterial();
						final List<Block> blocks = cuboid.blocksFromTwoPointsInc(mat);
						data.setPreviousAction(cuboid);
						player.sendMessage(Strings.TITLE + "Replacing " + blocks.size() + " blocks to " + TextUtils.capitalize(matToReplaceWith.name().toLowerCase()) /*+ " (Estimate " + ((blocks.size() / divisor) / 4) + " seconds)"*/);
//						if (blocks.size() < divisor)
							for (final Block b : blocks) {
								b.setType(matToReplaceWith);
								b.setData(info2.getData());
							}
//						for (final Block b : blocks.subList(blocks.size() / divisor, blocks.size())) {
//							b.setType(matToReplaceWith);
//							b.setData(info2.getData());
//						}
//
//						Bukkit.getScheduler().scheduleSyncDelayedTask(AllAssets.instance(), new Runnable() {
//
//							@Override
//							public void run() {
//								try {
//									player.sendMessage(Strings.TITLE + "Complete!");
//								} catch (final Exception e) {
//									e.printStackTrace();
//								}
//							}
//
//						}, (blocks.size() - divisor) / divisor * 5);
//						for (int i = 0; i < blocks.size() - divisor; i += divisor) {
//							final List<Block> blocksList = blocks.subList(i, i + divisor);
//
//							Bukkit.getScheduler().scheduleSyncDelayedTask(AllAssets.instance(), new Runnable() {
//
//								@Override
//								public void run() {
//									for (final Block b : blocksList) {
//										b.setType(matToReplaceWith);
//										b.setData(info2.getData());
//									}
//								}
//							}, (i / divisor) * 5);
//						}
						break;
					}
					case "expand":
					case "max": {
						final Location a = data.getPos1();
						final Location b = data.getPos2();
						a.setY(256);
						b.setY(0);
						data.setPos1(a);
						data.setPos2(b);
						player.sendMessage(Strings.TITLE + "Expanded selection");
						break;
					}
					case "undo": {
						final DoubleMap<Location, Material, Byte> map = data.getPreviousAction();
						Set<Location> blockList = data.getPreviousAction().keySet();
//						List<Location> blockList = new ArrayList<Location>();
//						blockList.addAll(data.getPreviousAction().keySet());
						player.sendMessage(Strings.TITLE + "Undoing..." /*+ " (Estimate " + ((blockList.size() / divisor) / 4) + " seconds)"*/);

//						if (blockList.size() < divisor) {
							for (Location loc : blockList) {
								loc.getBlock().setType(map.getValue1(loc));
								loc.getBlock().setData(map.getValue2(loc));
							}
							return;
//						}
//						for (final Location loc : (new LinkedList<Location>(blockList)).subList(((blockList.size() + (blockList.size() % divisor)) - divisor), blockList.size())) {
//							loc.getBlock().setType(map.getValue1(loc));
//							loc.getBlock().setData(map.getValue2(loc));
//							blockList.remove(loc);
//						}
//						Bukkit.getScheduler().scheduleSyncDelayedTask(AllAssets.instance(), new Runnable() {
//							@Override
//							public void run() {
//								try {
//									player.sendMessage(Strings.TITLE + "Complete!");
//								} catch (final Exception e) {
//									e.printStackTrace();
//								}
//							}
//						}, (blockList.size() - divisor) / divisor * 5);
//						for (int i = 0; i < blockList.size() - divisor; i += divisor) {
//							final List<Location> bl = (new LinkedList<Location>(blockList)).subList(i, i + divisor);
//							Bukkit.getScheduler().scheduleSyncDelayedTask(AllAssets.instance(), new Runnable() {
//								@Override
//								public void run() {
//									for (final Location loc : bl) {
//										loc.getBlock().setType(map.getValue1(loc));
//										loc.getBlock().setData(map.getValue2(loc));
//										bl.remove(loc);
//									}
//								}
//							}, (i / divisor) * 5);
//						}
//						break;
					}
				}
			}
		}

	}
}
