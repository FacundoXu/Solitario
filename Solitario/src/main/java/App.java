import Klondike.Klondike;
import ui.SelectionController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.*;

public class App extends Application {

    private static final String KLONDIKE_PATH = "saves/klondike.txt";
    private static final String SPIDER_PATH = "saves/spider.txt";

    private static final int SPIDER = 0;
    private static final int KLONDIKE = 1;
    private final SelectionController controller = new SelectionController();

    @Override
    public void start(Stage primaryStage) {
        try {
            File klondikePath = new File(KLONDIKE_PATH);
            File spiderPath = new File(SPIDER_PATH);

            if (klondikePath.exists()) {
                controller.loadController(primaryStage, KLONDIKE);

            } else if (spiderPath.exists()) {
                controller.loadController(primaryStage, SPIDER);

            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("selectionWindow.fxml"));
                loader.setController(controller);
                Parent root = loader.load();
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.setTitle("Solitaire");
                primaryStage.show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        controller.exit();
    }
}