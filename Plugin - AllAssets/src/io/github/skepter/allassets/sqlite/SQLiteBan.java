package io.github.skepter.allassets.sqlite;

import org.bukkit.entity.Player;

public class SQLiteBan {

	private SQLite sqlite;
	
	public SQLiteBan(SQLite sqlite) {
		this.sqlite = sqlite;
	}
	
	public void banPlayer(Player banner, Player bannedPlayer, String message) {
		//do it :)
		//check that !(message > 256)
		sqlite.execute("");
	}
	
}
