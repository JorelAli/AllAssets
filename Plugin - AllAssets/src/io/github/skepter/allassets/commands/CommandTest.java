/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and Tundra
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
package io.github.skepter.allassets.commands;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;

/**
 * 
 * @author MCSpartans
 *
 */
public class CommandTest {

	public CommandTest(final CommandFramework framework) {
		framework.registerCommands(this);
	}
	
	/*
	 * (Messages between these green parts are comments - they aren't actually run in the code)
	 * 
	 * CamelCase is like this: ThisMessageIsInCamelCase
	 * Lowercase is like this: thismessageisinlowercase
	 * CamelCase uses a capital letter for 'each word'
	 * 
	 * (Feel free to type here, don't be shy :P)
	 * 
	 * So if i do * it wont wrote code?
	 * You need the / OR, the alternative is to start a line with // and then write your comment
	 * For example:
	 */

	//This is where the command name is created
	@CommandHandler(name = "test", aliases = { "thisisatest" }, permission = "test", description = "Runs a test command")
	public void onCommand(final CommandArgs args) {
		
		// This gets the player that ran the command.
		final Player player = PlayerGetter.getPlayer(args);
		
		//Checks if the player doesn't exist (For example, if the console ran the command)
		if (player != null) {
			
			//If the person that ran the command is actually a player, then do this:
			switch (args.getArgs().length) {
			
				/*
				 * This is where arguments are processed.
				 * For example: If the command is /test hello pig dog chicken
				 * then hello is the 0th argument
				 * pig is the 1st argument
				 * dog is the 2nd argument
				 * chicken is the 3rd argument.
				 * 
				 * These arguments are 'filtered' out with the word 'case'
				 */
			
				case 0:
					//This is what case 0 does. They've just typed /test. Nothing else
					
					/**
					 * player = the player that ran the command
					 * sendMessage("MESSAGE") sends that message to the player
					 * ; is needed at the end of the line or Java breaks. XD; 
					 */
					player.sendMessage("You have typed /test. Nothing happens.");
					player.sendMessage("You need to type /test <mob> or /test <mob> <amount>");
					//wut does syntax do? Syntax is a word in the English dictionary which means like 'this is the format for the command'
					// 5 arguments - We need to use case 5.
					player.sendMessage("The format for the command: /test <mob> <amount> <x> <y> <z>");
					player.sendMessage("You failed the command");
					//For example, if the player only typed /test, the code will go here
					//like that? yup :D
					return;
				case 1:
					player.sendMessage("You have typed /test and something else (a mob)");
					
					/**
					 * if starts an if statement. Think irl like "If I do this, then what"
					 * args.getArgs()[0] gets the first argument (0th argument) that the player types.
					 * For example, /test pig - pig is the 0ths argument
					 * 
					 * equalsIgnoreCase() checks to see if the player typed pig, but it doesn't
					 * check if the case is the same. So they could have typed PIG or PiG or pIg
					 * 
					 * "pig" checks if they typed pig XD
					 * 
					 * I gtg for dinner :/
					 * 
					 */
					
					if(args.getArgs()[0].equalsIgnoreCase("pig")) {
						//spawn a pig
					}
					
					
					
					
					// If the player typed /test hi, or /test dog, or /test [anything here], the code will go here
					
					//Return means that's the end of the code.
					return;
				case 2: 
					//Second argument. :D
					return;
				case 5:
					//Right then. On with the code :D
					//Ctrl + Shift + O = import required classes (In this case, Entity)
					EntityType type = null;
					//This makes whatever they type lowercase, so the mob options have to be lowercase as well
					switch(args.getArgs()[0].toLowerCase()) {
					case "pig":
						type = EntityType.PIG;
						//Break is like return, except it only ends the code up to the switch part.
						//In other words, it continues the code from
						break;
					case "zombie":
						type = EntityType.ZOMBIE;
						//And so on
						//Since it would take ages to type out every single mob, we'll just leave it for these two for now. Can i  try to make a mob thing?
						//Go for it
						break;
					case "chicken":
						type = EntityType.CHICKEN;
						break;
					}
					//Here onwards.
					
					///////////////////////////
					
					/*
					 * This gets the amount of mobs. It creates a 'variable' in Java called 'amount' which
					 * is an 'int' (whole number). The default value for the amount is 1.
					 * 
					 * It then checks to see if argument 1 (which will be the amount) is a whole number
					 * By using Integer.parseInt(). If it is, everything will run as normal
					 * 
					 * If it isn't, it would normally create an error, but using the try {} catch() {} thingy
					 * prevents that error.
					 */
					int amount = 1;
					try{
						amount = Integer.parseInt(args.getArgs()[1]);
					} catch(Exception e) {
					}
					
					if(amount > 100) {
						player.sendMessage("There's a limit of 100 on here!");
						return;
					}
						
					
					///////////////////////////
					
					//Gets the coordinates by using the system above to get the amount
					int xCoordinate = 0;
					try{
						xCoordinate = Integer.parseInt(args.getArgs()[2]);
					} catch(Exception e) {
					}
					
					int yCoordinate = 0;
					try{
						yCoordinate = Integer.parseInt(args.getArgs()[3]);
					} catch(Exception e) {
					}
					
					int zCoordinate = 0;
					try{
						zCoordinate = Integer.parseInt(args.getArgs()[4]);
					} catch(Exception e) {
					}
					
					//////////////////////////
					//would u like me to add in all the mobs later to this command?
				
					//If you wish to... AllAssets uses some weird funky method of doing it... lets see
					//So. Lets commit this, save and export. (Create a .jar file we can put onto the server))	//In this case, it will run the spawn code 'amount' of times to spawn multiple mobs
					for(int i = 0; i < amount; i++) {
						//Creates a 'code' location in which to spawn the mob
						Location locationToSpawn = new Location(player.getWorld(), xCoordinate, yCoordinate, zCoordinate);
						//Spawns the mob with the location, and the mob type (see above)
						player.getWorld().spawnEntity(locationToSpawn, type);
					}
				}
			}
		return;
	}
	
	//Forget about this for now.

//	@Help(name = "CAMELCASENAME")
//	public void printHelp(final CommandSender sender) {
//		TextUtils.printHelp(sender, "CAMELCASENAME", "/LOWERCASENAME ARGUMENT - DESCRIPTION");
//	}
}
