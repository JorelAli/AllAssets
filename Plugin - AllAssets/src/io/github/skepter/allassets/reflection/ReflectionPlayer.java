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
package io.github.skepter.allassets.reflection;

import io.github.skepter.allassets.reflection.PacketBuilder.PacketType;

import java.lang.reflect.Method;

import org.bukkit.entity.Player;

public class ReflectionPlayer {

	public enum AnimationType {
		SWING_ARM, DAMAGE, LEAVE_BED, EAT_FOOD, CRITICAL_EFFECT, MAGIC_EFFECT, CROUCH, UNCROUCH;
	}

	private Player player;

	public ReflectionPlayer(final Player player) {
		this.player = player;
	}

	public void openAnvil() {
		try {
			MinecraftReflectionUtils utils = new MinecraftReflectionUtils(player);

			Method method = utils.nmsPlayer.getClass().getDeclaredMethod("openTileEntity", utils.getNMSClass("ITileEntityContainer"));

			Class<?> tileEntityContainerAnvilClass = utils.getNMSClass("TileEntityContainerAnvil");
			Object world = ReflectionUtils.getPerfectField(utils.nmsPlayer, utils.nmsPlayer.getClass().getSuperclass().getSuperclass().getSuperclass(), "world");
//			Object inventory = ReflectionUtils.getPerfectField(utils.nmsPlayer, utils.nmsPlayer.getClass().getSuperclass(), "inventory");

			Object tileEntityContainerAnvil = tileEntityContainerAnvilClass.getConstructor(utils.getNMSClass("World"), utils.getNMSClass("BlockPosition")).newInstance(world, null);
			Object activeContainer = ReflectionUtils.getPrivateFieldValue(utils.nmsPlayer, utils.nmsPlayer.getClass().getSuperclass().getDeclaredField("activeContainer"));
			ReflectionUtils.setPerfectField(activeContainer, activeContainer.getClass().getSuperclass(), "checkReachable", false);

//			Constructor<?> blockPosition = utils.getNMSClass("BlockPosition").getConstructor(int.class, int.class, int.class);
//			Object defaultBlockPosition = blockPosition.newInstance(0, 0, 0);

//			Constructor<?> c = utils.getNMSClass("ContainerAnvil").getConstructor(utils.getNMSClass("PlayerInventory"), utils.getNMSClass("World"), utils.getNMSClass("BlockPosition"), utils.getNMSClass("EntityHuman"));
//			Object anvilContainer = c.newInstance(inventory, world, defaultBlockPosition, utils.nmsPlayer);
//
//			Method m = utils.getOBCClass("event.CraftEventFactory").getDeclaredMethod("callInventoryOpenEvent", utils.getNMSClass("EntityPlayer"), utils.getNMSClass("Container"));
//			m.invoke(utils.nmsPlayer, utils.nmsPlayer, anvilContainer);

			new PacketBuilder(player, PacketType.PLAY_OUT_OPEN_WINDOW).set("a", (int) ReflectionUtils.getPrivateFieldValue(utils.nmsPlayer, "containerCounter")).set("b", "minecraft:anvil").set("c", utils.chatSerialize("Repairing")).set("d", 9);

			//			ReflectionUtils.setPerfectField(utils.nmsPlayer, utils.nmsPlayer.getClass().getSuperclass(), "activeContainer", anvilContainer);
			ReflectionUtils.setPerfectField(activeContainer, activeContainer.getClass().getSuperclass(), "windowId", ((int) ReflectionUtils.getPrivateFieldValue(utils.nmsPlayer, "containerCounter")));

			//			activeContainer.getClass().getDeclaredMethod("addSlotListener", utils.nmsPlayer.getClass()).invoke(activeContainer, utils.nmsPlayer);

			method.invoke(utils.nmsPlayer, tileEntityContainerAnvil);
			//ReflectionUtils.setPerfectField(o, o.getClass().getSuperclass(), "", (int) ReflectionUtils.getPrivateFieldValue(utils.nmsPlayer, "containerCounter"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doAnimation(final AnimationType type) {
		try {
			final MinecraftReflectionUtils utils = new MinecraftReflectionUtils(player);
			Object animationPacket = utils.emptyPacketPlayOutAnimation;
			animationPacket = animationPacket.getClass().getConstructor().newInstance();
			ReflectionUtils.setPrivateField(animationPacket, "a", player.getEntityId());
			int animationID = 0;
			switch (type) {
			case CRITICAL_EFFECT:
				animationID = 4;
				break;
			case CROUCH:
				animationID = 104;
				break;
			case DAMAGE:
				animationID = 1;
				break;
			case EAT_FOOD:
				animationID = 3;
				break;
			case LEAVE_BED:
				animationID = 2;
				break;
			case MAGIC_EFFECT:
				animationID = 5;
				break;
			case SWING_ARM:
				animationID = 0;
				break;
			case UNCROUCH:
				animationID = 105;
				break;
			default:
				break;
			}
			ReflectionUtils.setPrivateField(animationPacket, "b", Integer.valueOf(animationID));
			utils.sendOutgoingPacket(animationPacket);
		} catch (final Exception exception) {
		}
	}

	public void putToBed() {
		new PacketBuilder(player, PacketType.PLAY_OUT_BED).set("a", player.getEntityId()).setLocation("b", "c", "d", player.getLocation()).send();
	}

	public void awakeFromBed() {
		doAnimation(AnimationType.LEAVE_BED);
	}

}
