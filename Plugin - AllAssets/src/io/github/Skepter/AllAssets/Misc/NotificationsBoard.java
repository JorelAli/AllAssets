/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Misc;

import io.github.Skepter.AllAssets.Libs.SimpleScoreboard;
import io.github.Skepter.AllAssets.Tasks.TPS;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/** Class designed to help with notifications (sending, updating etc.) Utilizes
 * the 'AllAssets.notifications' permission */
public class NotificationsBoard {

	private Player player;
	private static int errorLogCount;
	private static int spamLogCount;
	private static String playerName;

	static {
		errorLogCount = 0;
		spamLogCount = 0;
		clearPlayerName();
	}

	//ONLY allow admins (those with perms) to have notifications. allow them to toggle on/off
	public NotificationsBoard(Player player) {
		this.player = player;
	}

	public void updateBoard() {
		final SimpleScoreboard board = new SimpleScoreboard(ChatColor.YELLOW + "Notifications");
		board.add("Current TPS", TPS.getTPSAsInt());
		board.add("Error Logs", errorLogCount);
		board.add("Spam Logs", spamLogCount);

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

	public static void clearSpamLogs() {
		spamLogCount = 0;
	}

	/** A player tried to tp to you, but you have tp toggled off */
	public static void someoneTriedToTPButGotDenied(String pName) {
		playerName = pName;
	}

	public static void clearPlayerName() {
		playerName = "";
	}

}
