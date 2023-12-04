package ui.Klondike;

import Klondike.Klondike;
import Spider.Spider;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import ui.CardView;
import ui.CardWrapper;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import Card.*;
import javafx.scene.shape.Rectangle;
import ui.Controller;
import ui.SelectionController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class KlondikeController implements Controller {

    private static final String KLONDIKE_PATH = "saves/klondike.txt";
    private static final String SPIDER_PATH = "saves/spider.txt";
    private Stage stage;

    @FXML
    private GridPane tableauGrid;

    @FXML
    private HBox foundationBox;
    @FXML
    private Button restart;

    @FXML
    private HBox stockBox;

    @FXML
    private Button newGameButton;

    private ArrayList<ImageView>[] tableauViews;
    private int[] visibleIdx;
    private Klondike game = new Klondike();
    CardWrapper selectedCard;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void initialize() {
        initializeStock();
        initializeFoundation();
        initializeTableau();
        selectedCard = null;
        restart.setOnAction(event -> handleRestart());
        newGameButton.setOnAction(event -> handleNewGameButton());
    }

    private void initializeStock() {

        ImageView card1 = CardView.getCardBack();
        ImageView card2 = CardView.getCardBack();

        card2.setTranslateX(-30);
        card2.setTranslateY(5);

        stockBox.getChildren().add(card1);
        stockBox.getChildren().add(card2);

        card1.setOnMouseClicked(event -> handleStockClick());
        card2.setOnMouseClicked(event -> handleStockClick());
    }

    private void handleTableauClick(CardWrapper tableauCard) {
        if (selectedCard != null) {
            if (selectedCard.container == tableauGrid) {
                int originTableauIdx = GridPane.getColumnIndex(selectedCard.view);
                int originCardIdx = tableauViews[originTableauIdx].size() - GridPane.getRowIndex(selectedCard.view) - 1;
                int destTableauIdx = GridPane.getColumnIndex(tableauCard.view);
                System.out.println("origin" + visibleIdx[originTableauIdx]);
                System.out.println("dest" + visibleIdx[destTableauIdx]);
                if (game.moveTableauToTableau(originCardIdx, originTableauIdx, destTableauIdx)) {
                    System.out.println("entró");
                    if (visibleIdx[originTableauIdx] > -1) visibleIdx[originTableauIdx] -= 1;
                    refreshTableau(originTableauIdx);
                    refreshTableau(destTableauIdx);
                    System.out.println("origin" + visibleIdx[originTableauIdx]);
                    System.out.println("dest" + visibleIdx[destTableauIdx]);
                }
            } else if (selectedCard.container == stockBox) {
                int destTableauIdx = GridPane.getColumnIndex(tableauCard.view);
                if (game.moveStockToTableau(destTableauIdx)) {
                    refreshTableau(destTableauIdx);
                    refreshWaste();
                }
            }
            selectedCard.view.setOpacity(1);
            selectedCard = null;
        } else {
            selectedCard = tableauCard;
            selectedCard.view.setOpacity(0.5);
        }
    }

    private void refreshTableau(int tableauIdx) {

        Card[] newCards = game.peekTableauVisibleCards(tableauIdx);
//        System.out.println("new cards length " + newCards.length);
        if (newCards != null) {
            for (int i = tableauViews[tableauIdx].size() - 1; i > visibleIdx[tableauIdx] - 1; i--) {
                System.out.println("rem " + i);
                tableauGrid.getChildren().remove(tableauViews[tableauIdx].get(i));
                tableauViews[tableauIdx].remove(i);
            }
            for (int i = 0; i < newCards.length; i++) {
                System.out.println("Add " + i);
                Card c = newCards[i];
                ImageView view = CardView.getCard(c);
                tableauGrid.add(view, tableauIdx, visibleIdx[tableauIdx] + i);
                tableauViews[tableauIdx].add(visibleIdx[tableauIdx] + i, view);
                view.setOnMouseClicked(event -> handleTableauClick(new CardWrapper(c, view, tableauGrid)));
            }
        } else {
            tableauGrid.getChildren().remove(tableauViews[tableauIdx].get(0));
            tableauViews[tableauIdx].remove(0);
            Rectangle rectangle = CardView.getEmptyPlace();
            tableauGrid.add(rectangle, tableauIdx, 0);
            rectangle.setOnMouseClicked(event -> handleEmptyTableauClick(tableauIdx));
        }
    }

    private void handleEmptyTableauClick(int tableauIdx) {
        System.out.println("Entró");
        if (selectedCard != null) {
            if (selectedCard.container == tableauGrid) {
                int originTableauIdx = GridPane.getColumnIndex(selectedCard.view);
                int originCardIdx = tableauViews[originTableauIdx].size() - GridPane.getRowIndex(selectedCard.view) - 1;
                System.out.println("origin" + visibleIdx[originTableauIdx]);
                System.out.println("dest" + visibleIdx[tableauIdx]);
                if (game.moveTableauToTableau(originCardIdx, originTableauIdx, tableauIdx)) {
                    System.out.println("entró");
                    if (visibleIdx[originTableauIdx] > -1) visibleIdx[originTableauIdx] -= 1;
                    visibleIdx[tableauIdx] += 1;
                    refreshTableau(originTableauIdx);
                    refreshTableau(tableauIdx);
                    System.out.println("origin" + visibleIdx[originTableauIdx]);
                    System.out.println("dest" + visibleIdx[tableauIdx]);
                }
            } else if (selectedCard.container == stockBox) {
                for (int i = 0; i < 7; i++) {
                    System.out.println(game.peekTableauTopCard(i));
                }
                if (game.moveStockToTableau(tableauIdx)) {
                    visibleIdx[tableauIdx] += 1;
                    refreshTableau(tableauIdx);
                    refreshWaste();
                }
//            } else if (selectedCard.container == foundationBox) {
//                int destTableauIdx = GridPane.getColumnIndex(tableauCard.view);
//                if (game.moveFoundationToTableau(destTableauIdx)) {
//                    refreshTableau(des);
//                    refreshStock();
//                }
            }
            selectedCard.view.setOpacity(1);
            selectedCard = null;
        }
    }

    private void handleStockClick() {
        if (selectedCard != null) {
            selectedCard.view.setOpacity(1);
            selectedCard = null;
        }

        if (game.stockCardsLeft() == 0 && game.stockPass() == 3) {
            return;
        }

        Card c = game.stockNextCard();
        if (c == null) {
            stockBox.getChildren().clear();
            initializeStock();
            return;
        }
        ImageView cardV = CardView.getCard(c);

        if (game.stockCardsLeft() == 0) {
            stockBox.getChildren().remove(1);
            stockBox.getChildren().remove(0);
            Rectangle emptyStock = CardView.getEmptyPlace();
            stockBox.getChildren().add(emptyStock);
            emptyStock.setOnMouseClicked(event -> handleStockClick());
            cardV.setTranslateX(45);
        } else if (game.stockCardsLeft() == 1) {
            stockBox.getChildren().remove(2);
            stockBox.getChildren().remove(1);
            cardV.setTranslateX(45);
        } else if (stockBox.getChildren().size() == 3) {
            stockBox.getChildren().remove(2);
        }
        stockBox.getChildren().add(cardV);
        cardV.setOnMouseClicked(event -> handleWasteClick(new CardWrapper(c, cardV, stockBox)));
    }

    private void handleWasteClick(CardWrapper wasteCard) {
        if (selectedCard == null) {
            selectedCard = wasteCard;
            selectedCard.view.setOpacity(0.5);
        } else {
            selectedCard.view.setOpacity(1);
            selectedCard = null;
        }
    }

    public void initializeFoundation() {
        foundationBox.setSpacing(15); // Add spacing between rectangles

        for (int i = 0; i < 4; i++) {
            Rectangle emptySpace = CardView.getEmptyPlace();
            emptySpace.setOnMouseClicked(event -> handleFoundationClick(foundationBox.getChildren().indexOf(emptySpace)));
            foundationBox.getChildren().add(emptySpace);
        }
    }

    public void initializeTableau() {
        visibleIdx = new int[]{0, 1, 2, 3, 4, 5, 6};
        tableauViews = new ArrayList[7];
        for (int i = 0; i < 7; i++) {
            tableauViews[i] = new ArrayList<>();
        }
        for (int i = 0; i < 7; i++) {
            Card c = game.peekTableauTopCard(i);
            ImageView view = CardView.getCard(c);
            tableauGrid.add(view, i, i);
            tableauViews[i].add(view);
            view.setOnMouseClicked(event -> handleTableauClick(new CardWrapper(c, view, tableauGrid)));
            for (int j = i + 1; j < 7; j++) {
                ImageView cardBack = CardView.getCardBack();
                tableauGrid.add(cardBack, j, i);
                tableauViews[j].add(cardBack);
            }
        }
    }

    private void refreshWaste() {
        selectedCard.remove();
        Card card = game.peekStockTopCard();
        if (card == null) {
            return;
        }
        ImageView cardV = CardView.getCard(card);
        stockBox.getChildren().add(cardV);
        cardV.setOnMouseClicked(event -> handleWasteClick(new CardWrapper(card, cardV, stockBox)));
    }

    private void handleFoundationClick(int foundationIdx) {
        if (selectedCard != null) {
            if (selectedCard.container == stockBox) {
                if (game.moveStockToFoundation(foundationIdx)) {
                    foundationBox.getChildren().remove(foundationIdx);
                    foundationBox.getChildren().add(foundationIdx, selectedCard.view);
                    selectedCard.view.setOnMouseClicked(event -> handleFoundationClick(foundationIdx));
                    selectedCard.remove();
                    refreshWaste();
                }
            } else if (selectedCard.container == tableauGrid) {
                int tableauIdx = GridPane.getColumnIndex(selectedCard.view);
                if (game.moveTableauToFoundation(tableauIdx, foundationIdx)) {
                    foundationBox.getChildren().remove(foundationIdx);
                    foundationBox.getChildren().add(foundationIdx, selectedCard.view);
                    selectedCard.view.setOnMouseClicked(event -> handleFoundationClick(foundationIdx));
                    selectedCard.remove();
                    if (visibleIdx[tableauIdx] == tableauViews[tableauIdx].size() - 1) visibleIdx[tableauIdx] -= 1;
                    refreshTableau(tableauIdx);
                }
            }
            selectedCard.view.setOpacity(1);
            selectedCard = null;
            if (game.verifyVictory()) {
                showVictoryMessage();
            }
        }
    }

    private void showVictoryMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡Congrats!");
        alert.setHeaderText(null);
        alert.setContentText("¡¡¡VICTORY!!!");
        alert.getButtonTypes().setAll(ButtonType.OK);
        alert.showAndWait();
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

    private void handleRestart() {
        System.out.println("New Game!\n");
        game = new Klondike();
        stockBox.getChildren().clear();
        foundationBox.getChildren().clear();
        tableauGrid.getChildren().clear();
        initializeStock();
        initializeFoundation();
        initializeTableau();
    }

    public void load() {
        game.loadGame();
        stockBox.getChildren().clear();
        System.out.println("carga");

        foundationBox.getChildren().clear();
        tableauGrid.getChildren().clear();
        loadStock();
        loadFoundation();
        loadTableau();
    }

    public void save() {
        game.saveGame();
    }

    private void loadTableau() {
        for (int i = 0; i < 7; i++) {
            Card[] visibleCards = game.peekTableauVisibleCards(i);
            if (visibleCards == null) {
                visibleIdx[i] = 0;
//                Rectangle rectangle = CardView.getEmptyPlace();
//                tableauGrid.add(rectangle, tableauIdx, 0);
//                rectangle.setOnMouseClicked(event -> handleEmptyTableauClick(tableauIdx));

                int idx = i;
                Rectangle emptySpace = CardView.getEmptyPlace();
                tableauGrid.add(emptySpace, i, 0);
                emptySpace.setOnMouseClicked(event -> handleEmptyTableauClick(idx));
                continue;
            }
            int size = game.peekSize(i);
            visibleIdx[i] = size - visibleCards.length;

            for (int j = 0; j < visibleIdx[i]; j++) {
                ImageView cardBack = CardView.getCardBack();
                tableauGrid.add(cardBack, i, j);
                tableauViews[i].add(cardBack);
            }
            for (int j = 0; j < visibleCards.length; j++) {
                Card card = visibleCards[j];
                ImageView view = CardView.getCard(card);
                view.setOnMouseClicked(event -> handleTableauClick(new CardWrapper(card, view, tableauGrid)));
                tableauGrid.add(view, i, visibleIdx[i] + j);
                tableauViews[i].add(view);
            }

        }

    }

    private void loadFoundation() {
        foundationBox.setSpacing(15); // Add spacing between rectangles
        for (int i = 0; i < 4; i++) {

            Card topCard = game.peekFoundation(i);
            if (topCard == null) {
                Rectangle emptySpace = CardView.getEmptyPlace();
                emptySpace.setOnMouseClicked(event -> handleFoundationClick(foundationBox.getChildren().indexOf(emptySpace)));
                foundationBox.getChildren().add(emptySpace);
            } else {
                ImageView view = CardView.getCard(topCard);
                view.setOnMouseClicked(event -> handleFoundationClick(foundationBox.getChildren().indexOf(view)));
                foundationBox.getChildren().add(view);
            }
        }
    }

    private void loadStock() {
        Card topCard = game.peekStockTopCard();
        ImageView card1 = CardView.getCardBack();
        ImageView card2 = CardView.getCardBack();
        card2.setTranslateX(-30);
        card2.setTranslateY(5);
        stockBox.getChildren().add(card1);
        stockBox.getChildren().add(card2);
        card1.setOnMouseClicked(event -> handleStockClick());
        card2.setOnMouseClicked(event -> handleStockClick());
        if (topCard == null) {
            stockBox.getChildren().clear();
            initializeStock();
            return;
        }
        ImageView cardV = CardView.getCard(topCard);
        if (game.stockCardsLeft() == 0) {
            stockBox.getChildren().remove(1);
            stockBox.getChildren().remove(0);
            Rectangle emptyStock = CardView.getEmptyPlace();
            stockBox.getChildren().add(emptyStock);
            emptyStock.setOnMouseClicked(event -> handleStockClick());
            cardV.setTranslateX(45);
        } else if (game.stockCardsLeft() == 1) {
            stockBox.getChildren().remove(2);
            stockBox.getChildren().remove(1);
            cardV.setTranslateX(45);
        } else if (stockBox.getChildren().size() == 3) {
            stockBox.getChildren().remove(2);
        }
        stockBox.getChildren().add(cardV);
        cardV.setOnMouseClicked(event -> handleWasteClick(new CardWrapper(topCard, cardV, stockBox)));
    }
}