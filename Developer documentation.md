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

Debugger
--------
Just a little class I created to help debug issues by checking values:

* printVariable(String name, Object object) - Prints a variable into the console
* printList(Collection<?> list) - Prints a list into the console
* printMap(Map<?,?> map) - Prints a map into the console

LogEvent
--------
AllAssets uses the custom Event LogEvent to manage logs. For example, if AllAssets was to throw an error, it would add a new ErrorLog, or if someone was to report grief, it would add a GriefLog. By using the LogEvent, you can listen for these logs and make appropriate effects based on which log type it is
* getMessage() - Returns the message from the log
* getLogType() - Returns the log type (CHAT, ERROR, OTHER, GRIEF)

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
By using the offline player version of the user, you are unable to get the player's ping and you are unable to set their last location and last waypoint.

UUIDAPI
-------
An API designed to allow UUID's to be managed easier.
```java
UUIDAPI.getPlayer("Skepter");
```
This will return Skepter's UUID AS LONG AS he has joined the server once.
