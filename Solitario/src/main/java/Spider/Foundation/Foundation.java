package Spider.Foundation;

import Card.Card;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Foundation {

    private final List<Card> cards;

    public Foundation() {
        cards = new LinkedList<>();
    }

    public void assignCards(Card[] cardsArray) {
        cards.addAll(Arrays.asList(cardsArray));
    }

    public boolean verify() {
        return !cards.isEmpty();
    }
}