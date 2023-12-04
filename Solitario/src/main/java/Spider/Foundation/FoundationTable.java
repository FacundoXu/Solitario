package Spider.Foundation;

import Card.Card;

import java.io.Serializable;

public class FoundationTable implements Serializable {

    // Constants
    private static final int MAX_NUM_FOUNDATIONS = 8;
    private static final int NOT_FOUND = -1;

    // Attributes
    private final FoundationColumn[] foundations = new FoundationColumn[MAX_NUM_FOUNDATIONS];

    // Constructor
    public FoundationTable() {
        for (int i = 0; i < MAX_NUM_FOUNDATIONS; i++) {
            foundations[i] = new FoundationColumn();
        }
    }

    // Methods
    public boolean assignCards(Card[] cards) {
        if (cards == null)
            return false;

        int i = this.getAvailableSpot();

        if (i != NOT_FOUND) {
            foundations[i].assignCards(cards);
            return true;
        }
        return false;
    }

    private int getAvailableSpot() {
        for (int i = 0; i < foundations.length; i++) {
            if (foundations[i].isEmpty())
                return i;
        }
        return NOT_FOUND;
    }

    public boolean gameWon() {
        for (FoundationColumn foundation : foundations) {
            if (foundation.isEmpty())
                return false;
        }
        return true;
    }

    public boolean isEmpty(int i) {
        return foundations[i].isEmpty();
    }

    public Card peekFoundation(int i) {
        return foundations[i].peek();
    }
}