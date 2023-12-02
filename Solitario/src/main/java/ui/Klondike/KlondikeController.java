package ui.Klondike;

import Klondike.Klondike;
import Spider.Spider;
import javafx.scene.control.Button;
import ui.CardView;
import ui.CardWrapper;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import Card.*;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KlondikeController {

        @FXML
        private GridPane tableauGrid;

        @FXML
        private HBox foundationBox;
        @FXML
        private Button restart;

        @FXML
        private HBox stockBox;
        private final ArrayList<ImageView>[] tableauViews = new ArrayList[7];
        private Klondike game = new Klondike();

        CardWrapper selectedCard;

        @FXML
        private void initialize() {
            initializeStock();
            initializeFoundation();
            initializeTableau();
            restart.setOnAction(event -> handleRestart());
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
            for (int i = 0; i < 7; i++) {
                tableauViews[i] = new ArrayList<>();
            }
            for (int i = 0; i < 7; i++){
                Card c = game.peekTableauTopCard(i);
                ImageView view = CardView.getCard(c);
                tableauGrid.add(view, i, i);
                tableauViews[i].add(view);
                view.setOnMouseClicked(event -> handleTableauClick(new CardWrapper(c, view, tableauGrid)));
                for (int j = i + 1; j < 7; j++){
                    ImageView cardBack = CardView.getCardBack();
                    tableauGrid.add(cardBack, j, i);
                    tableauViews[j].add(cardBack);
                }
            }
        }

    private void handleTableauClick(CardWrapper tableauCard) {
        if (selectedCard != null) {
            if(selectedCard.container == tableauGrid){
                int originTableauIdx = GridPane.getColumnIndex(selectedCard.view);
//                System.out.println(originTableauIdx);
                int originCardIdx = tableauViews[originTableauIdx].size() - GridPane.getRowIndex(selectedCard.view) - 1;
//                System.out.println(originCardIdx);
                int destTableauIdx = GridPane.getColumnIndex(tableauCard.view);
//                System.out.println(destTableauIdx);
//                System.out.println(tableauViews[originCardIdx].size());
//                System.out.println(tableauViews[destTableauIdx].size());

                if (game.moveTableauToTableau(originCardIdx, originTableauIdx, destTableauIdx)) {
                    refreshTableau(originTableauIdx, GridPane.getRowIndex(selectedCard.view), destTableauIdx);
                }
            } else if (selectedCard.container == stockBox){
                int destTableauIdx = GridPane.getColumnIndex(tableauCard.view);
                if (game.moveStockToTableau(destTableauIdx)) {

//                    ImageView oldTop = tableauViews[destTableauIdx].get(tableauViews[destTableauIdx].size() - 1);
//                    tableauGrid.getChildren().remove(oldTop);
//                    tableauGrid.add(oldTop, destTableauIdx, tableauViews[destTableauIdx].size() - 1);

                    tableauViews[destTableauIdx].add(selectedCard.view);
                    tableauGrid.add(selectedCard.view, destTableauIdx, tableauViews[destTableauIdx].size() - 1);
                    Card newCard = selectedCard.card;
                    ImageView newView = selectedCard.view;
                    selectedCard.view.setOnMouseClicked(event -> handleTableauClick(new CardWrapper(newCard, newView, tableauGrid)));
                    selectedCard.remove();
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
                } else if (selectedCard.container == tableauGrid) {
                    int tableauIdx = GridPane.getColumnIndex(selectedCard.view);
                    int rowIdx = GridPane.getRowIndex(selectedCard.view);
                    if (game.moveTableauToFoundation(tableauIdx,foundationIdx)) {
                        if (rowIdx != 0) {
                            foundationBox.getChildren().remove(foundationIdx);
                            foundationBox.getChildren().add(foundationIdx, selectedCard.view);
                            selectedCard.view.setOnMouseClicked(event -> handleFoundationClick(foundationIdx));

                            selectedCard.remove();
                            tableauGrid.getChildren().remove(tableauViews[tableauIdx].get(tableauViews[tableauIdx].size() - 1));
                            tableauGrid.getChildren().remove(tableauViews[tableauIdx].get(tableauViews[tableauIdx].size() - 1));
                            Card topCard = game.peekTableauTopCard(tableauIdx);
                            ImageView view = CardView.getCard(topCard);
                            tableauViews[tableauIdx].remove(tableauViews[tableauIdx].size() - 1);
                            tableauViews[tableauIdx].remove(tableauViews[tableauIdx].size() - 1);
//                            System.out.println(tableauViews[tableauIdx].size());
                            tableauViews[tableauIdx].add(view);
                            System.out.println(tableauViews[tableauIdx].size());
                            tableauGrid.add(view, tableauIdx, rowIdx - 1);
                            view.setOnMouseClicked(event -> handleTableauClick(new CardWrapper(topCard, view, tableauGrid)));
                        }else {
                            foundationBox.getChildren().remove(foundationIdx);
                            foundationBox.getChildren().add(foundationIdx, selectedCard.view);
                            selectedCard.view.setOnMouseClicked(event -> handleFoundationClick(foundationIdx));

                            tableauGrid.getChildren().remove(tableauViews[tableauIdx].get(rowIdx));
                            tableauViews[tableauIdx].subList(rowIdx, tableauViews[tableauIdx].size()).clear();
                            Rectangle rectangle = CardView.getEmptyPlace();
                            tableauGrid.add(rectangle, tableauIdx, rowIdx);
                            rectangle.setOnMouseClicked(event -> handleEmptyTableauClick(tableauIdx));
                        }
                    }
                }
            selectedCard.view.setOpacity(1);
            selectedCard = null;
            }
        }
    // Tablueau to Found dosnt select T card
    // El 2do tableau no borra
    // Cuando muevo el último del stock
    public void handleEmptyTableauClick(int destTableauIdx) {
        if (selectedCard != null) {
            if (selectedCard.container == stockBox) {
                if (game.moveStockToTableau(destTableauIdx)) {
                    tableauViews[destTableauIdx].add(selectedCard.view);
                    tableauGrid.add(selectedCard.view, destTableauIdx, tableauViews[destTableauIdx].size() - 1);
                    Card newCard = selectedCard.card;
                    ImageView newView = selectedCard.view;
                    selectedCard.view.setOnMouseClicked(event -> handleTableauClick(new CardWrapper(newCard, newView, tableauGrid)));
                    selectedCard.remove();
                    refreshWaste();
                }
            } else if (selectedCard.container == tableauGrid) {
                    int originTableauIdx = GridPane.getColumnIndex(selectedCard.view);
//                System.out.println(originTableauIdx);
                    int originCardIdx = tableauViews[originTableauIdx].size() - GridPane.getRowIndex(selectedCard.view) - 1;
//                System.out.println(originCardIdx);
//                System.out.println(destTableauIdx);
//                System.out.println(tableauViews[originCardIdx].size());
//                System.out.println(tableauViews[destTableauIdx].size());

                    if (game.moveTableauToTableau(originCardIdx, originTableauIdx, destTableauIdx)) {
                        refreshTableau(originTableauIdx, GridPane.getRowIndex(selectedCard.view), destTableauIdx);
                    }
                }
            selectedCard.view.setOpacity(1);
            selectedCard = null;
        }
    }
    private void refreshTableau(int tableauIdx, int rowIdx, int destTableauIdx) {
        int oldSize = tableauViews[destTableauIdx].size();
//        System.out.println(oldSize);
        tableauViews[destTableauIdx].addAll(tableauViews[tableauIdx].subList(rowIdx, tableauViews[tableauIdx].size()));
//        System.out.println(tableauViews[destTableauIdx].size());
        if (rowIdx != 0) {
            tableauGrid.getChildren().remove(tableauViews[tableauIdx].get(rowIdx - 1));
            tableauViews[tableauIdx].subList(rowIdx - 1, tableauViews[tableauIdx].size()).clear();
            for (int i = oldSize; i < tableauViews[destTableauIdx].size(); i++) {
//                System.out.println(i);
                tableauGrid.getChildren().remove(tableauViews[destTableauIdx].get(i));
                tableauGrid.add(tableauViews[destTableauIdx].get(i), destTableauIdx, i);
            }
            Card topCard = game.peekTableauTopCard(tableauIdx);
            if (topCard == null) {
                return;
            }
            ImageView view = CardView.getCard(topCard);
            tableauGrid.add(view, tableauIdx, rowIdx - 1);
            tableauViews[tableauIdx].add(view);
            view.setOnMouseClicked(event -> handleTableauClick(new CardWrapper(topCard, view, tableauGrid)));
        } else {
            tableauGrid.getChildren().remove(tableauViews[tableauIdx].get(rowIdx));
            tableauViews[tableauIdx].subList(rowIdx, tableauViews[tableauIdx].size()).clear();
            for (int i = oldSize; i < tableauViews[destTableauIdx].size(); i++) {
//                System.out.println(game.peekTableauTopCard(destTableauIdx).rank());
                tableauGrid.getChildren().remove(tableauViews[destTableauIdx].get(i));
                tableauGrid.add(tableauViews[destTableauIdx].get(i), destTableauIdx, i);
            }
            Rectangle rectangle = CardView.getEmptyPlace();
            tableauGrid.add(rectangle, tableauIdx, rowIdx);
            rectangle.setOnMouseClicked(event -> handleEmptyTableauClick(tableauIdx));
        }
        System.out.println(tableauViews[tableauIdx].size());
        System.out.println(tableauViews[destTableauIdx].size());
    }
    private void refreshWaste(){
        Card card = game.peekStockTopCard();
        if (card == null){return;}
        ImageView cardV = CardView.getCard(card);
        stockBox.getChildren().add(cardV);
        cardV.setOnMouseClicked(event -> handleWasteClick(new CardWrapper(card, cardV, stockBox)));
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

}