package Spider.Foundation;

import Card.Card;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FoundationColumn {

    private final List<Card> cards;

    public FoundationColumn() {
        cards = new LinkedList<>();
    }

    public void assignCards(Card[] cardsArray) {
        cards.addAll(Arrays.asList(cardsArray));
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}