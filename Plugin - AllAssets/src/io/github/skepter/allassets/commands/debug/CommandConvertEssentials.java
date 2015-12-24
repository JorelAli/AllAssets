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
 * * Claim it as your own. AllAssets is created by Skepter 
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
package io.github.skepter.allassets.commands.debug;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.api.users.User;
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import java.io.File;
import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CommandConvertEssentials {

	public CommandConvertEssentials(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "convertessentials", aliases = { "convertess" }, permission = "convertessentials", description = "Converts Essentials data to AllAssets data")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null)
			switch (args.getArgs().length) {
				case 0:
					printHelp(player);
					return;
				case 1:
					switch (args.getArgs()[0].toLowerCase()) {
						case "start":
							convert(0);
							return;
						case "delete":
							convert(1);
							return;
					}
					return;
			}
		return;
	}
	
	/** Converts the data.
	 * Mode 0 converts everything normally 
	 * Mode 1 converts everything and deletes the Essentiald data */
	private void convert(int mode) {
		File aaFolder = AllAssets.instance().getDataFolder();
		File pluginsFolder = aaFolder.getParentFile();
		File essFolder = null;
		for(File folder : pluginsFolder.listFiles())
			if(folder.isDirectory() && folder.getName().equals("Essentials"))
				essFolder = folder;
		File essUserData = null;
		for(File folder : essFolder.listFiles()) 
			if(folder.isDirectory() && folder.getName().equals("userdata"))
				essUserData = folder;
		for(File userFile : essUserData.listFiles()) {
			if(userFile.getName().endsWith(".yml")) {
				YamlConfiguration config = YamlConfiguration.loadConfiguration(userFile);
				User user = new User(userFile.getName().substring(0, userFile.getName().length() - 4));
				user.setIps(Arrays.asList(new String[] {config.getString("ipAddress")}));
				
			}
		}
		
	}

	@Help(name = "ConvertEssentials")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "ConvertEssentials", "/convertessentials start - Converts all of the Essentials data",
				"/convertessentials delete - Converts all of the Essentials data and then deletes the old data");
	}
}
