package Spider.Tableau;

import Card.Card;
import Spider.Stock.Stock;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class TableauTable implements Serializable {

    // Constants
    public static final int MAX_NUM_TABLEAUS = 10;

    // Attributes
    private final TableauStack[] tableaus = new TableauStack[MAX_NUM_TABLEAUS];

    // Constructor
    public TableauTable(Stock stock) {
        for (int i = 0; i < 4; i++) {
            List<Card> leftCards = new LinkedList<>();

            for (int j = 0; j < 6; j++) {
                leftCards.add(stock.drawCard());
            }
            tableaus[i] = new TableauStack(leftCards.toArray(new Card[0]));
        }

        for (int i = 4; i < 10; i++) {
            List<Card> rightCards = new LinkedList<>();

            for (int j = 0; j < 5; j++) {
                rightCards.add(stock.drawCard());
            }
            tableaus[i] = new TableauStack(rightCards.toArray(new Card[0]));
        }
    }

    // Methods
    public Card pickUp(int tableau) {
        return tableaus[tableau].pop();
    }

    public Card peek(int tableau) {
        return tableaus[tableau].peek();
    }

    public Card[] peek() {
        Card[] topCards = new Card[MAX_NUM_TABLEAUS];

        for (int i = 0; i < MAX_NUM_TABLEAUS; i++) {
            topCards[i] = this.peek(i);
        }
        return topCards;
    }

    public boolean insert(int tableau, Card card) {
        return tableaus[tableau].push(card);
    }

    public void returnCard(int tableau, Card card) {
        tableaus[tableau].returnCard(card);
    }

    public boolean move(int origin, int i, int target) {
        Card[] cardsArray = tableaus[origin].popArray(i);

        if (cardsArray == null)
            return false;

        if (!tableaus[target].pushArray(cardsArray)) {
            tableaus[origin].returnArray(cardsArray);
            return false;
        }
        return true;
    }

    public Card[] verifyTable() {
        for (int i = 0; i < MAX_NUM_TABLEAUS; i++) {
            Card[] cards = tableaus[i].getWinnerCards();

            if (cards != null)
                return cards;
        }
        return null;
    }

    public void assignStockCards(Stock stock) {
        for (TableauStack tableauStack : tableaus) {
            tableauStack.pushStockCard(stock.drawCard());
        }
    }
}