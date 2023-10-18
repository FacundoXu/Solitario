package KlondikeTest.Card;

import Klondike.Card.Card;
import Klondike.Card.Color;
import Klondike.Card.Deck;
import Klondike.Card.Suit;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class DeckTest {
    Card[] sortedDeck = {
            new Card(Suit.HEARTS, 1, Color.RED),
            new Card(Suit.HEARTS, 2, Color.RED),
            new Card(Suit.HEARTS, 3, Color.RED),
            new Card(Suit.HEARTS, 4, Color.RED),
            new Card(Suit.HEARTS, 5, Color.RED),
            new Card(Suit.HEARTS, 6, Color.RED),
            new Card(Suit.HEARTS, 7, Color.RED),
            new Card(Suit.HEARTS, 8, Color.RED),
            new Card(Suit.HEARTS, 9, Color.RED),
            new Card(Suit.HEARTS, 10, Color.RED),
            new Card(Suit.HEARTS, 11, Color.RED),
            new Card(Suit.HEARTS, 12, Color.RED),
            new Card(Suit.HEARTS, 13, Color.RED),
            new Card(Suit.DIAMONDS, 1, Color.RED),
            new Card(Suit.DIAMONDS, 2, Color.RED),
            new Card(Suit.DIAMONDS, 3, Color.RED),
            new Card(Suit.DIAMONDS, 4, Color.RED),
            new Card(Suit.DIAMONDS, 5, Color.RED),
            new Card(Suit.DIAMONDS, 6, Color.RED),
            new Card(Suit.DIAMONDS, 7, Color.RED),
            new Card(Suit.DIAMONDS, 8, Color.RED),
            new Card(Suit.DIAMONDS, 9, Color.RED),
            new Card(Suit.DIAMONDS, 10, Color.RED),
            new Card(Suit.DIAMONDS, 11, Color.RED),
            new Card(Suit.DIAMONDS, 12, Color.RED),
            new Card(Suit.DIAMONDS, 13, Color.RED),
            new Card(Suit.CLUBS, 1, Color.BLACK),
            new Card(Suit.CLUBS, 2, Color.BLACK),
            new Card(Suit.CLUBS, 3, Color.BLACK),
            new Card(Suit.CLUBS, 4, Color.BLACK),
            new Card(Suit.CLUBS, 5, Color.BLACK),
            new Card(Suit.CLUBS, 6, Color.BLACK),
            new Card(Suit.CLUBS, 7, Color.BLACK),
            new Card(Suit.CLUBS, 8, Color.BLACK),
            new Card(Suit.CLUBS, 9, Color.BLACK),
            new Card(Suit.CLUBS, 10, Color.BLACK),
            new Card(Suit.CLUBS, 11, Color.BLACK),
            new Card(Suit.CLUBS, 12, Color.BLACK),
            new Card(Suit.CLUBS, 13, Color.BLACK),
            new Card(Suit.SPADES, 1, Color.BLACK),
            new Card(Suit.SPADES, 2, Color.BLACK),
            new Card(Suit.SPADES, 3, Color.BLACK),
            new Card(Suit.SPADES, 4, Color.BLACK),
            new Card(Suit.SPADES, 5, Color.BLACK),
            new Card(Suit.SPADES, 6, Color.BLACK),
            new Card(Suit.SPADES, 7, Color.BLACK),
            new Card(Suit.SPADES, 8, Color.BLACK),
            new Card(Suit.SPADES, 9, Color.BLACK),
            new Card(Suit.SPADES, 10, Color.BLACK),
            new Card(Suit.SPADES, 11, Color.BLACK),
            new Card(Suit.SPADES, 12, Color.BLACK),
            new Card(Suit.SPADES, 13, Color.BLACK),
    };

    @Test
    public void createDeck() {
        // Arrange
        Card[] d = Deck.createDeck(false);

        //Assert
        assertArrayEquals(sortedDeck, d);
    }

    @Test
    public void createShuffledDeck() {
        //Arrange
        Card[] d = Deck.createDeck();

        //Assert
        assertNotEquals(Arrays.toString(sortedDeck), Arrays.toString(d));
    }
}