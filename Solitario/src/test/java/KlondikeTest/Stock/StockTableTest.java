package KlondikeTest.Stock;

import Klondike.Card.Card;
import Klondike.Card.Color;
import Klondike.Card.Suit;
import Klondike.Stock.StockTable;
import org.junit.Test;

import static org.junit.Assert.*;

public class StockTableTest {

    @Test
    public void oneCard() {
        // Arrange
        Card c = new Card(Suit.CLUBS, 12, Color.BLACK);
        Card[] cardArray = {c};
        StockTable t = new StockTable(cardArray);

        // Act
        boolean empty1 = t.isEmpty();
        Card getCard1 = t.getCard();
        Card peek1 = t.peek();
        Card nextCard1 = t.nextCard();
        Card peek2 = t.peek();
        Card nextCard2 = t.nextCard();
        Card nextCard3 = t.nextCard();
        Card nextCard4 = t.nextCard();
        Card nextCard5 = t.nextCard();
        Card nextCard6 = t.nextCard();
        Card nextCard7 = t.nextCard();
        Card getCard2 = t.getCard();
        boolean empty2 = t.isEmpty();

        // Assert
        assertFalse(empty1);
        assertTrue(empty2);
        assertNull(getCard1);
        assertEquals(c, getCard2);
        assertNull(peek1);
        assertEquals(c, peek2);
        assertEquals(c, nextCard1);
        assertNull(nextCard2);
        assertEquals(c, nextCard3);
        assertNull(nextCard4);
        assertEquals(c, nextCard5);
        assertNull(nextCard6);
        assertNull(nextCard7);
    }
}