package io.github.skepter.allassets.version.packets;


public class PacketEnums {

	public enum GameStateEffect {
		INVALID_BED(0, 0),
		END_RAINING(1, 0),
		BEGIN_RAINING(2, 0),
		CHANGE_GAMEMODE_SURVIVAL(3, 0),
		CHANGE_GAMEMODE_CREATIVE(3, 1),
		CHANGE_GAMEMODE_ADVENTURE(3, 2),
		CHANGE_GAMEMODE_SPECTATOR(3, 3),
		ENTER_CREDITS(4, 0),
		DEMO_MESSAGE_WELCOME(5, 0),
		DEMO_MESSAGE_CONTROLS(5, 101),
		DEMO_MESSAGE_JUMP(5, 102),
		DEMO_MESSAGE_INVENTORY(5, 103),
		ARROW_HIT(6, 0),
		FADE_VALUE_BRIGHT(7, 0),
		FADE_VALUE_DARK(7, 1),
		FADE_TIME(8, 0),
		ELDER_GUARDIAN_APPEARANCE(10, 0);

		private final int id;
		private final float dataValue;

		GameStateEffect(int id, float dataValue) {
			this.id = id;
			this.dataValue = dataValue;
		}

		public int getId() {
			return id;
		}
		
		public float getDataValue() {
			return dataValue;
		}
	}

	/*
	 * /** Take case when setting values! Use 0 if no value is required:
	 * 
	 * @param effect The game effect to use
	 * 
	 * @param value CHANGE_GAMEMODE: 0 = survival, 1 = creative, 2 = adventure,
	 * 3 = spectator
	 * 
	 * @param value DEMO_MESSAGE: 0 = welcome screen, 101 = controls, 102 = jump
	 * control, 103 = inventory control
	 * 
	 * @param value FADE_VALUE: 1 = dark, 0 = bright;
	 * 
	 * @param value FADE_TIME: ticks for the sky to fade
	 * 
	 * public void doGameStateChange(final GameStateEffects effect, final int
	 * value) { try { final PacketBuilder packet = new PacketBuilder(player,
	 * PacketType.PLAY_OUT_GAME_STATE_CHANGE); int effectCode = effect.id;
	 * packet.setInt("b", effectCode).set("c", Float.valueOf(value)).send(); }
	 * catch (final Exception exception) { } }
	 */

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
