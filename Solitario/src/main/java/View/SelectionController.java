package View;

import View.Klondike.KlondikeController;
import View.Spider.SpiderController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;


public class SelectionController {

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Button start;

    @FXML
    private void initialize() {
        choiceBox.getItems().addAll("KLONDIKE", "SPIDER");

        start.setOnAction(event -> {
            try {
                handleStartButton();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
    @FXML
    private void handleStartButton() throws Exception {
        String selectedOption = choiceBox.getValue();
        String fxmlFile = null;
        Class<?> controllerClass = null;

        if ("KLONDIKE".equals(selectedOption)) {
            fxmlFile = "klondike.fxml";
            controllerClass = KlondikeController.class;
        } else if ("SPIDER".equals(selectedOption)) {
            fxmlFile = "spider.fxml";
            controllerClass = SpiderController.class;
        }
        System.out.println(fxmlFile);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlFile));
            loader.setController(controllerClass.newInstance());
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) start.getScene().getWindow();
            currentStage.setScene(scene);
        } catch (IOException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}

