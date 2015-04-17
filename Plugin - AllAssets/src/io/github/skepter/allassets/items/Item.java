package io.github.skepter.allassets.items;

import org.bukkit.Material;

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

	GRASS(2, 0, "Grass", "grass block"),
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
	RED_SAND(12, 1, "Red sand", "rsand"),
	GRAVEL(13, 0, "Gravel"),
	GOLD_ORE(14, 0, "Gold ore", "oregold", "gore"),
	IRON_ORE(15, 0, "Iron ore", "oreiron", "iore"),
	COAL_ORE(16, 0, "Coal ore", "orecoal", "core"),

	//Logs (Wood)
	LOG(17, 0, "Log"),
	SPRUCE_LOG(17, 1, "Spruce log"),
	BIRCH_LOG(17, 2, "Birch log"),
	JUNGLE_LOG(17, 3, "Jungle log"),

	//Leaves
	LEAVES(18, 0, "Leaves"),
	SPRUCE_LEAVES(18, 1, "Spruce leaves"),
	BIRCH_LEAVES(18, 2, "Birch leaves"),
	JUNGLE_LEAVES(18, 3, "Jungle leaves"),

	SPONGE(19, 0, "Sponge"),
	WET_SPONGE(19, 1, "Wet sponge", "wsponge"),
	GLASS(20, 0, "Glass"),
	LAPIS_LAZULI_ORE(21, 0, "Lapis lazuli ore", "lapis ore", "lore"),
	LAPIS_LAZULI_BLOCK(22, 0, "Lapis lazuli block", "lapis block"),
	DISPENSER(23, 0, "Dispenser"),
	SANDSTONE(24, 0, "Sandstone"),
	CHISELED_SANDSTONE(24, 1, "Chiseled sandstone", "csandstone"),
	SMOOTH_SANDSTONE(24, 2, "Smooth sandstone", "ssandstone"),
	NOTE_BLOCK(25, 0, "Note block"),

	/** Uh... is this even an item?! */
	BED(26, 0, "Bed block"),
	POWERED_RAIL(27, 0, "Powered rail", "prail"),
	DETECTOR_RAIL(28, 0, "Detector rail", "drail"),
	STICKY_PISTON(29, 0, "Sticky piston", "spiston"),
	COBWEB(30, 0, "Cobweb", "web"),
	DEAD_SHRUB(31, 0, "Dead shrub", "dead bush", "dead leaf", "dead leaves"),
	TALL_GRASS(31, 1, "Tall grass"),
	FERN(31, 2, "Fern"),
	DEAD_BUSH(32, 0, "Dead bush"),
	PISTON(33, 0, "Piston"),

	/** Erm... I'm wondering if we should remove these.... */
	PISTON_HEAD(34, 0, "Piston head"),

	//Wool
	WOOL(35, 0, "Wool", "white wool"),
	ORANGE_WOOL(35, 1, "Orange wool", "owool"),
	MAGENTA_WOOL(35, 2, "Magenta wool", "light purple wool"),
	LIGHT_BLUE_WOOL(35, 3, "Light blue wool"),
	YELLOW_WOOL(35, 4, "Yellow wool", "ywool"),
	LIME_WOOL(35, 5, "Lime wool", "light green wool"),
	PINK_WOOL(35, 6, "Pink wool", "pwool"),
	//I'm sorry, I'm British and like the British English spelling too :P
	GRAY_WOOL(35, 7, "Gray wool", "dark gray wool", "grey wool", "dark grey wool"),
	LIGHT_GREY_WOOL(35, 8, "Light gray wool", "light grey wool"),
	CYAN_WOOL(35, 9, "Cyan wool", "cwool"),
	PURPLE_WOOL(35, 10, "Purple wool", "dark purple wool", "lilac wool"),
	BLUE_WOOL(35, 11, "Blue wool", "dark blue wool"),
	BROWN_WOOL(35, 12, "Brown wool"),
	GREEN_WOOL(35, 13, "Green wool", "dark green wool", "gwool"),
	RED_WOOL(35, 14, "Red wool", "rwool"),
	BLACK_WOOL(35, 15, "Black wool"),

	//Stained glass
	STAINED_GLASS(95, 0, "Stained glass", "white stained glass", "white glass"),
	ORANGE_STAINED_GLASS(95, 1, "Orange stained glass", "orange glass"),
	MAGENTA_STAINED_GLASS(95, 2, "Magenta stained glass", "light purple stained glass", "magenta glass"),
	LIGHT_BLUE_STAINED_GLASS(95, 3, "Light blue stained glass", "light blue glass"),
	YELLOW_STAINED_GLASS(95, 4, "Yellow stained glass", "yellow glass"),
	LIME_STAINED_GLASS(95, 5, "Lime stained glass", "light green stained glass", "light green glass", "lime glass"),
	PINK_STAINED_GLASS(95, 6, "Pink stained glass", "pink glass"),
	GRAY_STAINED_GLASS(95, 7, "Gray stained glass", "dark gray stained glass", "grey stained glass", "dark grey stained glass", "gray glass", "dark gray glass", "grey glass", "dark grey glass"),
	LIGHT_GREY_STAINED_GLASS(95, 8, "Light gray stained glass", "light grey stained glass", "light gray glass", "light grey glass"),
	CYAN_STAINED_GLASS(95, 9, "Cyan stained glass", "cyan glass"),
	PURPLE_STAINED_GLASS(95, 10, "Purple stained glass", "dark purple stained glass", "lilac stained glass", "purple glass", "dark purple glass"),
	BLUE_STAINED_GLASS(95, 11, "Blue stained glass", "dark blue stained glass", "blue glass", "dark blue glass"),
	BROWN_STAINED_GLASS(95, 12, "Brown stained glass", "brown glass"),
	GREEN_STAINED_GLASS(95, 13, "Green stained glass", "dark green stained glass", "green glass", "dark green glass"),
	RED_STAINED_GLASS(95, 14, "Red stained glass", "red glass"),
	BLACK_STAINED_GLASS(95, 15, "Black stained glass", "black glass"),

	//Big gap over here

	COMMAND_BLOCK(137, 0, "Command block", "cmdblock"),

	//And here

	QUARTZ(155, 0, "Quartz"),
	CHISELED_QUARTZ(155, 1, "Chiseled quartz", "chiseledquartz", "cquartz"),
	PILLAR_QUARTZ(155, 2, "Pillar quartz", "pillarquartz", "pquartz"),

	//Stained glass panes
	STAINED_GLASS_PANE(160, 0, "Stained glass pane", "white stained glass pane", "white glass pane"),
	ORANGE_STAINED_GLASS_PANE(160, 1, "Orange stained glass pane", "orange glass pane"),
	MAGENTA_STAINED_GLASS_PANE(160, 2, "Magenta stained glass pane", "light purple stained glass pane", "magenta glass pane"),
	LIGHT_BLUE_STAINED_GLASS_PANE(160, 3, "Light blue stained glass pane", "light blue glass pane"),
	YELLOW_STAINED_GLASS_PANE(160, 4, "Yellow stained glass pane", "yellow glass pane"),
	LIME_STAINED_GLASS_PANE(160, 5, "Lime stained glass pane", "light green stained glass pane", "light green glass pane", "lime glass pane"),
	PINK_STAINED_GLASS_PANE(160, 6, "Pink stained glass pane", "pink glass pane"),
	GRAY_STAINED_GLASS_PANE(160, 7, "Gray stained glass pane", "dark gray stained glass pane", "grey stained glass pane", "dark grey stained glass pane", "gray glass pane", "dark gray glass pane", "grey glass pane", "dark grey glass pane"),
	LIGHT_GREY_STAINED_GLASS_PANE(160, 8, "Light gray stained glass pane", "light grey stained glass pane", "light gray glass pane", "light grey glass pane"),
	CYAN_STAINED_GLASS_PANE(160, 9, "Cyan stained glass pane", "cyan glass pane"),
	PURPLE_STAINED_GLASS_PANE(160, 10, "Purple stained glass pane", "dark purple stained glass pane", "lilac stained glass pane", "purple glass pane", "dark purple glass pane"),
	BLUE_STAINED_GLASS_PANE(160, 11, "Blue stained glass pane", "dark blue stained glass pane", "blue glass pane", "dark blue glass pane"),
	BROWN_STAINED_GLASS_PANE(160, 12, "Brown stained glass pane", "brown glass pane"),
	GREEN_STAINED_GLASS_PANE(160, 13, "Green stained glass pane", "dark green stained glass pane", "green glass pane", "dark green glass pane"),
	RED_STAINED_GLASS_PANE(160, 14, "Red stained glass pane", "red glass pane"),
	BLACK_STAINED_GLASS_PANE(160, 15, "Black stained glass pane", "black glass pane"),
	
	STAINED_CARPET(171, 0, "Stained carpet", "white carpet", "white carpet"),
	ORANGE_STAINED_CARPET(171, 1, "Orange carpet", "orange carpet"),
	MAGENTA_STAINED_CARPET(171, 2, "Magenta carpet", "light purple carpet", "magenta carpet"),
	LIGHT_BLUE_STAINED_CARPET(171, 3, "Light blue carpet", "light blue carpet"),
	YELLOW_STAINED_CARPET(171, 4, "Yellow carpet", "yellow carpet"),
	LIME_STAINED_CARPET(171, 5, "Lime carpet", "light green carpet", "light green carpet", "lime carpet"),
	PINK_STAINED_CARPET(171, 6, "Pink carpet", "pink carpet"),
	GRAY_STAINED_CARPET(171, 7, "Gray carpet", "dark gray carpet", "grey carpet", "dark grey carpet", "gray carpet", "dark gray carpet", "grey carpet", "dark grey carpet"),
	LIGHT_GREY_STAINED_CARPET(171, 8, "Light gray carpet", "light grey carpet", "light gray carpet", "light grey carpet"),
	CYAN_STAINED_CARPET(171, 9, "Cyan carpet", "cyan carpet"),
	PURPLE_STAINED_CARPET(171, 10, "Purple carpet", "dark purple carpet", "lilac carpet", "purple carpet", "dark purple carpet"),
	BLUE_STAINED_CARPET(171, 11, "Blue carpet", "dark blue carpet", "blue carpet", "dark blue carpet"),
	BROWN_STAINED_CARPET(171, 12, "Brown carpet", "brown carpet"),
	GREEN_STAINED_CARPET(171, 13, "Green carpet", "dark green carpet", "green carpet", "dark green carpet"),
	RED_STAINED_CARPET(171, 14, "Red carpet", "red carpet"),
	BLACK_STAINED_CARPET(171, 15, "Black carpet", "black carpet"),

	//And here

	DIAMOND(264, 0, "Diamond"),
	IRON_INGOT(265, 0, "Iron ingot", "iron"),
	GOLD_INGOT(266, 0, "Gold ingot", "gold"),

	SIGN(323, 0, "Sign");

	private final int id;
	private final int meta;
	private final String name;
	private final String[] aliases;
	private final BlockInfo info;

	public int getId() {
		return id;
	}

	public int getMeta() {
		return meta;
	}

	public String getName() {
		return name;
	}

	public String[] getAliases() {
		return aliases;
	}

	public BlockInfo getInfo() {
		return info;
	}

	@SuppressWarnings("deprecation")
	Item(int id, int meta, String name, String... aliases) {
		this.id = id;
		this.meta = meta;
		this.name = name;
		this.aliases = aliases;
		this.info = new BlockInfo(Material.getMaterial(id), (byte) meta);
	}

	/** Finds the item from the input
	 * 
	 * @param input Can be a String (name), ##:## or ##
	 * @return An Item match, or null if it cannot find a match */
	public static Item match(String input) {
		String n = input.replace(" ", "");
		for (Item i : Item.values()) {
			if (i.getName().replace(" ", "").equalsIgnoreCase(n))
				return i;
			for (String str : i.getAliases()) {
				if (str.replace(" ", "").equalsIgnoreCase(n)) {
					return i;
				}
			}
		}
		if (input.matches("\\d+:\\d+")) {
			for (Item i : Item.values())
				if (i.getId() == Integer.parseInt(input.split(":")[0]) && i.getMeta() == Integer.parseInt(input.split(":")[1]))
					return i;
		} else if (input.matches("\\d")) {
			for (Item i : Item.values())
				if (i.getId() == Integer.parseInt(input))
					return i;
		}
		return null;
	}

}
