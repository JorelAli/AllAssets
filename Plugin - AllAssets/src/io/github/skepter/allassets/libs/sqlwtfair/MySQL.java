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
package io.github.skepter.allassets.libs.sqlwtfair;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/** Makes using MySQL a LOT easier */
public class MySQL {
	private final Connection connection;

	private final long startupTime;

	/** Constructs a new connection to a MySQL database
	 * 
	 * @param host the hostname of the MySQL database
	 * @param port the port of the MySQL database or {@code null} for default
	 * @param database the name of the database to connect to
	 * @param username the username
	 * @param password the password
	 * @throws SQLException if an error occurred whilst connecting to the
	 * database
	 * @throws ClassNotFoundException if no JDBC driver was found */
	public MySQL(final String host, final String port, final String database, final String username, final String password) throws SQLException, ClassNotFoundException {
		final long start = System.currentTimeMillis();
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + (port == null ? "3306" : port) + "/" + database, username, password);
		startupTime = System.currentTimeMillis() - start;
	}

	/** Gets the raw connection to the database
	 * 
	 * @return the {@link Connection} to the database */
	public Connection getRawConnection() {
		return connection;
	}

	/** Gets how long it took to connect to the MySQL database
	 * 
	 * @return a time in milliseconds */
	public long getStartupTime() {
		return startupTime;
	}

	/** Closes the connection to the database
	 * 
	 * @throws SQLException if an error occurred while closing the database */
	public void close() throws SQLException {
		connection.close();
	}

	/** Creates a table in the MySQL database
	 * 
	 * @param name the name of the desired table
	 * @param ifNotExists whether we should only create the table if it doesn't
	 * exist. Should normally be {@code true}
	 * @param columns any number of columns to add to the table
	 * @throws SQLException if an error occurred whilst creating the table */
	public void createTable(final String name, final boolean ifNotExists, final ColumnBuilder... columns) throws SQLException {
		final Statement statement = connection.createStatement();
		final StringBuilder builder = new StringBuilder();

		builder.append("CREATE TABLE ");
		if (ifNotExists)
			builder.append("IF NOT EXISTS ");

		builder.append("(");
		for (int i = 0; i < columns.length; i++) {
			builder.append(columns[i].build());
			builder.append(",");
		}

		for (int i = 0; i < columns.length; i++) {
			if (columns[i].isPrimaryKey()) {
				builder.append("PRIMARY KEY (");
				builder.append(columns[i].getName());
				builder.append(")");

				if (i != (columns.length - 1))
					builder.append(",");
			}

			if (columns[i].isUnique()) {
				builder.append("UNIQUE (");
				builder.append(columns[i].getName());
				builder.append(")");

				if (i != (columns.length - 1))
					builder.append(",");
			}

			if (columns[i].isForeignKey()) {
				builder.append("FOREIGN KEY (");
				builder.append(columns[i].getName());
				builder.append(") REFERENCES ");
				builder.append(columns[i].getForeignKey());

				if (i != (columns.length - 1))
					builder.append(",");
			}
		}

		if (builder.lastIndexOf(",") == builder.length())
			builder.deleteCharAt(builder.length());

		builder.append(")");

		statement.executeUpdate(builder.toString());
		statement.close();
	}

	/** Deletes a table from the MySQL database
	 * 
	 * @param name the name of the table
	 * @param ifExists whether we should only delete the table if it exists.
	 * Should normally be {@code true}
	 * @throws SQLException if an error occurred whilst deleting the table */
	public void deleteTable(final String name, final boolean ifExists) throws SQLException {
		final Statement statement = connection.createStatement();
		statement.executeUpdate("DROP TABLE " + (ifExists ? "IF EXISTS " : "") + name);
		statement.close();
	}

}
