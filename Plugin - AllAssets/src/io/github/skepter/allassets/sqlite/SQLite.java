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

/** SQLite class taken straight from NecessaryExtrasCore Good thing that Skepter
 * owns that xD */
public class SQLite {

	private String DatabaseURL;
	private Connection Connection;

	public SQLite(File databaseFile) {
		if (!databaseFile.getParentFile().exists())
			databaseFile.getParentFile().mkdir();

		if (!databaseFile.exists())
			try {
				databaseFile.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

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
