package ui.Klondike;

import Klondike.Klondike;
import ui.CardView;
import ui.CardWrapper;
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
        private final int[] tableauSizes = {1, 2, 3, 4, 5, 6, 7};

        private final Klondike game = new Klondike();

        CardWrapper selectedCard;

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
                emptySpace.setOnMouseClicked(event -> handleFoundationClick(foundationBox.getChildren().indexOf(emptySpace)));
                foundationBox.getChildren().add(emptySpace);
            }
        }


    public void initializeTableau(){
            for (int i = 0; i < 7; i++){
                Card c = game.peekTableauTopCard(i);
                ImageView view = CardView.getCard(c);
                tableauGrid.add(view, i, i);
                view.setOnMouseClicked(event -> handleTableauClick(new CardWrapper(c, view, tableauGrid)));
                for (int j = i + 1; j < 7; j++){
                    tableauGrid.add(CardView.getCardBack(), j, i);
                }
            }
        }

    private void handleTableauClick(CardWrapper tableauCard) {
        if (selectedCard != null) {
            if(selectedCard.container == tableauGrid){
                int originTableauIdx = GridPane.getColumnIndex(selectedCard.view);
                int originCardIdx = tableauSizes[originTableauIdx] - GridPane.getRowIndex(selectedCard.view) - 1;
                int destTableauIdx = GridPane.getColumnIndex(tableauCard.view);
                System.out.println("originTableauIdx = " + originTableauIdx);
                System.out.println("originCardIdx = " + originCardIdx);
                System.out.println("destTableauIdx = " + destTableauIdx);
                System.out.println("tableauSizes[originTableauIdx] = " + tableauSizes[originTableauIdx]);
                System.out.println("tableauSizes[destTableauIdx] = " + tableauSizes[destTableauIdx]);

                if (game.moveTableauToTableau(originCardIdx, originTableauIdx, destTableauIdx)) {
                    refreshTableau(originTableauIdx, tableauSizes[originTableauIdx] - 1);
                    int oldSize = tableauSizes[destTableauIdx];
                    tableauSizes[destTableauIdx] = oldSize + originCardIdx + 1;
                    Card[] newCards = game.peekTableauVisibleCards(destTableauIdx);

                    for (int i = 0; i < originCardIdx; i++){
                        System.out.println("Esto no debería estar");
                        tableauGrid.add(CardView.getCard(newCards[i]), destTableauIdx, oldSize + i);
                    }
                    ImageView view = CardView.getCard(newCards[originCardIdx + 1]);
                    tableauGrid.add(view, destTableauIdx, oldSize + originCardIdx);
                    view.setOnMouseClicked(event -> handleTableauClick(new CardWrapper(newCards[originCardIdx], view, tableauGrid)));
                    selectedCard.remove();
                    System.out.println("new tableauSizes[originTableauIdx] = " + tableauSizes[originTableauIdx]);
                    System.out.println("new tableauSizes[destTableauIdx] = " + tableauSizes[destTableauIdx]);

                }
                selectedCard.view.setOpacity(1);
                selectedCard = null;
            }
            //            ; &&
            //                    game.moveTableauToTableau()
//            game.moveStockToTableau();
//            game.moveStockToTableau();
        } else {
            // Una carta
            selectedCard = tableauCard;
            selectedCard.view.setOpacity(0.5);
        }
    }

    public void handleStockClick(){
            if (selectedCard != null){
                selectedCard.view.setOpacity(1);
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

            cardV.setOnMouseClicked(event -> handleWasteClick(new CardWrapper(c, cardV, stockBox)));
        }

        private void handleWasteClick(CardWrapper wasteCard) {
            if (selectedCard == null){
                selectedCard = wasteCard;
                selectedCard.view.setOpacity(0.5);
            } else {
                selectedCard.view.setOpacity(1);
                selectedCard = null;
            }
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
                    selectedCard.view.setOpacity(1);
                    selectedCard = null;
                    return;
                }
                if (selectedCard.container == tableauGrid) {
                    int tableauIdx = GridPane.getColumnIndex(selectedCard.view);
                    int rowIdx = GridPane.getRowIndex(selectedCard.view);
                    if (game.moveTableauToFoundation(tableauIdx,foundationIdx)) {
                        foundationBox.getChildren().remove(foundationIdx);
                        foundationBox.getChildren().add(foundationIdx, selectedCard.view);
                        selectedCard.view.setOnMouseClicked(event -> handleFoundationClick(foundationIdx));
                        selectedCard.remove();
                        refreshTableau(tableauIdx, rowIdx);
                    }
                    selectedCard.view.setOpacity(1);
                    selectedCard = null;
                    }
                }
            }

    private void refreshTableau(int tableauIdx,int rowIdx) {
            Card topCard = game.peekTableauTopCard(tableauIdx);
            if(topCard == null){return;}
            ImageView view = CardView.getCard(topCard);
            tableauGrid.add(view, tableauIdx, rowIdx -1);
            tableauSizes[tableauIdx] = rowIdx;
            view.setOnMouseClicked(event -> handleTableauClick(new CardWrapper(topCard, view, tableauGrid)));;
    }
    private void refreshWaste(){
        Card card = game.peekStockTopCard();
        if (card == null){return;}
        ImageView cardV = CardView.getCard(card);
        stockBox.getChildren().add(cardV);
        cardV.setOnMouseClicked(event -> handleWasteClick(new CardWrapper(card, cardV, stockBox)));
    }
}