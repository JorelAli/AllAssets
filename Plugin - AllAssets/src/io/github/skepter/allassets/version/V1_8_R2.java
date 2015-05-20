package io.github.skepter.allassets.version;

import io.github.skepter.allassets.AllAssets;

import java.lang.reflect.Field;

import net.minecraft.server.v1_8_R2.BlockAnvil.TileEntityContainerAnvil;
import net.minecraft.server.v1_8_R2.BlockPosition;
import net.minecraft.server.v1_8_R2.Container;
import net.minecraft.server.v1_8_R2.EntityHuman;
import net.minecraft.server.v1_8_R2.EntityPlayer;

import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class V1_8_R2 implements NMS{

	Plugin plugin;
	
	public V1_8_R2(AllAssets allAssets) {
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
		BlockPosition blockPosition = new BlockPosition(0, 0, 0);
		EntityHuman human = (EntityHuman) ((CraftPlayer) player).getHandle();
		EntityPlayer ePlayer = (EntityPlayer) ((CraftPlayer) player).getHandle();

		TileEntityContainerAnvil anv = new TileEntityContainerAnvil(ePlayer.world, blockPosition);
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
		// TODO Auto-generated method stub
		
	}

}
