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

import io.github.skepter.allassets.utils.DoubleMap;
import io.github.skepter.allassets.utils.Files;
import io.github.skepter.allassets.utils.Utils;

import java.io.File;

/** Loads SQLite classes. Used to open and close SQLite databases. Register new
 * SQLite classes inside the init() method. */
public class SQLiteLoader {

	public enum SQLiteType {
		BAN;
	}

	private static DoubleMap<SQLiteType, SQLite, SQLiteManager> sqliteMap;

	public SQLiteLoader() {
		if (sqliteMap == null)
			sqliteMap = new DoubleMap<SQLiteType, SQLite, SQLiteManager>();
	}

	public void init() {
		final SQLite ban = getSQLite(SQLiteType.BAN);
		final SQLiteManager banManager = new SQLiteBan(ban);
		sqliteMap.put(SQLiteType.BAN, ban, banManager);
		sqliteMap.getValue1(SQLiteType.BAN).open();
		sqliteMap.getValue2(SQLiteType.BAN).createTable();
	}

	public void shutDown() {
		sqliteMap.getValue1(SQLiteType.BAN).close();
	}

	public SQLite sqliteFromClass(final SQLiteManager m) {
		return Utils.reverseValue2(sqliteMap).getValue1(m);
	}

	private SQLite getSQLite(final SQLiteType t) {
		switch (t) {
			case BAN:
				return new SQLite(new File(Files.getStorage(), "bannedplayers.db"));
		}
		return null;
	}

	public SQLiteManager getSQLiteManager(final SQLiteType t) {
		return sqliteMap.getValue2(t);
	}

	public DoubleMap<SQLiteType, SQLite, SQLiteManager> getMap() {
		return sqliteMap;
	}
}
