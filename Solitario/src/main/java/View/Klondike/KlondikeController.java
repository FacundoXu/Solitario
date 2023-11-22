package View.Klondike;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class KlondikeController {
    @FXML
    private Button backButton;

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void initialize() {
        // Initialize any components or set up initial game state
    }

    @FXML
    private void handleBackButton() {
        // Handle back button click
        // You might want to close the current window and return to the previous one
        primaryStage.close();
    }

}
