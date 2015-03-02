package io.github.skepter.allassets.sqlite;

public class SQLiteBuilder {

	private SQLiteBuilder builder;
	private StringBuilder query;
	private String tableName;

	public SQLiteBuilder(String tableName) {
		builder = this;
		this.tableName = tableName;
		this.query = new StringBuilder();
	}

	/** Values are the values to put into the table. For example:
	 * "banner VARCHAR(16), bannedPlayer VARCHAR(16), banMessage VARCHAR(256), time BIGINT"*/
	public SQLiteBuilder create(String values) {
		query.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" (").append(values).append(");");
		return builder;
	}
	
	@Override
	public String toString() {
		return query.toString();
	}

}
