package io.github.skepter.allassets.version;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.reflection.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import net.minecraft.server.v1_8_R1.Block;
import net.minecraft.server.v1_8_R1.BlockContainer;
import net.minecraft.server.v1_8_R1.BlockPosition;
import net.minecraft.server.v1_8_R1.Blocks;
import net.minecraft.server.v1_8_R1.Chunk;
import net.minecraft.server.v1_8_R1.ChunkSection;
import net.minecraft.server.v1_8_R1.Container;
import net.minecraft.server.v1_8_R1.EntityHuman;
import net.minecraft.server.v1_8_R1.EntityPlayer;
import net.minecraft.server.v1_8_R1.EnumTileEntityState;
import net.minecraft.server.v1_8_R1.IContainer;
import net.minecraft.server.v1_8_R1.PacketPlayOutOpenSignEditor;
import net.minecraft.server.v1_8_R1.TileEntity;
import net.minecraft.server.v1_8_R1.TileEntityContainerAnvil;
import net.minecraft.server.v1_8_R1.TileEntitySign;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class V1_8_R1 implements NMS {

	Plugin plugin;

	public V1_8_R1(AllAssets allAssets) {
		this.plugin = allAssets;
	}

	@Override
	public int getPing(Player player) {
		return ((CraftPlayer) player).getHandle().ping;
	}

	@Override
	public void setInvunerability(Player player, boolean invunerable) {
		((CraftPlayer) player).getHandle().abilities.isInvulnerable = invunerable;
	}

	@Override
	public boolean isInvunerable(Player player) {
		return ((CraftPlayer) player).getHandle().abilities.isInvulnerable;
	}

	@Override
	public void openAnvil(Player player) {
		EntityHuman human = (EntityHuman) ((CraftPlayer) player).getHandle();
		EntityPlayer ePlayer = (EntityPlayer) ((CraftPlayer) player).getHandle();

		TileEntityContainerAnvil anv = new TileEntityContainerAnvil(ePlayer.world, BlockPosition.ZERO);
		Container container = anv.createContainer(ePlayer.inventory, human);
		container.checkReachable = false;
		ePlayer.openTileEntity(anv);
		human.activeContainer = container;
		try {
			Field f = ePlayer.getClass().getDeclaredField("containerCounter");
			f.setAccessible(true);
			human.activeContainer.windowId = f.getInt(ePlayer);
		} catch (Exception e) {
		}
		human.activeContainer.addSlotListener(ePlayer);
	}

	@Override
	public void openSign(Player player, Sign sign) {
		TileEntitySign teSign = null;
		try {
			Field field = sign.getClass().getDeclaredField("sign");
			field.setAccessible(true);
			teSign = (TileEntitySign) field.get(sign);
		} catch (Exception e) {
		}

		teSign.isEditable = true;

		BlockPosition blockPosition = new BlockPosition(sign.getLocation().getBlockX(), sign.getLocation().getBlockY(), sign.getLocation().getBlockZ());

		PacketPlayOutOpenSignEditor packet = new PacketPlayOutOpenSignEditor(blockPosition);

		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}

	@Override
	public boolean setBlock(Location loc, int blockId, byte data) {
		World world = loc.getWorld();
		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		net.minecraft.server.v1_8_R1.World w = ((CraftWorld) world).getHandle();
		Chunk chunk = w.getChunkAt(x >> 4, z >> 4);
		return a(chunk, x & 0x0f, y, z & 0x0f, Block.getById(blockId), data, loc);
	}

//	@SuppressWarnings("deprecation")
	private boolean a(Chunk that, int i, int j, int k, Block block, int l, Location location) {
		try {
			int i1 = k << 4 | i;
			if (j >= ((int[]) ReflectionUtils.getPrivateFieldValue(that, "f"))[i1] - 1) {
				int[] arr = (int[]) ReflectionUtils.getPrivateFieldValue(that, "f");
				arr[i1] = -999;
				ReflectionUtils.setFinalPrivateField(that, "f", arr);
			}

			int j1 = that.heightMap[i1];

			Method getType = that.getClass().getDeclaredMethod("getType", int.class, int.class, int.class);
			getType.setAccessible(true);
			Block block1 = (Block) getType.invoke(that, i, j, k);

			/* getData method */

			int k1;

			if (j >> 4 >= that.getSections().length) {
				k1 = 0;
			}
			ChunkSection cs = that.getSections()[(j >> 4)];
			k1 = ((cs != null) ? cs.c(i, j & 0xF, k) : 0);

			/* End of getData method */

			if (block1 == block && k1 == l) {
				return false;
			} else {
				boolean flag = false;
				ChunkSection chunksection = that.getSections()[j >> 4];

				if (chunksection == null) {
					if (block == Blocks.AIR) {
						return false;
					}
					boolean e = (boolean) ReflectionUtils.getPrivateFieldValue(that.world.worldProvider, "e");
					chunksection = that.getSections()[j >> 4] = new ChunkSection(j >> 4 << 4, !e);
					flag = j >= j1;
				}

				int l1 = that.locX * 16 + i;
				int i2 = that.locZ * 16 + k;

				if (!that.world.isStatic) {
					block1.f(that.world, new BlockPosition(l1, j, i2));
					//block1.f(that.world, l1, j, i2, k1);
				}

				// CraftBukkit start - Delay removing containers until after they're cleaned up
				if (!(block1 instanceof IContainer)) {
					chunksection.setType(i, j & 15, k, block.getBlockData());
					//				chunksection.setTypeId(i, j & 15, k, block);
				}
				// CraftBukkit end

				if (!that.world.isStatic) {
					block1.remove(that.world, new BlockPosition(l1, j, i2), block1.fromLegacyData(k1));
				} else if (block1 instanceof IContainer && block1 != block) {

					/* p method */

					TileEntity tileentity = that.world.getTileEntity(new BlockPosition(l1, j, i2));
					if ((tileentity != null) && ((boolean) ReflectionUtils.getPrivateFieldValue(that.world, "L"))) {
						ReflectionUtils.setPrivateField(tileentity, "d", true);
						@SuppressWarnings("rawtypes")
						List list = (List) ReflectionUtils.getPrivateFieldValue(that.world, "a");
						list.remove(tileentity);
						ReflectionUtils.setFinalPrivateField(that.world, "a", list);
					} else {
						if (tileentity != null) {
							@SuppressWarnings("rawtypes")
							List list = (List) ReflectionUtils.getPrivateFieldValue(that.world, "a");
							list.remove(tileentity);
							ReflectionUtils.setFinalPrivateField(that.world, "a", list);
							that.world.tileEntityList.remove(tileentity);
						}

						Chunk chunk = that.world.getChunkAt(i >> 4, k >> 4);

						if (chunk != null)
							chunk.f(new BlockPosition(i & 0xF, j, k & 0xF));
					}

					/* End of p method*/
				}

				// CraftBukkit start - Remove containers now after cleanup
				if (block1 instanceof IContainer) {
					chunksection.setType(i, j & 15, k, block.getBlockData());
				}
								
				// CraftBukkit end

				if (chunksection.getType(i, j & 15, k) != block.getBlockData()) {
					return false;
				} else {
					chunksection.setType(i, j & 15, k, block.fromLegacyData(l));
					if (flag) {
						that.initLighting();
					}
					TileEntity tileentity;

					if (block1 instanceof IContainer) {
						tileentity = that.a(new BlockPosition(i, j, k), EnumTileEntityState.IMMEDIATE);
						if (tileentity != null) {
							tileentity.u();
						}
					}

					// CraftBukkit - Don't place while processing the BlockPlaceEvent, unless it's a BlockContainer
					if (!that.world.isStatic && (!that.world.captureBlockStates || (block instanceof BlockContainer))) {
						block.onPlace(that.world, new BlockPosition(l1, j, i2), null);
					}

					if (block instanceof IContainer) {
						// CraftBukkit start - Don't create tile entity if placement failed
						Method m = that.getClass().getDeclaredMethod("getType", int.class, int.class, int.class);
						m.setAccessible(true);
						if ((Block) m.invoke(that, i, j, k) != block) {
							return false;
						}
						// CraftBukkit end

						tileentity = that.a(new BlockPosition(i, j, k), EnumTileEntityState.IMMEDIATE);
						if (tileentity == null) {
							tileentity = ((IContainer) block).a(that.world, l);
							that.world.setTileEntity(new BlockPosition(l1, j, i2), tileentity);
						}

						if (tileentity != null) {
							tileentity.u();
						}
					}

					ReflectionUtils.setPrivateField(that, "q", true);
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
