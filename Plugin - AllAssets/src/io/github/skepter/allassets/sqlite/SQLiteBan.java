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
