/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
 *
 * AllAssets was created by Skepter (http://skepter.github.io/).
 *
 * You are able to:
 * * View AllAsset's source code on github
 * * Experiment with the code to your wish
 * * Download it to use on your server
 *
 * You are NOT able to:
 * * Sell AllAssets - it is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter
 * * Distribute it on any other website. The ONLY places where it can be downloaded from is github and the Bukkit repository
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. The only exception is the BukkitDev staff who I have allowed permission to decompile (to search for malicious code)
 *
 * You can not:
 * * Hold me liable for your actions:
 *     - If there is a bug in the program, you are allowed to notify me.
 *     - If other players abuse AllAssets, you are NOT allowed to hold me liable for that
 *     - If it was setup incorrectly or you used a non-official build, you can NOT hold me liable for that.
 * * Yell at me:
 *     - If something is wrong, please approach the situation calmly.
 * * Modify the code:
 *     - You are NOT allowed to 'steal' the whole code from Github, modify it, compile it yourself and distribute that.
 *
 * If you are to break from these implications, future use of this plugin will be forbidden.
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Commands.Administration;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.CommandFramework.Completer;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.TextUtils;
import io.github.Skepter.AllAssets.Utils.YesNoConversation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.org.apache.commons.io.FileUtils;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.conversations.BooleanPrompt;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;

public class CommandRestore {

	public CommandRestore(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "restore", aliases = { "revert" }, permission = "restore", description = "Restores a backed up a world", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		switch (args.getArgs().length) {
		case 0:
			ErrorUtils.notEnoughArguments(args.getSender());
			return;
		case 1:
			if (args.getArgs()[0].equalsIgnoreCase("list")) {
				args.getSender().sendMessage(TextUtils.title("List of worlds"));
				for (File file : AllAssets.getWorldStorage().listFiles()) {
					args.getSender().sendMessage(file.getName());
				}
				return;
			} else {
				for (File file : AllAssets.getWorldStorage().listFiles()) {
					if (args.getArgs()[0].equalsIgnoreCase(file.getName())) {
						new YesNoConversation(args.getSender(), new RestorePrompt(args.getArgs()[0].toLowerCase()), "Are you sure you want to restore this world? Your previous world cannot be recovered!");
					}
				}
				return;
			}
		}
	}

	@Completer(name = "restore", aliases = { "revert" })
	public List<String> backupCompleter(final CommandArgs args) {
		final List<String> list = new ArrayList<String>();
		for (File file : AllAssets.getWorldStorage().listFiles()) {
			list.add(file.getName());
		}
		return list;
	}

	private class RestorePrompt extends BooleanPrompt {

		private final String world;

		public RestorePrompt(String world) {
			this.world = world;
		}

		@Override
		public String getPromptText(ConversationContext context) {
			return YesNoConversation.getPromptText();
		}

		@Override
		protected Prompt acceptValidatedInput(final ConversationContext context, boolean b) {
			if (b) {
				Bukkit.getScheduler().runTaskAsynchronously(AllAssets.instance(), new Runnable() {
					@Override
					public void run() {
						try {
							Bukkit.unloadWorld(world, false);
							new File(".", world).mkdirs();
							FileUtils.copyDirectory(new File(AllAssets.getWorldStorage(), world), new File(".", world));
							Bukkit.getServer().createWorld(new WorldCreator(world));
						} catch (IOException e) {
							ErrorUtils.conversableError(context.getForWhom(), "There was an error whilst restoring the world");
							return;
						}
					}
				});
				context.getForWhom().sendRawMessage(AllAssets.title + world + " was restored successfully");
			} else {
				context.getForWhom().sendRawMessage(AllAssets.title + "Restoration for " +  world + " was cancelled");
			}
			return Prompt.END_OF_CONVERSATION;
		}

	}

}
