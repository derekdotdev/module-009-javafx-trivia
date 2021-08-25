package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseRUD {

	ArrayList<Score> tempList = new ArrayList<Score>();
	ArrayList<Score> tempScores = new ArrayList<Score>();
	Connection c;
	Statement stmt;
	
	public void insertScore(Score currentScore) {
		c = null;
		stmt = null;
		try {
		c = CreateDatabase.connect("insertScore");
		c.setAutoCommit(false);

		// Create PreparedStatement with wildcards for Insertion
		String sqlInsert = "INSERT INTO SCORES(strUserName, intScore, strTime) VALUES(?,?,?)";
		PreparedStatement pstmt = c.prepareStatement(sqlInsert);
		
		// Wildcard entry
		pstmt.setString(1, currentScore.getName());	
		pstmt.setInt(2,  currentScore.getScore());	
		pstmt.setString(3, currentScore.getTime());	
		pstmt.executeUpdate();
		pstmt.close();
		c.commit();
		c.close();
	
	} catch (Exception e) {
		System.out.println("Error in insertScore: " + e.getMessage());
		System.out.println();
	}

	}

	public ArrayList<Score> GenerateWinners() {
		c = null;
		stmt = null;
		try {
			c = CreateDatabase.connect("generateWinners");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT strUserName, intScore, strTime FROM SCORES ORDER BY intScore DESC;");
			while (rs.next()) {
				String tempName = rs.getString("strUserName");
				int tempScore = rs.getInt("intScore");
				String tempTime = rs.getString("strTime");
				tempList.add(new Score(tempName, tempScore, tempTime));
			}
			rs.close();
			stmt.close();
			c.close();
			return tempList;
		} catch (Exception e) {
			System.out.println("Error in generateWinners: " + e.getMessage());
			System.out.println();
		}
		return null;
	}

	public static void retrieveScores() {
		ArrayList<Score> tempList = new ArrayList<Score>();
		Connection c = null;
		Statement stmt = null;
		try {
			c = CreateDatabase.connect("retrieveScores");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM SCORES SORT BY intScore desc;");
			while (rs.next()) {
				String tempName = rs.getString("strUserName");
				int tempScore = rs.getInt("intScore");
				String tempTime = rs.getString("strTime");
				tempList.add(new Score(tempName, tempScore, tempTime));
				System.out.println(tempList);
			}
			rs.close();
			stmt.close();
			c.close();

		} catch (Exception e) {
			System.out.println("Error in insertScore: " + e.getMessage());
			System.out.println();
		}
	}
}