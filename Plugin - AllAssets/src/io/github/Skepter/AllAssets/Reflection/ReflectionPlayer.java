/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
 *
 * AllAssets was created by Skepter (http://skepter.github.io/).
 *
 * You are able to:
 * * View AllAsset's source code on github
 * * Experiment with the code to your wish
 * * Download it to use on your server
 *
 * You are NOT able to:
 * * Sell AllAssets - it is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter
 * * Distribute it on any other website. The ONLY places where it can be downloaded from is github and the Bukkit repository
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. The only exception is the BukkitDev staff who I have allowed permission to decompile (to search for malicious code)
 *
 * You can not:
 * * Hold me liable for your actions:
 *     - If there is a bug in the program, you are allowed to notify me.
 *     - If other players abuse AllAssets, you are NOT allowed to hold me liable for that
 *     - If it was setup incorrectly or you used a non-official build, you can NOT hold me liable for that.
 * * Yell at me:
 *     - If something is wrong, please approach the situation calmly.
 * * Modify the code:
 *     - You are NOT allowed to 'steal' the whole code from Github, modify it, compile it yourself and distribute that.
 *
 * If you are to break from these implications, future use of this plugin will be forbidden.
 *******************************************************************************/
package io.github.Skepter.AllAssets.Reflection;

import io.github.Skepter.AllAssets.Reflection.PacketBuilder.PacketType;

import org.bukkit.entity.Player;

public class ReflectionPlayer {

	public enum AnimationType {
		SWING_ARM, DAMAGE, LEAVE_BED, EAT_FOOD, CRITICAL_EFFECT, MAGIC_EFFECT, CROUCH, UNCROUCH;
	}

	public static void doAnimation(final Player player, final AnimationType type) {
		try {
			final ReflectionUtils utils = new ReflectionUtils(player);
			Object animationPacket = utils.emptyPacketPlayOutAnimation;
			animationPacket = animationPacket.getClass().getConstructor().newInstance();
			utils.setPrivateField(animationPacket, "a", player.getEntityId());
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
			utils.setPrivateField(animationPacket, "b", Integer.valueOf(animationID));
			utils.sendOutgoingPacket(animationPacket);
		} catch (final Exception exception) {
		}
	}

	public static void putToBed(final Player player) {
		new PacketBuilder(player, PacketType.PLAY_OUT_BED).set("a", player.getEntityId()).setLocation("b", "c", "d", player.getLocation()).send();
	}

	public static void awakeFromBed(final Player player) {
		doAnimation(player, AnimationType.LEAVE_BED);
	}

}
