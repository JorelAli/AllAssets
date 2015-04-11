package io.github.skepter.allassets.items;

public enum Item {

	AIR(0, 0, "Air"),

	//Stones
	STONE(1, 0, "Stone", "sstone", "smoothstone", "rock"),
	GRANITE(1, 1, "Granite"),
	POLISHED_GRANITE(1, 2, "Polished granite", "pgranite"),
	DIORITE(1, 3, "Diorite"),
	POLISHED_DIORITE(1, 4, "Polished diorite", "pdiorite"),
	ANDESITE(1, 5, "Andesite"),
	POLISHED_ANDESITE(1, 6, "Polished andesite", "pandesite"),

	GRASS(2, 0, "Grass"),
	DIRT(3, 0, "Dirt"),
	COARSE_DIRT(3, 1, "Coarse dirt", "coarsedirt"),
	PODZOL(3, 2, "Podzol", "podzol"),
	COBBLESTONE(4, 0, "Cobblestone", "cobble"),

	//Wooden planks
	WOOD(5, 0, "Wood", "plank", "woodenplank", "woodplank", "wooden plank", "wood plank"),
	SPRUCE_WOOD(5, 1, "Spruce wood", "spruce plank", "spruceplank", "spruce wooden plank", "sprucewoodenplank"),
	BIRCH_WOOD(5, 2, "Birch wood", "birch plank", "birchplank", "birchwoodenplank"),
	JUNGLE_WOOD(5, 3, "Jungle wood"),
	ACACIA_WOOD(5, 4, "Acacia wood"),
	DARK_OAK_WOOD(5, 5, "Dark oak wood"),

	//Saplings
	SAPLING(6, 0, "Sapling"),
	SPRUCE_SAPLING(6, 1, "Spruce sapling"),
	BIRCH_SAPLING(6, 2, "Birch sapling"),
	JUNGLE_SAPLING(6, 3, "Jungle sapling"),
	ACACIA_SAPLING(6, 4, "Acacia sapling"),
	DARK_OAK_SAPLING(6, 5, "Dark oak sapling"),

	BEDROCK(7, 0, "Bedrock", "adminium"),
	FLOWING_WATER(8, 0, "Flowing water", "water", "flowingwater"),
	STILL_WATER(9, 0, "Stationary water", "stationarywater", "stillwater"),
	FLOWING_LAVA(10, 0, "Flowing lava", "lava", "flowinglava"),
	STILL_LAVA(11, 0, "Stationary lava", "stationarylava", "stilllava"),

	SAND(12, 0, "Sand"),
	RED_SAND(12, 1, "Red sand", "rsand");

	private final int id;
	private final int meta;
	private final String name;
	private final String[] aliases;

	Item(int id, int meta, String name, String... aliases) {
		this.id = id;
		this.meta = meta;
		this.name = name;
		this.aliases = aliases;
	}

	public Item match(String name) {
		String n = name.replace(" ", "");
		for (Item i : Item.values()) {
			for (String str : i.aliases) {
				String s = str.replace(" ", "");
				if (s.equalsIgnoreCase(n) || s.equalsIgnoreCase(i.name.replace(" ", "")))
					return i;
			}
		}

		if (name.matches("\\d+:\\d+")) 
			for (Item i : Item.values()) 
				if (i.id == Integer.parseInt(name.split(":")[0]) && i.meta == Integer.parseInt(name.split(":")[1]))
					return i;
			
		if(name.matches("\\d"))
			for(Item i : Item.values())
				if(i.id == Integer.parseInt(name))
					return i;

		//Error :(
		return null;
	}

}
