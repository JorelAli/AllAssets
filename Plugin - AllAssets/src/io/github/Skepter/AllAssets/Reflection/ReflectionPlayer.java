package io.github.Skepter.AllAssets.Reflection;

import io.github.Skepter.AllAssets.Reflection.PacketBuilder.PacketType;

import org.bukkit.entity.Player;

public class ReflectionPlayer {

	public static void doAnimation(final Player player, AnimationType type) {
		try {
			ReflectionUtils utils = new ReflectionUtils(player);
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
			utils.sendPacket(animationPacket);
		} catch (Exception exception) {
		}
	}

	public static void putToBed(final Player player) {
		new PacketBuilder(player, PacketType.PLAY_OUT_BED).set("a", player.getEntityId()).setLocation("b","c","d", player.getLocation()).send();
	}

	public static void awakeFromBed(final Player player) {
		doAnimation(player, AnimationType.LEAVE_BED);
	}

}
