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
		SWING_ARM(0),
		DAMAGE(1),
		LEAVE_BED(2),
		EAT_FOOD(3),
		CRITICAL_EFFECT(4),
		MAGIC_EFFECT(5),
		CROUCH(104),
		UNCROUCH(105);

		private final int id;
		
		AnimationType(int id) {
			this.id = id;
		}
		
		public int getId() {
			return id;
		}
	}
	
}
