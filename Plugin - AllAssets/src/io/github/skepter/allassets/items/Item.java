package io.github.skepter.allassets.items;

public enum Item {

	
	AIR(0, 0, "Air"),
	STONE(1, 0, "Stone", "sstone", "smoothstone", "rock"),
	GRASS(2, 0, "Grass"),
	DIRT(3, 0, "Dirt"),
	COARSE_DIRT(3, 1, "Coarse dirt", "coarsedirt"),
	PODZOL(3, 2, "Podzol", "podzol"),
	COBBLESTONE(4, 0, "Cobblestone", "cobble"),
	
	WOOD(5, 0, "Wood", "plank", "woodenplank", "woodplank", "wooden plank", "wood plank"),
	SPRUCE_WOOD(5, 1, "Spruce wood", "spruce plank", "spruceplank", "spruce wooden plank", "sprucewoodenplank"),
	BIRCH_WOOD(5, 2, "Birch wood", "birch plank", "birchplank")

	private final int id;
	private final String name;
	private final String[] aliases;
	
	Item(int id, int meta, String name, String... aliases) {
		this.id = id;
		this.name = name;
		this.aliases = aliases;
	}
	
	
	
}
