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

	@Deprecated
	FLOWING_WATER(8, 0, "Flowing water", "water block", "flowingwater"),
	@Deprecated
	STILL_WATER(9, 0, "Stationary water", "stationarywater", "stillwater"),
	@Deprecated
	FLOWING_LAVA(10, 0, "Flowing lava", "lava block", "flowinglava"),
	@Deprecated
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

	@Deprecated
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

	@Deprecated
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

	//Where's block 36 :P

	//Flowers
	DANDELION(37, 0, "Dandelion", "yellow flower"),
	POPPY(38, 0, "Poppy", "rose", "red flower"),
	BLUE_ORCHID(38, 1, "Blue orchid", "orchid", "blue flowers", "blue flower"),
	ALLIUM(38, 2, "Allium", "purple flower"),
	AZURE_BLUET(38, 3, "Azure bluet", "azure", "white flowers"),
	RED_TULIP(38, 4, "Red tulip", "rtulip"),
	ORANGE_TULIP(38, 5, "Orange tulip", "otulip", "orange flower"),
	WHITE_TULIP(38, 6, "White tulip", "wtulip"),
	PINK_TULIP(38, 7, "Pink tulip", "ptulip", "pink flower"),
	OXEYE_DAISY(38, 8, "Oxeye daisy", "daisy"),

	BROWN_MUSHROOM(39, 0, "Brown mushroom", "mushroom"),
	RED_MUSHROOM(40, 0, "Red mushroom"),

	GOLD_BLOCK(41, 0, "Gold block", "golden block"),
	IRON_BLOCK(42, 0, "Iron block"),

	//double slabs

	//Single slabs
	STONE_SLAB(44, 0, "Stone slab", "slab"),
	SANDSTONE_SLAB(44, 1, "Sandstone slab", "sand slab"),
	WOODEN_SLAB(44, 2, "Wooden slab", "wood slab", "log slab"),
	COBBLESTONE_SLAB(44, 3, "Cobblestone slab", "cobble slab"),
	BRICK_SLAB(44, 4, "Brick slab"),
	STONE_BRICK_SLAB(44, 5, "Stone brick slab", "sbrick slab"),
	NETHER_BRICK_SLAB(44, 6, "Nether brick slab", "nbrick slab", "nether slab"),
	QUARTZ_SLAB(44, 7, "Quartz slab", "qslab"),

	BRICKS(45, 0, "Bricks", "Brick block"),
	TNT(46, 0, "TNT"),
	BOOKSHELF(47, 0, "Bookshelf", "books"),
	MOSS_STONE(48, 0, "Moss stone", "mossy cobblestone", "mossy cobblestone", "mcobble"),
	OBSIDIAN(49, 0, "Obsidian", "obby"),
	TORCH(50, 0, "Torch"),

	@Deprecated
	FIRE(51, 0, "Fire", "fire block"),

	MONSTER_SPAWNER(52, 0, "Monster spawner", "mob spawner", "spawner"),
	OAK_WOOD_STAIRS(53, 0, "Oak wood stairs", "stairs", "wooden stairs", "wood stairs", "oak stairs"),
	CHEST(54, 0, "Chest", "double chest"),
	REDSTONE_WIRE(55, 0, "Redstone wire"),

	DIAMOND_ORE(56, 0, "Diamond ore", "dore"),
	DIAMOND_BLOCK(57, 0, "Diamond block", "dblock"),
	CRAFTING_TABLE(58, 0, "Crafting table", "workbench"),
	WHEAT_CROPS(59, 0, "Wheat crops"),
	FARMLAND(60, 0, "Farmland", "tilled dirt"),
	FURNACE(61, 0, "Furnace"),
	BURNING_FURNACE(62, 0, "Burning furnace", "on furnace"),
	@Deprecated
	STANDING_SIGN_BLOCK(63, 0, "Standing sign block"),
	@Deprecated
	OAK_DOOR_BLOCK(64, 0, "Oak door block"),
	LADDER(65, 0, "Ladder"),
	RAIL(66, 0, "Rail"),
	COBBLESTONE_STAIRS(67, 0, "Cobblestone stairs", "cobble stairs"),
	@Deprecated
	WALL_MOUNTED_SIGN_BLOCK(68, 0, "Wall-mounted sign block"),
	LEVER(69, 0, "Lever", "switch"),
	STONE_PRESSURE_PLATE(70, 0, "Stone pressure plate", "pressure plate"),
	@Deprecated
	IRON_DOOR_BLOCK(71, 0, "Iron door block"),
	WOODEN_PRESSURE_PLATE(72, 0, "Wooden pressure plate", "wood pressure plate"),
	
	REDSTONE_ORE(73, 0, "Redstone ore", "redore", "rore"),
	@Deprecated
	GLOWING_REDSTONE_ORE(74, 0, "Glowing redstone ore", "on redstone ore"),
	@Deprecated
	UNLIT_REDSTONE_TORCH(75, 0, "Redstone torch (off)", "unlit redstone torch", "off redstone torch"),
	REDSTONE_TORCH(76, 0, "Redstone torch (on)", "redstone torch", "lit redstone torch", "on redstone torch"),
	STONE_BUTTON(77, 0, "Stone button", "button"),
	SNOW_LAYER(78, 0, "Snow layer"),
	ICE(79, 0, "Ice"),
	SNOW_BLOCK(80, 0, "Snow block", "snow"),
	CACTUS(81, 0, "Cactus", "cacti"),
	CLAY(82, 0, "Clay"),
	//note how this is sugar caneS, but the item will be sugar cane (without the s)
	SUGAR_CANE_BLOCK(83, 0, "Sugar cane block", "reeds", "sugar canes"),
	JUKEBOX(84, 0, "Jukebox", "music box"),
	OAK_FENCE(85, 0, "Oak fence", "fence", "wood fence", "wooden fence"),
	PUMPKIN(86, 0, "Pumpkin"),
	NETHERRACK(87, 0, "Netherrack", "hellstone", "bloodstone", "hellrock"),
	SOUL_SAND(88, 0, "Soul sand"),
	GLOWSTONE(89, 0, "Glowstone"),
	@Deprecated
	NETHER_PORTAL(90, 0, "Nether portal", "portal"),
	JACK_O_LANTERN(91, 0, "Jack o'Lantern", "jack o lantern", "lit pumpkin", "on pumpkin"),
	@Deprecated
	CAKE_BLOCK(92, 0, "Cake block"),
	REDSTONE_REPEATER_BLOCK(93, 0, "Unpowered repeater"),
	POWERED_REDSTONE_REPEATER_BLOCK(94, 0, "Powered repeater"),

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

	WOODEN_TRAPDOOR(96, 0, "Wooden trapdoor", "trapdoor", "wood trapdoor"),
	
	//Stone monster eggs
	STONE_MONSTER_EGG(97, 0, "Stone monster egg", "stone mob egg", "stone mob"),
	COBBLESTONESTONE_MONSTER_EGG(97, 1, "Cobblestone monster egg", "Ccbble mob egg", "cobble mob"),
	STONE_BRICK_MONSTER_EGG(97, 2, "Stone brick monster egg", "stone brick mob egg", "stone brick mob"),
	MOSSY_STONE_BRICK_MONSTER_EGG(97, 3, "Mossy stone brick monster egg", "mossy stone mob egg", "mossy stone mob"),
	CRACKED_STONE_BRICK_MONSTER_EGG(97, 4, "Cracked stone brick monster egg", "cracked stone mob egg", "cracked stone mob"),
	CHISELED_STONE_BRICK_MONSTER_EGG(97, 5, "Chiseled stone brick monster egg", "chiseled stone mob egg", "chiseled stone mob"),
	
	//Stone bricks
	STONE_BRICK(97, 0, "Stone brick", "stone brick", "stone brick", "sbrick"),
	MOSSY_STONE_BRICK(97, 1, "Mossy stone brick", "mossy stone", "mossy stone", "mossy sbrick"),
	CRACKED_STONE_BRICK(97, 2, "Cracked stone brick", "cracked stone", "cracked stone", "cracked sbrick", "cbrick"),
	CHISELED_STONE_BRICK(97, 3, "Chiseled stone brick", "chiseled stone", "chiseled stone", "chiseled sbrick", "chbrick"),
	
	BROWN_MUSHROOM_BLOCK(99, 0, "Brown mushroom block", "mushroom block"),
	RED_MUSHROOM_BLOCK(100, 0, "Red mushroom block"),
	IRON_BARS(101, 0, "Iron bars"),
	GLASS_PANE(102, 0, "Glass pane", "glass panes"),
	MELON_BLOCK(103, 0, "Melon block", "watermelon block"),
	PUMPKIN_STEM(104, 0, "Pumpkin stem"),
	MELON_STEM(105, 0, "Melon stem", "watermelon stem"),
	VINES(106, 0, "Vines", "vine"),
	FENCE_GATE(107, 0, "Fence gate", "oak fence gate", "wooden fence gate", "wood fence gate"),
	BRICK_STAIRS(108, 0, "Brick stairs", "brick stair"),
	STONE_BRICK_STAIRS(109, 0, "Stone brick stairs", "stone brick stair"),
	MYCELIUM(110, 0, "Mycelium"),
	//I often spell it as lilly, so to prevent confusion...
	LILY_PAD(111, 0, "Lily pad", "water lily", "lilly pad", "lily", "lilly"),
	NETHER_BRICK_BLOCK(112, 0, "Nether brick", "nether brick block", "nether bricks"),
	
	
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

	SIGN(323, 0, "Sign"),

	WATER_BUCKET(326, 0, "Water bucket", "water"),
	LAVA_BUCKET(327, 0, "Lava bucket", "lava");

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

	public boolean isBlock() {
		return (getId() <= 256);
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
