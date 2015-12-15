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

import io.github.skepter.allassets.AllAssets;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/** SQLite class taken straight from NecessaryExtrasCore Good thing that Skepter
 * owns that xD */
public class SQLite {

	private final String DatabaseURL;
	private Connection Connection;
	private final File databaseFile;

	public SQLite(final File databaseFile) {
		if (!databaseFile.getParentFile().exists())
			databaseFile.getParentFile().mkdir();

		if (!databaseFile.exists())
			try {
				databaseFile.createNewFile();
			} catch (final IOException e1) {
				e1.printStackTrace();
			}

		this.databaseFile = databaseFile;

		DatabaseURL = "jdbc:sqlite:" + databaseFile.getAbsolutePath();

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (final ClassNotFoundException e) {
			System.out.println("No SQLite JDBC Driver available!");
			e.printStackTrace();
		}
	}

	public void open() {
		try {
			Connection = DriverManager.getConnection(DatabaseURL);
			AllAssets.instance().getLogger().info("Opening database " + databaseFile.getName());
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		if (Connection != null)
			try {
				Connection.close();
			} catch (final SQLException e) {
				e.printStackTrace();
			}
	}

	public void execute(final String Query) {
		try {
			Connection.createStatement().execute(Query);
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet executeQuery(final String Query) {
		Statement Statement = null;
		try {
			Statement = Connection.createStatement();
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		ResultSet Result = null;
		try {
			Result = Statement.executeQuery(Query);
			return Result;
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		try {
			Statement.close();
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public PreparedStatement prepareStatement(final String Query) {
		try {
			return Connection.prepareStatement(Query);
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<String> resultToArray(final ResultSet result, final String data) {
		final ArrayList<String> arr = new ArrayList<String>();
		try {
			while (result.next())
				arr.add(result.getString(data));
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		try {
			result.close();
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}

	public String resultToString(final ResultSet result, final String data) {
		try {
			if (result.next())
				return result.getString(data);
			else
				return null;
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
