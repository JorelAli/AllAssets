AllAssets - 0.4 Alpha
=====================

Third version

Commands:
* Everything from version 0.2 and 0.3 Pre-Alpha
* /filebrowser - allows you to browse the server files and read config files (only .yml, .txt and .properties files)
* /fileeditor - allows you to edit server config files (only .yml files)
  * http://i.imgur.com/hI14V4s.gifv 
* /more - sets your itemstack value to 64
* /butcher -e - butcher with explosions :P

Features:
* MultiCommandListener - can now execute multiple commands at once (/say hi|/say bye)
* StopCommandListener - adds an extra layer of security when using /stop
* SkeletonArrowListener - can now pick up skeleton arrows
* Added code for tp disabling (tptoggle), to be implemented at a later date

Fixes/Improvements:
* /firework updates - now has support for multiple colors and fades
  * http://i.imgur.com/PVrJqLH.gifv
* Adjusted config so it doesn't store location values as 194.2491024718468732562757826317684

Developer improvements:
* Added AAFireworkBuilder to the API.

Planned features:
* Silence command (Prevents chat messages being sent to the player - useful for youtube stuff etc.)
* WorldBackup system
* How many unique players join the server
* Announcer
* Recent command
* Offline TP support
* Friends list
* Advanced features:
  * Climbing vines like ladders
  * Recipes command
  * Instant mining command
  * Disposal chest
  * Jail command
  * Redstone lighting netherrack/pumpkins/glowstone 
  * Username highlighter
