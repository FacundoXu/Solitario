package Spider.Foundation;

import Card.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class FoundationColumnTest {

    @Test
    public void verifying() {

        // Arrange
        Card c1 = new Card(Suit.DIAMONDS, 13, Color.RED);
        Card c2 = new Card(Suit.DIAMONDS, 12, Color.RED);
        Card c3 = new Card(Suit.DIAMONDS, 11, Color.RED);
        Card c4 = new Card(Suit.DIAMONDS, 10, Color.RED);
        Card c5 = new Card(Suit.DIAMONDS, 9, Color.RED);
        Card c6 = new Card(Suit.DIAMONDS, 8, Color.RED);
        Card c7 = new Card(Suit.DIAMONDS, 7, Color.RED);
        Card c8 = new Card(Suit.DIAMONDS, 6, Color.RED);
        Card c9 = new Card(Suit.DIAMONDS, 5, Color.RED);
        Card c10 = new Card(Suit.DIAMONDS, 4, Color.RED);
        Card c11 = new Card(Suit.DIAMONDS, 3, Color.RED);
        Card c12 = new Card(Suit.DIAMONDS, 2, Color.RED);
        Card c13 = new Card(Suit.DIAMONDS, 1, Color.RED);
        Card[] cards = {c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13};
        FoundationColumn foundation1 = new FoundationColumn();
        FoundationColumn foundation2 = new FoundationColumn();
        foundation1.assignCards(cards);

        // Act
        boolean verify1 = foundation1.isEmpty();
        boolean verify2 = foundation2.isEmpty();

        // Assert
        assertFalse(verify1);
        assertTrue(verify2);
    }
}