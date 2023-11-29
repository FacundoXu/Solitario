package View.Spider;

import Card.Card;
import Spider.Spider;
import View.CardView;
import View.CardWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

public class SpiderController {
    @FXML
    private HBox stockBox;

    @FXML
    private HBox foundationBox;

    @FXML
    private GridPane tableauGrid;

    @FXML
    private Button restart;

    private Spider game = new Spider();
    private CardWrapper selectedCard;

    @FXML
    private void initialize() {
        initializeStock();
        initializeFoundation();
        initializeTableau();
        restart.setOnAction(event -> handleRestart());
    }

    private void initializeStock() {
        ImageView card1 = CardView.getCardBack();
        stockBox.getChildren().add(card1);
        card1.setOnMouseClicked(event -> handleStockClick());
    }

    private void handleStockClick() {
        if (game.getStockSize() == 0)
            return;

        if (game.getStockSize() == 10) {
            stockBox.getChildren().remove(0);
            Rectangle emptySpace = CardView.getEmptyPlace();
            stockBox.getChildren().add(emptySpace);
        }

        game.drawStockCards();
        System.out.println("STOCK DRAW");
        Card[] cards = game.peekTableauTopCards();

        for (int i = 0; i < 10; i++) {
            Card c = cards[i];
            ImageView view = CardView.getCard(c);
            tableauGrid.add(view, i, game.getTableauSize(i) - 1);
            view.setOnMouseClicked(event -> handleTableauClick(new CardWrapper(c, view, tableauGrid)));
        }
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
            Card c = game.peekTableauTopCard(i);
            ImageView view = CardView.getCard(c);
            tableauGrid.add(view, i, 5);
            view.setOnMouseClicked(event -> handleTableauClick(new CardWrapper(c, view, tableauGrid)));
        }

        for (int i = 4; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                tableauGrid.add(CardView.getCardBack(), i, j);
            }
            Card c = game.peekTableauTopCard(i);
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
            System.out.println("destIndex: " + destIndex);
            System.out.println();
            selectedCard.view.setOpacity(1);
            selectedCard = null;
        }
    }

    private void handleRestart() {
        game = new Spider();
        stockBox.getChildren().clear();
        foundationBox.getChildren().clear();
        tableauGrid.getChildren().clear();
        initializeStock();
        initializeFoundation();
        initializeTableau();
    }
}