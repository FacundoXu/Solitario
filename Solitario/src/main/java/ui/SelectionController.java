package ui;

import ui.Klondike.KlondikeController;
import ui.Spider.SpiderController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;

public class SelectionController {


    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Button start;

    private Controller controller;

    private String fxmlFile = null;
    private Class<?> controllerClass = null;
    private static final int SPIDER = 0;
    private static final int KLONDIKE = 1;



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

        if ("KLONDIKE".equals(selectedOption)) {
            fxmlFile = "klondike.fxml";
            controllerClass = KlondikeController.class;

        } else if ("SPIDER".equals(selectedOption)) {
            fxmlFile = "spider.fxml";
            controllerClass = SpiderController.class;
        }
        startController();
    }

    private void startController(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlFile));
            controller = (Controller) controllerClass.newInstance();
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) start.getScene().getWindow();
            currentStage.setScene(scene);

        } catch (IOException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

    }
    public void loadController(int game) {
        if (game == SPIDER ){
            fxmlFile = "spider.fxml";
            controllerClass = SpiderController.class;
        }else {
            fxmlFile = "klondike.fxml";
            controllerClass = KlondikeController.class;
        }
        startController();
        controller.load();
    }

}