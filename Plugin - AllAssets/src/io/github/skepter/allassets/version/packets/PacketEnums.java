package io.github.skepter.allassets.version.packets;

public class PacketEnums {

	public enum GameStateEffects {
		INVALID_BED(0),
		END_RAINING(1),
		BEGIN_RAINING(2),
		CHANGE_GAMEMODE(3),
		ENTER_CREDITS(4),
		DEMO_MESSAGE(5),
		ARROW_HIT(6),
		FADE_VALUE(7),
		FADE_TIME(8),
		ELDER_GUARDIAN_APPEARANCE(10);

		private final int id;

		GameStateEffects(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}
	}
	
	public enum AnimationType {
		SWING_ARM,
		DAMAGE,
		LEAVE_BED,
		EAT_FOOD,
		CRITICAL_EFFECT,
		MAGIC_EFFECT,
		CROUCH,
		UNCROUCH;
	}
	
}
