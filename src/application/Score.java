package application;

public class Score {

	// Create local variables
	private String strUserName;
	private String strTime = "0";
	@SuppressWarnings("unused")
	private int intTime = 0;
	private int intScore = 0;

	// Overloaded constructor
	public Score(String name) {
		strUserName = name;
		intScore = 0;
	}

	// Constructor
	public Score(String name, int score, String time) {
		strUserName = name;
		strTime = time;
		intScore = score;
	}

	// Getters and Setters
	public String getName() {
		return strUserName;
	}

	public String getTime() {
		return strTime;
	}

	public int getScore() {
		return intScore;
	}

	@Override
	public String toString() {
		return "\nName: " + strUserName + "\nTime: " + strTime + "\nScore: " + intScore;
	}

	// Method to increase stored score
	public void increaseScore() {
		intScore++;
	}

	// Method to increase stored time
	public void setTime(int time) {
		intTime += time;
		strTime = "0:" + intTime;
	}
}
