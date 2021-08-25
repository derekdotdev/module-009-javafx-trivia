package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CreateDatabase {

	// Path to typical sqlite dbs on my Mac
	// private static String url = "jdbc:sqlite:/Users/derekdileo/Documents/Software
	// Development/Workspaces/javadatabase_programs/sqlite/db/";

	// url creates jdbc within current project folder
	private static String url = "jdbc:sqlite:";

	// url requested by professor for project
	// private static String url = "jdbc:sqlite:C:\";

	private static String urldb = url + "trivia.db";
	private static Connection c = null;
	private static ArrayList<Score> tempList;

	public static Connection connect(String str) {
		try {
			if (c == null) {
				c = DriverManager.getConnection(urldb);
			} else {
				c.close();
				c = DriverManager.getConnection(urldb);
			}
		} catch (SQLException e) {
			System.out.println("Error: SQLException: " + e.getMessage());
			e.printStackTrace();
		}
		// Point of reference useful for debugging
		System.out.println("\nConnection to SQLite has been established via " + str + ".");
		return c;
	}

	public static void createTable() {
		c = null;
		// String tableName = table;
		System.out.println("Initial connection to SQLite has been established.");
		System.out.println("Creating table...");

		try {
			// Connect to DB & notify
			c = connect("createTable()");

			// Store string value for SQL statement
			String sql = "CREATE TABLE IF NOT EXISTS SCORES (ID INTEGER PRIMARY KEY, strUserName TEXT NOT NULL, intScore INT NOT NULL, strTime TEXT)";

			// Create, execute and close Statement to create table
			Statement stmt = c.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Error: SQLException: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (c != null) {
					c.close();
					System.out.println("\nThe createTable() method has finished !");
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}


	public static void GenerateWinners() {

		tempList = null;
		Statement stmt;
		c = null;
		try {
			stmt = null;
			c = connect("generateWinners");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM SCORES ORDER BY intScore DESC;");
			while (rs.next()) {
				String tempName = rs.getString("strUserName");
				int tempScore = rs.getInt("intScore");
				String tempTime = rs.getString("strTime");
				tempList.add(new Score(tempName, tempScore, tempTime));
			}
			rs.close();
			stmt.close();
			c.close();
			System.out.println("Database contents: " + tempList);
		} catch (Exception e) {
			System.out.println("Error in generateWinners: " + e.getMessage());
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// GenerateWinners();
		createTable();
	}

}
