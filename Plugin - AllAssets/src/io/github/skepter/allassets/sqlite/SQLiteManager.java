package io.github.skepter.allassets.sqlite;

/** Abstract SQLite class. Used to keep track of classes using SQLite. */
public abstract class SQLiteManager {

	public abstract void createTable();
	
	public abstract String tableName();
	
}
