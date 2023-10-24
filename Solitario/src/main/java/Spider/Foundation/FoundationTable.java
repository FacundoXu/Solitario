package Spider.Foundation;

import Card.Card;

public class FoundationTable {

    private final FoundationColumn[] foundations = new FoundationColumn[8];

    public FoundationTable() {
        for (int i = 0; i < 8; i++) {
            foundations[i] = new FoundationColumn();
        }
    }

    public boolean assignCards(Card[] cards) {
        if (cards == null)
            return false;

        int i = this.getAvailableSpot();

        if (i != -1) {
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
        return -1;
    }

    public boolean gameWon() {
        for (FoundationColumn foundation : foundations) {
            if (foundation.isEmpty())
                return false;
        }
        return true;
    }
}