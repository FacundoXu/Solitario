package Klondike.Stock;

import Card.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class StockTableTest {

    @Test
    public void oneCard() {

        // Arrange
        Card card = new Card(Suit.CLUBS, 12, Color.BLACK);
        Card[] cardArray = {card};
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
        assertEquals(card, getCard2);
        assertNull(peek1);
        assertEquals(card, peek2);
        assertEquals(card, nextCard1);
        assertNull(nextCard2);
        assertEquals(card, nextCard3);
        assertNull(nextCard4);
        assertEquals(card, nextCard5);
        assertNull(nextCard6);
        assertNull(nextCard7);
    }
}