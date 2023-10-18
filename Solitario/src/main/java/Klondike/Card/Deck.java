package Klondike.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

    static public Card[] createDeck() {
        return createDeck(true);
    }

    static public Card[] createDeck(boolean shuffle) {

        ArrayList<Card> deck = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            for (int i = 0; i < 13; i++) {
                Color color = (suit == Suit.HEARTS || suit == Suit.DIAMONDS) ? Color.RED : Color.BLACK;
                deck.add(new Card(suit, i + 1, color));
            }
        }

        if (shuffle)
            Collections.shuffle(deck, new Random());

        return deck.toArray(new Card[0]);
    }
}