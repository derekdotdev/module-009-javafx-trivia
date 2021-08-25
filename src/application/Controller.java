package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

// All the behind-the-scenes logic goes in Controller.java
// Submit a form, send to database etc...

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Controller implements Initializable {

	private ArrayList<TriviaOptions> guiItems = null;
	TriviaOptions Question = null;
	private Score tempScore;
	private int questionCounter = 0;
	private boolean timerSafe = true;
	private Timeline timer;
	private Integer timeSeconds = 10;

	// Local GUI Elements
	@FXML
	private MenuBar menuBar;
	@FXML
	private Label lblName = new Label();
	@FXML
	private Label lblUserName = new Label();
	@FXML
	private Button btnBeginGame;
	@FXML
	private TextField nameEntry;
	@FXML
	private Label lblPoints = new Label("Points Total");
	@FXML
	private Label lblPoints2 = new Label("0");
	@FXML
	private Label lblCountdown = new Label("Countdown Clock");
	@FXML
	private Label lblTimer = new Label("");
	@FXML
	private Label lblObject1 = new Label("Object 1");
	@FXML
	private Label lblObj1 = new Label();
	@FXML
	private Label lblPlus = new Label("+");
	@FXML
	private Label lblEquals = new Label("=");
	@FXML
	private Label lblQuestion = new Label("?");
	@FXML
	private Label lblObject2 = new Label("Object 2");
	@FXML
	private Label lblObj2 = new Label();
	@FXML
	private Label lblTimestamp = new Label("Timestamp Area");
	@FXML
	private TextArea taTimestampArea = new TextArea();
	@FXML
	private TextArea taAnswers = new TextArea();
	@FXML
	private Button btnChoice1 = new Button("Choice 1");
	@FXML
	private Button btnChoice2 = new Button("Choice 2");
	@FXML
	private Button btnChoice3 = new Button("Choice 3");
	@FXML
	private Button btnChoice4 = new Button("Choice 4");
	@FXML
	private Text scoreCounter;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		FileInput readme = new FileInput();

		guiItems = readme.getFile();

		nameEntry.setText(ConfirmBoxName.display());

		System.out.println("View is now loaded!");

	}

	// Start Button Pressed!
	public void beginGame(ActionEvent actionEvent) {

		// Add name to Score object
		tempScore = new Score(nameEntry.getText());

		// Enable buttons
		btnChoice1.setDisable(false);
		btnChoice2.setDisable(false);
		btnChoice3.setDisable(false);
		btnChoice4.setDisable(false);

		// Get question elements
		incrementQuestion();

		// Set GUI
		setQuestionGUI();

		// Create timer and run
		timer = new Timeline();
		timer.setCycleCount(Timeline.INDEFINITE);
		timer.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> timerEvent()));
		timer.playFromStart();
	}

	// Ten second timer for each question
	public void timerEvent() {
		// Decrement timer
		timeSeconds--;

		// Push time to GUI
		lblTimer.setText(timeSeconds.toString());

		// Skip question if time runs out
		if (timeSeconds <= 0) {

			// If wrong answer input,
			// question counter and GUI already set
			// Jump over this and carry on
			if (timerSafe) {
				questionCounter++;
				incrementQuestion();
				setQuestionGUI();
			}

			timerSafe = true;
			timeSeconds = 10;
			lblTimer.setText(timeSeconds.toString());
			if (questionCounter > 9) {
				timer.stop();
			}
		}
	}

	// Get Elements for Next Question
	private void incrementQuestion() {

		Boolean safe = false;
		safe = questionCheck();
		if (safe) {
			Question = guiItems.get(questionCounter);
		}
	}

	// Check if game has ended
	public boolean questionCheck() {
		// If no question 10 reached:
		if (questionCounter > 9) {

			taTimestampArea.setText(" ");
			taTimestampArea.appendText(tempScore.toString());

			btnBeginGame.setDisable(true);
			btnChoice1.setDisable(true);
			btnChoice2.setDisable(true);
			btnChoice3.setDisable(true);
			btnChoice4.setDisable(true);

			// Push current score to database
			DatabaseRUD finalEntry = new DatabaseRUD();
			finalEntry.insertScore(tempScore);

			// Pull all from database & print to GUI
			generateReport();
			return false;
		}
		return true;
	}

	// Set GUI values
	private void setQuestionGUI() {

		// Create ArrayLists for Question Objects and Strings
		ArrayList<Object> triviaObjects = Question.getTriviaObjects();
		ArrayList<String> triviaLabels = Question.getTriviaLabels();

		// Grab Strings for Each Question Element
		String objDisplay1 = triviaLabels.get(0);
		String objDisplay2 = triviaLabels.get(1);
		String optDisplay1 = triviaLabels.get(2);
		String optDisplay2 = triviaLabels.get(3);
		String optDisplay3 = triviaLabels.get(4);
		String optDisplay4 = triviaLabels.get(5);

		// Set GUI Labels to Each Question Element
		lblObj1.setText(objDisplay1);
		lblObj2.setText(objDisplay2);

		taAnswers.setText("Option 1: " + optDisplay1 + "\nOption 2: " + optDisplay2 + "\nOption 3: " + optDisplay3
				+ "\nOption 4: " + optDisplay4);

		// Grab Objects for Each Possible Answer
		Object btnObj1 = triviaObjects.get(2);
		Object btnObj2 = triviaObjects.get(3);
		Object btnObj3 = triviaObjects.get(4);
		Object btnObj4 = triviaObjects.get(5);

		// Set Button to Object Values
		btnChoice1.setUserData(btnObj1);
		btnChoice2.setUserData(btnObj2);
		btnChoice3.setUserData(btnObj3);
		btnChoice4.setUserData(btnObj4);

	}

	// Respond to answer entry
	public void pushAnswer(ActionEvent event) {

		// Determine which button was pressed
		Button pushedButton = (Button) event.getSource();

		// Determine if answer is correct
		boolean rightAnswer = false;
		rightAnswer = Question.compareItem(pushedButton.getUserData());

		if (rightAnswer == true) {
			tempScore.increaseScore();
		}

		// Increment question counter
		questionCounter++;

		// Get data and set GUI for next question
		incrementQuestion();
		setQuestionGUI();

		// Update GUI score
		scoreCounter.setText(tempScore.getScore() + " Points");

		// Add time to score
		tempScore.setTime(timeSeconds);

		// Skip to next question
		timeSeconds = 0;

		// Don't jump two questions ahead
		timerSafe = false;

	}

	public void insertRecord() {
		DatabaseRUD db = new DatabaseRUD();
		db.insertScore(tempScore);
	}

	// When game is over, print DB scores to console and GUI
	public void generateReport() {
		DatabaseRUD DBHandler = new DatabaseRUD();
		ArrayList<Score> reportScore = DBHandler.GenerateWinners();
		taTimestampArea.setText(" ");
		System.out.println();
		if (reportScore != null) {

			int noOfRecords = reportScore.size();
			System.out.println("Number of Records: " + noOfRecords);

			if (noOfRecords <= 10) {
				for (Score e : reportScore) {
					taTimestampArea.appendText(e.toString());
					System.out.println(e.toString());
				}
			} else {
				for (int i = 0; i <= 9; i++) {
					String str = reportScore.get(i).toString();
					taTimestampArea.appendText(str);
					System.out.println(str);
				}

			}

		}
	}

}
