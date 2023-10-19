package Spider.Foundation;

import Card.Card;

public class FoundationTable {

    private final Foundation[] foundations = new Foundation[4];

    public FoundationTable() {
        for (int i = 0; i < 8; i++) {
            foundations[i] = new Foundation();
        }
    }

    public void assignCards(int i, Card[] cardsArray) {
        foundations[i].assignCards(cardsArray);
    }
}