package io.github.skepter.allassets.utils;

import org.bukkit.entity.EntityType;

public class InputParser {

	private String inputString;
	
	public InputParser(String inputString) {
		this.inputString = inputString;
	}
	
	public EntityType parseMob() {
		if(EntityType.valueOf(inputString) != null)
			return EntityType.valueOf(inputString);
		switch(inputString.toLowerCase()) {
		case "zombiepigman":
		case "pigzombie":
			return EntityType.PIG_ZOMBIE;
		case "enderdragon":
		case "dragon":
			return EntityType.ENDER_DRAGON;
		case "mooshroom":
		case "mushroomcow":
			return EntityType.MUSHROOM_COW;
		case "golem":
		case "irongolem":
			return EntityType.IRON_GOLEM;
		}
		return EntityType.UNKNOWN;
	}
}
