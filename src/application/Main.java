package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	// Call it window for sanity purposes
	Stage window;

	@Override
	public void start(Stage primaryStage) throws IOException {

		// Create SCORES table (if not exists) within project directory (for testing)
		// For final submission, point all to C:\
		CreateDatabase.createTable();
		window = primaryStage;

		// Runs closeProgram() whenever window is closed manually
		// Useful for any necessary housekeeping prior to quitting
		window.setOnCloseRequest(e -> {
			// Consume the event to allow ConfirmBox to take it from here
			e.consume();
			closeProgram();
		});

		Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
		window.setTitle("Trivia Mania!");
		window.setScene(new Scene(root, 800, 600));
		window.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void closeProgram() {
		// Ask if user wants to exit
		Boolean answer = ConfirmBoxExit.display("Exit?", "Are you sure you want to exit?");
		if (answer) {
			// Run any necessary code before window closes:
			// Save / transfer files, etc...
			System.out.println("Window Closed!");
			window.close();
		}
	}

}
