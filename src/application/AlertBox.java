package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

// A custom class for generating separate alert windows (stages)
// Uses same background image as Main, so requires multiple panes

public class AlertBox {

	public static void display(String title, String message) {

		// Create Stage
		Stage window = new Stage();

		// Lock out primary window until alert is cleared
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		window.setMinHeight(100);
		
		Label label = new Label();
		label.setId("alert-box-label");
		label.setText(message);
		
		Button closeButton = new Button ("Close");
		closeButton.setOnAction(e -> window.close());
		
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);

		StackPane stackPane1 = new StackPane();
		StackPane stackPane2 = new StackPane();

		stackPane2.setId("alert-box-pane");
		stackPane1.getChildren().addAll(stackPane2, layout);

		Scene scene = new Scene(stackPane1);
		window.setScene(scene);

		scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		window.showAndWait();
		
	}

}

