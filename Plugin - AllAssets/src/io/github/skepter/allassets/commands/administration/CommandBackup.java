/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and MCSpartans
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and MCSpartans
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.commands.administration;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.CommandFramework.Completer;
import io.github.skepter.allassets.utils.Files;
import io.github.skepter.allassets.utils.Files.Directory;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.YesNoConversation;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.FileUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.conversations.BooleanPrompt;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;

public class CommandBackup {

	public CommandBackup(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "backup", permission = "backup", description = "Backs up a world")
	public void onCommand(final CommandArgs args) {
		switch (args.getArgs().length) {
			case 0:
				ErrorUtils.notEnoughArguments(args.getSender());
				return;
			case 1:
				if (args.getArgs()[0].equalsIgnoreCase("list")) {
					args.getSender().sendMessage(TextUtils.title("List of worlds"));
					for (final World world : Bukkit.getWorlds())
						args.getSender().sendMessage(world.getName());
					return;
				} else {
					World world = null;
					try {
						world = Bukkit.getWorld(args.getArgs()[0]);
					} catch (final Exception e) {
						ErrorUtils.worldNotFound(args.getSender(), args.getArgs()[0]);
						return;
					}
					final World w = world;

					for (final File file : Files.getDirectory(Directory.BACKUP).listFiles())
						if (file.isDirectory())
							if (file.getName().equals(world.getName())) {
								args.getSender().sendMessage(Strings.TITLE + "There is already a backup for " + world.getName() + ", backing it up again will lose the previous backup");
								new YesNoConversation(args.getSender(), new BackupPrompt(w), "Do you want to continue?");
								return;
							} else
								break;
					Bukkit.getScheduler().runTaskAsynchronously(AllAssets.instance(), new Runnable() {
						@Override
						public void run() {
							try {
								FileUtils.copyDirectory(w.getWorldFolder(), new File(Files.getDirectory(Directory.BACKUP), w.getName()));
								args.getSender().sendMessage(Strings.TITLE + w.getName() + " was backed up successfully ");
							} catch (final IOException e) {
								ErrorUtils.backupError(args.getSender());
								return;
							}
						}
					});

					return;
				}
		}
	}

	@Completer(name = "backup")
	public List<String> backupCompleter(final CommandArgs args) {
		final List<String> list = new ArrayList<String>();
		for (final World world : Bukkit.getWorlds())
			list.add(world.getName());
		return list;
	}

	private class BackupPrompt extends BooleanPrompt {

		private final World world;

		public BackupPrompt(final World world) {
			this.world = world;
		}

		@Override
		public String getPromptText(final ConversationContext context) {
			return YesNoConversation.getPromptText();
		}

		@Override
		protected Prompt acceptValidatedInput(final ConversationContext context, final boolean b) {
			if (b) {
				Bukkit.getScheduler().runTaskAsynchronously(AllAssets.instance(), new Runnable() {
					@Override
					public void run() {
						try {
							FileUtils.copyDirectory(world.getWorldFolder(), new File(Files.getDirectory(Directory.BACKUP), world.getName()));
						} catch (final IOException e) {
							ErrorUtils.conversableError(context.getForWhom(), "There was an error whilst backing up the world");
							return;
						}
					}
				});
				context.getForWhom().sendRawMessage(Strings.TITLE + world.getName() + " was backed up successfully");
			} else
				context.getForWhom().sendRawMessage(Strings.TITLE + " Back up for " + world.getName() + " was cancelled");
			return Prompt.END_OF_CONVERSATION;
		}

	}

}
