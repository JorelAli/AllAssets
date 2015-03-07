package io.github.skepter.allassets.sqlite;

import io.github.skepter.allassets.utils.Files;
import io.github.skepter.allassets.utils.Utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/** Loads SQLite classes. Used to open and close SQLite databases.
 * Register new SQLite classes inside the init() method. */
public class SQLiteLoader {

	private static Map<SQLite, SQLiteManager> sqliteMap;

	public SQLiteLoader() {
		if (sqliteMap == null)
			sqliteMap = new HashMap<SQLite, SQLiteManager>();
	}
	
	public void init() {
		sqliteMap.put(new SQLite(new File(Files.getStorage(), "bannedplayers.db")), new SQLiteBan());
		
		for(Entry<SQLite, SQLiteManager> e : sqliteMap.entrySet()) {
			e.getKey().open();
			e.getValue().createTable();
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
