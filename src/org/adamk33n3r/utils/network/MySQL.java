package org.adamk33n3r.utils.network;

import java.sql.*;

public class MySQL {
	
	String dbtime;
	String dbURL;
	String dbClass = "com.mysql.jdbc.Driver";
	Connection conn;
	
	public static void main(String[] args) {
		MySQL sql = new MySQL("jdbc:mysql://www.textbookchecker.com/games", true);
		sql.query("SELECT * FROM `karthas`");
		sql.close();
	}
	
	/**
	 * Creates a connection
	 * @param dbURL - The database to connect to {@code jdbc:mysql://your.database.domain/yourDBname}
	 * @param connect - If true, connect on creation
	 */
	public MySQL(String dbURL, boolean connect) {
		this.dbURL = dbURL;
		if (connect)
			create();
	}
	
	/**
	 * Creates a connection
	 * @param dbURL - The database to connect to {@code jdbc:mysql://your.database.domain/yourDBname}
	 * @return true if connection was made, false otherwise
	 */
	public boolean create() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(dbURL, "root", "nike27nike27");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}return true;
	}
	
	/**
	 * Should be called when not in use
	 * @return true if successfully closed, false otherwise
	 */
	public boolean close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}return true;
	}
	
	public void query(String query) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				System.out.println(String.format("%s: %s - %s", id, username, password));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
