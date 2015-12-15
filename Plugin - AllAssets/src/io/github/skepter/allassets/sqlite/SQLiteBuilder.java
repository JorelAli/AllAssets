/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter 
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter 
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
package io.github.skepter.allassets.sqlite;

public class SQLiteBuilder {

	private final SQLiteBuilder builder;
	private final StringBuilder query;
	private final String tableName;

	public SQLiteBuilder(final String tableName) {
		builder = this;
		this.tableName = tableName;
		this.query = new StringBuilder();
	}

	/** Values are the values to put into the table. For example:
	 * "banner VARCHAR(16), bannedPlayer VARCHAR(16), banMessage VARCHAR(256), time BIGINT" */
	public SQLiteBuilder create(final String values) {
		query.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" (").append(values).append(");");
		return builder;
	}

	@Override
	public String toString() {
		return query.toString();
	}

}
