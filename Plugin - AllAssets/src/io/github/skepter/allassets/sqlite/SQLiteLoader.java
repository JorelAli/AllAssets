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

import io.github.skepter.allassets.utils.Files;
import io.github.skepter.allassets.utils.Utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;

/** Loads SQLite classes. Used to open and close SQLite databases.
 * Register new SQLite classes inside the init() method. */
public class SQLiteLoader {

	private static Map<SQLite, SQLiteManager> sqliteMap;

	public SQLiteLoader() {
		if (sqliteMap == null)
			sqliteMap = new HashMap<SQLite, SQLiteManager>();
	}
	
	public void init() {
		SQLite ban = new SQLite(new File(Files.getStorage(), "bannedplayers.db"));
		SQLiteManager banManager = new SQLiteBan(ban);
		sqliteMap.put(ban, banManager);
		
		for(Entry<SQLite, SQLiteManager> e : sqliteMap.entrySet()) {
			e.getKey().open();
			e.getValue().createTable();
			Bukkit.getLogger().info("Opened SQLite table: " + banManager.tableName());
		}
	}
	
	public void shutDown() {
		for(SQLite sql : sqliteMap.keySet()) {
			sql.close();
		}
	}
	
	public SQLite sqliteFromClass(SQLiteManager m) {
		return Utils.reverse(sqliteMap).get(m);
	}
	
	public Map<SQLite, SQLiteManager> getMap() {
		return sqliteMap;
	}
}
