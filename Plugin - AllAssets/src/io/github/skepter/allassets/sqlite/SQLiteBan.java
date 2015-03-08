/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 * 
 * AllAssets, created by Skepter and Tundra
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
package io.github.skepter.allassets.sqlite;

import org.bukkit.entity.Player;

public class SQLiteBan extends SQLiteManager {

	private SQLite sqlite;

	public SQLiteBan() {
		this.sqlite = new SQLiteLoader().sqliteFromClass(this);
	}

	public void banPlayer(Player banner, Player bannedPlayer, String message) {
		sqlite.execute("INSERT INTO BANNEDPLAYERS(banner, bannedPlayer, banMessage, time) VALUES('" + banner.getName() + "', '" + bannedPlayer.getName() + "', '" + message + "', '" + String.valueOf(System.currentTimeMillis()) + "');");
		bannedPlayer.kickPlayer(message);
	}

	public boolean isBanned(String username) {
		try {
			sqlite.resultToString(sqlite.executeQuery("SELECT * FROM " + tableName() + " WHERE bannedPlayer='" + username + "';"), "playerName");
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public String getBannedMessage(String username) {
		if(isBanned(username)) {
			return sqlite.resultToString(sqlite.executeQuery("SELECT * FROM " + tableName() + " WHERE bannedPlayer='" + username + "';"), "banMessage");
		}
		return null;
	}

	@Override
	public void createTable() {
		sqlite.execute("CREATE TABLE IF NOT EXISTS " + tableName() + " (banner VARCHAR(16), bannedPlayer VARCHAR(16), banMessage VARCHAR(256), time BIGINT);");
	}

	@Override
	public String tableName() {
		return "BannedPlayers";
	}
}
