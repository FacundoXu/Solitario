package Spider.Tableau;

import Card.*;
import Spider.Stock.Stock;
import org.junit.Test;

import static org.junit.Assert.*;

public class TableauTableTest {
    Card[] deck1 = Deck.createDeck(false);
    Card[] deck2 = Deck.createDeck(false);

    public Card[] concatenateDecks(Card[] deck1, Card[] deck2) {
        int length1 = deck1.length;
        int length2 = deck2.length;
        Card[] combinedDeck = new Card[length1 + length2];
        System.arraycopy(deck1, 0, combinedDeck, 0, length1);
        System.arraycopy(deck2, 0, combinedDeck, length1, length2);
        return combinedDeck;
    }

    Card[] combinedDeck = concatenateDecks(deck1, deck2);

    @Test
    public void moveCards() {

        // Arrange
        Stock stock = new Stock(combinedDeck);
        TableauTable tableauTable = new TableauTable(stock);

        // Act
        Card pick1 = tableauTable.pickUp(0);
        Card pickCard1 = new Card(Suit.SPADES, 8, Color.BLACK);
        Card peek1 = tableauTable.peek(0);
        Card peekCard1 = new Card(Suit.SPADES, 9, Color.BLACK);

        boolean insert2 = tableauTable.insert(1, pickCard1);
        boolean insert3 = tableauTable.insert(7, pickCard1);
        Card peek2 = tableauTable.peek(7);

        Card pick2 = tableauTable.pickUp(7);
        boolean insert4 = tableauTable.insert(0, pick2);
        Card peek3 = tableauTable.peek(0);

        Card randomCard = new Card(Suit.DIAMONDS, 10, Color.RED);
        boolean insert5 = tableauTable.insert(4, randomCard);
        boolean move1 = tableauTable.move(0, 0, 4);
        Card peek4 = tableauTable.peek(4);
        tableauTable.pickUp(4);
        Card peek5 = tableauTable.peek(4);
        Card peek6 = tableauTable.peek(0);
        Card peekCard2 = new Card(Suit.SPADES, 10, Color.BLACK);

        // Assert
        assertEquals(pickCard1, pick1);
        assertEquals(peekCard1, peek1);

        assertFalse(insert2);
        assertTrue(insert3);
        assertEquals(pickCard1, peek2);

        assertEquals(pickCard1, pick2);
        assertTrue(insert4);
        assertEquals(pickCard1, peek3);

        assertTrue(insert5);
        assertTrue(move1);
        assertEquals(pick1, peek4);
        assertEquals(peekCard1, peek5);
        assertEquals(peekCard2, peek6);
    }
}