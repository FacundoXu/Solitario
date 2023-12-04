package ui.Spider;

import Card.*;
import Spider.Spider;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.CardView;
import ui.CardWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import ui.Controller;
import ui.SelectionController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpiderController implements Controller {

    private static final String KLONDIKE_PATH = "saves/klondike.txt";
    private static final String SPIDER_PATH = "saves/spider.txt";

    private Spider spider = new Spider();
    private CardWrapper selectedCard;
    private Stage stage;

    @FXML
    private HBox stockBox;

    @FXML
    private HBox foundationBox;

    @FXML
    private GridPane tableauGrid;

    @FXML
    private Button restart;

    @FXML
    private Button newGameButton;

    @FXML
    private void initialize() {
        initializeStock();
        initializeFoundation();
        initializeTableau();
        restart.setOnAction(event -> handleRestart());
        newGameButton.setOnAction(event -> handleNewGameButton());
    }

    private void initializeStock() {
        ImageView card1 = CardView.getCardBack();
        stockBox.getChildren().add(card1);
        card1.setOnMouseClicked(event -> handleStockClick());
    }

    private void handleStockClick() {
        if (spider.getStockSize() == 0)
            return;

        if (spider.getStockSize() == 10) {
            stockBox.getChildren().remove(0);
            Rectangle emptySpace = CardView.getEmptyPlace();
            stockBox.getChildren().add(emptySpace);
        }

        spider.drawStockCards();
        Card[] cards = spider.peekTableauTopCards();

        for (int i = 0; i < 10; i++) {
            Card c = cards[i];
            ImageView view = CardView.getCard(c);
            tableauGrid.add(view, i, spider.getTableauSize(i) - 1);
            view.setOnMouseClicked(event -> handleTableauClick(new CardWrapper(c, view, tableauGrid)));
        }

        refreshFoundations();
        System.out.println("Stock Draw\n");
    }

    public void initializeFoundation() {
        foundationBox.setSpacing(15);

        for (int i = 0; i < 8; i++) {
            Rectangle emptySpace = CardView.getEmptyPlace();
            foundationBox.getChildren().add(emptySpace);
        }
    }

    public void initializeTableau() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                tableauGrid.add(CardView.getCardBack(), i, j);
            }
            Card c = spider.peekTableauTopCard(i);
            ImageView view = CardView.getCard(c);
            tableauGrid.add(view, i, 5);
            view.setOnMouseClicked(event -> handleTableauClick(new CardWrapper(c, view, tableauGrid)));
        }

        for (int i = 4; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                tableauGrid.add(CardView.getCardBack(), i, j);
            }
            Card c = spider.peekTableauTopCard(i);
            ImageView view = CardView.getCard(c);
            tableauGrid.add(view, i, 4);
            view.setOnMouseClicked(event -> handleTableauClick(new CardWrapper(c, view, tableauGrid)));
        }
    }

    private void handleTableauClick(CardWrapper tableauCard) {
        if (selectedCard == null) {
            selectedCard = tableauCard;
            selectedCard.view.setOpacity(0.5);

        } else {
            int originCol = GridPane.getColumnIndex(selectedCard.view);
            int originIndex = GridPane.getRowIndex(selectedCard.view);
            int destCol = GridPane.getColumnIndex(tableauCard.view);
            int destIndex = GridPane.getRowIndex(tableauCard.view);
            System.out.println("originCol: " + originCol);
            System.out.println("destCol: " + destCol);
            System.out.println("originIndex: " + originIndex);
            System.out.println("destIndex: " + destIndex + "\n");

            if (originIndex < spider.getTableauSize(originCol) - 1) {
                tableauToTableauMove(originCol, destCol, originIndex, destIndex);

            } else {
                topCardToTableauMove(originCol, destCol, originIndex, destIndex);
            }

            System.out.println(Arrays.toString(spider.peekTableauTopCards()) + "\n");
            selectedCard.view.setOpacity(1);
            selectedCard = null;
            refreshFoundations();
        }
    }

    private void refreshFoundations() {
        if (spider.addWonColumnsToFoundations()) {
            foundationBox.getChildren().clear();

            for (int i = 0; i < 10; i++) {
                int lastColumnCardIndex = spider.getTableauSize(i);
                List<Node> winnerNodes = removeCardsFromColumn(i, lastColumnCardIndex);

                if (!winnerNodes.isEmpty()) {
                    Node king = winnerNodes.get(0);
                    HBox foundationColumn = new HBox();
                    foundationColumn.getChildren().add(king);
                    foundationBox.getChildren().add(foundationColumn);
                }

                refreshHiddenCards(i, lastColumnCardIndex);
            }

            System.out.println("Winner Cards Added!\n");

            if (spider.verifyVictory()) {
                showVictoryMessage();
            }
        }
    }

    private void showVictoryMessage() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("¡Congrats!");
        alert.setHeaderText(null);
        alert.setContentText("¡¡¡VICTORY!!!");
        alert.getButtonTypes().setAll(ButtonType.OK);
        alert.showAndWait();
    }

    private List<Node> removeCardsFromColumn(int columnIndex, int startIndex) {
        List<Node> nodesToRemove = new ArrayList<>();

        tableauGrid.getChildren().forEach(node -> {
            if (GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) == columnIndex &&
                    GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) >= startIndex) {
                nodesToRemove.add(node);
            }
        });

        tableauGrid.getChildren().removeAll(nodesToRemove);
        return nodesToRemove;
    }

    private void tableauToTableauMove(int originCol, int destCol, int originIndex, int destIndex) {
        int numCardsToMove = spider.getTableauSize(originCol) - originIndex;

        if (originCol == destCol || (!spider.moveTableauToTableau(originCol, originIndex - spider.getTableauHiddenCardsSize(originCol), destCol))) {
            System.out.println("Invalid move\n");

        } else {
            for (int i = 0; i < numCardsToMove; i++) {
                ImageView movedCardView = getCardViewFromGridPane(originCol, originIndex + i);
                tableauGrid.getChildren().remove(movedCardView);
                tableauGrid.add(movedCardView, destCol, destIndex + i + 1);
            }

            refreshHiddenCards(originCol, originIndex);
            System.out.println("Cards Pile Moved");
        }
    }

    private void topCardToTableauMove(int originCol, int destCol, int originIndex, int destIndex) {
        if (originCol == destCol || !spider.moveTopCardToTableau(originCol, destCol)) {
            System.out.println("Invalid move\n");

        } else {
            ImageView movedCardView = selectedCard.view;
            tableauGrid.getChildren().remove(movedCardView);
            tableauGrid.add(movedCardView, destCol, destIndex + 1);
            refreshHiddenCards(originCol, originIndex);
            System.out.println("Top Card Moved");
        }
    }

    private void refreshHiddenCards(int originCol, int originIndex) {
        if (spider.getTableauVisibleCardsSize(originCol) == 1) {
            int hiddenCardRow = originIndex - 1;
            Node cardToRemove = getNodeByColumnAndRowIndex(originCol, hiddenCardRow, tableauGrid);
            tableauGrid.getChildren().remove(cardToRemove);
            Card c = spider.peekTableauTopCard(originCol);
            ImageView view = CardView.getCard(c);
            tableauGrid.add(view, originCol, hiddenCardRow);
            view.setOnMouseClicked(event -> handleTableauClick(new CardWrapper(c, view, tableauGrid)));
        }
    }

    private ImageView getCardViewFromGridPane(int col, int row) {
        for (Node node : tableauGrid.getChildren()) {
            if (node instanceof ImageView && GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return (ImageView) node;
            }
        }
        return null;
    }

    private Node getNodeByColumnAndRowIndex(int columnIndex, int rowIndex, GridPane gridPane) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null) {
                if (GridPane.getColumnIndex(node) == columnIndex && GridPane.getRowIndex(node) == rowIndex) {
                    return node;
                }
            }
        }
        return null;
    }

    private void handleRestart() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("New Game!");
        alert.setHeaderText("Do you want to start a new game?");
        alert.setContentText("Your current progress will be lost.");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                spider = new Spider();
                stockBox.getChildren().clear();
                foundationBox.getChildren().clear();
                tableauGrid.getChildren().clear();
                initializeStock();
                initializeFoundation();
                initializeTableau();
                System.out.println("New Game!\n");
            }
        });
    }

    @FXML
    private void handleNewGameButton() {
        try {
            Path klondikePath = Paths.get(KLONDIKE_PATH);
            Path spiderPath = Paths.get(SPIDER_PATH);

            try {
                Files.deleteIfExists(klondikePath);
                Files.deleteIfExists(spiderPath);

            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }

            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("selectionWindow.fxml"));
            loader.setController(new SelectionController());
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) newGameButton.getScene().getWindow();
            currentStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveGame() {
        spider.saveGame();
    }

    public void loadFoundation() {
        foundationBox.setSpacing(15);

        for (int i = 0; i < 8; i++) {
            if (spider.foundationIsEmpty(i)) {
                Rectangle emptySpace = CardView.getEmptyPlace();
                foundationBox.getChildren().add(emptySpace);

            } else {
                Card c = spider.peekFoundation(i);
                ImageView view = CardView.getCard(c);
                foundationBox.getChildren().add(view);
            }
        }
    }

    public void loadTableau() {
        for (int i = 0; i < 10; i++) {
            int hiddenCardsSize = spider.getTableauHiddenCardsSize(i);

            for (int j = 0; j < hiddenCardsSize; j++) {
                tableauGrid.add(CardView.getCardBack(), i, j);
            }

            List<Card> visibleCards = spider.getTableauVisibleCards(i);
            int visibleCardsSize = spider.getTableauVisibleCardsSize(i);

            for (int x = 0; x < visibleCardsSize; x++) {
                Card c = visibleCards.get(x);
                ImageView view = CardView.getCard(c);
                tableauGrid.add(view, i, hiddenCardsSize + x);
                view.setOnMouseClicked(event -> handleTableauClick(new CardWrapper(c, view, tableauGrid)));
            }
        }
    }

    @Override
    public void load() {
        spider.loadGame();
        stockBox.getChildren().clear();
        foundationBox.getChildren().clear();
        tableauGrid.getChildren().clear();
        initializeStock();
        loadFoundation();
        loadTableau();
        System.out.println("Game Loaded");
    }

    public void save() {
        spider.saveGame();
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void exit() {
    }
}