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
package io.github.skepter.allassets.misc;

import io.github.skepter.allassets.libs.SimpleScoreboard;
import io.github.skepter.allassets.tasks.TPS;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/** Class designed to help with notifications (sending, updating etc.) Utilizes
 * the 'AllAssets.notifications' permission */
public class NotificationsBoard {

	private final Player player;
	private static int errorLogCount;
	private static int spamLogCount;
	private static int griefLogCount;
	private static String tpPlayerName;

	static {
		errorLogCount = 0;
		spamLogCount = 0;
		clearPlayerName();
	}

	//ONLY allow admins (those with perms) to have notifications. allow them to toggle on/off
	public NotificationsBoard(final Player player) {
		this.player = player;
	}

	@Deprecated
	public static void updateAll() {
		for (final Player p : Bukkit.getOnlinePlayers())
			new NotificationsBoard(p).updateBoard();
	}

	public void updateBoard() {
		final SimpleScoreboard board = new SimpleScoreboard(ChatColor.YELLOW + "Notifications");
		board.add("Current TPS", TPS.getTPSAsInt());
		board.add("Error Logs", errorLogCount);
		board.add("Spam Logs", spamLogCount);
		board.add("Grief Logs", griefLogCount);

		if (!tpPlayerName.equals(""))
			board.add(ChatColor.YELLOW + tpPlayerName + " tried to tp to you");

		board.build();
		board.send(player);
	}

	public static void addErrorLog() {
		errorLogCount++;
	}

	public static void clearErrorLogs() {
		errorLogCount = 0;
	}

	public static void addSpamLog() {
		spamLogCount++;
	}

	public static void addGriefLog() {
		griefLogCount++;
	}

	public static void clearSpamLogs() {
		spamLogCount = 0;
	}

	/** A player tried to tp to you, but you have tp toggled off */
	public static void someoneTriedToTPButGotDenied(final String pName) {
		tpPlayerName = pName;
	}

	public static void clearPlayerName() {
		tpPlayerName = "";
	}

}
