package View.Klondike;

import Klondike.Klondike;
import View.CardView;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import Card.*;
import javafx.scene.shape.Rectangle;

public class KlondikeController {

        @FXML
        private GridPane tableauGrid;

        @FXML
        private HBox foundationBox;

        @FXML
        private HBox stockBox;

        private final Klondike game = new Klondike();
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
            ImageView card2 = CardView.getCardBack();

            card2.setTranslateX(-30);
            card2.setTranslateY(5);

            stockBox.getChildren().add(card1);
            stockBox.getChildren().add(card2);

            card1.setOnMouseClicked(event -> handleStockClick());
            card2.setOnMouseClicked(event -> handleStockClick());

        }

        public void initializeFoundation(){
            foundationBox.setSpacing(15); // Add spacing between rectangles

            for (int i = 0; i < 4; i++){
                Rectangle emptySpace = CardView.getEmptyPlace();
                emptySpace.setOnMouseClicked(event -> handleFoundationClick(emptySpace));
                foundationBox.getChildren().add(emptySpace);
            }
        }


    public void initializeTableau(){
            for (int i = 0; i < 7; i++){
                Card c = game.peekTableauTopCard(i);
                tableauGrid.add(CardView.getCard(c), i, i);
                for (int j = i + 1; j < 7; j++){
                    tableauGrid.add(CardView.getCardBack(), j, i);
                }
            }
        }

        public void handleStockClick(){
            if (selectedCard != null){
                selectedCardView.setOpacity(1);
                selectedCardView = null;
                selectedCard = null;
            }

            if (game.stockCardsLeft() == 0 && game.stockPass() == 3){
                return;
            }
            Card c  = game.stockNextCard();
            if (c == null){
                stockBox.getChildren().clear();
                initializeStock();
                return;
            }
            ImageView cardV = CardView.getCard(c);

            if (game.stockCardsLeft() == 0){
                stockBox.getChildren().remove(1);
                stockBox.getChildren().remove(0);
                Rectangle emptyStock = CardView.getEmptyPlace();
                stockBox.getChildren().add(emptyStock);
                emptyStock.setOnMouseClicked(event -> handleStockClick());
                cardV.setTranslateX(45);
            } else if (game.stockCardsLeft() == 1){
                stockBox.getChildren().remove(2);
                stockBox.getChildren().remove(1);
                cardV.setTranslateX(45);
            }else if (stockBox.getChildren().size() == 3) {
                stockBox.getChildren().remove(2);}
            stockBox.getChildren().add(cardV);
            cardV.setOnMouseClicked(event -> handleWasteClick(c, cardV));
        }

        private void handleWasteClick(Card wasteCard, ImageView wasteCardView) {
            if (selectedCard == null){
                selectedCard = wasteCard;
                selectedCardView = wasteCardView;
                selectedCardView.setOpacity(0.5);
            } else {
                selectedCardView.setOpacity(1);
                selectedCardView = null;
                selectedCard = null;
            }
        }

        private void handleFoundationClick(Rectangle emptySpace) {
            if (selectedCard != null){
                if (game.moveStockToFoundation(foundationBox.getChildren().indexOf(emptySpace))){
                }
            }
        }

}