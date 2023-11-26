package View.Spider;

import Card.Card;
import Spider.Spider;
import View.CardView;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

public class SpiderController {
    @FXML
    private GridPane tableauGrid;

    @FXML
    private HBox foundationBox;

    @FXML
    private HBox stockBox;

    private final Spider game = new Spider();
    private ImageView selectedCardView;
    private Card selectedCard;

    @FXML
    private void initialize() {
        initializeStock();
        initializeFoundation();
        initializeTableau();
    }

    private void initializeStock() {
        ImageView card1 = CardView.getCardBack();
        stockBox.getChildren().add(card1);
        card1.setOnMouseClicked(event -> handleStockClick());
    }

    public void initializeFoundation() {
        foundationBox.setSpacing(15); // Add spacing between rectangles

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
            tableauGrid.add(CardView.getCard(c), i, 5);
        }

        for (int i = 4; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                tableauGrid.add(CardView.getCardBack(), i, j);
            }
            Card c = game.peekTableauTopCard(i);
            tableauGrid.add(CardView.getCard(c), i, 4);
        }
    }

    private void handleStockClick() {
    }
}
