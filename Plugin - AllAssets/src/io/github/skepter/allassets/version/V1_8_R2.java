package io.github.skepter.allassets.version;

import io.github.skepter.allassets.AllAssets;
import net.minecraft.server.v1_8_R2.BlockPosition;
import net.minecraft.server.v1_8_R2.ChatMessage;
import net.minecraft.server.v1_8_R2.ContainerAnvil;
import net.minecraft.server.v1_8_R2.EntityHuman;
import net.minecraft.server.v1_8_R2.EntityPlayer;
import net.minecraft.server.v1_8_R2.PacketPlayOutOpenWindow;

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
		BlockPosition bp = new BlockPosition(0, 0, 0);

		EntityHuman human = (EntityHuman) ((CraftPlayer) player).getHandle();
		EntityPlayer ePlayer = (EntityPlayer) ((CraftPlayer) player).getHandle();
		ContainerAnvil anvil = new ContainerAnvil(human.inventory, human.world, bp, human);
		anvil.checkReachable = false;

		int cc = ePlayer.nextContainerCounter();
		ePlayer.playerConnection.sendPacket(new PacketPlayOutOpenWindow(cc, "minecraft:anvil", new ChatMessage("Repairing", new Object[]{}), 9));
		human.activeContainer = anvil;
		human.activeContainer.windowId = ePlayer.nextContainerCounter();
		human.activeContainer.addSlotListener(ePlayer);
	}

}
