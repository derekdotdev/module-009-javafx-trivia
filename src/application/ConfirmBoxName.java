package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

// A custom class for generating separate confirm windows (stages)
// In this case, it is being used to confirm user wants to quit.
// Uses same background image as Main, so requires multiple panes

public class ConfirmBoxName {

	static String strReturnName;
	static Stage window;

	public static String display() {

		window = new Stage();

		StackPane stackPane1 = new StackPane();
		StackPane stackPane2 = new StackPane();

		VBox layout = new VBox(10);
		layout.setPadding(new Insets(10, 10, 10, 10));

		// Lock out primary window until alert is cleared
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Welcome to Trivia Mania!");
		window.setMinWidth(250);


		// Do not allow program to proceed without a name
		window.setOnCloseRequest(e -> {
			// Consume the event to allow ConfirmBox to take it from here
			e.consume();
			closeProgram();
		});

		// Ask for name
		Label label = new Label();
		label.setText("What shall I call you?");
		TextField confirmBoxNameInput = new TextField(null);
		// Create submit button
		Button submitButton = new Button("Let's Go!");
		
		submitButton.setOnAction(e -> {

			if (confirmBoxNameInput.getText() != null) {
				strReturnName = confirmBoxNameInput.getText();
				window.close();

			} else {
				AlertBox.display("ERROR", "Please enter a name!");
				System.out.println("Please enter a name!");
			}

		});

		layout.getChildren().addAll(label, confirmBoxNameInput, submitButton);
		layout.setAlignment(Pos.CENTER);

		stackPane1.getChildren().addAll(stackPane2, layout);

		Scene scene = new Scene(stackPane1);
		window.setScene(scene);
		window.showAndWait();
		
		return strReturnName;
	}

	static void closeProgram() {
		// Ask if user wants to exit
		Boolean answer = ConfirmBoxExit.display("Exit?", "Are you sure you want to exit?");
		if (answer) {
			// Handle housekeeping (NA for this example). Quit program
			// Handy feature if time needed to save / transfer files, etc...
			System.out.println("Window Closed!");
			window.close();
			System.exit(0);
		}
	}
}


