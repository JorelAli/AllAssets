/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
 *
 * AllAssets was created by Skepter and Tundra (http://skepter.github.io/).
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
	private static String playerName;

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
		for(Player p : Bukkit.getOnlinePlayers()) {
			new NotificationsBoard(p).updateBoard();
		}
	}

	public void updateBoard() {
		final SimpleScoreboard board = new SimpleScoreboard(ChatColor.YELLOW + "Notifications");
		board.add("Current TPS", TPS.getTPSAsInt());
		board.add("Error Logs", errorLogCount);
		board.add("Spam Logs", spamLogCount);
		board.add("Grief Logs", griefLogCount);

		if (!playerName.equals(""))
			board.add(ChatColor.YELLOW + playerName + " tried to tp to you");

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
		playerName = pName;
	}

	public static void clearPlayerName() {
		playerName = "";
	}

}
