package io.github.skepter.allassets.version.nms;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.reflection.ReflectionUtils;

import java.lang.reflect.Field;

import net.minecraft.server.v1_7_R3.Block;
import net.minecraft.server.v1_7_R3.BlockContainer;
import net.minecraft.server.v1_7_R3.Blocks;
import net.minecraft.server.v1_7_R3.Chunk;
import net.minecraft.server.v1_7_R3.ChunkSection;
import net.minecraft.server.v1_7_R3.EntityPlayer;
import net.minecraft.server.v1_7_R3.IContainer;
import net.minecraft.server.v1_7_R3.NBTTagCompound;
import net.minecraft.server.v1_7_R3.PacketPlayOutOpenSignEditor;
import net.minecraft.server.v1_7_R3.TileEntity;
import net.minecraft.server.v1_7_R3.TileEntitySign;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_7_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_7_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class NMS_V1_7_R3 implements NMS {

	Plugin plugin;

	public NMS_V1_7_R3(AllAssets allAssets) {
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
		//		//BlockPosition blockPosition = new BlockPosition(0, 0, 0);
		//		EntityHuman human = (EntityHuman) ((CraftPlayer) player).getHandle();
		//		EntityPlayer ePlayer = (EntityPlayer) ((CraftPlayer) player).getHandle();
		//
		//		ContainerAnvil anv = new ContainerAnvil(ePlayer.world, blockPosition);
		//		Container container = anv.createContainer(ePlayer.inventory, human);
		//		container.checkReachable = false;
		//		ePlayer.openTileEntity(anv);
		//		human.activeContainer = container;
		//		try {
		//			Field f = ePlayer.getClass().getDeclaredField("containerCounter");
		//			f.setAccessible(true);
		//			human.activeContainer.windowId = f.getInt(ePlayer);
		//		} catch (Exception e) {
		//		}
		//		human.activeContainer.addSlotListener(ePlayer);
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

		PacketPlayOutOpenSignEditor packet = new PacketPlayOutOpenSignEditor(sign.getLocation().getBlockX(), sign.getLocation().getBlockY(), sign.getLocation().getBlockZ());

		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}

	@Override
	public boolean setBlock(Location loc, int blockId, byte data) {
		World world = loc.getWorld();
		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		net.minecraft.server.v1_7_R3.World w = ((CraftWorld) world).getHandle();
		Chunk chunk = w.getChunkAt(x >> 4, z >> 4);
		return a(chunk, x & 0x0f, y, z & 0x0f, Block.e(blockId), data);
	}

	private boolean a(Chunk that, int i, int j, int k, Block block, int l) {
		int i1 = k << 4 | i;

		if (j >= that.b[i1] - 1) {
			that.b[i1] = -999;
		}

		int j1 = that.heightMap[i1];
		Block block1 = that.getType(i, j, k);
		int k1 = that.getData(i, j, k);

		if (block1 == block && k1 == l) {
			return false;
		} else {
			boolean flag = false;
			ChunkSection chunksection = that.i()[j >> 4];

			if (chunksection == null) {
				if (block == Blocks.AIR) {
					return false;
				}

				chunksection = that.i()[j >> 4] = new ChunkSection(j >> 4 << 4, !that.world.worldProvider.g);
				flag = j >= j1;
			}

			int l1 = that.locX * 16 + i;
			int i2 = that.locZ * 16 + k;

			if (!that.world.isStatic) {
				block1.f(that.world, l1, j, i2, k1);
			}

			// CraftBukkit start - Delay removing containers until after they're cleaned up
			if (!(block1 instanceof IContainer)) {
				chunksection.setTypeId(i, j & 15, k, block);
			}
			// CraftBukkit end

			if (!that.world.isStatic) {
				block1.remove(that.world, l1, j, i2, block1, k1);
			} else if (block1 instanceof IContainer && block1 != block) {
				that.world.p(l1, j, i2);
			}

			// CraftBukkit start - Remove containers now after cleanup
			if (block1 instanceof IContainer) {
				chunksection.setTypeId(i, j & 15, k, block);
			}
			// CraftBukkit end

			if (chunksection.getTypeId(i, j & 15, k) != block) {
				return false;
			} else {
				chunksection.setData(i, j & 15, k, l);
				if (flag) {
					that.initLighting();
				}
				TileEntity tileentity;

				if (block1 instanceof IContainer) {
					tileentity = that.e(i, j, k);
					if (tileentity != null) {
						tileentity.u();
					}
				}

				// CraftBukkit - Don't place while processing the BlockPlaceEvent, unless it's a BlockContainer
				if (!that.world.isStatic && (!that.world.captureBlockStates || (block instanceof BlockContainer))) {
					block.onPlace(that.world, l1, j, i2);
				}

				if (block instanceof IContainer) {
					// CraftBukkit start - Don't create tile entity if placement failed
					if (that.getType(i, j, k) != block) {
						return false;
					}
					// CraftBukkit end

					tileentity = that.e(i, j, k);
					if (tileentity == null) {
						tileentity = ((IContainer) block).a(that.world, l);
						that.world.setTileEntity(l1, j, i2, tileentity);
					}

					if (tileentity != null) {
						tileentity.u();
					}
				}

				that.n = true;
				return true;
			}
		}
	}

	@Override
	public String nmsName(ItemStack itemStack) {
		return CraftItemStack.asNMSCopy(itemStack).a();
	}

	@Override
	public ItemStack addStringNBT(ItemStack itemStack, String key, String value) {
		net.minecraft.server.v1_7_R3.ItemStack is = CraftItemStack.asNMSCopy(itemStack);
		if (is.tag == null)
			is.tag = new NBTTagCompound();
		is.tag.setString(key, value);
		return CraftItemStack.asCraftMirror(is);
	}

	@Override
	public String getStringNBT(ItemStack itemStack, String key) {
		net.minecraft.server.v1_7_R3.ItemStack is = CraftItemStack.asNMSCopy(itemStack);
		return is.tag.getString(key);
	}

	
	@Override
	public String getLocale(Player player) {
		EntityPlayer p = ((CraftPlayer) player).getHandle();
		try {
			return (String) ReflectionUtils.getPrivateFieldValue(p, "locale");
		} catch (Exception e) {
		}
		return "en";
	}

	@Override
	public void setSpeed(Player player, float value) {
		EntityPlayer p = ((CraftPlayer) player).getHandle();
		p.abilities.flySpeed = (value / 2.0F);
		p.abilities.walkSpeed = (value / 2.0F);
		p.updateAbilities();
	}
}
