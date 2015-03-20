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
	
	public enum GameStateEffects {
		INVALID_BED, END_RAINING, BEGIN_RAINING, CHANGE_GAMEMODE, ENTER_CREDITS, DEMO_MESSAGE, ARROW_HIT, FADE_VALUE, FADE_TIME, MOB_APPEARANCE;
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
			PacketBuilder packet = new PacketBuilder(player, PacketType.PLAY_OUT_ANIMATION);
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
			packet.set("a", player.getUniqueId()).setInt("b", animationID).send();
		} catch (final Exception exception) {
		}
	}
	
	/**
	 * Take case when setting values!
	 * Use 0 if no value is required:
	 * @param effect The game effect to use
	 * @param value CHANGE_GAMEMODE: 0 = survival, 1 = creative, 2 = adventure, 3 = spectator
	 * @param value DEMO_MESSAGE: 0 = welcome screen, 101 = controls, 102 = jump control, 103 = inventory control
	 * @param value FADE_VALUE: 1 = dark, 0 = bright;
	 * @param value FADE_TIME: ticks for the sky to fade 
	 */
	public void doGameStateChange(final GameStateEffects effect, int value) {
		try {
			PacketBuilder packet = new PacketBuilder(player, PacketType.PLAY_OUT_GAME_STATE_CHANGE);
			int effectCode = 0;
			switch(effect) {
				case ARROW_HIT:
					effectCode = 6;
					break;
				case BEGIN_RAINING:
					effectCode = 2;
					break;
				case CHANGE_GAMEMODE:
					effectCode = 3;
					break;
				case DEMO_MESSAGE:
					effectCode = 5;
					break;
				case END_RAINING:
					effectCode = 2;
					break;
				case ENTER_CREDITS:
					effectCode = 4;
					break;
				case FADE_TIME:
					effectCode = 8;
					break;
				case FADE_VALUE:
					effectCode = 7;
					break;
				case INVALID_BED:
					effectCode = 0;
					break;
				case MOB_APPEARANCE:
					effectCode = 10;
					break;
				default:
					break;
			}
			packet.setInt("b", effectCode).set("c", Float.valueOf(value)).send();
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
