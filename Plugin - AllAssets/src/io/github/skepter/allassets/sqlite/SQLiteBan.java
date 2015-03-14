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

import io.github.skepter.allassets.api.events.BanEvent;
import io.github.skepter.allassets.utils.Strings;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SQLiteBan extends SQLiteManager {

	private SQLite sqlite;

	public SQLiteBan(SQLite sqlite) {
		this.sqlite = sqlite;
	}

	public void banPlayer(CommandSender banner, Player bannedPlayer, String reason) {
		PreparedStatement s = sqlite.prepareStatement("INSERT INTO " + tableName() + " (banner, bannedPlayer, banMessage, time) VALUES(?, ?, ?, ?);");
		try {
			BanEvent event = new BanEvent(banner, bannedPlayer, reason);
			Bukkit.getPluginManager().callEvent(event);
			if (!event.isCancelled()) {
				s.setString(1, event.getBanner().getName());
				s.setString(2, event.getBannedPlayer().getName());
				s.setString(3, event.getReason());
				s.setLong(4, System.currentTimeMillis());
				s.execute();
				bannedPlayer.kickPlayer(event.getReason());
				Bukkit.broadcastMessage(Strings.TITLE + event.getBannedPlayer().getName() + " was banned by " + event.getBanner().getName() + " for " + event.getReason());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void unbanPlayer(String username) {
		PreparedStatement s = sqlite.prepareStatement("DELETE FROM " + tableName() + " WHERE bannedPlayer=?;");
		try {
			s.setString(1, username);
			s.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isBanned(String username) {
		try {
			PreparedStatement s = sqlite.prepareStatement("SELECT * FROM " + tableName() + " WHERE bannedPlayer=?;");
			s.setString(1, username);
			ResultSet r = s.executeQuery();
			if (r.getRow() == 0 && !r.next())
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public String getBannedMessage(String username) {
		if (isBanned(username)) {
			PreparedStatement s = sqlite.prepareStatement("SELECT * FROM " + tableName() + " WHERE bannedPlayer=?;");
			try {
				s.setString(1, username);
				return sqlite.resultToString(s.executeQuery(), "banMessage");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public long getBannedTime(String username) {
		if (isBanned(username)) {
			PreparedStatement s = sqlite.prepareStatement("SELECT * FROM " + tableName() + " WHERE bannedPlayer=?;");
			try {
				s.setString(1, username);
				return Long.valueOf(sqlite.resultToString(s.executeQuery(), "time"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0L;
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
