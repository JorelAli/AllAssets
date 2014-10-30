package io.github.Skepter.Misc;

import io.github.Skepter.Libs.SimpleScoreboard;
import io.github.Skepter.Tasks.TPS;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/** Class designed to help with notifications (sending, updating etc.) */
public class NotificationsBoard {

	private Player player;
	private int errorLogCount;
	private int spamLogCount;
	private String playerName;

	//ONLY allow admins (those with perms) to have notifications. allow them to toggle on/off
	public NotificationsBoard(Player player) {
		this.player = player;
		errorLogCount = 0;
		spamLogCount = 0;
	}

	public void updateBoard() {
		final SimpleScoreboard board = new SimpleScoreboard(ChatColor.YELLOW + "Notifications");
		board.add("Current TPS", TPS.getTPSAsInt());
		board.add("Error Logs", errorLogCount);
		board.add("Spam Logs", spamLogCount);
		
		if(!playerName.equals(""))
			board.add(ChatColor.YELLOW + playerName + " tried to tp to you");
		board.add("");
		board.build();
		board.send(player);
	}

	public void addErrorLog() {
		errorLogCount++;
		updateBoard();
	}
	
	public void clearErrorLogs() {
		errorLogCount = 0;
		updateBoard();
	}

	public void addSpamLog() {
		spamLogCount++;
		updateBoard();
	}
	
	public void clearSpamLogs() {
		spamLogCount = 0;
		updateBoard();
	}
	
	/** A player tried to tp to you, but you have tp toggled off */
	public void someoneTriedToTPButGotDenied(String playerName) {
		this.playerName = playerName;
		updateBoard();
	}
	
	public void clearPlayerName() {
		playerName = "";
	}

}
