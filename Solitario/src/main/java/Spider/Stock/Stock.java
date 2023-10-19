package Spider.Stock;

import Spider.Card.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stock {

    private final List<Card> cards;
    private int size;

    public Stock(Card[] cardsArray) {
        cards = new ArrayList<>(Arrays.asList(cardsArray));
        size = cards.size();
    }

    public Card drawCard() {
        if (cards.isEmpty())
            return null;
        size--;
        return cards.remove(size);
    }
}
