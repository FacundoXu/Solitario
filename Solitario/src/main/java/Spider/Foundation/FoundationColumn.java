package Spider.Foundation;

import Card.Card;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FoundationColumn implements Serializable {

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