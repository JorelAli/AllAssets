AllAssets Developer Documentation
=================================

API overview
------------
The AllAssets API has been designed for ease of use and to allow your plugin to cooperate with AllAssets in order to give the best result possible. All of the API classes can be found in io.github.Skepter.AllAssets.API

FireworkBuilder
-----------------
The firework builder is a class designed to help make fireworks in the ItemStack form. Example usage:

```java
public ItemStack getFirework() {
  new AAFireworkBuilder(8).setPower(1).addTrail(true).setType(Type.BALL).addColor(Color.GRAY).addFade(Color.WHITE).getFirework();
}
```

Simply, use the methods to create your firework and use '.getFirework()' at the end
Methods:
* new FireworkBuilder(int value) - Creates a new builder with the amount of fireworks to generate

* setPower(int value) - Sets the power (flight length) of the firework
* setType(Type type) - Sets the type of firework (e.g. ball/burst)
* addFade(Color color) - Adds a fade color to the firework, can be used more than once
* addFlicker() - Adds flicker (use true to add flicker)
* addTrail() - Adds a trail of particles
* addColor(Color color) - Adds a color to the firework, can be used more than once
* getFirework() - Returns the firework ItemStack

ItemBuilder
-----------
The ItemBuilder class is a simple class designed to create advanced ItemStacks by using a chained builder. Example usage:
```java
public ItemStack getItem() {
	return new ItemBuilder(Material.DIAMOND_AXE).setDisplayName("Diamond Warhammer").setLore("Warning, this is really powerful!", "Use with caution").addGlow().build();
}
```

Simply use the chainable methods to create your custom item with '.build()' at the end
Methods:
* new ItemBuilder(Material material) - Sets the material for the item. The amount is 1.
* new ItemBuilder(Material material, int amount) - Sets the material and amount for the item.
* new ItemBuilder(Material material, short dataValue) - Sets the material and data value (e.g. wool color) for the item. The amount is 1.
* new ItemBuilder(Material material, int amount, short dataValue) - Sets the material, data value and amount for the item.
* new ItemBuilder(ItemStack itemStack) - Creates a new ItemBuilder from an ItemStack.

* setDisplayName(String name) - Sets the display name for the item
* setLore(String... lore) | setLore(List<String> lore) - Sets the lore for an item
* addLore(String... lore) - Adds the variable strings to the current lore
* addEnchantment(Enchantment enchantment, int power) - Adds an enchantment to the item
* removeEnchantment(Enchantment enchantment) - Removes an enchantment from the item
* addGlow() - Adds a glowing effect without needing an enchantment
* removeGlow() - Removes the glowing effect from the item
* getDisplayName() - Gets the display name of the item
* getLore() - Gets the lore of the item
* hasGlow() - Checks if an item has a glow 
* build() - Gets the ItemStack

Debugger
--------
Just a little class I created to help debug issues by checking values:

* printVariable(String name, Object object) - Prints a variable into the console. The name is used to describe the object
* printList(Collection<?> list) - Prints a list into the console
* printMap(Map<?,?> map) - Prints a map into the console

LogEvent
--------
AllAssets uses the custom Event LogEvent to manage logs. For example, if AllAssets was to throw an error, it would add a new ErrorLog, or if someone was to report grief, it would add a GriefLog. By using the LogEvent, you can listen for these logs and make appropriate effects based on which log type it is
* getMessage() - Returns the message from the log
* getLogType() - Returns the log type (CHAT, ERROR, OTHER, GRIEF)

CustomConfig
------------
A really simple implementation of a YAML configuration system. To use, create a new instance and from there, you can reload, save or retrieve the data file. Example usage:

```java
public FileConfiguration getMyDataFile() {
	return new CustomConfig(new File(plugin.getDataFolder(), "data.yml"), "data file").getDataFile();
}
```

* new CustomConfig(File storageLocation, String usage) - Creates a new instance. The storage location is the direct path to the file and the usage is used to note any errors.
* 

PlayerMap
---------
The PlayerMap class is a customer Map which is designed for players. This map automatically removes players when they logout in order to reduce memory leaks. Use it in the normal form of a HashMap.

User
----
AllAssets' player data is stored using the User class. By using it, you can get data from a user and set data for that user.
There are three ways to get User instances:
* new User(Player);
* new User(OfflinePlayer);
* new User(UUID);
AllAssets also uses the OfflineUser class to get an instance of a user that is offline. Note that some methods that are available in the User class cannot by used in the OfflineUser class (such as the users ping).

UUIDAPI
-------
An API designed to allow UUID's to be managed easier. Example usage:

```java
UUIDAPI.getPlayer("Skepter");
```
This will return Skepter's UUID AS LONG AS he has joined the server once.

PlayerRequest
-------------
The PlayerRequest class is used to make requests from one player to another. It has not been tested and is prone to errors.

```java
//show example usage
new PlayerRequest(Player from, Player to, String information, long timeout)
//TODO Write about in documentation
```

Paginator
---------
The Paginator class is designed to print large amounts of information to a user, by using a page system. 
* new Paginator(List<String> textData, int pageSize, String usage) - Creates a new paginator instance. The text data is the list containing each line of information. The page size is the size of the page (For example, a page size of 10 will display 10 elements per page). The usage is the name of the function, for example "Help index".
* send(CommandSender sender, int pageNumber) - Sends the page number (pageNumber) to the sender.
* getMaxPageNumber() - Returns the maximum page number
* getPageNumberShown() - Can be used after showing a page to a user. Once a page has been shown, this value updates to the page number that was last sent to the user.

```java
List<String> infoToPrint = Arrays.asList(new String[] { "This is info 1", "This is info 2" });
new Paginator(infoToPrint, 10, "Prints example usage").send(player, 0);
```

CustomItem
---------------
A class which is used to easily create custom items, which can then be spawned in with the /spawnitem command. This is an abstract class, so cannot be used on its own, however it can be extended by another class to handle the features of the item. Example usage creating a custom item:

```java
public class SuperPickaxe extends CustomItem {

	//Helps create the item when this class is initialized.
	public SuperPickaxe(Javaplugin plugin, ItemStack is, String name) {
		super(plugin, is, name);
	}
	
	@Override
	public boolean leftClickBlock(Player player) {
		getInteractedBlock(player).getLocation().getBlock().setType(Material.AIR);
		return true;
	}
}
```

This class handles the events when the item is used. To create the actual item, you must invoke the constructor elsewhere (Often in the main class). For example (using the ItemBuilder):

```java
new SuperPickaxe(this, new ItemBuilder(Material.DIAMOND_PICKAXE).addGlow().build(), "SuperPickaxe");
```

If you only want to allow a player to use the item if they have a certain permission, you can use the alternative constructor:
```java
public SuperPickaxe(Javaplugin plugin, ItemStack is, String name, String permission) {
	super(plugin, is, name, permission);
}
```
