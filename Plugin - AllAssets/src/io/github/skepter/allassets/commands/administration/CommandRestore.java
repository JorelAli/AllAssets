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
import io.github.skepter.allassets.reflection.MinecraftReflectionUtils;
import io.github.skepter.allassets.utils.Files;
import io.github.skepter.allassets.utils.Files.Directory;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.YesNoConversation;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;
import io.github.skepter.allassets.utils.utilclasses.WorldUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.conversations.BooleanPrompt;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;

@Deprecated
public class CommandRestore {

	public CommandRestore(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "restore", aliases = { "revert" }, permission = "restore", description = "Restores a backed up a world")
	public void onCommand(final CommandArgs args) {
		switch (args.getArgs().length) {
			case 0:
				ErrorUtils.notEnoughArguments(args.getSender());
				return;
			case 1:
				if (args.getArgs()[0].equalsIgnoreCase("list")) {
					args.getSender().sendMessage(TextUtils.title("List of worlds"));
					for (final File file : Files.getDirectory(Directory.BACKUP).listFiles())
						args.getSender().sendMessage(file.getName());
					return;
				} else {
					for (final File file : Files.getDirectory(Directory.BACKUP).listFiles())
						if (args.getArgs()[0].equalsIgnoreCase(file.getName()))
							new YesNoConversation(args.getSender(), new RestorePrompt(args.getArgs()[0].toLowerCase()), "Are you sure you want to restore this world? Your previous world cannot be recovered!");
					return;
				}
		}
	}

	@Completer(name = "restore", aliases = { "revert" })
	public List<String> backupCompleter(final CommandArgs args) {
		final List<String> list = new ArrayList<String>();
		for (final File file : Files.getDirectory(Directory.BACKUP).listFiles())
			list.add(file.getName());
		return list;
	}

	private class RestorePrompt extends BooleanPrompt {

		private final String world;

		public RestorePrompt(final String world) {
			this.world = world;
		}

		@Override
		public String getPromptText(final ConversationContext context) {
			return YesNoConversation.getPromptText();
		}

		@Override
		protected Prompt acceptValidatedInput(final ConversationContext context, final boolean b) {
			if (b) {
				try {
					final MinecraftReflectionUtils utils = new MinecraftReflectionUtils(Bukkit.getPlayer("Skepter"));
					Object o = utils.getNMSClass("WorldLoader").newInstance();
					o = o.getClass().getConstructor(File.class).newInstance(Bukkit.getWorld(world).getWorldFolder());
					o.getClass().getMethod("e", String.class).invoke(o, world);
				} catch (final Exception e) {
				}
				final WorldUtils wUtils = new WorldUtils(world);
				//				utils.unloadWorld();
				//				Bukkit.unloadWorld(world, true);
				Bukkit.getScheduler().runTaskLaterAsynchronously(AllAssets.instance(), new Runnable() {
					@Override
					public void run() {
						try {
							//							utils.deleteWorld();
							//							if (new File(world).exists())
							//								new File(world).delete();
							wUtils.copyWorld(new File(Files.getDirectory(Directory.BACKUP), world));
							//							FileUtils.copyDirectory(new File(AllAssets.getWorldStorage(), world), new File(world));
							Bukkit.getServer().createWorld(new WorldCreator(world));
							context.getForWhom().sendRawMessage(Strings.TITLE + world + " was restored successfully");
						} catch (final Exception e) {
							ErrorUtils.conversableError(context.getForWhom(), "There was an error whilst restoring the world");
							return;
						}
					}
				}, 200L);
			} else
				context.getForWhom().sendRawMessage(Strings.TITLE + "Restoration for " + world + " was cancelled");
			return Prompt.END_OF_CONVERSATION;
		}

	}

}
