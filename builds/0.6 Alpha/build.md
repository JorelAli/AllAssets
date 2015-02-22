AllAssets - 0.6 Alpha
=====================

Forth version

Commands:
* Everything from version 0.4 Alpha
* /announcer - an announcer to announce messages
* /silence - silences a player (they do not receive messages)
* /backup - backs up a world. No current method to restore it
* [INCOMPLETE] /friend - friend system
* /extinguish - extinguish fires
* /forcecommand - force a player to run a command
* /fakeop - fakes op
* /fakedeop - fakes deop
* /setspawn - sets the spawn for the world
* [INCOMPLETE] /spawnmob - spawns mobs
* /remove - removes entities
* /debug 
  * physics - stops server physics
  * conflicts - shows command conflicts with plugins
  * testid - gets the ID of an item using Essentials' .csv file
  * testencrypt - tests the encryption setting
  * testdecrypt - tests the decryption setting
* [BROKEN] /ghost - no longer works due to NotificationBoard
* [INCOMPLETE] /give - gives item. Depends on Vault
* /balance - shows your balance. Depends on Vault
* /balancetop - shows the top balance. Depends on Vault
* /more - gives you more items
* /near - shows what's near
* /tphere - teleport a player to you
* /help - help pages. Depends on @Help annotation.

* /$firework - commandblock command to launch fireworks from the decoded value of an ItemStack

Features:
* Can now right click a command block with firework to let it launch that firework (buggy)
* sum([sum]) in chat will solve sums
* plugins command looks awesome
* NotificationsBoard shows grief logs

Broken features:
* /ghost command due to NotificationBoard
* /staffchat will not send messages unless you are in the staff chat

Fixes/Improvements:
* Fixed offline teleportation
* Now teleport to players with only part of their username
* Better config file management
* Fixed AFK bugs
* Fixed CommandBlock bugs.
* Instant respawn feature (may have been implemented in a previous version)
* Fixed ServerListPingEvent. Now shows MOTD without crashing.

Developer improvements:
* Renamed AAFireworkBuilder to FireworkBuilder
* Added ItemBuilder
* Added Utils package in API:
  * Added Sphere to create a sphere of blocks
  * Added PlayerMap as a Map that automatically prevents memory leaks with players
  * Added Debugger to debug variables during code
* Added OfflineUser for offline players
* Now uses Reflection to hook into the VaultAPI manually. To be removed once Vault accepts AllAssets
* Added a bunch of serializers
* CustomObject to convert objects easily

Planned features:
* WorldBackup restore system
* How many unique players join the server
* Recent command
* Advanced features:
  * Climbing vines like ladders
  * Recipes command
  * Instant mining command
  * Disposal chest
  * Jail command
  * Redstone lighting netherrack/pumpkins/glowstone 
  * Username highlighter
