package ui.Spider;

import Card.Card;
import Spider.Spider;
import javafx.scene.Node;
import ui.CardView;
import ui.CardWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;

public class SpiderController {
    @FXML
    private HBox stockBox;

    @FXML
    private HBox foundationBox;

    @FXML
    private GridPane tableauGrid;

    @FXML
    private Button restart;

    private Spider spider = new Spider();
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
        if (spider.getStockSize() == 0)
            return;

        if (spider.getStockSize() == 10) {
            stockBox.getChildren().remove(0);
            Rectangle emptySpace = CardView.getEmptyPlace();
            stockBox.getChildren().add(emptySpace);
        }

        spider.drawStockCards();
        System.out.println("Stock Draw\n");
        Card[] cards = spider.peekTableauTopCards();

        for (int i = 0; i < 10; i++) {
            Card c = cards[i];
            ImageView view = CardView.getCard(c);
            tableauGrid.add(view, i, spider.getTableauSize(i) - 1);
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
                int numCardsToMove = spider.getTableauSize(originCol) - originIndex;

                if (spider.moveTableauToTableau(originCol, originIndex - spider.getTableauHiddenCardsSize(originCol), destCol)) {
                    System.out.println("Cards Moved");

                    for (int i = 0; i < numCardsToMove; i++) {
                        ImageView movedCardView = getCardViewFromGridPane(originCol, originIndex + i);
                        tableauGrid.getChildren().remove(movedCardView);
                        tableauGrid.add(movedCardView, destCol, destIndex + i + 1);
                    }

                } else {
                    System.out.println("Invalid move\n");
                }

            } else {
                if (spider.moveTopCardToTableau(originCol, destCol)) {
                    System.out.println("Top Card Moved");
                    ImageView movedCardView = selectedCard.view;
                    tableauGrid.getChildren().remove(movedCardView);
                    tableauGrid.add(movedCardView, destCol, destIndex + 1);

                } else {
                    System.out.println("Invalid move\n");
                }
            }

            System.out.println(Arrays.toString(spider.peekTableauTopCards()) + "\n");
            selectedCard.view.setOpacity(1);
            selectedCard = null;
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

    private void handleRestart() {
        System.out.println("New Game!\n");
        spider = new Spider();
        stockBox.getChildren().clear();
        foundationBox.getChildren().clear();
        tableauGrid.getChildren().clear();
        initializeStock();
        initializeFoundation();
        initializeTableau();
    }
}