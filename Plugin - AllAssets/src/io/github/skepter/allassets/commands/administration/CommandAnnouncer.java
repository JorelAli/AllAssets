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
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.tasks.AnnouncerTask;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CommandAnnouncer {

	public CommandAnnouncer(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	private int taskID;

	@CommandHandler(name = "announcer", aliases = { "announce" }, permission = "announcer", description = "Configure the scheduled announcer")
	public void onCommand(final CommandArgs args) {
		printHelp(args.getSender());
		return;
	}

	@CommandHandler(name = "announcer.start", permission = "announcer", description = "Start the announcer")
	public void startAnnouncer(final CommandArgs args) {
		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(AllAssets.instance(), new AnnouncerTask(), 0, AllAssets.instance().getAAConfig().config().getInt("announcerTime"));
		args.getSender().sendMessage(Strings.TITLE + "The announcer has started");
		return;
	}

	@CommandHandler(name = "announcer.stop", permission = "announcer", description = "Stop the announcer")
	public void stopAnnouncer(final CommandArgs args) {
		Bukkit.getScheduler().cancelTask(taskID);
		args.getSender().sendMessage(Strings.TITLE + "The announcer has been stopped");
		return;
	}

	@CommandHandler(name = "announcer.list", permission = "announcer", description = "List all announcements")
	public void listAnnouncements(final CommandArgs args) {
		args.getSender().sendMessage(TextUtils.title("Announcer list"));
		for (final String key : AllAssets.instance().getAAConfig().announcer().getKeys())
			args.getSender().sendMessage(Strings.HOUSE_STYLE_COLOR + key + " " + ChatColor.translateAlternateColorCodes('&', AllAssets.instance().getAAConfig().announcer().getString(key)));
	}

	@CommandHandler(name = "announcer.add", permission = "announcer", description = "Add a new announcement")
	public void addAnnouncement(final CommandArgs args) {
		final String message = TextUtils.getMsgStringFromArgs(args.getArgs(), 0, args.getArgs().length);
		setAnnouncer(message);
		args.getSender().sendMessage(Strings.TITLE + "Successfully added a new message to the announcer");
	}

	@CommandHandler(name = "announcer.remove", permission = "announcer", description = "Remove an announcement")
	public void removeAnnouncement(final CommandArgs args) {
		if (TextUtils.isInteger(args.getArgs()[0]))
			AllAssets.instance().getAAConfig().announcer().removeKey(String.valueOf(args.getArgs()[0]));
	}

	private void setAnnouncer(final String data) {
		int ID = 1;
		try {
			ID = AllAssets.instance().getAAConfig().announcer().getKeys().size() + 1;
		} catch (final Exception e) {
			//Catch nothing since if it fails, the ID will default to 1 anyway
		}
		AllAssets.instance().getAAConfig().announcer().set(String.valueOf(ID), data);
	}

	@Help(name = "Announcer")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "Announcer", "/announcer start - starts the announcer", "/announcer stop - stops the announcer", "/announcer list - lists all announcements", "/announcer add <message> - adds a new message to the announcer", "/announcer remove <ID> - removes a message from the announcer based on its ID (use /announcer list to find the announcement ID)");
	}

}
