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

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.bukkit.Bukkit;

/** SQLite class taken straight from NecessaryExtrasCore Good thing that Skepter
 * owns that xD */
public class SQLite {

	private String DatabaseURL;
	private Connection Connection;
	private File databaseFile;
	
	public SQLite(File databaseFile) {
		if (!databaseFile.getParentFile().exists())
			databaseFile.getParentFile().mkdir();

		if (!databaseFile.exists())
			try {
				databaseFile.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
		this.databaseFile = databaseFile;

		DatabaseURL = "jdbc:sqlite:" + databaseFile.getAbsolutePath();

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println("No SQLite JDBC Driver available!");
			e.printStackTrace();
		}
	}

	public void open() {
		try {
			Connection = DriverManager.getConnection(DatabaseURL);
			Bukkit.getLogger().info("Opening database " + databaseFile.getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		if (Connection != null) {
			try {
				Connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void execute(String Query) {
		try {
			Connection.createStatement().execute(Query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet executeQuery(String Query) {
		Statement Statement = null;
		try {
			Statement = Connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet Result = null;
		try {
			Result = Statement.executeQuery(Query);
			return Result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			Statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public PreparedStatement prepareStatement(String Query) {
		try {
			return Connection.prepareStatement(Query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<String> resultToArray(ResultSet result, String data) {
		ArrayList<String> arr = new ArrayList<String>();
		try {
			while (result.next()) {
				arr.add(result.getString(data));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}

	public String resultToString(ResultSet result, String data) {
		try {
			if (result.next()) {
				return result.getString(data);
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
